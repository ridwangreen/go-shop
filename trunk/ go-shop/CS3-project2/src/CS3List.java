/**
 * A generic list interface
 */
public interface CS3List<D> {

  /**
   * Checks if the list is empty
   *
   * @return true iff list is empty
   */
  public boolean empty();

  /**
   * Returns the length of the list
   *
   * @return the length of the list
   */
  public int length();

  /**
   * Adds data to the end of the list
   *
   * @param data the data to be added at the end of the list
   */
  public void add(D data);

  /**
   * Inserts data into a sorted list according to the Comparator.
   * If the list is not sorted or different Comparators are used
   * this method may insert the data in a random place in the list.
   * It is recommended that all elements be inserted with this
   * method if the list is to be sorted.
   *
   * @param data the data to be inserted
   * @param comp the Comparator used for ordering the list
   */
  public void insert(D data, java.util.Comparator<D> comp);

  /**
   * Print the data in the list on the standard output in order. A
   * traversal of the list is performed and each node's data
   * is printed on a separate line (using println)
   */
  public void print();

} // CS3List<D>
