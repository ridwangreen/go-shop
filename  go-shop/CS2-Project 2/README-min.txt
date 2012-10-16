README: CS2 Project 2 (Frenzy)
Initial Submission
Design and Implementation Notes

student name : Rebecca Dudley
student login : rcd1575

Answer the questions below.
*Note: this is part of the graded portion of the project. 
Make your answers brief (at most 1-2 paragraphs) and clear.

Please keep line lengths < 80 characters for readability and printing.

Design:
-----------
1. How do your MODEL, VIEW, and CONTROLLER elements interact?
   Be sure to identify which classes are part of the MODEL, which
   are part of the VIEW, and which are part of the CONTROLLER.

Answer:
the board view class doesn't know where the player(model) is going next, the player does.
All the model does is display all the elements on the board, no logic of 
what to do when events are fired should be in the board view.
The controller or FrenzyListener listens for button events and performs the appropriate actions independent of the view.



2. Identify or describe known problems with this version of the program.


Answer:
Design wise the view and the model are coupled inside of boardview.  Will fix in final version. 


