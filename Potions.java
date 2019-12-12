public class Potion extends Item {
	
	private int manaRestore;
	private int healthRestore;

	public Potion(String name, int quantity, int manaRestore, int healthRestore) {

		super(name, quantity);
		this.name = name;
		this.quantity = quantity;
		this.manaRestore = manaRestore;
		this.healthRestore = healthRestore;

	}

	public int getManaRestore() {
		return manaRestore;
	} 

	public int getHealthRestore() {
		return healthRestore;
	} 

} 