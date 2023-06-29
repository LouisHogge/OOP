import java.io.*;
import java.util.Scanner;
import java.util.Vector;
public class Player
{
	private String name;
	
	private Vacuum playerVacuum = new Vacuum();
	private Coins playerCoins = new Coins();
	private Life playerLives = new Life();
	private Potion playerPotion = new Potion();
	private Keychain playerKeychain = new Keychain();
	
	private Room currentRoom;
	private Wall currentWall;
	private World world;
	private Grid g; 
	//At the beginning of the game, the player has 500 coins and 3 lives.
	//We will ask the player to enter his name and his name will be put in the string name;

	public Player(String name, Room createdRoom, Wall createdWall, World worldCreated, Grid gCreated) throws GridException
	{
		this.name = name;
		currentRoom = createdRoom;
		currentWall = createdWall;
		world = worldCreated;
		g = gCreated;
		int x = currentRoom.getRoomPositionX();
		int y = currentRoom.getRoomPositionY();
		g.setContent(x, y, "@");
	}

	public World getWorld()
	{
		return world;
	}

	public Room getCurrentRoom()
	{
		return currentRoom;
	}

	public int getPlayerCoins()
	{
		return playerCoins.getQuantity();
	}
	
	public int getPlayerLives()
	{
		return playerLives.getQuantity();
	}
	public Keychain getPlayerKeychain()
	{
		return playerKeychain;
	}

	public boolean doesPlayerHaveAPotion()
	{
		if (playerPotion.getQuantity() == 1)
			return true;
		else
			return false;
	}

	public boolean doesPlayerHaveAVacuum()
	{
		if (playerVacuum.getQuantity() == 1)
			return true;
		else
			return false;
	}

	public void addPlayerVacuum(int v)
	{
		playerVacuum.addQuantity(v);
	}

	public void addPlayerCoins(int c)
	{
		playerCoins.addQuantity(c);

	}

	public void spendCoins(int c)
	{
		playerCoins.removeQuantity(c);

	}
	public void addPlayerLives(int l)
	{
			playerLives.addQuantity(l);
	}
	public void lifeLost(int l)
	{
		playerLives.removeQuantity(l);
	}

	public void addPlayerPotions(int p)
	{
		playerPotion.addQuantity(p);
	}

	public void turnLeft() 
	{
		if (currentWall == currentRoom.getRoomWallUp())
			currentWall = currentRoom.getRoomWallLeft();
		else if (currentWall == currentRoom.getRoomWallLeft()) 
			currentWall = currentRoom.getRoomWallDown();
		else if (currentWall == currentRoom.getRoomWallDown()) 
			currentWall = currentRoom.getRoomWallRight();
		else if (currentWall == currentRoom.getRoomWallRight()) 
			currentWall = currentRoom.getRoomWallUp();
	}
	public void turnRight() 
	{
		if (currentWall == currentRoom.getRoomWallUp()) 
			currentWall = currentRoom.getRoomWallRight();
		else if (currentWall == currentRoom.getRoomWallRight()) 
			currentWall = currentRoom.getRoomWallDown();
		else if (currentWall == currentRoom.getRoomWallDown()) 
			currentWall = currentRoom.getRoomWallLeft();
		else if (currentWall == currentRoom.getRoomWallLeft()) 
			currentWall = currentRoom.getRoomWallUp();
	}

	public void move() throws GridException
	{
		int canigo = canIGo();
		if (canigo == 0)
			System.out.println("\nThe door is locked.");
		else if (canigo == 2)
			System.out.println("\nYou are facing a wall.");
		else if (canigo == 4)
				System.out.println("The Boss Ghost terrifies you, you run to hide yourself behind the door of the previous room !"+'\n');
		else
		{
			int x = currentRoom.getRoomPositionX();
			int y = currentRoom.getRoomPositionY();
			g.setContent(x, y, " ");
			if (currentWall == currentRoom.getRoomWallUp()) 
			{
				currentRoom = world.getGameBoardRoomXY(currentRoom.getRoomPositionX()-1,currentRoom.getRoomPositionY());
				currentWall = currentRoom.getRoomWallUp();
				x = currentRoom.getRoomPositionX();
				y = currentRoom.getRoomPositionY();
				g.setContent(x, y, "@");
			}
			else if (currentWall == currentRoom.getRoomWallRight()) 
			{
				currentRoom = world.getGameBoardRoomXY(currentRoom.getRoomPositionX(),currentRoom.getRoomPositionY()+1);
				currentWall = currentRoom.getRoomWallRight();
				x = currentRoom.getRoomPositionX();
				y = currentRoom.getRoomPositionY();
				g.setContent(x, y, "@");
			}
			else if (currentWall == currentRoom.getRoomWallDown()) 
			{
				currentRoom = world.getGameBoardRoomXY(currentRoom.getRoomPositionX()+1,currentRoom.getRoomPositionY());
				currentWall = currentRoom.getRoomWallDown();
				x = currentRoom.getRoomPositionX();
				y = currentRoom.getRoomPositionY();
				g.setContent(x, y, "@");
			}
			else if (currentWall == currentRoom.getRoomWallLeft()) 
			{
				currentRoom = world.getGameBoardRoomXY(currentRoom.getRoomPositionX(),currentRoom.getRoomPositionY()-1);
				currentWall = currentRoom.getRoomWallLeft();
				x = currentRoom.getRoomPositionX();
				y = currentRoom.getRoomPositionY();
				g.setContent(x, y, "@");
			}
			if (canigo == 3)
				System.out.println("\nThe way is clear, you move forward.");
			else if (canigo == 1)
				System.out.println("\nThe door is unlocked, you move forward.");
			else if (canigo == 5)
				System.out.println("You vacuumed the Boss Ghost up, well done ! You can now escape from the haunted house !"+'\n');
		}
	}

	private int canIGo() throws GridException
	{
		//every wall with negative even ID does not have doors 
		if (currentWall.getId() <0 && currentWall.getId() % 2 ==0)
			return 2;
		//every wall with negative odd ID has a hole to go through it
		else if (currentWall.getId() % 2 ==-1)
		{
			if (currentRoom.getRoomPositionX() == world.getWorldHeight()-1 && currentRoom.getRoomPositionY() == world.getWorldWidth()-2 && currentWall == currentRoom.getRoomWallRight() || currentRoom.getRoomPositionX() == world.getWorldHeight()-2 && currentRoom.getRoomPositionY() == world.getWorldWidth()-1 && currentWall == currentRoom.getRoomWallDown())
			{
				//If the player tries to enter the last room, he has to defeat the Boss Ghost
				System.out.println("\nThe way is clear and now the Boss Ghost is in front of you, if you want to escape from the haunted house you have to defeat him..."+'\n');
				if (doesPlayerHaveAVacuum())
					return 5;
				else
					return 4;
			}
			else
				return 3;
		}
		//every wall with positive ID does have doors
		else 
		{
			int taille = playerKeychain.getSize();
			for (int i=0 ; i<taille ; i++)
			{
				Key key = playerKeychain.getKey(i);
				if (key.getId() == currentWall.getId())
				{
					if (currentRoom.getRoomPositionX() == world.getWorldHeight()-1 && currentRoom.getRoomPositionY() == world.getWorldWidth()-2 && currentWall == currentRoom.getRoomWallRight() || currentRoom.getRoomPositionX() == world.getWorldHeight()-2 && currentRoom.getRoomPositionY() == world.getWorldWidth()-1 && currentWall == currentRoom.getRoomWallDown())
					{
						//If the player tries to enter the last room, he has to defeat the Boss Ghost
						g.setContent(currentRoom.getRoomPositionX(), currentRoom.getRoomPositionY(),"@");
						System.out.println("\nThe door is unlocked and now the Boss Ghost is in front of you, if you want to escape from the haunted house you have to defeat him..."+'\n');
						if (doesPlayerHaveAVacuum())
							return 5;
						else
							return 4;
					}
					else
						return 1;
				}		
			}
			System.out.println("\nYou don't have the right key to open this door.\nIf you want and have at least 750 coins or the vacuum you can pay 5OO coins to get the key.");
			System.out.println("\nDo you want to buy the key ?");
			System.out.println("\n1. Yes");
			System.out.println("\n2. No");
			System.out.println("\nPlease enter your choice (1 or 2) :");
			Scanner s = new Scanner(System.in);
			int buy = s.nextInt();
			while (buy != 1 && buy !=2)
			{	
				System.out.println("\nPlease enter a valid choice (1 or 2): ");
				buy = s.nextInt();
		    }
		    if (buy==1)
		    {
		    	if (getPlayerCoins() >= 750 || getPlayerCoins() >= 500 && doesPlayerHaveAVacuum() == true)
		    	{
		    		spendCoins(500);
		    		Key doorId = new Key();
		    		doorId.assignId(currentWall.getId());
		    		playerKeychain.addKeys(doorId);
		    		if (currentRoom.getRoomPositionX() == world.getWorldHeight()-1 && currentRoom.getRoomPositionY() == world.getWorldWidth()-2 && currentWall == currentRoom.getRoomWallRight() || currentRoom.getRoomPositionX() == world.getWorldHeight()-2 && currentRoom.getRoomPositionY() == world.getWorldWidth()-1 && currentWall == currentRoom.getRoomWallDown())
		    		{
		    			//If the player tries to enter the last room, he has to defeat the Boss Ghost
		    			System.out.println("\nThe way is clear and now the Boss Ghost is in front of you, if you want to escape from the haunted house you have to defeat him..."+'\n');
		    			if (doesPlayerHaveAVacuum())
		    				return 5;
		    			else
		    				return 4;
		    		}
		    		else
		    			return 1;
		    	}
		    	else
		    	{
		    		System.out.println("You are not rich enough to buy the key. \n");
		    		return 0;
		    	}
		    }
		    else
		    	return 0;
		}
	}

	public void teleport() throws GridException
	{
		
		if(doesPlayerHaveAPotion())
		{
			int x = currentRoom.getRoomPositionX();
			int y = currentRoom.getRoomPositionY();
			g.setContent(x, y," ");
			currentRoom = world.getGameBoardRoomXY(0, 0);
			currentWall = currentRoom.getRoomWallUp();
			System.out.println("\nYou teleported yourself to the lottery machine in room (0,0).\n");
			g.setContent(0, 0, "@");
		}
		else
			System.out.println("\nYou don't have any potion.\n");
	}
	public String toString()
	{

		Box roomBox = currentRoom.getRoomBox();
		boolean yesBox = currentRoom.isThereABox();
		boolean emptyBox = roomBox.isEmpty();
		Keychain roomKeychain = currentRoom.getRoomKeychain();
		boolean yesKeychain = currentRoom.isThereAKeychain();
		boolean emptyKeychain = roomKeychain.emptyKeychain();
		int roomKeychainSize = roomKeychain.getSize();
		int height = world.getWorldHeight();
		int width = world.getWorldWidth();
		Room finalRoom = world.getGameBoardRoomXY(height-1, width-1);
		Room lotteryRoom = world.getGameBoardRoomXY(0,0);

		String out = "\n====="+ this.name + "=====\n";
		out += "You are in : \n";
		int positionX = currentRoom.getRoomPositionX();
		int positionY = currentRoom.getRoomPositionY();
	
		out += "(" + positionX + "," + positionY + ")\n";
        out += "\n====="+ "Player backpack content" + "=====\n";
        out += "Coins : " + playerCoins.getQuantity() + "\n";
        out += "Lives : " + playerLives.getQuantity() + " \n";
        out += "Potion : " + playerPotion.getQuantity() +"\n";
        out += "Vacuum : " + playerVacuum.getQuantity() +"\n";
        out += "Keys : " + playerKeychain.getSize() +"\n";
        
        if(playerKeychain.getSize() != 0)
        {
        	out += "\n====="+ " Player's key ID's" + "=====\n";
        	for(int i =0; i<playerKeychain.getSize(); i++)
        	{
        		Key currentKey = playerKeychain.getKey(i);
        			out += "Key " + currentKey.getId() +"\n";	
        	}
        }
        
        int orientation = 0;
        if (currentWall == currentRoom.getRoomWallUp())
        {
			orientation= 0;
			out += "\nOrientation : up\n";
        }
		else if (currentWall == currentRoom.getRoomWallRight()) 
		{
			orientation= 1;
			out += "\nOrientation : right\n";
		}
		else if (currentWall == currentRoom.getRoomWallDown()) 
		{
			orientation= 2;
			out += "\nOrientation : down \n";
		}
		else if (currentWall == currentRoom.getRoomWallLeft()) 
		{
			orientation = 3;
			out += "\nOrientation : left\n";
		}

        out += "\n";

        if(currentRoom != finalRoom)
        {
        	if(yesBox == true && emptyBox ==false)
        	{
        		out+="There is a box on the floor.\n";
        		out+=roomBox.toString();
        	}
        	else
        	{
        		out+="The box has already been emptied.\n";
        	}

        	if(yesKeychain == false && emptyKeychain == false)
        	{
        		if(roomKeychainSize == 1)
        		{
        			Key currentKey = roomKeychain.getKey(0);
        			out += "\nThere is a key on the floor.\n";
        			out += "\n====="+ "Key ID" + "=====\n";
        			out += "Key "+currentKey.getId()+"\n";
        		}
        		else
        		{
        			out += "\nThere are " + roomKeychainSize + " keys on the floor.\n";

        			out += "\n====="+ "Key ID's" + "=====\n";
        			for(int i =0; i < roomKeychainSize; i++)
        			{
          				Key currentKey = roomKeychain.getKey(i);
        				out += "Key " + currentKey.getId() +"\n";
        			}
        		}
        	}
        	else
        		out += "\nThere is no key in the room.\n";
        	if(currentRoom == lotteryRoom)
        		out += "\nThere is a lottery machine. $_$\n";
        }
		return out;
	}
}