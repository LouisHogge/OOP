import java.util.Vector;
import java.util.Random;

public class Room
{
	// A room is an object capable of containing a player, a box, a lottery machine, keys, ghosts, walls and doors.

	private int positionX;
	private int positionY;
	private Keychain roomKeychain = new Keychain(); 
	private Box roomBox = new Box(); 
	private LotteryMachine roomLottery;
	private Wall[] TabWall = new Wall[4];	

	public Room(int positionX, int positionY) 
	{
		for (int i=0 ; i<4 ; i++)
			TabWall[i] = new Wall();
		this.positionX = positionX;
		this.positionY = positionY;
		if (this.positionX == 0 && this.positionY == 0)
			roomLottery = new LotteryMachine(); 
	}

	public int getRoomPositionX()
	{
		return this.positionX;
	}

	public int getRoomPositionY()
	{
		return this.positionY;
	}

	public Box getRoomBox()
	{
		return roomBox;
	}

	public Keychain getRoomKeychain()
	{
		return roomKeychain;
	}

	public Wall getRoomWallUp()
	{
		return TabWall[0];
	}

	public Wall getRoomWallRight()
	{
		return TabWall[1];
	}

	public Wall getRoomWallDown()
	{
		return TabWall[2];
	}

	public Wall getRoomWallLeft()
	{
		return TabWall[3];
	}

	public boolean isThereAKeychain()
	{
		return roomKeychain.emptyKeychain(); //true if empty and false if not.
	}

	public boolean isThereABox()
	{
		if (roomBox.getBoxCoins()==0)
			return false;
		else
			return true;
	}
}