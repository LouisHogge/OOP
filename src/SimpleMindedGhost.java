import java.io.*;
import java.util.Random;

public class SimpleMindedGhost extends Ghost
{
	public SimpleMindedGhost(Room room, Player player, int height, int width)
	{
		super(room, player, height, width);
	}

	public void simpleMindedGhostAttack()
	{
			System.out.println("The Simple-minded Ghost is in the room, he stares into space and smiles like an idiot."+'\n');
	}
}