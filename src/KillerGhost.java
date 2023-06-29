import java.io.*;
import java.util.Random;

public class KillerGhost extends Ghost
{
	public KillerGhost(Room room, Player player ,int height, int width)
	{
		super(room, player ,height, width);
	}

	public void killerGhostAttack()
	{
		Random rand = new Random();
		int randLives = rand.nextInt(4); //between 0 and 4
		int lives=0;
		if (randLives == 0) // 25% probability of losing 2 lives
		{
			lives=2;
			super.player.lifeLost(lives);
		}
		else
		{
			lives=1;
			super.player.lifeLost(lives);
		}
		System.out.println("\nOh no a killer ghost !\n");
		System.out.println("The Killer Ghost is in the room, he attacks you and takes you " + lives +" life.ves."+'\n');
	}
}