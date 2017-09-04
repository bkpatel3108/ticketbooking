#####################################################################################

Build and Run Application Commands

#####################################################################################

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Application Test and Build Instructions 
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

1. Clone following Repository to your computer.
<<URL>>

2. Open Terminal or Cmd, and change directory to Application (TIcketService)
Ex : $ cd njit/workspaces/java-assignments/ticketbooking/

3. To run tests
$ mvn test

4. To Run Integration Tests and Build project
$ mvn install

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Application Run Instructions 
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

$ cd target/
$ java -cp ticketbooking-0.0.1-SNAPSHOT.jar com.app.ticketbooking.App


#####################################################################################

Example of Using Application

#####################################################################################

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Section 1 : Configuration
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

You can configure this application in 2 ways. 
Using quick configuration and manual configuration

..............................................................

1. Using Default Configuration

----- Ticket Service Configuration -----
1 - For Default 
2 - For Manual 
0 - To Quit
Enter a number: 1

----- Default Configuration -----
Number of rows in venue : 10
Number of seats in each row : 10
Best Seat Search Configuration : Book only in same row
Ticket hold expire time (in seconds) : 30
Time interval for background service to recheck for expired tickets (in seconds) : 2

..............................................................

2. Using Manual Configuration

----- Ticket Service Configuration -----
1 - For Default 
2 - For Manual 
0 - To Quit
Enter a number: 2

----- Venue Configuration -----
Please enter number of rows in venue : 10

Please enter number of seats in each row : 10

----- Best Seat Search Configuration -----
1 - Book only in same Row 
2 - Book max in Same Row(Coming Soon) 
3 - Divide seats in equal Rows(Coming Soon)
Enter a number: 1

----- Ticket/Seat Hold Timeout Configuration -----

Please enter ticket hold expire time (in seconds) : 30   

Please enter time interval for background service to recheck for expired tickets (in seconds) : 2


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Section 2 : Ticket Booking Console
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

>> 1 : To check no of seats available

---------- Ticket Booking Console ----------
1 - To check no of seats available
2 - To find and hold best seats
3 - To confirm hold seats
4 - To print venue chart
0 - To quit

Please Enter a number : 1

No of seats available : 100

..............................................................

>> 2 : To find and hold best seats

---------- Ticket Booking Console ----------
1 - To check no of seats available
2 - To find and hold best seats
3 - To confirm hold seats
4 - To print venue chart
0 - To quit

Please Enter a number : 2

Please enter number of seats to find and hold : 9

Please enter email id of customer : b1

----- Ticket/Seat Hold Details -----
Seat Hold Id : 1000000000
Customr email id : b1
Seat hold status : HELD
Error message : null
1-1	1-2	1-3	1-4	1-5	1-6	1-7	1-8	1-9

..............................................................

>> 3 : To confirm hold seats

---------- Ticket Booking Console ----------
1 - To check no of seats available
2 - To find and hold best seats
3 - To confirm hold seats
4 - To print venue chart
0 - To quit

Please Enter a number : 3

Please enter seat hold id : 1000000000

Please enter email id of customer :b1

----- Ticket Reservation Confirmation Code -----
Confirmation code : Success#2000000000


..............................................................

>> 4 : To print venue chart

---------- Ticket Booking Console ----------
1 - To check no of seats available
2 - To find and hold best seats
3 - To confirm hold seats
4 - To print venue chart
0 - To quit

Please Enter a number : 4

---------------- Current Venue Booking ---------------------------
RESV	RESV	RESV	RESV	RESV	RESV	RESV	RESV	RESV	FREE	
FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	
FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	
FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	
FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	
FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	
FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	
FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	
FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	
FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	FREE	
------------------------------------------------------------------


