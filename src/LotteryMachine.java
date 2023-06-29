import java.util.Random;
public class LotteryMachine
{
	private Coins ticketPrice = new Coins();

	public LotteryMachine()
	{
		ticketPrice.removeQuantity(250);
		// The price of a lottery ticket is 250 coins.
	}
	
	// This function makes sure that the player has enough money to buy a lottery ticket.
	public boolean buyTicket(Player p)
	{
		int playerCoins = p.getPlayerCoins();

		if (playerCoins >= ticketPrice.getQuantity())
			return true;
		else 
			return false;
	}

	public void playLottery(Player p)
	{
		Random rand = new Random();
		int randLives = rand.nextInt(16); // from 0 to 15
		int randCoins = rand.nextInt(501); //from 0 to 500
		p.spendCoins(ticketPrice.getQuantity());
		p.addPlayerCoins(randCoins);
		if (randLives == 0) //6.667 percent probability of winning a life
		{
			p.addPlayerLives(1);
			System.out.println("You won one life and " + randCoins+" coins.\n");
		}
		else
			System.out.println("You won " + randCoins+" coins.\n");
	}
}