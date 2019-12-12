/** 
 * Object class for the Rooms in the Dungeon.
 *
 * @author Hannah Juraszek
 * @version 30 November 2019
 */

import java.util.ArrayList;


public class Room {
	
	private String description; 
	private Room northRoom; 
	private Room eastRoom; 
	private Room southRoom; 
	private Room westRoom; 
	ArrayList<Item> itemList = new ArrayList<Item>();

	/**
	 * Constructor for a Room object.
	 *
	 * @param description the description of the Room
	 */
	public Room(String description) {
		this.description = description; 
		northRoom = null;
		eastRoom = null;
		southRoom = null;
		westRoom = null;
	}

	/**
	 * Constructor for a Room object with a Potion inside.
	 *
	 * @param description the description of the Room
	 */
	public Room(String description, Potion item) {
		this.description = description; 
		this.itemList.add(item);
		northRoom = null; 
		eastRoom = null; 
		southRoom = null; 
		westRoom = null; 
	} 

	/** 
	 * Default constructor for a Room.
	 * Initializes name/northRoom/eastRoom/southRoom/westRoom to null.
	 */
	public Room() {
		description = null;
		northRoom = null;
		eastRoom = null;
		southRoom = null; 
		westRoom = null; 
	} 

	/**
	 * Getter for Room's description.
	 *
	 * @return the description of the Room
	 */
	public String getDescription() {
		return description; 
	} 

	/**
	 * Setter for Room's north Room.
	 *
	 * @param room the Room to this Room's north
	 */
	public void setNorthRoom(Room room) {
		this.northRoom = room; 
	} 

	/**
	 * Getter for Room's north Room.
	 *
	 * @return the Room to this Room's north
	 */
	public Room getNorthRoom() {
		return northRoom;
	} 

	/**
	 * Setter for Room's east Room.
	 *
	 * @param room the Room to this Room's east
	 */
	public void setEastRoom(Room room) {
		this.eastRoom = room;
	} 

	/**
	 * Getter for Room's east Room.
	 *
	 * @return the Room to this Room's east
	 */
	public Room getEastRoom() {
		return eastRoom;
	} 

	/**
	 * Setter for Room's south Room.
	 *
	 * @param room the Room to this Room's south
	 */
	public void setSouthRoom(Room room) { 
		this.southRoom = room; 
	} 

	/**
	 * Getter for Room's south Room.
	 *
	 * @return the Room to this Room's south
	 */
	public Room getSouthRoom() { 
		return southRoom;
	} 

	/**
	 * Setter for Room's west Room.
	 *
	 * @param room the Room to this Room's west
	 */
	public void setWestRoom(Room room) { 
		this.westRoom = room; 
	} 

	/**
	 * Getter for Room's west Room.
	 *
	 * @return the Room to this Room's west
	 */
	public Room getWestRoom() {
		return westRoom;
	} 

	public void takeItems(Player thePlayer) {
		//For every item in this Room's item list
		for (int i = 0; i < this.itemList.size(); i++) {
			//itemAdded is a boolean variable for use in the inner for loop, initialized
			//to false for every item in this Room's itemList.
			//Necessary because of the inner for loop (checking if the item we're taking
			//is already in the itemList of the Player and can just have an updated
			//quantity). If we iterate through the for loop and still haven't added the item,
			//that means the item is a new item, but still needs to be added to the itemList.
			boolean itemAdded = false;
			//If thePlayer's itemList is not empty and this Room's itemList is not empty
			if (!thePlayer.itemList.isEmpty() && !this.itemList.isEmpty()) {
				//Then for every item in thePlayer's itemList,
				for (int j = 0; j < thePlayer.itemList.size(); j++) {
					//If the current item being examined in this Room's itemList's name matches
					//the current item being examined in thePlayer's itemList
					if (this.itemList.get(i).getName().equals(thePlayer.itemList.get(j).getName())) {
						//Then increase the quantity held of that item by the quantity of that item in the Room
						thePlayer.itemList.get(i).increaseQuantity(this.itemList.get(i).getQuantity());
						System.out.println("You found a " + this.itemList.get(i).getName() + " and took it.");
						this.itemList.remove(i);
						itemAdded = true;
						//And break out of the loop that is searching thePlayer's itemList
						break;
					}
					//If we made it to the end of the for loop and never added the item,
					if(itemAdded == false) {
						//Add the item to thePlayer.itemList and remove from this.itemList
						thePlayer.itemList.add(this.itemList.get(i));
						System.out.println("You found a " + this.itemList.get(i).getName() + " and took it.");
						this.itemList.remove(i);
					}
				}
			}
			//Otherwise if thePlayer's itemList is empty,
			else {
				//Just add the item without regard for quantities
				thePlayer.itemList.add(this.itemList.get(i));
				System.out.println("You found a " + this.itemList.get(i).getName() + " and took it.");
				this.itemList.remove(i);
			}
		}
	}

	/**
	 * Overridden toString method to convert GameCharacter objects to a
	 * string by including its description and the directions of possible
	 * exits.
	 *
	 * @return the String representation of the Room
	 */
	@Override
    public String toString() { 
        String val = "You are in " + description; 
        val += "\nThere are exits to the "; 
        if (northRoom != null)
            val += " N "; 
        if (eastRoom != null) 
            val += " E "; 
        if (southRoom != null) 
            val += " S "; 
        if (westRoom != null) 
            val += " W "; 
        return  val; 
    } 
}