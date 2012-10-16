/*
 * WordTree.java
 *
 * Version:
 * $Id$
 *
 * Revisions:
 * $Log$
 */

/**
 * WordTree program for project2 sorts words specifically
 * 
 * @author rcd1575: Rebecca Dudley
 */
public class WordTree extends DynamicTree<CRObject>{
	
	
	private WordList freqList;
	
	/**
	 * creates a new wordtree and a new list to hold frequencies
	 */
	public WordTree(){
		
		super();
		freqList = new WordList();
	}
	
	/**
	 * creates a list containing CRObjects sorted by their frequencies
	 * 
	 * @return  the list with CRObjects sorted by frequencies
	 */
	public WordList makeFreqList(){
		
		makeFreqList(root);
		return freqList;
	}
	
	/**
	 * creates a list containing CRObjects sorted by their frequencies
	 * 
	 * @param node  the current node
	 */
	private void makeFreqList(DNode<CRObject> node){
		
		if(node != null){
			freqList.insert(node.getData(), new CompLines());
			makeFreqList(node.getLeft());
			makeFreqList(node.getRight());
		}
	}
	
	/**
	 * searches for a word in the word tree
	 * 
	 * @param word  the word to be found
	 * @return  the toString for CRObject when found "word not found" if not found
	 */
	public String find(String word){
	
		String toReturn;
		CRObject toFind = new CRObject(word);
		CRObject found = super.find(toFind);
		if(found == null){
			toReturn = word + " word not found";
		}else{
			toReturn = found.toString();
		}
		return toReturn;
		
	}

}
