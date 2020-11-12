package automobily.enums;

public enum  Engine
{
	V4("V4"),
	V6("V6"),
	V8("V8"),
	V10("V10"),
	V12("V12");

	private String name;

	Engine(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
