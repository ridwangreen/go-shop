/**
 * 
 */
package com.example.goshop;

/**
 * This will be the middle man for the ui and the back end.
 * 
 * @author Charles Lander (cjl1750@rit.edu)
 *
 */
public class Controller implements ControllerInterface {
	private ItemManager itemManager;
	
	public Controller() {
		itemManager = new ItemManager();
	}

	/* (non-Javadoc)
	 * @see com.example.goshop.ControllerInterface#addCategory(com.example.goshop.Category)
	 */
	public boolean addCategory(Category toAdd) {
		return itemManager.addCategory(toAdd);
	}

	/* (non-Javadoc)
	 * @see com.example.goshop.ControllerInterface#removeCategory(com.example.goshop.Category)
	 */
	public boolean removeCategory(Category toRemove) {
		return itemManager.removeCategory(toRemove);
	}

	/* (non-Javadoc)
	 * @see com.example.goshop.ControllerInterface#editCategory(com.example.goshop.Category, java.lang.String)
	 */
	public boolean editCategory(Category toEdit, String newName) {
		return itemManager.editCategory(toEdit, newName);
	}

	/* (non-Javadoc)
	 * @see com.example.goshop.ControllerInterface#addItem(com.example.goshop.Item, com.example.goshop.Category)
	 */
	public boolean addItem(Item item, Category toAdd) {
		return itemManager.addItem(item, toAdd);
	}

	/* (non-Javadoc)
	 * @see com.example.goshop.ControllerInterface#removeItem(com.example.goshop.Item, com.example.goshop.Category)
	 */
	public boolean removeItem(Item item, Category toRemove) {
		return itemManager.removeItem(item, toRemove);
	}

	/* (non-Javadoc)
	 * @see com.example.goshop.ControllerInterface#editItem(com.example.goshop.Item, java.lang.String)
	 */
	public boolean editItem(Item item, String newName) {
		return itemManager.editItem(item, newName);
	}

}
