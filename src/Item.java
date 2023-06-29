abstract class Item
{
	protected int quantity;

	// Coins, vacuum, lives and potion will be items.

	protected void addQuantity(int createdQuantity)
	{
		quantity += createdQuantity;
	}
	
	protected void removeQuantity(int createdQuantity)
	{
		quantity -= createdQuantity;

	}

	protected int getQuantity()
	{
		return quantity;
	}
}