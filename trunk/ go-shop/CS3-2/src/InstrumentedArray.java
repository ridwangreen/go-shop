
/*
 * InstrumentedArray.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * InstrumentedArray  reports how many times its elements are accessed for reading
 * Hi bill!!!
 *
 * @author Becca Dudley    Brandon Thivierge
 */

public class InstrumentedArray {
	
	private Integer[] nativeArray;  //the native array.  Arrrg!
	private int getCount;   //the counter for number of times elements have been accessed.  Arrrg!
	
   /**
    * constructor Creates and instrumented array full of integers.
    * Arrrg!
    * 
    * @param size     the number of integers in the array
    */
   public InstrumentedArray( int size ) {
	   
	   nativeArray = new Integer[size];
	   getCount = 0;
	   
   }
   
   /**
    * constructor that creates and instrumented array from an existing array
    * Arrrg!
    * 
    * @param data   the native array
    */
   public InstrumentedArray( Integer[] data ){
	   
	   nativeArray = data;
	   getCount = 0;
	   
   }
   
   /**
    * gets the value of one of the specified element
    * Arrg!
    * 
    * @param index   the index of the desired element
    */
   public Integer get(int index){
	   
	   getCount++;
	   return nativeArray[index];
	   
   }
   
   
   /**
    * change the value of the element at the specified index
    * Arrrg!
    * 
    * @param index    the index of the element to be changed
    * @param value    the value to replace the old element
    */
   public void put(int index, Integer value){
	
	   nativeArray[index] = value;
	   
   }
   
   
   /**
    * Get the total element reads that have been performed on this array
    * Arrrg!
    * 
    * @return the current number of reads
    */
   public int getTotalReads(){
	   
	   return getCount;
	   
   }
   
   
   /**
    * finds the size of this array
    * Arrrg!!
    * 
    * @return the number of elements in the array
    */
   public int size(){
	   
	   return nativeArray.length;
	   
   }

   

} // InstrumentedArray
