/*
 * NetworkPlayer.java
 *
 * Version:
 *   $Id: NetworkPlayer.java,v 1.1 2002/10/22 21:12:53 se362 Exp $
 *
 * Revisions:
 *   $Log: NetworkPlayer.java,v $
 *   Revision 1.1  2002/10/22 21:12:53  se362
 *   Initial creation of case study
 *
 */

import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;
import java.awt.Color;

/**
 *  This class inerits from player. It is involved in the network game.
 *  This identifies that a network player is the second player.
 *
 *  @author
 */
public class NetworkPlayer extends Player {

   // The commands that can be send back and forth
   private static int RESIGN = 0;
   private static int DRAWOFFER = 1;
   private static int ACCEPTDRAW = 2;
   private static int REFUSEDRAW = 3;
   private static int ENDOFGAME = 4;
   private static int ROGER = 90;
   private static int RESEND = 98;
   private static int NETWORKPLAYER = 1;

   // The port number we will read from
   private static int PORTNUM = 1051;

   // Set timeout when waiting for client to connect (in milliseconds)
   private static int TIMEOUT = 600000;
   
   // The host we'll connect to if we're a remote system
   private URL host = null;

   // The socket used for communication
   private Socket clientSocket = null;

   // The socket that listens for a connect request
   private ServerSocket serverSocket = null;

   // Where we send our output; wraps clientSocket's outputStream
   private ObjectOutputStream out = null;

   // Where we receive our input; wraps clientSocket's inputStream
   private ObjectInputStream in = null;

   // Where we temporarily store input received and future output
   private Object inputObj = null, outputObj = null;

   // The address to connect to when we are a remote computer
   private URL IP = null;

    
   /**
    *  Constructor that creates a default object of this class
    */
   public NetworkPlayer( int playerNum, Rules rules, Driver theDriver ) {

    	// call super classes (player) constructor to give it starting info
    	super( playerNum, rules, theDriver );
        type = Player.NETWORKPLAYER;
    	// set values to correct initial state
   }

    
   /**
    * Set the host that we'll connect to if we're a remote system
    */
   public void setHost( URL host ) {
        this.host = host;
   }


   /**
    * The host waits for the network player to connect.  If there is a timeout,
    * we generate an actionEvent telling the GUI.
    *
    * @pre this is a network game
    * @post the connection has been established
    */
   public void waitForConnect()
   {

       // fire off an action even to tell the GUI to display the waiting for
       // client dialogue
       // Start up a ServerSocket on port PORTNUM.
       try {
          serverSocket = new ServerSocket(PORTNUM);
       } catch (IOException e) {

          // Should be an actionEvent
          System.err.println("Could not listen on port " + PORTNUM
                             + ".  Exception: \n"
                             + e + "\n\nProgram exiting...");
          cleanup();
          // Questionable
          // TODO: Use this?
          // theDriver.endGame();
       }

       // Set a timeout
       boolean eThrown = false;
       for ( int i = 0; eThrown && i < 4; i++ ) {
            try {
                serverSocket.setSoTimeout( TIMEOUT );
            } catch ( SocketException e ) {
                System.err.println( "Couldn't set timeout for serverSocket:\n" 
                                    + e );
                eThrown = true;
            }
       }
        
       eThrown = false;
       
       try {
           // Wait for a connect request, then initialize clientSocket
           clientSocket = serverSocket.accept();
           // For communication over network
           out = new ObjectOutputStream( clientSocket.getOutputStream() );
           in = new ObjectInputStream( clientSocket.getInputStream() );
       } catch (InterruptedIOException e) {
           System.err.println("Timed out while witing for client to connect. "
                              + "Program exiting...");
           cleanup();
           eThrown = true;
       } catch (IOException e) {
           System.err.println("Error while waiting for client to connect "
                              + "(not a timeout).  Program exiting...");
           cleanup();
           eThrown = true;
           // Questionable
           // TODO: Use this?
           //theDriver.endGame();
       } 

       // if eThrown == true, something should be done HERE!
       
       // if a client connects begin setting up the game
       // get the client player's name
       takeName();

       // tell the client our name
       sendName();

       // send the client their color
       sendColor();
   }


   /**
    * This method establishes a connection to the host
    *
    * @param host - the host to connect to
    *
    * @pre the host specified exists
    * @post connection to the host has been established
    */
   public void connectToHost()
   {
   	// create the proper sockets and attempt to establish a connection
       try {
           // Connect to host
           clientSocket = new Socket( host.getHost(), PORTNUM );

           // Streams for communication over network
           out = new ObjectOutputStream( clientSocket.getOutputStream() );
           in = new ObjectInputStream( clientSocket.getInputStream() );
       } catch (IOException e ) {

           System.err.println("Couldn't get I/O for connection to: "
                              + host.getHost() + "\n" + e
                              + "\nProgram exiting...");
           cleanup();
           // TODO: Use this?
           // theDriver.endGame();
       } catch (Exception e) {
           System.err.println(
			  "An exception occured while attempting to connect"
                              + "to: " + host.getHost() + "\n" + e
                              + "\nProgram exiting...");
           cleanup();
           // TODO: Use this?
           // theDriver.endGame();
       }

       // once a connection is established, begin setting up the game
       // send the host our name
       sendName();

       // get the host player's name
       takeName();

       // get our color from the host
       takeColor();
   }


   /**
    * This method takes the name of the other player and stores it
    *
    *
    * @pre name is null
    * @post the name is displayed at the other end of the connection
    */
   public void takeName()
   {
       Object inputObj = null;
       Integer outputObj = null;
       
       // repeat until a string is received and confirmation is sent
       do  {
            // read the name
           try {
                inputObj = in.readObject();
           } catch ( Exception e ) {
                   // add better error detect later

                   System.err.println( "Error reading name from network"
                                       + " stream:\n" + e );
           }

            // make sure it's OK
            if ( inputObj instanceof String ) {

                // Set the other player's name
                // TODO: call to Driver, possibly modify
                theDriver.setPlayerName( 2, (String) inputObj );

                outputObj = new Integer( ROGER );

                try {
                    out.writeObject( outputObj );
                    out.flush();
                } catch ( IOException e ) {
                       System.err.println( "IOException while sending confirm"
					   + " over network stream:\n" + e);
                }
            } else {
                outputObj = new Integer( RESEND );
                try {
                    out.writeObject( outputObj );
                    out.flush();
                } catch ( IOException e ) {
                       System.err.println( "IOException while sending confirm"
					   + " over network stream:\n" + e);
                }
            }
       } while ( outputObj.intValue() == RESEND );
   }

    
   /**
    * This method sends the name of this player to the other computer
    *
    * @pre name is not null
    * @post the host has the proper name for the player
    */
   public void sendName()
   {
      Integer inputObj = null;
      // Keep looping until confirm is received
   	do {
        // use the socket's output stream to send our name to the other player
          try {
               // TEMPORARY HACK FOR COMPILE
               //String name = "My Name";
               out.writeObject( playerName );
               out.flush();
          } catch ( IOException e ) {
               System.err.println( "IOException while sending name"
                                   + " over network stream:\n" + e);
          }
          // get confirmation from host
          try {
               inputObj = (Integer) in.readObject();
          } catch ( Exception e ) {
                // add better error detect later
                System.err.println( "Error getting confirmation from network"
                                    + " stream:\n" + e );
          }
      } while ( inputObj.intValue() == RESEND );
   }

    
   /**
    * This method gets the color of this player from the other computer;
    * this method will only be executed by the client computer.
    *
    * @pre the color is null
    * @post this computer has the proper color for the player
    */
   public void takeColor()
   {
       Object inputObj = null;
       Integer outputObj = null;
       // repeat until a Color is received and confirmation is sent
       do {
            // read the Color
           try {
                inputObj = in.readObject();
           } catch ( Exception e ) {
                   // add better error detect later
                   System.err.println( "Error reading color from network"
                                       + " stream:\n" + e );
           }
            // make sure it's OK
            if ( inputObj instanceof Color ) {
                // Set this player's color
                playerColor = ( Color ) inputObj;
                // Set the other player's color
                if ( this.playerNumber == 1 ) {
                    if ( this.playerColor == Color.white ) {                
                        theDriver.setPlayerColor( 2, Color.blue );
                    } else {
                        theDriver.setPlayerColor( 2, Color.white );
                    }
                } else if ( this.playerNumber == 2 ) {
                    if ( this.playerColor == Color.white ) {                
                        theDriver.setPlayerColor( 1, Color.blue );
                    } else {
                        theDriver.setPlayerColor( 1, Color.white );
                    }
                }
                
                outputObj = new Integer( ROGER );
                try {
                    out.writeObject( outputObj );
                    out.flush();
                } catch ( IOException e ) {
                       System.err.println( "IOException while sending confirm"
                                               + " over network stream:\n" + e);
                }
            } else {
                outputObj = new Integer( RESEND );
                try {
                    out.writeObject( outputObj );
                    out.flush();
                } catch ( IOException e ) {
                       System.err.println( "IOException while sending confirm"
                                               + " over network stream:\n" + e);
                }
            }
       } while ( outputObj.intValue() == RESEND );
   }

    
   /**
    * This method sends the color of this player to the other computer;
    * will only be executed by the host computer.
    *
    * @pre the color is not null
    * @post the other computer has the proper color for the player
    */
   public void sendColor()
   {
      Integer inputObj = null;
      // Keep looping until confirm is received
   	do {
          // use the socket's output stream to send the color to the client
          try {
               // TEMPORARY HACK FOR COMPILE
        	  // TODO: what is Color?
               Color color = Color.red;
               out.writeObject( color );
               out.flush();
          } catch ( IOException e ) {
               System.err.println( "IOException while sending color"
                                   + " over network stream:\n" + e);
          }
          // get confirmation from host
          try {
               inputObj = (Integer) in.readObject();
          } catch ( Exception e ) {
                // add better error detect later
                System.err.println( "Error getting confirmation from network"
                                    + " stream:\n" + e);
          }
      } while ( inputObj.intValue() == RESEND );
   }

    
   /**
    * Wait for the other player to send us a move or command.  If there is a
    * timeout, we generate an actionEvent telling the GUI.  We must then
    * send the move along to Rules, or call processCommand.
    *
    * @pre the game is in progress
    * @post we have recieved a move from the other player.
    */
   public void waitForPlayer()
   {
       
       Integer outputObj = new Integer( ROGER );
       
       // We'll loop until we get good input
       do {
            // Receive something from the remote player
            try {
                   inputObj = in.readObject();
            } catch ( Exception e ) {
                   // add better error detect later
                   System.err.println( "Error reading command from network"
                                       + " stream:\n" + e);
            }
            if ( inputObj instanceof Integer ) {
                // If it's an Integer, it's a command, so process it.
                outputObj = processCommand( ( (Integer) inputObj ).intValue());
                try {
                        out.writeObject( outputObj );
                } catch ( IOException e ) {
                       System.err.println( "IOException while sending confirm"
					   + " over network stream:\n" + e);
                }
		
                // this may conflict with Driver class
                if ( outputObj.intValue() == ACCEPTDRAW ) {
                    cleanup();
                }
            } else if ( inputObj instanceof NetworkMove ) {
		
        // TODO: Call to NetworkMove, okay
		NetworkMove remoteMove = ( NetworkMove ) inputObj;
		
                // If it's a move, send it off to be validated
                theRules.validateMove( new Move( this, 
		      remoteMove.startLocation(), remoteMove.endLocation() ) );

		// DEBUG:
                outputObj = new Integer( ROGER );
                try {
                        out.writeObject( outputObj );
                } catch ( IOException e ) {
                       System.err.println( "IOException while sending confirm"
					   + " over network stream:\n" + e);
                }
            } else {
                // The command recieved was bad; ask them to resend it
                outputObj = new Integer( RESEND );
                //DEBUG
		System.err.println( "Bad input received over network." );
                try {
                        out.writeObject( outputObj );
                } catch ( IOException e ) {
                       System.err.println( "IOException while sending confirm"
					   + " over network stream:\n" + e);
                }
            }
       } while ( outputObj.intValue() == RESEND );
   }

    
   /**
    * Process an incoming command, and take the appropriate action.
    *
    */
   public Integer processCommand( int command ) {
        Integer result = new Integer( ROGER );
        int answer = 0;
        String resignMessage = null;
        
        // If a draw offer is recieved, fire off an action event to inform the
   	//    GUI.
        if ( command == DRAWOFFER ) {
        	answer = theDriver.displayMessageWithResult("Draw Offer", "The remote player has requested a draw"
        			+ "\n\nWill you agree to a draw?");
	    
            // Make the answer readable by the remote player object
            if ( answer == YES ) {
                result = new Integer( ACCEPTDRAW );
            } else {
                result = new Integer( REFUSEDRAW );
            }
	    
          // Locally, this answer will be returned and sent by waitForPlayer()
        }
	
        // If a draw accept is recieved, fire off an action event to
   	  //    inform the GUI and call endGame in theDriver.
        else if ( command == ACCEPTDRAW ) {
        	theDriver.displayMessage("Draw Offer Accepted", "The remote player has accepted your draw offer."
        			+ "\n\nClick OK to end the program.");
         
            // TODO: Use this?
            // theDriver.endGame();
        }
	
        // If a draw is refused, let the user know and proceed with the game.
        else if ( command == REFUSEDRAW ) {
        	theDriver.displayMessage("Draw Offer Refused", "The remote player has refused your draw offer."
        			+ "\n\nClick OK to continue the game.");
        }
	
        // If a resign command is received, inform the user and exit the game
        else if ( command == RESIGN ) {
        	// TODO: Shouldn't be in backend
        	theDriver.displayMessage("Remote Resignation", "The remote player has resigned."
        			+ "\n\nClick OK to end the program.");
           
            cleanup();
            // TODO: Use this?
            // theDriver.endGame();
        }
	
        // If an end of game command is received, wait for a text message to
   	//    be sent over and then fire off an action event to tell the GUI
   	//    to display the message in a dialogue box when the user clicks OK,
   	//    endGame should be called in theDriver.
        else if ( command == ENDOFGAME ) {
            String endMessage = null;
            try {
                endMessage = (String) in.readObject();
            } catch ( Exception e ) {
                   // add better error detect later
                   System.err.println( "Error reading end of game message from"
                                       + " network stream:\n" + e);
            }
            theDriver.displayMessage("Game Over", endMessage + "\n\nClick OK to end the program");
            
            cleanup();
        } else {
            
            System.err.println( "Bad command received over network stream."
                             + "  Unrecoverable error.  Program exiting..." );
            // TODO: Use this?
            //theDriver.endGame();
        }
        return result;
   }

    
   /**
    * This method sends some command over network to the local system
    *
    * @param type - the type of command that is an integer value
    *
    * @pre type has to be a valid number for the command
    * @post command is sent to the local user
    */
   public void sendCommand(int type)
   {
       Integer inputObj = null;
       //try {
           do {
                // send the command given across the active socket
               try {
                   out.writeObject( new Integer( type ) );
               } catch ( IOException e ) {
                   System.err.println( "IOException while sending command over"
                                       + " network stream:\n" + e);
               }
                // get the confirmation
               try {
                   inputObj = (Integer) in.readObject();
               } catch ( Exception e ) {
                   // add better error detect later
                   System.err.println( "Error reading confirm from network"
                                       + " stream:\n" + e);
               }
           } while ( inputObj != null && inputObj.intValue() == RESEND );
       /*} catch ( IllegalCastException e ) {
           System.err.println( "Tried to send a command that wasn't an int."
                               + "  Program exiting..." );
       }*/
   }

    
   /**
    * The move is sent to the remote player
    *
    * @param move - move that was made by the local player
    *
    * @pre move is a legal move
    * @post the move is sent to the network player
    */
   public void sendMove()
   {
       Integer inputObj = new Integer( ROGER );
       do {
            // send the given move across the active socket
           try {
                out.writeObject( new NetworkMove( theMove ) );
		
           } catch ( IOException e ) {
               System.err.println( "IOException while sending move over"
                                       + " network stream: " + e);
           }
	   
	   // get the confirmation
	   try {
	       inputObj = (Integer) in.readObject();
	   } catch ( Exception e ) {
	       // add better error detect later
	       System.err.println( "Error reading confirm from network"
				   + " stream." );
	   }
       } while ( inputObj.intValue() == RESEND );
   }

    
   /**
    *  A DRAWOFFER message is sent to the remote player.  On this end, we will
    *  wait for a reply and generate an appropriate actionEvent based on it.
    *
    */
   public void offerDraw( Player player )
   {
       
       // send the draw offer over the network
       try {
           out.writeObject( new Integer( DRAWOFFER ) );
           
       } catch ( IOException e ) {
           System.err.println( "IOException while sending draw offer over"
                               + " network stream.");
       }
       
       // get the answer; this is handled by waitForPlayer()
       // If the offer is accepted, the player will be informed, and the game
       // will end.
       // If the offer is refuesed, again the player will be informed, and the 
       // game will continue as usual.
       waitForPlayer();
   }

    
   /**
    * When the current player accepts a draw, this method is called in the
    * opposite player to inform them that the draw has been accepted.
    * We're a networkPlayer, so we send the ACCEPTDRAW command over to the
    * remote player and waitForPlayer.
    *
    */
   public void acceptDraw( Player player ) {
       // send the draw accept over the network
       try {
            out.writeObject( new Integer( ACCEPTDRAW ) );
       } catch ( IOException e ) {
            System.err.println( "IOException while sending accept draw"
                                + " over network stream.");
       }
     
       theDriver.displayMessage("Game Over", "Since you agreed to draw, the game is now over."
    		   + "\n\nClick OK to end the program.");
           
       cleanup();
       // TODO: Use this?
       //theDriver.endGame();
   }

    
   /**
    * Method that is invoked when the end of game conditions have been
    * met.  Send the ENDOFGAME command over to the remote player and then
    * send the message endMessage over.
    *
    * @param endMessage  Message indicating the end of the game.
    */
   public void endOfGame(String endMessage){
       try {
            out.writeObject( new Integer( ENDOFGAME ) );
            out.writeObject( endMessage );
            cleanup();
       } catch ( IOException e ) {
            System.err.println( "IOException while sending end of game"
                                + " message over network stream.");
       }
   }

    
   /**
    * Return the integer constant representing the networkPlayer class
    *
    * @pre instance of player exists
    * @post this method has changed nothing
    */
   public int getPlayerType()
   {
   	// return the constant representing this class
   	return NETWORKPLAYER;
   }

    
   /**
    * Closes the streams & sockets
    */
   public void cleanup() {
       
       if ( out != null ) {
           try {
                out.close();
           } catch( IOException e ) {
               System.err.println( "Error:  " + playerName + 
                                   " couldn't close output stream.\n" 
                                   + e );
           }
       }
       
       if ( in != null ) {
           try {
                in.close();
           } catch( IOException e ) {
               System.err.println( "Error:  " + playerName + 
                                   " couldn't close input stream.\n" 
                                   + e );
           }
       }
       
       if ( clientSocket != null ) {
           try {
                clientSocket.close();
           } catch ( IOException e ) {
               System.err.println( "Error:  " + playerName + 
                                   " couldn't close clientSocket.\n" 
                                   + e );
           }
       }
       
       if ( serverSocket != null ) {
           try {
                serverSocket.close();
           } catch ( IOException e ) {
               System.err.println( "Error:  " + playerName + 
                                   " couldn't close clientSocket.\n" 
                                   + e );
           }
       }
   }

    
   // BELOW: TEMP METHODS FOR COMPILE
   
   public void endInDeclineDraw( Player player ) {
   }
   
   public void endInDraw( Player player ) {
   }
   
}
