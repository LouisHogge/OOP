import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import java.util.Random;

public class World
{
	private final int height;
	private final int width;
	public Room[][] gameBoard;
	private Grid g;
	 
	private static int xSolution = 0;
	private static int ySolution = 0;
	private static int heightCounter;
    private static int widthCounter;
    private static int negativeEvenIdCounter = 0;
    private static int negativeOddIdCounter = -1;
    private static int doorIdCounter = 0;
    private static int createdVacuum = 1;
    private static int createdPotion = 1;
    private static int useless = 0;
    private static Vector<Integer> trueWay = new Vector<Integer>();

    public World(int width, int height, Grid gCreated) throws GridException
	{
		this.width = width;
		this.height = height;
		widthCounter = (width-1);
		heightCounter = (height-1);
		g = gCreated;
		gameBoard = new Room[this.height][this.width];
	}

	public int getWorldHeight()
    {
    	return this.height;
    }

    public int getWorldWidth()
    {
    	return this.width;
    }

	public Room getGameBoardRoomXY(int x, int y)
	{
		return gameBoard[x][y];
	}

	public void worldGeneration() throws GridException
	{
		for (int x=0 ; x<this.height ; x++)
		{
			for (int y=0 ; y<this.width ; y++)
			{
				g.setActivated(x, y, true);
				g.setContent(x, y, " ");
				gameBoard[x][y] = new Room(x, y);
				wallGeneration(x, y); 
			}
		}
        keyGeneration();
	}

	private void wallGeneration(int x, int y) throws GridException
	{
		if (x==(this.height-1) && y==(this.width-1))
		{
			gameBoard[x][y].getRoomWallRight().assignId(negativeEvenIdCounter-=2);
			gameBoard[x][y].getRoomWallDown().assignId(negativeEvenIdCounter-=2);
		}
		else if (x==xSolution && y==ySolution)
		{
            if (createdVacuum ==1)
            {
                Random rand = new Random();
                int randomNb = rand.nextInt(10); //random between 0 and 9
                if (randomNb == 0 || x==(this.height-1) && y==(this.width-2) || x==(this.height-2) && y==(this.width-1)) //1 chance sur 10 pour que ça spawn pas trop loin non plus
                {
                    gameBoard[x][y].getRoomBox().addBoxVacuum(1);
                    createdVacuum--;
                }
            }
            if (createdPotion ==1)
            {
                Random rand = new Random();
                int randomNb = rand.nextInt(10); // random between 0 and 9
                if (randomNb == 0 || x==(this.height-1) && y==(this.width-2) || x==(this.height-2) && y==(this.width-1)) //idem
                {
                    gameBoard[x][y].getRoomBox().addBoxPotion(1);
                    createdPotion--;
                }
            }
			Random randWall = new Random();
			int randomNb = randWall.nextInt(2); //random between 0 and 1
			if (widthCounter == 0)
			{
				gameBoard[x][y].getRoomWallRight().assignId(negativeEvenIdCounter-=2);
				gameBoard[x][y].getRoomWallDown().assignId(doorIdCounter+=1);
				g.setWall(x, y, Grid.DOWN, 'o');
				xSolution=(x+1);
				heightCounter--;
			}
			else if (heightCounter == 0)
			{
				gameBoard[x][y].getRoomWallRight().assignId(doorIdCounter+=1);
				gameBoard[x][y].getRoomWallDown().assignId(negativeEvenIdCounter-=2);
				g.setWall(x, y, Grid.RIGHT, 'o');
				ySolution=(y+1);
				widthCounter--;
			}
			else if (randomNb==0)
			{
                Random randWall1 = new Random();
                int randomNb1 = randWall1.nextInt(3); //random between 0 and 2
				gameBoard[x][y].getRoomWallRight().assignId(doorIdCounter+=1);
                g.setWall(x, y, Grid.RIGHT, 'o');
                if (randomNb1==0)
                    gameBoard[x][y].getRoomWallDown().assignId(negativeEvenIdCounter-=2);
                else if (randomNb1==1)
                {
                    gameBoard[x][y].getRoomWallDown().assignId(negativeOddIdCounter-=2);
                    g.setWall(x, y, Grid.DOWN, ' ');
                }
                else
                {
                    gameBoard[x][y].getRoomWallDown().assignId(doorIdCounter+=1);
                    g.setWall(x, y, Grid.DOWN, 'o');
                }
                ySolution=(y+1);
                widthCounter--;	
            }
            else
            {
                Random randWall2 = new Random();
                int randomNb2 = randWall2.nextInt(3); //random number between 0 and 2
            	gameBoard[x][y].getRoomWallDown().assignId(doorIdCounter+=1);
            	g.setWall(x, y, Grid.DOWN, 'o');
            	if (randomNb2==0)
                    gameBoard[x][y].getRoomWallRight().assignId(negativeEvenIdCounter-=2);
                else if (randomNb2==1)
                {
                    gameBoard[x][y].getRoomWallRight().assignId(negativeOddIdCounter-=2);
                    g.setWall(x, y, Grid.RIGHT, ' ');
                }
                else
                {
                    gameBoard[x][y].getRoomWallRight().assignId(doorIdCounter+=1);
                    g.setWall(x, y, Grid.RIGHT, 'o');
                }
                xSolution=(x+1);
                heightCounter--;
            }
        }
        else if (x==(this.height-1))
        {
        	gameBoard[x][y].getRoomWallDown().assignId(negativeEvenIdCounter-=2);
        	Random rand = new Random();
			int randomNb = rand.nextInt(3); //random number between 0 and 2
        	if (randomNb==0)
        		gameBoard[x][y].getRoomWallRight().assignId(negativeEvenIdCounter-=2);
        	else if (randomNb==1)
        	{
        		gameBoard[x][y].getRoomWallRight().assignId(negativeOddIdCounter-=2);
        		g.setWall(x, y, Grid.RIGHT, ' ');
        	}
        	else
        	{
        		gameBoard[x][y].getRoomWallRight().assignId(doorIdCounter+=1);
        		g.setWall(x, y, Grid.RIGHT, 'o');
        	}
        }
        else if (y==(this.width-1))
        {
            gameBoard[x][y].getRoomWallRight().assignId(negativeEvenIdCounter-=2);
        	Random rand = new Random();
			int randomNb = rand.nextInt(3);
        	if (randomNb==0)
        		gameBoard[x][y].getRoomWallDown().assignId(negativeEvenIdCounter-=2);
        	else if (randomNb==1)
        	{
        		gameBoard[x][y].getRoomWallDown().assignId(negativeOddIdCounter-=2);
        		g.setWall(x, y, Grid.DOWN, ' ');
        	}
        	else
        	{
        		gameBoard[x][y].getRoomWallDown().assignId(doorIdCounter+=1);
        		g.setWall(x, y, Grid.DOWN, 'o');
        	}
        }
        else if (x!=0 && x!=(this.height-1) && y!=0 && y!=(this.width-1))
        {
        	Random rand1= new Random();
			int randomNb1 = rand1.nextInt(3);
			Random rand2 = new Random();
			int randomNb2 = rand2.nextInt(3);
        	if (randomNb1==0)
        		gameBoard[x][y].getRoomWallRight().assignId(negativeEvenIdCounter-=2);
        	else if (randomNb1==1)
        	{
        		gameBoard[x][y].getRoomWallRight().assignId(negativeOddIdCounter-=2);
        		g.setWall(x,y, Grid.RIGHT, ' ');
        	}
        	else
        	{
        		gameBoard[x][y].getRoomWallRight().assignId(doorIdCounter+=1);
        		g.setWall(x, y, Grid.RIGHT, 'o');
        	}
        	if (randomNb2==0)
        		gameBoard[x][y].getRoomWallDown().assignId(negativeEvenIdCounter-=2);
        	else if (randomNb2==1)
        	{
        		gameBoard[x][y].getRoomWallDown().assignId(negativeOddIdCounter-=2);
        		g.setWall(x, y, Grid.DOWN, ' ');
        	}
        	else
        	{
        		gameBoard[x][y].getRoomWallDown().assignId(doorIdCounter+=1); 
        		g.setWall(x, y, Grid.DOWN, 'o');
        	}
        }
        if (x==0)
        	gameBoard[x][y].getRoomWallUp().assignId(negativeEvenIdCounter-=2);
        else
        	gameBoard[x][y].getRoomWallUp().assignId(gameBoard[x-1][y].getRoomWallDown().getId());
        if (y==0)
        	gameBoard[x][y].getRoomWallLeft().assignId(negativeEvenIdCounter-=2);
        else
        	gameBoard[x][y].getRoomWallLeft().assignId(gameBoard[x][y-1].getRoomWallRight().getId()); 
    }
 
	private void keyGeneration()
    {
        boolean[][] keyWay = new boolean[this.height][this.width];
        for (int i=0; i<this.height; i++)
        {
            for (int j=0; j<this.width; j++)
                keyWay[i][j]=false;
        }
        int tabX[] = new int[((this.height)*(this.width))];
        int tabY[] = new int[((this.height)*(this.width))];
        int counterXY = 0;
        int randCounter = 0;
        
        for (int x=0 ; x<this.height ; x++)
        {
            for (int y=0 ; y<this.width ; y++)
            {
                boolean[][] access = new boolean[this.height][this.width];
                for (int m=0; m<this.height; m++)
                {
                    for (int n=0; n<this.width; n++)
                        access[m][n]=false;
                }
                boolean roomAccess;
                int trueWall = 0;
                trueWay.clear();
                roomAccess = isThisRoomAccessible(x, y, keyWay, access);
                if (!(trueWay.isEmpty()))
                    trueWall = trueWay.lastElement();
                int right = 0;
                int down = 0;
                Key rightId = new Key();
                Key downId = new Key();
                
                if (roomAccess==true && trueWall==1 || roomAccess==true && trueWall==4 || x==0 && y==0)
                {
                    if (keyWay[x][y] == false)
                    {
                        keyWay[x][y] = true;
                        tabX[counterXY] = x;
                        tabY[counterXY] = y;
                        counterXY++;
                        randCounter++;
                    }
                    
                    if (gameBoard[x][y].getRoomWallRight().getId() > 0)
                    {
                        right++;
                        rightId.assignId(gameBoard[x][y].getRoomWallRight().getId());
                    }
                    if (gameBoard[x][y].getRoomWallDown().getId() > 0)
                    {
                        down++;
                        downId.assignId(gameBoard[x][y].getRoomWallDown().getId());
                    }

                    for (int i=x ; i<this.height ; i++)
                    {
                        int z = 0;
                        for (int j=y ; j<this.width ; j++)
                        {
                            if (keyWay[i][j]==true)
                            {
                                if (gameBoard[i][j].getRoomWallRight().getId() % 2 ==-1)
                                {
                                    if (i==this.height-1 && j==this.width-2)
                                        useless++;
                                    else
                                    {
                                        if (keyWay[i][j+1] == false)
                                        {
                                            keyWay[i][j+1] = true;
                                            tabX[counterXY] = i;
                                            tabY[counterXY] = j+1;
                                            counterXY++;
                                            randCounter++;
                                            z++;
                                        }
                                    }
                                }
                                if (gameBoard[i][j].getRoomWallDown().getId() % 2 ==-1)
                                {
                                    if (i==this.height-2 && j==this.width-1)
                                        useless++;
                                    else
                                    {
                                        if (keyWay[i+1][j] == false)
                                        {
                                            keyWay[i+1][j] = true;
                                            tabX[counterXY] = i+1;
                                            tabY[counterXY] = j;
                                            counterXY++;
                                            randCounter++;
                                            z++;
                                        }
                                    }
                                }
                                if (gameBoard[i][j].getRoomWallUp().getId() % 2 ==-1)
                                {
                                    if (keyWay[i-1][j] == false)
                                    {
                                        keyWay[i-1][j] = true;
                                        tabX[counterXY] = i-1;
                                        tabY[counterXY] = j;
                                        counterXY++;
                                        randCounter++;
                                        i-=2;
                                        z++;
                                    }
                                }
                                else if (gameBoard[i][j].getRoomWallLeft().getId() % 2 ==-1)
                                {
                                    if (keyWay[i][j-1] == false)
                                    {
                                        keyWay[i][j-1] = true;
                                        tabX[counterXY] = i;
                                        tabY[counterXY] = j-1;
                                        counterXY++;
                                        randCounter++;
                                        j-=2;
                                        z++;
                                    }
                                }
                            }
                            if (z == 0)
                                break;
                        }
                        if (z == 0)
                            break;
                    }
                    if (randCounter > 0)
                    {
                        Random rand1 = new Random();
                        int randomNb1 = rand1.nextInt(randCounter); //random number between 0 and randCounter-1
                        int randomX1 = tabX[randomNb1];
                        int randomY1 = tabY[randomNb1];
                        Random rand2 = new Random();
                        int randomNb2 = rand2.nextInt(randCounter); //random number between 0 and randCounter-1
                        int randomX2 = tabX[randomNb2];
                        int randomY2 = tabY[randomNb2];
                        if (right==1)
                            gameBoard[randomX1][randomY1].getRoomKeychain().addKeys(rightId);
                        if (down==1)
                            gameBoard[randomX2][randomY2].getRoomKeychain().addKeys(downId);
                    }
                    else
                    {
                        if (right==1)
                            gameBoard[0][0].getRoomKeychain().addKeys(rightId);
                        if (down==1)
                            gameBoard[0][0].getRoomKeychain().addKeys(downId);
                    }
                }
                else if (roomAccess==true && trueWall==2 || roomAccess==true && trueWall==3)
                {
                    if (gameBoard[x][y].getRoomWallRight().getId() > 0)
                    {
                        right++;
                        rightId.assignId(gameBoard[x][y].getRoomWallRight().getId());
                    }
                    if (gameBoard[x][y].getRoomWallDown().getId() > 0)
                    {
                        down++;
                        downId.assignId(gameBoard[x][y].getRoomWallDown().getId());
                    }

                    if (randCounter > 0)
                    {
                        Random rand3 = new Random();
                        int randomNb3 = rand3.nextInt(randCounter); //random number between 0 and randCounter-1
                        int randomX3 = tabX[randomNb3];
                        int randomY3 = tabY[randomNb3];
                        Random rand4 = new Random();
                        int randomNb4 = rand4.nextInt(randCounter); //random number between 0 and randCounter-1
                        int randomX4 = tabX[randomNb4];
                        int randomY4 = tabY[randomNb4];
                        if (right==1)
                            gameBoard[randomX3][randomY3].getRoomKeychain().addKeys(rightId);
                        if (down==1)
                            gameBoard[randomX4][randomY4].getRoomKeychain().addKeys(downId);
                    }
                    else
                    {
                        if (right==1)
                            gameBoard[0][0].getRoomKeychain().addKeys(rightId);
                        if (down==1)
                            gameBoard[0][0].getRoomKeychain().addKeys(downId);
                    }
                }
            }
        }
    }

    private boolean isThisRoomAccessible(int x, int y, boolean[][] keyWay, boolean[][] access)
    {
        boolean b1 = false;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        if (gameBoard[x][y].getRoomWallUp().getId() % 2 ==-1 && x!=0 && access[x-1][y] != true || gameBoard[x][y].getRoomWallUp().getId() > 0 && x!=0 && access[x-1][y] != true)
        {
            access[x][y] = true;
            if (keyWay[x-1][y] == true)
            {
                b1 = true;
                trueWay.add(1);
            }
            else
            {
                b1 = isThisRoomAccessible(x-1, y, keyWay, access);
                if (b1 == true)
                    trueWay.add(1);
            }
        }
        if (gameBoard[x][y].getRoomWallRight().getId() % 2 ==-1 && y!=(this.width-1) && access[x][y+1] != true || gameBoard[x][y].getRoomWallRight().getId() > 0 && y!=(this.width-1) && access[x][y+1] != true)
        {
            if (x==this.height-1 && y==this.width-2)
                useless++;
            else
            {
                access[x][y] = true;
                if (keyWay[x][y+1] == true)
                {
                    b2 = true;
                    trueWay.add(2);
                }
                else
                {
                    b2 = isThisRoomAccessible(x, y+1, keyWay, access);
                    if (b2 == true)
                        trueWay.add(2);
                }
            }
        }
        if (gameBoard[x][y].getRoomWallDown().getId() % 2 ==-1 && x!=(this.height-1) && access[x+1][y] != true || gameBoard[x][y].getRoomWallDown().getId() > 0 && x!=(this.height-1) && access[x+1][y] != true)
        {
            if (x==this.height-2 && y==this.width-1)
                useless++;
            else
            {
                access[x][y] = true;
                if (keyWay[x+1][y] == true)
                {
                    b3 = true;
                    trueWay.add(3);
                }
                 else
                 {
                    b3 = isThisRoomAccessible(x+1, y, keyWay, access);
                    if (b3 == true)
                        trueWay.add(3);
                }
            }
        }
        if (gameBoard[x][y].getRoomWallLeft().getId() % 2 ==-1 && y!=0 && access[x][y-1] != true || gameBoard[x][y].getRoomWallLeft().getId() > 0 && y!=0 && access[x][y-1] != true)
        {
            access[x][y] = true;
            if (keyWay[x][y-1] == true)
            {
                b4 = true;
                trueWay.add(4);
            }
            else
            {
                b4 = isThisRoomAccessible(x, y-1, keyWay, access);
                if (b4 == true)
                    trueWay.add(4);
            }
        }
        if (b1 == true || b2 == true || b3 == true || b4 == true)
            return true;
        else
            return false;
    }
}