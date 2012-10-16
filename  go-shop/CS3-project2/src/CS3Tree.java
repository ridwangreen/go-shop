public interface CS3Tree<D extends Comparable<D>> {

  /**
   * Returns the data in the tree comparing equal to the argument
   * data or null if a node with equal data is not found. Note that
   * the user of this method must construct a data item comparing
   * equal to the desired data. The matching data in the tree will
   * be returned if the search is successful.
   *
   * @param data the data to be searched for
   */
  public D get(D data);

  /**
   * The argument data is inserted in the tree if no value equal to
   * the argument already exists in the tree. The method returns
   * true if and only if the data was inserted into the tree.
   *
   * @param data the data to be inserted
   * @return true iff the data was inserted
   */
  public boolean add(D data); // returns true if added

  /**
   * Print the data in the tree on the standard output in order. An
   * inorder traversal of the tree is performed and each node's data
   * is printed on a separate line (using println)
   */
  public void print();

} // class CS3Tree<D extends Comparable<D>>

