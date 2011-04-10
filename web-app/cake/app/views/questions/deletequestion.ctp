<?php
/*****************************************************************************
 * views/questions/deletequestion.ctp                                             *
 *                                                                           *
 * Page to delete a question.                                                  *
 *****************************************************************************/
echo $this->Session->flash();
echo $form->create('Question', array('url' => "showquestions/$surveyid"));
echo $form->input('confirm', array('type' => 'hidden', 'value' => true));
echo "<p>Are you sure you want to delete $q_text?</p>";
echo '<p><strong>This action cannot be undone.</strong></p>';
echo $form->end('Yes');
echo $form->create('Question', array('action' => "showquestions/$surveyid"));
echo $form->end('No');
?>