/** 
 * Object class for Players.
 *
 * @author Hannah Juraszek
 * @version 30 November 2019
 */

import java.util.Scanner;

public class Player extends GameCharacter {
	
	private int mana;
	private int xp;
	private int xpToLevel;
	private int maxHealth;
	private int level;
	private boolean ranAway;

	/**
	 * Constructor for a Player object.
	 *
	 * @param name the name of the Player
	 * @param health the number of health points the Player has
	 * @param attackPower the attack power of the Player
	 * @param mana the mana of the Player
	 */
	public Player(String name, int health, int attackPower, int mana) {
		this.name = name;
		this.health = health;
		this.maxHealth = health;
		this.attackPower = attackPower;
		this.mana = mana;
		this.xpToLevel = 100;
		this.level = 1;
	}

	/**
	 * Player's attack method.
	 *
	 * @param theMonster the Monster object to attack
	 */
	public void attack(Monster theMonster) {
		boolean haveWeapon = false;
		int weaponIndex = -1;
		//If the Player has items in their itemList,
		if (!this.itemList.isEmpty()) {
			//For every item in the itemList
			for (int i = 0; i < itemList.size(); i++) {
				//Check to see if the item is a weapon
				if (itemList.get(i).getClass() == Weapon.class) {
					//If so, set the boolean variable to true
					haveWeapon = true;
					//And store the index we found the weapon at
					weaponIndex = i;
				}
			}
		}	

		//If we found a Weapon in the itemList,
		if (haveWeapon == true) {
			//Damage dealt = Player's attackPower + Weapon's weaponDamage
			theMonster.takeDamage(attackPower + this.itemList.get(weaponIndex).getWeaponDamage());
			System.out.println("You deal " + (attackPower + this.itemList.get(weaponIndex).getWeaponDamage()) + " damage.");
		}
		
		//If we didn't find a Weapon in the itemList
		else {
			//Damage dealt = Player's attackPower
			theMonster.takeDamage(attackPower);
			System.out.println("You deal " + attackPower + " damage.");
		}
		return;
	}

	/**
	 * Method to enact the Player casting a spell on a Monster.
	 *
	 * @param theMonster the Monster object to attack
	 */
	public void castSpell(Monster theMonster) {
		//If the Player has enough mana to cast a spell (3 mana required)
		if (mana >= 3) {
			//Deal 50 damage to theMonster
			theMonster.takeDamage(50);
			System.out.println("You spray the " + theMonster.getName() + " with Windex.");
			//And remove the mana used
			mana = mana - 3;
		}
		//If the player doesn't have enough mana, tell them
		else
			System.out.println("Not enough mana.");
	}

	/**
	 * Method to charge mana. Charges 1 mana per turn.
	 */
	public void chargeMana() {
		mana = mana + 1;
		System.out.println("You turn the nozzle on your Windex.");
		if (mana < 3)
			System.out.println("It's still on the locked setting.");
	}

	/**
	 * The method to enact the Player's turn on a Monster.
	 *
	 * @param theMonster the Monster to act upon when enacting the Player's turn
	 */
	public void takeTurn(Monster theMonster) {
		//Instantiate a Scanner to take in user's input
		Scanner input = new Scanner(System.in);
		char response = '0';
		//Prompt user for input
		System.out.println("What would you like to do?\n1. Attack\n2. Cast Spell (costs 3 mana)\n3. Charge Mana\n4. Use Item\n5. Run Away");

		//Keep looping until the user inputs a valid response
		while (response != '1' && response != '2' && response != '3' && response != '4' && response != '5') {
			response = input.next().charAt(0);
			int responseNum;
			ranAway = false;

			//If the user chose Attack
			if (response == '1')
				attack(theMonster);

			//Else if the user chose Cast Spell
			else if (response == '2')
				this.castSpell(theMonster);

			//Else if the user chose Charge Mana
			else if (response == '3')
				this.chargeMana();

			//Else if the user chose Use Item
			else if (response == '4') {
				//Print out the name and quantity of every item in the itemList
				for (int i = 0; i < itemList.size(); i++) {
					System.out.println((i + 1) + ") + " + itemList.get(i).getName() + " x" + itemList.get(i).getQuantity());
				}

				//Take the user's input again
				response = input.next().charAt(0);
				//Convert the input to an int value
				responseNum = Character.getNumericValue(response);
				//If the item chosen is a Potion
				if (itemList.get(responseNum - 1).getClass() == Potion.class) {
					//Increment health by the Potion's healthRestore
					health = health + itemList.get(responseNum - 1).getHealthRestore();
					//Increment mana by the Potion's manaRestore
					mana = mana + itemList.get(responseNum - 1).getManaRestore();
					System.out.println("You restored " + itemList.get(responseNum - 1).getHealthRestore() + " health and " + itemList.get(responseNum - 1).getManaRestore() + " mana.");

					//If the quantity of the item was 1 before we used it,
					if (itemList.get(responseNum - 1).getQuantity() == 1)
						//Remove it from the itemList
						itemList.remove(responseNum - 1);
					//Otherwise
					else
						//Decrease the quantity by one
						itemList.get(responseNum - 1).decreaseQuantity(1);
				}
			}

			//Else if the user chose Run Away
			else if (response == '5') {
				System.out.println("You successfully ran away.");
				ranAway = true;
				return;
			}

			//If none of the previous choices were selected, the user input an invalid choice
			else
				System.out.println("Invalid input.");
		}

	}

	/**
	 * Method to take items from the Monster.
	 *
	 * @param theMonster the Monster to take items from
	 */
	public void takeItems(Monster theMonster) {
		//For every item in theMonster's item list
		for (int i = 0; i < theMonster.itemList.size(); i++) {
			//itemAdded is a boolean variable for use in the inner for loop, initialized 
			//to false for every item in theMonster's itemList.
			//Necessary because of the inner for loop (checking if the item we're taking
			//is already in the itemList of the GameCharacter and can just have an updated 
			//quantity). If we iterate through the for loop and still haven't added the item, 
			//that means the item is a new item, but still needs to be added to the itemList.
			boolean itemAdded = false;
			//If this GameCharacter's item list is not empty and theMonster's item list is not empty
			if (!this.itemList.isEmpty() && !theMonster.itemList.isEmpty()) {
				//Then for every item in this GameCharacter's item list
				for (int j = 0; j < this.itemList.size(); j++) {
					//If the current item being examined in theMonster's item list's name matches the
					//current item being examined in this GameCharacter's item list,
					if (theMonster.itemList.get(i).getName().equals(this.itemList.get(j).getName())) {
						//then GameCharacter's item quantity = GameCharacter's item quantity + monster's item quantity
						this.itemList.get(j).setQuantity(this.itemList.get(j).getQuantity() + theMonster.itemList.get(i).getQuantity());
						System.out.println("You found a " + theMonster.itemList.get(i).getName() + " and took it.");
						theMonster.itemList.remove(i);
						itemAdded = true;
						//And break out of the loop that is searching this GameCharacter's itemList
						break;
					}
				}
				//If we made it to the end of the for loop and never added the item,
				if(itemAdded == false) {
					//Add the item to this.itemList and remove from theMonster.itemList
					this.itemList.add(theMonster.itemList.get(i));
					System.out.println("You found a " + theMonster.itemList.get(i).getName() + " and took it.");
					theMonster.itemList.remove(i);
				} 
			} 
			//Otherwise if this GameCharacter's itemList is empty,
			else {
				//Just add the item without regard for quantities
				this.itemList.add(theMonster.itemList.get(i));
				System.out.println("You found a " + theMonster.itemList.get(i).getName() + " and took it.");
				theMonster.itemList.remove(i);
			} 
		}
	}

	/** 
	 * Getter for the Player's mana.
	 *
	 * @return the mana of the Player
	 */
	public int getMana() {
		return mana;
	} 

	/**
	 * Setter for the Player's mana.
	 *
	 * @param mana the value to set the Player's mana to
	 */
	public void setMana(int mana) {
		this.mana = mana;
	}

	/**
	 * Setter for the Player's health.
	 *
	 * @param health the value to set the Player's health to
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Getter for the Player's current xp.
	 *
	 * @return the current xp of the Player
	 */
	public int getXP() {
		return xp;
	}

	/**
	 * Setter for the Player's current xp.
	 *
	 * @param xp the value to set the Player's xp to
	 */
	public void setXP(int xp) {
		this.xp = xp;
	}

	/**
	 * Getter for the Player's max health.
	 *
	 * @return the max health of the Player
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * Getter for the Player's xp to level.
	 *
	 * @return the amount of xp needed to level up
	 */
	public int getXPToLevel() {
		return xpToLevel;
	}

	/**
	 * Method to check whether or not the Player can level up.
	 *
	 * @return true if the Player's current xp is greater than or equal to the xp needed to level up, false otherwise
	 */
	public boolean checkLevelUp() {
		if (xp >= xpToLevel)
			return true;
		else
			return false;
	}

	/**
	 * Method to enact leveling up.
	 * The Player's max health, current health, attack power, mana, and level are increased.
	 */
	public void levelUp() {
		maxHealth = maxHealth + 20;
		health = health + 20;
		attackPower = attackPower + 2;
		mana = mana + 3;
		level = level +1;
		System.out.println("You leveled up! You are level " + level + ". \n+20 health \n+2 attack power \n+3 mana");
		xp = xp - xpToLevel;
		xpToLevel = xpToLevel * 2;
	}

	/**
	 * Method to check whether or not the Player has run away.
	 *
	 * @return true if the Player chose to run away, false if not
	 */
	public boolean checkRunAway() {
		return ranAway;
	}

	/**
	 * Overridden toString method to include the mana value of the Player.
	 *
	 * @return the String representation of the Player, including mana value
	 */
	@Override
	public String toString() {
		String val = "Name: " + name + "\nHP: " + health + "\nAP: " + attackPower + "\nMana: " + mana;
		return val;
	}
}