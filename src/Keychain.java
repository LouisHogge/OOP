import java.util.Vector;

public class Keychain
{
	//The player has a keychain that can contain the exact amount of keys in the game.

	private Vector<Key> keychain = new Vector<Key>();

	public Keychain()
	{
	}

	public void addKeys(Key k)
	{ 
		keychain.add(k);
	}

	public void deleteKey(Key k)
	{
		keychain.remove("k");
	}

	public int getSize()
	{
		return keychain.size();
	}

	// This function checks if the keychain is empty and returns a boolean: returns true if it s empty
	public boolean emptyKeychain()
	{
		return keychain.isEmpty();
	}

	public Key getKey(int x)
	{
		return keychain.elementAt(x);
	}

	// This function empties the keychain.
	public void emptyTheKeychain()
	{
		keychain.clear();
	}
}