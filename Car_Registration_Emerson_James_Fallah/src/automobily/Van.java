package automobily;

import automobily.enums.Color;

public class Van extends AbstractVehicle
{
	private Color color;
	private int storageSpace;

	public Van(String name, Color color, int storageSpace)
	{
		super(name);
		this.color = color;
		this.storageSpace = storageSpace;
	}

	@Override
	public String toString()
	{
		return "Name: "+name+" Color: "+color.toString()+" Storage space: "+storageSpace+" litres";
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}

	public int getStorageSpace()
	{
		return storageSpace;
	}

	public void setStorageSpace(int storageSpace)
	{
		this.storageSpace = storageSpace;
	}
}
