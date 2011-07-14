/*---------------------------------------------------------------------------*
 * Config.java                                                               *
 *                                                                           *
 * Holds configuration things (such as whether or not debugging is enabled.  *
 *---------------------------------------------------------------------------*/
package org.peoples.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaRecorder;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Holds static information about the current configuration, such as whether or
 * not debugging is enabled, etc.
 *
 * @author Austin Walker
 * @author Tony Xaio
 * @author Henry Liu
 */
public class Config
{
	//name of the file to write the config to
	private static final String CONFIG_FILE = "peoples.conf";
	
	//manual settings; can't be changed programatically
	/** Is debugging enabled? */
	public static final boolean D = true;

	/** Format of survey times. */
	public static final String TIME_FORMAT = "HHmm";

	/** Format of survey days. */
	public static final String DAY_FORMAT = "EE";
	
	
	//names/default values for configurable settings
	/** Is https enabled? */
	public static final String HTTPS = "https";
	public static final boolean HTTPS_DEFAULT = false;
	
	/** Frequency with which to run the survey scheduler (in minutes) */
	public static final String SCHEDULER_INTERVAL = "scheduler_interval";
	public static final int SCHEDULER_INTERVAL_DEFAULT = 60 * 24;

	/** Frequency with which to push data (in minutes) */
	public static final String PUSH_INTERVAL = "push_interval";
	public static final int PUSH_INTERVAL_DEFAULT = 60 * 24;

	/** Frequency with which to pull data (in minutes) */
	public static final String PULL_INTERVAL = "push_interval";
	public static final int PULL_INTERVAL_DEFAULT = 60 * 24;

	/** Server to connect to */
	public static final String SERVER = "server";
	//TODO remove this somehow
	public static final String SERVER_DEFAULT = "50.19.254.168";

	/** Salting value for hashing phone number */
	public static final String SALT = "salt";

	/** Approximate time between location updates, in minutes */
	public static String LOCATION_INTERVAL = "location_interval";
	public static int LOCATION_INTERVAL_DEFAULT = 15;

    /** Time to delay a survey for in minutes if the user chooses to. */
	public static final String SURVEY_DELAY = "survey_delay";
    public static final long SURVEY_DELAY_DEFAULT = 15;

    /** Phone number of the study administrator. */
    public static final String ADMIN_PHONE_NUMBER = "admin_phone_number";
	public static final String ADMIN_PHONE_NUMBER_DEFAULT =
		"7652996509"; //Austin's cell

	/** Name of the study administrator. */
	public static final String ADMIN_NAME = "admin_name";
	public static final String ADMIN_NAME_DEFAULT = "Admin";

	/** Should we allow the entry of blank free response answers? */
	public static final String ALLOW_BLANK_FREE_RESPONSE =
		"allow_blank_free_response";
	public static final boolean ALLOW_BLANK_FREE_RESPONSE_DEFAULT = true;

	/** Should we allow answering multiple choice with no answers? */
	public static final String ALLOW_NO_CHOICES = "allow_no_choices";
	public static final boolean ALLOW_NO_CHOICES_DEFAULT = false;

	/** Should the name/title of a survey be shown? */
	public static final String SHOW_SURVEY_NAME = "show_survey_name";
	public static final boolean SHOW_SURVEY_NAME_DEFAULT = true;
	
	/** What format should voice be recorded int */
	public static final String VOICE_FORMAT = "voice_format";
	public static final int VOICE_FORMAT_DEFAULT =
		MediaRecorder.OutputFormat.MPEG_4;
	
	/**
	 * Should full resolution photos (that are possibly/likely quite large) be
	 * sent to the server when the user takes a photo with their survey? 
	 * 
	 * Note that due to an old bug on a small number of Android devices, it is
	 * possible that that photos will not be able to be sent if this is turned
	 * on.  Make sure to test on the device in use.
	 */
	public static final String USE_FULL_RES_PHOTOS = "use_full_res_photos";
	public static final boolean USE_FULL_RES_PHOTOS_DEFAULT = false;
	
	/** Does the user have surveys enabled? */
	public static final String SURVEYS_LOCAL = "surveys_local";
	
	/** Does the user have location tracking enabled? */
	public static final String TRACKING_LOCAL = "tracking_local";
	
	/** Does the user have call/text logging enabled? */
	public static final String CALL_LOG_LOCAL = "call_log_local";
	
	/** Does the server have surveys enabled? */
	public static final String SURVEYS_SERVER = "survey";
	public static final boolean SURVEYS_SERVER_DEFAULT = false;
	
	/** Does the server have location tracking enabled? */
	public static final String TRACKING_SERVER = "location";
	public static final boolean TRACKING_SERVER_DEFAULT = false;
	
	/** Does the server have call/text logging enabled? */
	public static final String CALL_LOG_SERVER = "calllog";
	public static final boolean CALL_LOG_SERVER_DEFAULT = false;
	
	/**
	 * The number of different locations in which locations should be logged.
	 */
	public static final String NUM_LOCATIONS_TRACKED = "num_locations";
	
	/**
	 * Longitude for location tracked.  Since multiple locations can be set,
	 * make sure to append a number to the end of this. You can get the number
	 * of locations being tracked using {@link NUM_LOCATIONS_TRACKED}.
	 */
	public static final String TRACKED_LONG = "lt_long";
	
	/**
	 * Latitude for location tracked.  Since multiple locations can be set,
	 * make sure to append a number to the end of this. You can get the number
	 * of locations being tracked using {@link NUM_LOCATIONS_TRACKED}.
	 */
	public static final String TRACKED_LAT = "lt_lat";
	
	/**
	 * Radius for location tracked.  Since multiple locations can be set,
	 * make sure to append a number to the end of this. You can get the number
	 * of locations being tracked using {@link NUM_LOCATIONS_TRACKED}.
	 */
	public static final String TRACKED_RADIUS = "lt_rad";
	
	/**
	 * The number of different times during which locations should be logged.
	 */
	public static final String NUM_TIMES_TRACKED = "times_tracked";
	
	/**
	 * Start of time tracked.  Since multiple times can be set,
	 * make sure to append a number to the end of this. You can get the number
	 * of times being tracked using {@link NUM_TIMES_TRACKED}.
	 */
	public static final String TRACKED_START = "tt_start";
	
	/**
	 * End of time tracked.  Since multiple times can be set,
	 * make sure to append a number to the end of this. You can get the number
	 * of times being tracked using {@link NUM_TIMES_TRACKED}.
	 */
	public static final String TRACKED_END = "tt_end";
	
	/**
	 * The number of recent surveys to look at to determine the current survey
	 * completion rate.
	 */
	public static final String COMPLETION_SAMPLE = "completion_sample";
	public static final int COMPLETION_SAMPLE_DEFAULT = 10;
	
	//settings by type
	/** List of settings which are strings */
	public static final String[] STRINGS =
		{SERVER, SALT, ADMIN_PHONE_NUMBER, ADMIN_NAME, TRACKED_START,
		TRACKED_END};
	
	/** List of settings which are booleans */
	public static final String[] BOOLEANS =
		{HTTPS, ALLOW_BLANK_FREE_RESPONSE, ALLOW_NO_CHOICES, SHOW_SURVEY_NAME,
		USE_FULL_RES_PHOTOS, SURVEYS_LOCAL, TRACKING_LOCAL, CALL_LOG_LOCAL,
		SURVEYS_SERVER, TRACKING_SERVER, CALL_LOG_SERVER};
	
	/** List of settings which are ints */
	public static final String[] INTS =
		{SCHEDULER_INTERVAL, PUSH_INTERVAL, PULL_INTERVAL, LOCATION_INTERVAL,
		SURVEY_DELAY, VOICE_FORMAT, NUM_LOCATIONS_TRACKED, NUM_TIMES_TRACKED,
		COMPLETION_SAMPLE};
	
	/** List of settings which are floats */
	public static final String[] FLOATS =
		{TRACKED_LONG, TRACKED_LAT, TRACKED_RADIUS};
	
	/**
	 * Get the setting associated with a certain key.
	 * 
	 * @param ctxt - the current context
	 * @param key - the setting to get
	 * @param ifNotFound - the string to return if the setting isn't found
	 * @return a string holding that setting, or ifNotFound if that setting
	 * wasn't found
	 */
	public static String getSetting(Context ctxt, String key, String ifNotFound)
	{
		SharedPreferences settings = ctxt.getSharedPreferences(
        		CONFIG_FILE, 0);
		if (!settings.contains(key) && key.equals(SALT))
		{
			//generate a salt value automatically
			//this is pretty much as secure as we're going to get
			SecureRandom rand = new SecureRandom();
			SharedPreferences.Editor editor = ctxt.getSharedPreferences(
	        		CONFIG_FILE, 0).edit();
			String salt = new BigInteger(130, rand).toString(32);
	        editor.putString(SALT, salt);
	        editor.commit();
	        return salt;
		}
        return settings.getString(key, ifNotFound);
	}
	
	/**
	 * Get the setting associated with a certain key.
	 * 
	 * @param ctxt - the current context
	 * @param key - the setting to get
	 * @param ifNotFound - the value to return if the settings isn't found
	 * @return true or false if the setting was found, or the value of
	 * ifNotFound if it wasn't
	 */
	public static boolean getSetting(Context ctxt, String key, boolean ifNotFound)
	{
		SharedPreferences settings = ctxt.getSharedPreferences(
        		CONFIG_FILE, 0);
        return settings.getBoolean(key, ifNotFound);
	}
	
	/**
	 * Get the setting associated with a certain key.
	 * 
	 * @param ctxt - the current context
	 * @param key - the setting to get
	 * @param ifNotFound - the value to return if the settings isn't found
	 * @return a float holding the setting, or the value of
	 * ifNotFound if the setting wasn't found
	 */
	public static float getSetting(Context ctxt, String key, float ifNotFound)
	{
		SharedPreferences settings = ctxt.getSharedPreferences(
        		CONFIG_FILE, 0);
        return settings.getFloat(key, ifNotFound);
	}
	
	/**
	 * Get the setting associated with a certain key.
	 * 
	 * @param ctxt - the current context
	 * @param key - the setting to get
	 * @param ifNotFound - the value to return if the setting isn't found
	 * @return an int holding the setting, or ifNotFound if the setting wasn't
	 * found
	 */
	public static int getSetting(Context ctxt, String key, int ifNotFound)
	{
		SharedPreferences settings = ctxt.getSharedPreferences(
        		CONFIG_FILE, 0);
        return settings.getInt(key, ifNotFound);
	}
	
	/**
	 * Set the setting of key to val.
	 * 
	 * @param ctxt - the current context
	 * @param key - the setting to set
	 * @param val - the String to set it to
	 * @return true on success
	 */
	public static boolean putSetting(Context ctxt, String key, String val)
	{
		SharedPreferences settings = ctxt.getSharedPreferences(
        		CONFIG_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, val);
        return editor.commit();
	}
	
	/**
	 * Set the setting of key to val.
	 * 
	 * @param ctxt - the current context
	 * @param key - the setting to set
	 * @param val - the boolean to set it to
	 * @return true on success
	 */
	public static boolean putSetting(Context ctxt, String key, boolean val)
	{
		SharedPreferences settings = ctxt.getSharedPreferences(
        		CONFIG_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, val);
        return editor.commit();
	}
	
	/**
	 * Set the setting of key to val.
	 * 
	 * @param ctxt - the current context
	 * @param key - the setting to set
	 * @param val - the String to set it to
	 * @return true on success
	 */
	public static boolean putSetting(Context ctxt, String key, int val)
	{
		SharedPreferences settings = ctxt.getSharedPreferences(
        		CONFIG_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, val);
        return editor.commit();
	}
	
	/**
	 * Set the setting of key to val.
	 * 
	 * @param ctxt - the current context
	 * @param key - the setting to set
	 * @param val - the float to set it to
	 * @return true on success
	 */
	public static boolean putSetting(Context ctxt, String key, float val)
	{
		SharedPreferences settings = ctxt.getSharedPreferences(
        		CONFIG_FILE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, val);
        return editor.commit();
	}
}
