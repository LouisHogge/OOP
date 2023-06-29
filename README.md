# Object Oriented Programming Project

## Project Description

The aim of our game is to escape from a haunted house. You begin in room (0,0). You have to find keys in order to open doors. You will find boxes on your way. One of these boxes contain a vacuum. You will have to find this vacuum in order to beat the final boss in room (height-1, width-1). Without it, you can't escape.


- List of all commands : 
	* % Turn (right | left) : Turn the player around by 90 degrees in the specified direction.
	* % Move : Move forward to the cell located in front of the character.
	* % Take keys: Pick up all the keys in the crurent room.
	* % Open box : Open the box in the current room and take all the objects inside of it.
	* % Use lottery : Play lottery if you are in room (0,0).
	* % Drink potion : Drink the teleportation potion. It will teleport you to the lottery.
	* % Help : Display a list of all commands understood by the game, with a brief documentation.
	* % Exit : Exit the game.

- List of all objects :
	
	* Keys : You have to use them to open the doors. Each key has an ID and this ID is the same one as the ID of the door it opens. You can buy them with 500 coins but you have to have minimum 750 coins.
	
	* Boxes : They can contains coins, lives, a potion and a vacuum. You have to buy them with 500 coins. 

	* Coins: The player begins the game with 500 coins. With them, keys, boxes and lottery ticket s can be bought. You can't have less than 250 coins otherwise you'll lose.

	* Lives : The player begins the game with 3 lives. If you have none, you'll lose.

	* Potion : There's only one potion in one of the boxes in the map. Some rooms are not accessible due to a random generation of the walls, etc. We made sure that the potion is accessible in the winning way. If you have it, you can teleport yourself to room (0,0) to play the lottery machine.

	* Vacuum : There's only one vacuum in one of the boxes in the map. We also made sure that it was in the box of an accessible room. If you want to win the game, you'll have to defeat a Boss Ghost located in room(height-1, width-1). You can only do it with the vacuum.

	* Lottery machine : There's a lottery machine in room(0,0). You can play for 250 coins. You can win coins and lives.

	* Ghosts : They are not objects. They are entities. Ghost appears in a random room. There are 3 types of ghosts :
		
		* Robber ghosts : They steal you 200 coins.
		* Killer ghosts : They steal you one life.
		* Simple minded ghosts : They just stare into space and smiles.

## How to Use the Project
### Compilation
```bash
javac *.java
```
### Run
```bash
java AdventureGame
```
