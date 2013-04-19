4003-243 Project 01: README
===============================
(please use the RETURN key to make multiple lines; don't assume autowrap.)

0. Author Information
---------------------

CS Username: 	jxz6853		Name:  		Jenny Zhen

-------------------
1. Problem Analysis
-------------------

Summarize the analysis work you did. 
What new information did it reveal?  How did this help you?
How much time did you spend on problem analysis?

	I spent about an hour for the first UML diagram that I did for the 
	overall structure of the project. I changed some things a little after 
	I finished implementing some parts. It revealed that certain things 
	might've been overlooked on the first analysis. The UML diagram helped 
	me get the project going.

---------
2. Design
---------

Explain the design you developed to use and why. 
What are the major components? What connects to what?
How much time did you spend on design?

	I used an abstract class to link the different players (human, good, bad, 
	random). Inside the abstract Player class, chooseMove() and play() are 
	abstract classes. Getting the player name, printing the moves, storing the 
	board and player information are all controlled by the super class. The 
	board is stored as a 2D array of characters. There is only one board 
	throughout the whole game. I spent upwards of 2 hours total on the design.

-----------------------------
3. Implementation and Testing
-----------------------------

Describe your implementation efforts here; this is the coding and testing.

What did you put into code first?
How did you test it?
How well does the solution work? 
Does it completely solve the problem?
Is anything missing?
How could you do it differently?
How could you improve it?
What design changes are needed for the next iteration?

How much total time would you estimate you worked on the project? 
If you had to do this again, what would you do differently?

	I put the Board and Connect4 classes into code first. I tested it after I 
	created the RandomPlayer to test the toString() and overall functionality 
	of the board. The solution for all players work as described on the project 
	documentation completely. If I could, I would've made goodPlayer look ahead 
	of more than just the current move that it is deciding on. Perhaps, it 
	would be a better solution. I could improve it by actually doing research 
	on how the game is played. Unfortunately, this was a game that I wasn't too 
	familiar with, so I couldn't think as far ahead as someone who've played 
	this game prior could. Design changes that I would make would include 
	improving the goodPlayer algorithm, adding other players of varying 
	difficulty, and possibly including a GUI. I would estimate approximately 
	three weekends, since I am usually too busy over the weekdays. I would 
	change the goodPlayer's strategy by a lot.  

----------------------
4. Development Process
----------------------

Describe your development process here; this is the overall process.

How much problem analysis did you do before your initial coding effort?
How much design work and thought did you do before your initial coding effort?
How many times did you have to go back to assess the problem?

What did you learn about software development?

	I took about an hour to analyze the structure of the problem. I did a 
	UML diagram, sketched the game, and played the game before coding. It 
	took me approximately two hours. I referred back to the UML diagram and 
	sketches as I coded nearly every other function. I learned that software 
	development can be tedious if certain pieces of code are scrapped, 
	especially if it changes the entire design of an algorithm, etc.