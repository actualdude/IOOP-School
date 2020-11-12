package automobily.enums;

public enum Fuel
{
	PETROL("Petrol"),
	DIESEL("Diesel"),
	ELECTRICITY("Electricity");

	protected String name;

	Fuel(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
