import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class AdventureGame
{
	public static void main(String[] args) throws Exception
	{
		int height = 0;
		int width = 0;
		int level =0;
		Boolean running = true;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner s = new Scanner(System.in);
		System.out.println("\nWelcome to the game " + '\n');
		System.out.println("What's your name ?" + '\n');
		String name = br.readLine();
		System.out.println("Nice to meet you, " + name +"." );	
		System.out.println("There are 2 levels:  "+ '\n');
		System.out.println("1. Easy : 3 ghosts live in the map."+'\n');
		System.out.println("2. Difficult: 6 ghosts live in the map."+'\n');
		System.out.println("Please enter your level of difficulty (1 or 2) :"+'\n');
		level = s.nextInt();
		while(level != 1 && level !=2)
		{	System.out.println("Please enter a valid level (1 or 2): ");
			level = s.nextInt();
		}

		System.out.println("Please enter the height of the map : ");
		height = s.nextInt();
		while(height < 0)
		{	System.out.println("Please enter a valid height (>0): ");
			height = s.nextInt();
		}

		System.out.println("Now enter the width : ");
		width=s.nextInt();	
		while(width < 0)
		{
			System.out.println("Please enter a valid width (>0): ");
			width = s.nextInt();
		}
		while(height ==1 && width ==1)
		{
			System.out.println("Please enter something different from 1 and 1.\n");
			System.out.println("Please enter the height of the map : ");
			height = s.nextInt();
			System.out.println("Now enter the width : ");
			width=s.nextInt();

		}

		
		boolean[][] b = new boolean[height][width];
		for(int i =0; i<height; i++)
		{
			for (int j =0; j<width; j++)
			{
				b[i][j]=true;
			}
		}
		Grid g = new Grid(b);

		World hauntedHouse = new World(width, height, g);
		hauntedHouse.worldGeneration();
		Room room1 = hauntedHouse.getGameBoardRoomXY(0,0);
		Player player = new Player(name, room1, room1.getRoomWallUp(), hauntedHouse, g);
		Random rand = new Random();
		LotteryMachine lottery = new LotteryMachine();


		Room roomRobert = hauntedHouse.getGameBoardRoomXY(rand.nextInt(height), rand.nextInt(width));
		KillerGhost robert = new KillerGhost(roomRobert, player, height, width);
		Room roomGerard = hauntedHouse.getGameBoardRoomXY(rand.nextInt(height), rand.nextInt(width));
		KillerGhost gerard = new KillerGhost(roomGerard, player, height, width);
		Room roomLouis = hauntedHouse.getGameBoardRoomXY(rand.nextInt(height), rand.nextInt(width));
		SimpleMindedGhost louis = new SimpleMindedGhost(roomLouis, player, height, width);
		Room roomHugo = hauntedHouse.getGameBoardRoomXY(rand.nextInt(height), rand.nextInt(width));
		SimpleMindedGhost hugo = new SimpleMindedGhost(roomHugo, player, height, width);
		Room roomJohnRoss = hauntedHouse.getGameBoardRoomXY(rand.nextInt(height), rand.nextInt(width));
		RobberGhost johnRoss = new RobberGhost(roomJohnRoss, player, height, width);
		Room roomGeorgesKevin = hauntedHouse.getGameBoardRoomXY(rand.nextInt(height), rand.nextInt(width));
		RobberGhost georgesKevin = new RobberGhost(roomGeorgesKevin, player, height, width);

		System.out.println("\nThanks !!! :p \n");
		System.out.println("You have to exit the map. The exit is located in the last room: room(height-1, width-1).");
		System.out.println("To do so, you will have to open doors with the keys you'll find.");
		System.out.println("Be careful because ghosts live in this map.\n");
		System.out.println("In the beginning of the game, you have 3 lives and 500 coins");
		System.out.println("You will find boxes along the way. You'll have to pay 500 coins and surprises are in them. There is also a lottery machine in room (0,0).");
		System.out.println("You can use it for 250 coins. You can win coins and lives.");
		System.out.println("You can buy keys for 500 coins if you have at least 75O coins or the vacuum.\n");
		System.out.println("Be careful with your money and your lives because you'll lose if you have 0 life or less than 250 coins (if you don't have the vacuum).\n");
		System.out.println("If some rooms are not accessible, keys are not generated for these room's doors.");
		System.out.println("Doors were generated from top to bottom and from left to right. Their ID's are in a rising order.\n");
		System.out.println("Please enter all your commands with an % beforehand. For example, % Help \n");
		System.out.println("Are you sure you want to enter this haunted house ?\nPlease enter 1 if you want to.");
		int answer = 0;
		answer =s.nextInt();
		if(answer == 1)
		{
			while(running)
			{
				Room playerRoom = player.getCurrentRoom();
				Box roomBox = playerRoom.getRoomBox();
				roomBox.toString();
				boolean yesBox= playerRoom.isThereABox();
				boolean yesKey = playerRoom.isThereAKeychain();	
				Room finalRoom = hauntedHouse.getGameBoardRoomXY(height-1, width-1);
				boolean openBox = roomBox.possibleToOpen(player);
			
				System.out.println(g);
				System.out.println(player.toString());
				



				System.out.println("Enter a new command, " + name);
				String command = br.readLine();


				if(command.equalsIgnoreCase("%" + " "+ "Exit"))
					running = false;
				if (command.equalsIgnoreCase("%" + " " + "Help"))
				{
					System.out.println('\n');
					System.out.println("Turn (right | left) : Turn in the specified direction" + '\n');
					System.out.println("Move : Move forward in your direction" + '\n');
					System.out.println("Take keys : Pick up all the keys in the current room " + '\n');
					System.out.println("Drink potion : Drink teleportation potion to go in room (0,0)" + '\n');
					System.out.println("Open box: You pay to open the box in the current room"+ '\n');
					System.out.println("Use lottery : You can play the lottery if you are in room (0,0) and if you have 250 coins\n");
					System.out.println("Exit : Exit the game\n");
					System.out.println("Help: This command"+ '\n');
				}
				if(command.equalsIgnoreCase("%" + " " + "Move"))
				{
					player.move();
					playerRoom = player.getCurrentRoom();
					if(playerRoom.getRoomPositionX() == height-1 && playerRoom.getRoomPositionY() == width-1 && player.doesPlayerHaveAVacuum() == true)
					{
						System.out.println(g);
						String out =
						System.lineSeparator() +
						"                  ###########################               " + System.lineSeparator() +
						"                  #       YOU WIN GG        #  " + System.lineSeparator() +
						"                  ###########################               " + System.lineSeparator() +
						"                                                            " + System.lineSeparator() +
						"                                                            " + System.lineSeparator();
						System.out.println(out);
						running = false;
						return;
        			}
		
					
					if(level ==1)
					{ 
						robert.ghostMove();
						louis.ghostMove();
						johnRoss.ghostMove();

						if(player.getCurrentRoom() == robert.getGhostCurrentRoom())
							robert.killerGhostAttack();
						if(player.getCurrentRoom() == louis.getGhostCurrentRoom())
						{
							System.out.println("\nOh here is louis...\n");
							louis.simpleMindedGhostAttack();
						}
						if(player.getCurrentRoom() == johnRoss.getGhostCurrentRoom())
							johnRoss.robberGhostAttack();
					}
					else
					{
						robert.ghostMove();
						gerard.ghostMove();

						louis.ghostMove();
						hugo.ghostMove();

						johnRoss.ghostMove();
						georgesKevin.ghostMove();

						if(player.getCurrentRoom() == robert.getGhostCurrentRoom())
							robert.killerGhostAttack();
						if(player.getCurrentRoom() == gerard.getGhostCurrentRoom())
							gerard.killerGhostAttack();
						if(player.getCurrentRoom() == louis.getGhostCurrentRoom())
						{
							System.out.println("\nOh here is louis...\n");
							louis.simpleMindedGhostAttack();
						}
						if(player.getCurrentRoom() == hugo.getGhostCurrentRoom())
						{
							System.out.println("\nOh here is hugo...\n");
							hugo.simpleMindedGhostAttack();
						}
						if(player.getCurrentRoom() == johnRoss.getGhostCurrentRoom())
							johnRoss.robberGhostAttack();
						if(player.getCurrentRoom() == georgesKevin.getGhostCurrentRoom())
							georgesKevin.robberGhostAttack();
					}
					
				}
				if(command.equalsIgnoreCase("%" + " "+ "Turn right"))
				{
					player.turnRight();
					if(level ==1)
					{ 
						robert.ghostMove();
						louis.ghostMove();
						johnRoss.ghostMove();

						if(player.getCurrentRoom() == robert.getGhostCurrentRoom())
							robert.killerGhostAttack();
						if(player.getCurrentRoom() == louis.getGhostCurrentRoom())
						{
							System.out.println("\nOh here is louis...\n");
							louis.simpleMindedGhostAttack();
						}
						if(player.getCurrentRoom() == johnRoss.getGhostCurrentRoom())
							johnRoss.robberGhostAttack();
					}
					else
					{
						robert.ghostMove();
						gerard.ghostMove();

						louis.ghostMove();
						hugo.ghostMove();

						johnRoss.ghostMove();
						georgesKevin.ghostMove();

						if(player.getCurrentRoom() == robert.getGhostCurrentRoom())
							robert.killerGhostAttack();
						if(player.getCurrentRoom() == gerard.getGhostCurrentRoom())
							gerard.killerGhostAttack();
						if(player.getCurrentRoom() == louis.getGhostCurrentRoom())
						{
							System.out.println("\nOh here is louis...\n");
							louis.simpleMindedGhostAttack();
						}
						if(player.getCurrentRoom() == hugo.getGhostCurrentRoom())
						{
							System.out.println("\nOh here is hugo...\n");
							hugo.simpleMindedGhostAttack();
						}
						if(player.getCurrentRoom() == johnRoss.getGhostCurrentRoom())
							johnRoss.robberGhostAttack();
						if(player.getCurrentRoom() == georgesKevin.getGhostCurrentRoom())
							georgesKevin.robberGhostAttack();
					}
				}
				if(command.equalsIgnoreCase("%" + " " + "Turn left"))
				{
					player.turnLeft();
					if(level ==1)
					{ 
						robert.ghostMove();
						louis.ghostMove();
						johnRoss.ghostMove();

						if(player.getCurrentRoom() == robert.getGhostCurrentRoom())
							robert.killerGhostAttack();
						if(player.getCurrentRoom() == louis.getGhostCurrentRoom())
						{
							System.out.println("\nOh here is louis...\n");
							louis.simpleMindedGhostAttack();
						}
						if(player.getCurrentRoom() == johnRoss.getGhostCurrentRoom())
							johnRoss.robberGhostAttack();
					}
					else
					{
						
						robert.ghostMove();
						gerard.ghostMove();

						louis.ghostMove();
						hugo.ghostMove();

						johnRoss.ghostMove();
						georgesKevin.ghostMove();

						if(player.getCurrentRoom() == robert.getGhostCurrentRoom())
							robert.killerGhostAttack();
						if(player.getCurrentRoom() == gerard.getGhostCurrentRoom())
							gerard.killerGhostAttack();
						if(player.getCurrentRoom() == louis.getGhostCurrentRoom())
						{
							System.out.println("\nOh here is louis...\n");
							louis.simpleMindedGhostAttack();
						}
						if(player.getCurrentRoom() == hugo.getGhostCurrentRoom())
						{
							System.out.println("\nOh here is hugo...\n");
							hugo.simpleMindedGhostAttack();
						}
						if(player.getCurrentRoom() == johnRoss.getGhostCurrentRoom())
							johnRoss.robberGhostAttack();
						if(player.getCurrentRoom() == georgesKevin.getGhostCurrentRoom())
							georgesKevin.robberGhostAttack();
					}
				}
				if (command.equalsIgnoreCase("%" + " " + "Open box"))
				{
					if(openBox == true)
						roomBox.emptyBox(player);
					else
						System.out.println("You are not rich enough to open the box. \n");
				}
				if(command.equalsIgnoreCase("%" + " "+ "Drink potion"))
				{
					player.teleport();

				}
				if(command.equalsIgnoreCase("%" + " " + "Take keys"))
				{
					Keychain playerKeychain = player.getPlayerKeychain();
					Keychain playerRoomKeychain = playerRoom.getRoomKeychain();
					int playerRoomKeychainSize = playerRoomKeychain.getSize();
					for(int i =0; i<playerRoomKeychainSize; i++)
					{
						Key currentKey = playerRoomKeychain.getKey(i);
						int currentKeyId = currentKey.getId();
						playerKeychain.addKeys(currentKey);
					

					} 
					playerRoomKeychain.emptyTheKeychain();
				}
	
				if(command.equalsIgnoreCase("%"+ " "+ "Use lottery"))
				{
					if(player.getCurrentRoom() == room1)
					{
						if(lottery.buyTicket(player) == true)
						{
							lottery.playLottery(player);

						}
						else
							System.out.println("You don't have enough money ! :(\n");

					}
					else
						System.out.println("You are not in Room (0,0).\n");

				}
				if(player.getPlayerLives() <= 0 || player.getPlayerCoins() < 250 && player.doesPlayerHaveAVacuum() == false)
				{
					if(player.getPlayerLives() <=0)
						System.out.println("You lost all your lives.\n");
					else
						System.out.println("You have no vacuum and not enough money. You've been expelled from this haunted house.\n");
					String out = 
					System.lineSeparator() +
					"                  ###########################               " + System.lineSeparator() +
					"                  #        GAME OVER        #  " + System.lineSeparator() +
					"                  ###########################               " + System.lineSeparator() +
					"                                                            " + System.lineSeparator() +
					"                                                            " + System.lineSeparator();

        		System.out.println(out);
				running = false;
				}
			}
		}
		else
			return;
	}
}