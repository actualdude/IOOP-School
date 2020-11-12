package automobily;

import java.io.Serializable;

public abstract class AbstractVehicle implements Serializable
{
	protected String name;

	public AbstractVehicle(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public boolean equals(Object o)
	{
		// LIST VIEW CLICKABLE FIX
		if (!(o instanceof AbstractVehicle)) return false;

		AbstractVehicle comparedVehicle = (AbstractVehicle) o;
		return name.equals(comparedVehicle.name);
	}
}
