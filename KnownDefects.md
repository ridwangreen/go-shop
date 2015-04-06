# Introduction #

Style defects this way:

**Defect Name**: Priority (Very Low, Low, Medium, Medium-high, High, Very High)
  * Detailed description of defect, and steps to recreate (if applicable)
  * How to fix the defect (if applicable)


# Defects #

_COMPLETE_ **Colors in add category**: High
  * The spinner drop-down list in the Add Category screen currently displays the hex code for the color.
  * Change the getView method so that it sets the background color of each dropdown item to the hex code, and display no text

**Add Item toast**: Low
  * The main screen displays a toast saying an item is added whenever the "ADD" button is pressed, even if the item isn't actually added
  * Use the boolean returns to check if the add item behavior worked before displaying the toast

_COMPLETE_ **Remove Item**: Very high
  * The ability to remove an item from the screen and have the change reflected in the back end

_COMPLETE_ **Remove category**: very high
  * Ability to remove a category and have the change reflected in the back end

**Edit item**: Medium
  * Ability to edit the name (and category?) of an item and have the change reflected in the back end

**Edit category**: Medium
  * Ability to edit the name and color of a category, and have the change reflected in the back end

**Category Color Change**: Medium
  * When a color is selected for a category, change the color of the typed name of the category to the chosen color

**Fonts**: Low
  * Make the fonts of all the text consistent and easy to read

**Checked Item**: Very High
  * When an item is checked, make sure the data is stored in the back end so that the checks don't disappear with a redraw.

**Checked Item Graphic**: Low
  * Add a graphic to a checked item to make it look "crossed off"

**Clear Checked**: Medium
  * Add a button and behavior to clear the list of all checked items

**Clean up Adapter**: Low
  * Clean up the behaviors associated with GoShopAdapter and all the sub-adapters

**Add Lifecycle Methods**: Medium-high
  * Add Activity life-cycle methods to all the activities for reading and writing state

**Back button**: Medium-Low
  * Add a "Return to Shopping List" button on the category manager

**Long Press**: High
  * Add long-press behavior to the list items for edit

_COMPLETED_ **Clean Up Packages**: Low
  * Clean up the packages for easier maintainability

**Disable Add**: Medium
  * Disable the Add Quick Item button if there is no text entered

**Dropdown Distinguishability**: Medium-Low
  * Make the category spinner more easy to identify as a dropdown menu