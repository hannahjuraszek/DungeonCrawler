/** 
 * Runner class that allows the user to explore a Dungeon.
 *
 * @author Hannah Juraszek
 * @version 30 November 2019
 */

import java.util.Scanner; 
import java.util.Random; 

public class RoomRunner {

	private static Weapon weapon = new Weapon("Broom Stick", 1, 16);
	private static Potion healthPotion = new Potion("Health Potion", 1, 0, 30); 
	private static Potion manaPotion = new Potion("Mana Potion", 1, 3, 0); 
	private static Monster theMonster = new Monster();
	private static Player thePlayer;
	private static boolean loopControl = true; 
	private static Monster roach = new Monster("Roach", 60, 10, 100);
	private static boolean combatLoop = true;
	private static int monstersKilled = 0;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); 
		Dungeon newDungeon = new Dungeon(); 
		Room current = new Room(); 
		current = newDungeon.getStartingRoom(); 
		Random ranGen = new Random();

		System.out.print("Please input your hero's name: ");
		String playerName = input.nextLine();

		thePlayer = new Player(playerName, 80, 12, 0);
		thePlayer.itemList.add(manaPotion);

		System.out.println();
		System.out.println("Enter q at any time to quit.");
		System.out.println("Enter i to view your inventory.");
		System.out.println("Enter u to use an item from your inventory.");
		System.out.println();
		System.out.println("PURGE THE HOUSE OF INSECT SCUM.");

		while (loopControl && monstersKilled < 10) { 
			System.out.println(current); 
			System.out.println("Monsters killed: " + monstersKilled + "/10");
			if (!current.itemList.isEmpty()) {
				current.takeItems(thePlayer);
			} 

			char response = input.next().charAt(0); 

			if (response == 'n') { 
				if (current.getNorthRoom() != null) {
					current = current.getNorthRoom(); 
					if (ranGen.nextInt(3) == 0) {
						System.out.println(current);
						theMonster = roach;
						System.out.println("You've encountered a " + theMonster.getName() + ".");
						combatLoop = true;
						while (theMonster.getHealth() > 0 && thePlayer.getHealth() > 0 && combatLoop) {
							manageCombat();
						} 
					} 
				} 
				else 
					System.out.println("Can't go that way."); 
			}

			else if (response == 'e') { 
				if (current.getEastRoom() != null) {
					current = current.getEastRoom(); 
					if (ranGen.nextInt(3) == 0) {
						System.out.println(current);
						theMonster = roach;
						System.out.println("You've encountered a " + theMonster.getName());
						combatLoop = true;
						while (theMonster.getHealth() > 0 && thePlayer.getHealth() > 0 && combatLoop) {
							manageCombat();
						} 
					} 
				} 
				else 
					System.out.println("Can't go that way."); 
			} 

			else if (response == 's') { 
				if (current.getSouthRoom() != null) {
					current = current.getSouthRoom(); 
					if (ranGen.nextInt(3) == 0) {
						System.out.println(current);
						theMonster = roach;
						System.out.println("You've encountered a " + theMonster.getName());
						combatLoop = true;
						while (theMonster.getHealth() > 0 && thePlayer.getHealth() > 0 && combatLoop) {
							manageCombat();
						} 
					} 
				} 
				else  
					System.out.println("Can't go that way."); 
			} 

			else if (response == 'w') { 
				if (current.getWestRoom() != null) {
					current = current.getWestRoom(); 
					if (ranGen.nextInt(3) == 0) {
						System.out.println(current);
						theMonster = roach;
						System.out.println("You've encountered a " + theMonster.getName());
						combatLoop = true;
						while (theMonster.getHealth() > 0 && thePlayer.getHealth() > 0 && combatLoop) {
							manageCombat();
						} 
					} 
				} 
				else 
					System.out.println("Can't go that way."); 
			} 

			else if (response == 'q') { 
				System.out.println("\nYou leave the house and go out for a beer.");
				loopControl = false; 
			} 

			else if (response == 'i') {
				if (thePlayer.itemList.isEmpty())
					System.out.println("Your inventory is empty.");
				else
					for (int i = 0; i < thePlayer.itemList.size(); i++) {
						System.out.println(thePlayer.itemList.get(i).getName() + " x" + thePlayer.itemList.get(i).getQuantity());
					}
			}

			else if (response == 'u') {
				int responseNum = 0;
				if (!thePlayer.itemList.isEmpty()) {
					for (int i = 0; i < thePlayer.itemList.size(); i++) {
						System.out.println((i + 1) + ") " + thePlayer.itemList.get(i).getName() + " x" + thePlayer.itemList.get(i).getQuantity());
					}

					response = input.next().charAt(0);
					responseNum = Character.getNumericValue(response);
					if (thePlayer.itemList.get(responseNum - 1).getClass() == Potion.class) {
						thePlayer.setHealth(thePlayer.getHealth() + thePlayer.itemList.get(responseNum - 1).getHealthRestore());
						thePlayer.setMana(thePlayer.getMana() + thePlayer.itemList.get(responseNum - 1).getManaRestore());
						
						if (thePlayer.getHealth() > thePlayer.getMaxHealth()) {
							System.out.println("You restored " + (thePlayer.itemList.get(responseNum - 1).getHealthRestore() - (thePlayer.getMaxHealth() - thePlayer.getHealth())) + " health and "
								+ thePlayer.itemList.get(responseNum - 1).getManaRestore() + " mana.");
							thePlayer.setHealth(thePlayer.getMaxHealth());
						}

						else
							System.out.println("You restored " + thePlayer.itemList.get(responseNum - 1).getHealthRestore() + " health and " + thePlayer.itemList.get(responseNum - 1).getManaRestore() + " mana.");

						if (thePlayer.itemList.get(responseNum - 1).getQuantity() == 1)
							thePlayer.itemList.remove(responseNum - 1);
						else
							thePlayer.itemList.get(responseNum - 1).setQuantity(thePlayer.itemList.get(responseNum - 1).getQuantity() - 1);
					}

					else
						System.out.println("You can't use that.");
				}

				else
					System.out.println("Your inventory is empty.");
			} 

			else 
				System.out.println("Invalid entry."); 

		} 
		if (monstersKilled >= 10)
			System.out.println("You win!");
		else
			System.out.println("Game over."); 
	} 


	public static void manageCombat() {
		while (combatLoop) { 
			System.out.println(thePlayer.getName() + " - HP: " + thePlayer.getHealth() + "/" + thePlayer.getMaxHealth() + " Mana: " + thePlayer.getMana());
			System.out.println(theMonster.getName() + " - HP: " + theMonster.getHealth());

			thePlayer.takeTurn(theMonster);
			if (thePlayer.checkRunAway()) {
				combatLoop = false;
				continue;
			}

			else if (theMonster.getHealth() <= 0) {
				System.out.println("You killed it! Gross. +" + theMonster.getXP() + "xp");
				thePlayer.setXP(theMonster.getXP() + thePlayer.getXP());
				System.out.println(thePlayer.getXP() + "/" + thePlayer.getXPToLevel() + "xp");
				monstersKilled ++;
				if (thePlayer.checkLevelUp())
					thePlayer.levelUp();
				if (!theMonster.itemList.isEmpty())
					thePlayer.takeItems(theMonster);
				combatLoop = false;
				continue;
			}

			theMonster.takeTurn(thePlayer);
			if (thePlayer.getHealth() <= 0) {
				System.out.println("You died.");
				combatLoop = false;
				loopControl = false;
				continue;
			}
		}
		return;
	} 
} 