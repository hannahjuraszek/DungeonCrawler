/** 
 * Object class for the Dungeon. Main driving factor behind dungeon initialization.
 *
 * @author Hannah Juraszek
 * @version 30 November 2019
 */

public class Dungeon extends Room {

	private Room kitchen; 
	private Room diningRoom; 
	private Room livingRoom; 
	private Room bathroom; 
	private Room bedroom; 
	private Room guestRoom; 
	private Potion healthPotion = new Potion("Health Potion", 1, 0, 30); 
	private Potion manaPotion = new Potion("Mana Potion", 1, 3, 0); 
	
	/**
	 * Constructor for the Dungeon. Instantiates all of the rooms and the overall 
	 * layout of the dungeon.
	 */
	public Dungeon() {
		kitchen = new Room("the kitchen. It smells of rotting food.", healthPotion); 
		diningRoom = new Room("the dining room. Dust covers the empty table."); 
		livingRoom = new Room("the living room. There are plastic coverings over all of the furniture. Just the way you like it."); 
		bathroom = new Room("the bathroom. It looks like it hasn't been touched for some time.", healthPotion); 
		bedroom = new Room("the bedroom. On the bed is the vague outline of a human body, as if there were one lying there right now."); 
		guestRoom = new Room("the guest room. It looks to be the least lived in space in the whole house.", manaPotion); 
		livingRoom.setEastRoom(guestRoom); 
		livingRoom.setWestRoom(bedroom); 
		diningRoom.setNorthRoom(livingRoom); 
		diningRoom.setEastRoom(kitchen); 
		diningRoom.setWestRoom(bathroom); 
		bedroom.setEastRoom(livingRoom); 
		bedroom.setSouthRoom(bathroom); 
		bathroom.setNorthRoom(bedroom); 
		bathroom.setEastRoom(diningRoom); 
		kitchen.setNorthRoom(guestRoom); 
		kitchen.setWestRoom(diningRoom); 
		guestRoom.setSouthRoom(kitchen); 
		guestRoom.setWestRoom(livingRoom);
	}
	
	/**
	 * Method to establish the starting room.
	 *
	 * @return the starting room – currently the living room
	 */
	public Room getStartingRoom() { 
		return livingRoom; //return that living room is the starting room
	} 
}