/*---------------------------------------------------------------------------*
 * MainActivity.java                                                         *
 *                                                                           *
 * User control panel with buttons to adjust settings, show a sample survey, *
 * get the phone's id, and exit.                                             *
 *---------------------------------------------------------------------------*/
//TODO add an indication of what percentage of surveys have been completed
package org.peoples.android;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.peoples.android.database.TakenDBHandler;
import org.peoples.android.survey.SurveyService;

/**
 * The Activity for the administration panel of the PEOPLES application.
 * 
 * @author Henry Liu
 * @author Austin Walker
 */
public class MainActivity extends Activity
{
	//logging tag
    private static final String TAG = "MainActivity";

    //little hack here to show toast (see the call button listener)
    private Activity getThis()
    {
    	return this;
    }
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (Config.D) Log.d(TAG, "starting mainActivity");
        
        //setting the layout of the activity
        Display display = ((WindowManager)
        		getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        //check what orientation the phone is in
        //getOrientation() is depreciated as of API 8, but we're targeting
        //API 7, so we have to use it
        if (display.getOrientation() == Configuration.ORIENTATION_PORTRAIT)
        { //yeah this makes no sense, but it works...
        	setContentView(R.layout.main_activity_horiz);
        }
        else
        {
        	setContentView(R.layout.main_activity_vert);
        }
        
        //add the survey progress bar
        //TODO change to using some type of vertical progress bar
        TakenDBHandler tdbh = new TakenDBHandler(this);
        tdbh.openRead();
        int p = tdbh.getCompletionRate();
        tdbh.close();
        TextView progress = new TextView(this);
//        VerticalProgressBar progress = new VerticalProgressBar(this);
//        progress.setMax(100);
        progress.setText("" + p);
        LinearLayout PBLayout =
        	(LinearLayout) findViewById(R.id.main_progressBarLayout);
        PBLayout.addView(progress);
        
        //go to settings button
        Button settings = (Button) findViewById(R.id.main_settingsButton);
        settings.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent settingsIntent = new Intent(view.getContext(),
                		SettingsActivity.class);
                startActivity(settingsIntent);
            }
        });
        
        //sample survey button
        Button sample = (Button) findViewById(R.id.main_sampleButton);
        sample.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent surveyIntent = new Intent(view.getContext(),
                		SurveyService.class);
                surveyIntent.setAction(SurveyService.ACTION_SURVEY_READY);
                startService(surveyIntent);
            }
        });
        
        //call survey admin button
        Button call = (Button) findViewById(R.id.main_callButton);
        call.setText(call.getText() + Config.getSetting(this,
        		Config.ADMIN_NAME, Config.ADMIN_NAME_DEFAULT));
        call.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
            	Intent callIntent = new Intent(Intent.ACTION_CALL);
            	callIntent.setData(Uri.parse("tel:"
            			+ Config.getSetting(getThis(), Config.ADMIN_PHONE_NUMBER,
            					Config.ADMIN_PHONE_NUMBER_DEFAULT)));
            	try
            	{
            		startActivity(callIntent);
            	}
            	catch (ActivityNotFoundException e)
            	{
            		Toast.makeText(getThis(),
            				"Call failed!", Toast.LENGTH_SHORT);
            	}
            }
        });
        
        //exit button
        Button quit = (Button) findViewById(R.id.main_exitButton);
        quit.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
            	finish();
            }
        });
    }
}