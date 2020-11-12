package automobily;

import automobily.enums.Color;
import automobily.enums.Engine;
import automobily.enums.Fuel;

public class Car extends AbstractVehicle
{
	private Engine engine;
	private Fuel fuel;
	private Color color;

	public Car(String name)
	{
		super(name);
	}

	public Car(String name, Engine engine, Fuel fuel, Color color)
	{
		this(name);
		this.engine = engine;
		this.fuel = fuel;
		this.color = color;
	}

	@Override
	public String toString()
	{
		return "Name: "+name+" Engine: "+engine.toString()+" Fuel: "+fuel.toString()+" Color: "+color.toString();
	}

	public Engine getEngine()
	{
		return engine;
	}

	public void setEngine(Engine engine)
	{
		this.engine = engine;
	}

	public Fuel getFuel()
	{
		return fuel;
	}

	public void setFuel(Fuel fuel)
	{
		this.fuel = fuel;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color)
	{
		this.color = color;
	}
}
