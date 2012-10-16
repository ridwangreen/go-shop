/**
 * CS3Stack.java
 *
 * An interface for a generic stack.
 *
 * From "An Introduction to Object-Oriented Programming with Java,
 * Comprehensive Edition", 4th edition, by C. Thomas Wu, (in print)
 */

public interface CS3Stack<E> {

    /** Removes all elements from the stack. */
    public void clear( );

    /** Returns true if the stack is empty, otherwise returns false. */
    public boolean isEmpty();

    /** Returns the top of stack element without removing it from the
        stack.  Throws an exception if the stack is empty. */
    public E peek() throws CS3StackEmptyException;

    /** Removes the top of stack element and returns it.  Throws an
        exception if the stack is empty. */
    public E pop() throws CS3StackEmptyException;

    /** Adds an element to the stack.  This element becomes the new 
        top of stack element. */
    public void push(E element);

    /** Returns the number of elements in this stack. */
    public int size();

}