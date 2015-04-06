This page will discuss the differences in the ItemManager and the controller

# Introduction #

The Controller and ItemManager classes look very similar, however they both will fill a separate role which may be apparent once development continues.


# Details #


The **ItemManager** is used to modify the data of the application as well as save and load the data when it needs to be.

The **Controller** is responsible for communicating the data from the back end of the application to the UI.

**NOTE** What's going to happen is that whenever any of the data is manipulated... either by an addition, deletion, or edit... there is going to be an Adapter (see http://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews) that will reference the list of Category objects (which should accurately reflect the XML or however we're persisting it). The Adapter will provide the information for the UI to completely rebuild itself.