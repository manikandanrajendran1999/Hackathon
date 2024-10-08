@DigiExam_Control
Feature: To verify the basic scenario of DigiExam Control

@TC-ALL
Scenario: Exam Coordinator check the all control flows and check as a student in a single flow
    Given Login as a Admin
    And User click the view session button
    And User select the students tab
    And Check the "Not Reported" student
    When switch to the incognito window
    And User login as a student
    And Student done the authentication
    And Student start the exam
    And switch back to the main window
    #=========Give_Extra_Time============ 
    When switch to the incognito window
    And Check the student remaining time before "Give Extra Time"
    And switch back to the main window
    And Click more options for the exam "Started" student
    And Click "Give Extra Time" for that student
    And User give the "10" mins time to that student
    And User give "Natural Disastor" reason
    When switch to the incognito window
    And Check the student remaining time after "Give Extra Time"
    And Check the given extra time
    And switch back to the main window
    And Check the extra time added to the student
    #=========Pause_Exam============
    And Click more options for the exam "Started" student
    And Click "Pause Exam" for that student
    And User give the "10" mins time to that student
    And User give "Pause Test" reason
    And Check the student status changed as "Paused" in controller screen
    When switch to the incognito window
    And Student check the paused exam message and resume time
    And switch back to the main window
    And Click more options for the exam "Paused" student
    And Click "Resume Exam" for that student
    And Check the student status changed as "Started" in controller screen
    When switch to the incognito window
    And Student check if the exam has resume
    And Check the student remaining time
    And switch back to the main window
    #========= Clear Answers - Reset Exam ============
    When switch to the incognito window
    And Check the student remaining time before "Clear Answers - Reset Exam"
    And switch back to the main window
    And Click more options for the exam "Started" student
    And Click "Clear Answers - Reset Exam" for that student
    And User give "Reset Exam Test" reason
    When switch to the incognito window
    And User re-start the exam as a student
    And Check the student remaining time after "Clear Answers - Reset Exam"
    And switch back to the main window
    #========= Allow To Re-login ============
	  And Click more options for the exam "Started" student
	  And Click "Allow To Re-login" for that student
	  And Click the "PROCEED" button as staff
	  When switch to the incognito window
	  And User check the re authentication as a student
	  And Click the "Logout" button as student
	  And User login as a student
	  And Student done the authentication
	  And Student start the exam
	  And switch back to the main window
    #========= Terminate Exam ============
    And Click more options for the exam "Started" student
    And Click "Terminate Exam" for that student
    And User give "Natural Disastor" reason
    When switch to the incognito window
    And User check the terminated alert and close the exam
    And Try to sign as a terminated student
    And switch back to the main window
    And Check the student status changed as "Exam Terminated" in controller screen
    
@TC-1
Scenario: Exam Coordinator give the extra time for the student and check as a student

    Given Login as a Admin
    And User click the view session button
    And User select the students tab
    #And Check the "Not Reported" student
    When switch to the incognito window
    And User login as a student
    And Student done the authentication
    And Student start the exam
    And Check the student remaining time before "Give Extra Time"
    And switch back to the main window
    #=========Give_Extra_Time============ 
    And Click more options for the exam "Started" student
    And Click "Give Extra Time" for that student
    And User give the "10" mins time to that student
    And User give "Natural Disastor" reason
    When switch to the incognito window
    And Check the student remaining time after "Give Extra Time"
    And Check the given extra time
    And switch back to the main window
    And Check the extra time added to the student
    
@TC-2
Scenario: Exam coordinator pause the exam and resume the exam, also check as a student

    Given Login as a Admin
    And User click the view session button
    And User select the students tab
    And Check the "Not Reported" student
    When switch to the incognito window
    And User login as a student
    And Student done the authentication
    And Student start the exam
    And Check the student remaining time
    And switch back to the main window
    #=========Pause_Exam============
    And Click more options for the exam "Started" student
    And Click "Pause Exam" for that student
    And User give the "10" mins time to that student
    And User give "Pause Test" reason
    And Check the student status changed as "Paused" in controller screen
    When switch to the incognito window
    And Student check the paused exam message and resume time
    And switch back to the main window
    And Click more options for the exam "Paused" student
    And Click "Resume Exam" for that student
    And Check the student status changed as "Started" in controller screen
    When switch to the incognito window
    And Check the student remaining time
    And switch back to the main window
    
@TC-3
Scenario: Exam coordinator Clear Answers - Reset Exam, also check as a student

    Given Login as a Admin
    And User click the view session button
    And User select the students tab
    And Check the "Not Reported" student
    When switch to the incognito window
    And User login as a student
    And Student done the authentication
    And Student start the exam
    And Check the student remaining time before "Clear Answers - Reset Exam"
    And switch back to the main window
    #========= Clear Answers - Reset Exam ============
    And Click more options for the exam "Started" student
    And Click "Clear Answers - Reset Exam" for that student
    And User give "Reset Exam Test" reason
    When switch to the incognito window
    And Check the student remaining time
    And User re-start the exam as a student
    And Check the student remaining time after "Clear Answers - Reset Exam"
    And switch back to the main window
    
@TC-4
Scenario: Exam coordinator Allow To Re-login Exam, also check as a student

    Given Login as a Admin
    And User click the view session button
    And User select the students tab
    #And Check the "Not Reported" student
    When switch to the incognito window
    And User login as a student
    And Student done the authentication
    And Student start the exam
    And Check the student remaining time
    And switch back to the main window
    #========= Allow To Re-login ============
	  And Click more options for the exam "Started" student
	  And Click "Allow To Re-login" for that student
	  And Click the "PROCEED" button as staff
	  When switch to the incognito window
	  And User check the re authentication as a student
	  And Click the "Logout" button as student
	  And User login as a student
	  And Student done the authentication
	  And Student start the exam
	  And switch back to the main window
	  
@TC-5
Scenario: Exam coordinator Terminate Exam, also check as a student

    Given Login as a Admin
    And User click the view session button
    And User select the students tab
    And Check the "Not Reported" student
    When switch to the incognito window
    And User login as a student
    And Student done the authentication
    And Student start the exam
    And Check the student remaining time
    And switch back to the main window
    #========= Terminate Exam ============
    And Click more options for the exam "Started" student
    And Click "Terminate Exam" for that student
    And User give "Natural Disastor" reason
    When switch to the incognito window
    And User check the terminated alert and close the exam
    And Try to sign as a terminated student
    And switch back to the main window
    And Check the student status changed as "Exam Terminated" in controller screen
    
    
 
	  