import java.io.*;
import java.util.Random;

// There will be ghosts in the map. They can move in the entire map but can't be in room(height-1, width-1)
public class Ghost
{
	int height;
	int width;
	protected Room currentGhostRoom;
	protected Player player;

	public Ghost(Room room, Player player, int height, int width)
	{
		currentGhostRoom = room;
		this.height = height;
		this.width = width;
		this.player = player;
	}

	public void ghostMove() 
	{
		Room finalRoom = this.player.getWorld().getGameBoardRoomXY(height-1, width-1);
		Random randX= new Random();
		int randomX = randX.nextInt(height);
		Random randY = new Random();
		int randomY = randY.nextInt(width);
		while(randomX == height-1 && randomY == width-1)
		{
			randomX = randX.nextInt(height);
			randomY= randY.nextInt(width);
		}
		currentGhostRoom = this.player.getWorld().getGameBoardRoomXY(randomX, randomY);

	}
	public Room getGhostCurrentRoom()
	{
		return currentGhostRoom;
	}
}