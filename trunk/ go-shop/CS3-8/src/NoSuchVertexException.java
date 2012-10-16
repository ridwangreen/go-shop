/*
 * NosuchVertexException.java
 *
 * Version:
 *    $Id: NoSuchVertexException.java,v 1.1 2010/04/26 14:16:26 vcss233 Exp $
 *
 * Revisions:
 *    $Log: NoSuchVertexException.java,v $
 *    Revision 1.1  2010/04/26 14:16:26  vcss233
 *    Initial revision
 *
 *    Revision 1.4  2001/04/03 14:28:23  cs3
 *    More style standards 20003
 *
 *    Revision 1.3  2001/03/09 12:42:36  cs3
 *    style changes -- ptt
 *
 */

/**
 * An exception thrown by a class that implements the DiGraph interface
 * to indicate a problem with the parameters passed to a method.  Note that
 * there is no way to create a NoVertexException that does not contain a
 * message.
 *
 * @author     Paul Tymann
 */

public class NoSuchVertexException extends Exception {

    /**
     * Create a new NoSuchVertexException that contains the specified
     * message.
     *
     * @param msg the message to be placed in the exception.
     */
    
    public NoSuchVertexException( String msg ) {
	super( msg );
    }

} // NoSuchVertexException
