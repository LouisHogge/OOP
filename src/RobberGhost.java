import java.io.*;
import java.util.Random;

public class RobberGhost extends Ghost
{
	public RobberGhost(Room room, Player player, int height, int width)
	{
		super(room, player, height, width);

	}

	public void robberGhostAttack()
	{
		Random rand = new Random();
		int randCoins = rand.nextInt(101); //between 0 and 100
		randCoins += 100;
		super.player.spendCoins(randCoins);
		System.out.println("\nEverybody be cool! This is a robbery! Any of you f*cking pricks move and I'll execute every m*therf*ckin' last one of you. - Pulp fiction\n");
		System.out.println("The Robber Ghost is in the room, he steals you " + randCoins +  " coins."+'\n');
	}
}