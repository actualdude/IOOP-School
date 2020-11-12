package utils;

public final class IsInteger
{
	public static boolean valueOf(String input)
	{
		try {
			Integer.parseInt(input);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
}
