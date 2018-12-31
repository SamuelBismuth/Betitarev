# Betitarev

![alt text](https://github.com/SamuelBismuth/Betitarev/blob/master/Logo/Betitarev_Logo_512.png)

A repository for the assignment of the course Software Engineering shared with  [@yoshago]( https://github.com/yoshago ) and [@Yishay1]( https://github.com/Yishay1 ).  

Everyone has already heard about betting. A lot of big companies have exploited all the resources (sports, horses...), using fictive or real money.  
The problem is, each bet is chosen by the company itself. What if someone want to bet about the number of windows there are in the room, or if he will success the course Software Engineering with grade of at least 85 ?  
Given two bettors (at least), and one judge, our application is a plat-form in which all the bet will run out.  
Let call Tic the first bettor and Tacthe second bettor. Tic is sure than the number of stars in the American flag is 42 and Tac thinks that there are 50 stars. Let Toc be the judge of the bet. Assuming that Tic, Tac, and Toc are already registered in our database, either Tic or Tac must create a new match with the exact bet. Toc designates the winner of the bet.  
The general idea is to put what we call a ”bet friend” into an application.  

![alt text](https://github.com/SamuelBismuth/Betitarev/blob/master/Screenshots/Place%20a%20bet.png)

# Processes

Here are all the processes Betitarev has.

1. Connection
	- Sign in with mail and password (mail containing "@betitarev.com are admin).
	- Change password if the user forgot him.
	- Register with mail password and fullname.

2. Social Network.
	- Access to profile with picture, mail and fullname.
	- Possibility to sign out.
	- Zooming in the picture by clicking on it.
	- Edit the profile. Possibility to change name, picture (by using device's camera), and password.
	- Search for other users and access to their profile (picture, fullname and mail).
	- Add friend and un-friend users.
	- Only for the admin users -> remove user from the application.
	- Send push notification to user as player or as arbitrator.

3. Placing a bet.
	- Searching a bettor (only among friends).
	- Editing the phrase and the guessing of the bet.
	- Editing the amount in virtual money.
	- Choosing if the bet must be arbitrate or not. (If need to be arbitrate, choose an arbitrator among the user's friends.)
	- Send the request. (push notification to all the concerned users).

4. Bet Historic.
	- Review of all the previous bet made by the user containing:
		- Fullname of the player 1 and his guessing.
		- Fullname of the player 2 and his guessing.
		- Phrase of the bet.
		- Amount of money for the bet.
		- Winner of the bet.

5. Statistics.
	- If not yet participate in any bet, the user can be redirected to the activity to place a bet.
	- Circular diagram with the following numbers:
		- Number of win.
		- Number of lose.
		- Number of draw.
		- Number of arbitrator.

# Bet process in video

![alt text](https://github.com/SamuelBismuth/Betitarev/blob/master/Video_Recorder/BetWithoutArbitrator.mp4)

# Included in this repository

- Diagrams folder which contains seven diagrams [here](https://github.com/SamuelBismuth/Betitarev/tree/master/Diagrams)
- doc folder with all the javadoc
- Firebase folder with the javascript function to handle notification by implementing a firebase function
- Logo folder with the logo of our application
- Paper folder containing the paper with the abstract and the requirements of the app
- Presentation fodler containing a support for the presentation which explain the main processes of the application
- Screeshots folder with some screenshots of the app
- src folder containing the source code of the app
- Video_Recorder folder showing some processes of the app

# Implementation Tasks

Assuming that all the task required back and front-end, and even phone configuration such that sending notification.  
The tasks are classify from the most to the less important.  
- [x] Log in/up and database connection. [@SamuelBismuth]( https://github.com/SamuelBismuth )
- [x] Real time database. [@SamuelBismuth]( https://github.com/SamuelBismuth )
- [x] Profile of the user activity. [@yoshago]( https://github.com/yoshago )
- [x] Place a bet activity. [@Yishay1]( https://github.com/Yishay1 )
- [x] Opened bet activity. [@SamuelBismuth]( https://github.com/SamuelBismuth )
- [x] Statistics activity. [@Yishay1]( https://github.com/Yishay1 )
- [x] Connection between all the activities. [@SamuelBismuth]( https://github.com/SamuelBismuth )
- [x] Account settings activity. [@yoshago]( https://github.com/yoshago )
- [x] Bet process (all the best process including the gain...). [@yoshago]( https://github.com/yoshago )  [@SamuelBismuth]( https://github.com/SamuelBismuth )  [@Yishay1]( https://github.com/Yishay1 )
- [x] Chat between friend. [@SamuelBismuth]( https://github.com/SamuelBismuth )
- [x] Fictive money transaction [@SamuelBismuth]( https://github.com/SamuelBismuth ) [@Yishay1]( https://github.com/Yishay1 )

# Team Work Rules

- Commit is required for all the new implemented function.
- Unit test is required to all the needed function.
- JavaDoc is required.
- New branches must be open is some hot code must be edit.


# Future Work

As future work a lot of things have to be improved:
- Implementing more tests to correct bugs.
- Improving the application design.
- Adding some features and processes.
- Fixing code syntax.



		






