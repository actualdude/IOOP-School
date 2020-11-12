package automobily.enums;

public enum Color
{
	WHITE("White"),
	BLACK("Black"),
	RED("Red"),
	GREEN("Green"),
	BLUE("Blue"),
	ORANGE_RED("Orange red");

	protected String name;

	Color(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
