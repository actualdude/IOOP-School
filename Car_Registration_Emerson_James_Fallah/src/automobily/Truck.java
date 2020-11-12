package automobily;

public class Truck extends AbstractVehicle
{
	private int weight;
	private int height;

	public Truck(String name, int weight, int height)
	{
		super(name);
		this.weight = weight;
		this.height = height;
	}

	@Override
	public String toString()
	{
		return "Name: "+name+" Weight: "+weight+" kg Height: "+height+" cm";
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}
}
