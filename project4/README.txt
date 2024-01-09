INTRODUCTION
------------

 * The purpose of this project is to design and implement a custom binary search tree data 
   structure that contains methods for finding, searching, and manipulating data elements 
   in an efficient way. This specific program represents a hiker's descent down a mountain,
   and utilizes the custom BST class to find safe paths down the mountain. 
   
 * MAIN METHOD: MountainClimb.java
   CUSTOM BST CLASS: BST.java
   MOUNTAIN CLASS: BSTMountain.java
   HIKER CLASS: Hiker.java
   REST STOP CLASS: RestStop.java

 * The program's main method takes a file path via command line argument and creates 
   a series of RestStops based on the data in the file. BSTMountain object based on a file full of 
   RestStps
   the user to enter a "sequence" to add to the MDeque. It them prompts the user
   for a "decoding key" which will be used to operate on the MDeque. 
   (F = remove front element, B = remove back element, R = reverse the elements)
   
 * The sequence must consist of only integers separated by commas and spaces, 
   and the key must consist of only "F", "B", or "R" characters.
   
   Sample user input - 
   Sequence: 
   12, 6, 9, 20, 17, -8, 19, 22
   Decoding key:
   FBRFBR

 * An explanation for each class can be found at the top of each file  
   Main method: NYCStreetTrees.java
   Parsing method: CSV.java
   Abstraction methods: Tree.java and TreeSpecies.java
   Data Structure methods: TreeList.java and TreeSpeciesList.java;
   
   
REQUIREMENTS
------------
 
 * If running in an IDE, you must create a package to store all the
   files.
 
CONFIGURATION
-------------
 
 * N/A


MAINTAINERS
-----------

Current maintainers:
 * Finn Fetherstonhaugh - fkf2005@nyu.edu