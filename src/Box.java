import java.util.Random;

public class Box 
{
	// a box can contain items : a random number of coins (between 300 and 800) and 0 or 1 life (with a 10% probability of winning) 
	// it can also contain a vacuum (only one box has it in it) and a teleportation potion (also in only one box in the entire map).
	// You have to pay 500 coins to open a box. When you open it, it adds the quantity to the lives of the player and to his coins
	private Vacuum boxVacuum = new Vacuum();
	private Coins boxCoins = new Coins();
	private Potion boxPotion = new Potion();
	private Life boxLives = new Life();
	private Coins boxPrice = new Coins();

	
	public Box()
	{
		Random rand = new Random();
		int randLives = rand.nextInt(10); // from 0 to 9
		int randCoins = rand.nextInt(501);
		randCoins += 300;
		boxLives.removeQuantity(3);
		boxCoins.removeQuantity(500);
		if (randLives == 0) //10% probability of finding a life in a box.
			boxLives.addQuantity(1);
		boxCoins.addQuantity(randCoins);
	}
	// This function checks if the player is rich enough to open a box
	public boolean possibleToOpen(Player p)
	{
		int playerCoins = p.getPlayerCoins();

		if (playerCoins >= boxPrice.getQuantity())
			return true;
		else 
			return false;
	}
	// This function empties the box and adds its content to the player's backpack.
	public void emptyBox(Player p)
	{
		p.spendCoins(boxPrice.getQuantity());
		p.addPlayerCoins(boxCoins.getQuantity());
		p.addPlayerLives(boxLives.getQuantity());
		p.addPlayerPotions(boxPotion.getQuantity());
		p.addPlayerVacuum(boxVacuum.getQuantity());

		boxLives.removeQuantity(boxLives.getQuantity());
		boxCoins.removeQuantity(boxCoins.getQuantity());
		boxPotion.removeQuantity(boxPotion.getQuantity());
		boxVacuum.removeQuantity(boxVacuum.getQuantity());
	}

	public void addBoxVacuum(int v)
	{
		boxVacuum.addQuantity(v);
	}

	public void addBoxPotion(int p)
	{
		boxPotion.addQuantity(p);
	}

	public int getBoxCoins()
	{
		return boxCoins.getQuantity();
	}

	public boolean isEmpty()
	{
		if (boxLives.getQuantity() ==0 && boxPotion.getQuantity() ==0 && boxCoins.getQuantity() ==0)
			return true;
		else
			return false;
	}
	public String toString()
	{
		String out = "\n====="+ "Box content" + "=====\n";
		out += "Coins : "+ boxCoins.getQuantity() +"\n";
		out += "Lives : " + boxLives.getQuantity() + "\n";
		out+= "Potion : " + boxPotion.getQuantity() +"\n";
		out += "Vacuum : " + boxVacuum.getQuantity() +"\n";
		return out;
	}
}