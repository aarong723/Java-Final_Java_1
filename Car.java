//Aaron Gold
//Final project
public class Car {

	private int year;
	private String make, model, color;
	private char option; // 'l' = lease, 'p' = purchase
	private double price;
	private boolean available;
	
	public Car (String c, int y, String mk, String md, char o, double p, boolean a) // Full-arg constructor
	{
		color = c;
		year = y;
		make = mk;
		model = md;
		option = o;
		price = p;
		available = a;
		
	}
	
	public Car() // No-arg constructor
	{
		color = "No color";
		year = 0;
		make = "no make";
		model = "no model";
		option = ' ';
		price = 0.0;
		available = false;
	}

	public void setYear(int y)
	{
		year = y;
	}
	
	public void setMake(String mk)
	{
		make = mk;
	}
	
	public void setModel(String md)
	{
		model = md;
	}
	
	public void setOption(char o)
	{
		option = o;
	}
	
	public void setPrice(double p)
	{
		price = p;
	}
	
	public void setAvailability(boolean a)
	{
		available = a;
	}
	
	public void setColor(String c)
	{
		color = c;
	}
	public int getYear()
	{
		return (year);
	}
	
	public String getMake()
	{
		return (make);
	}
	
	public String getModel()
	{
		return (model);
	}
	
	public char getOption()
	{
		return(option);
	}
	
	public boolean isAvailable()
	{
		return(available);
	}
	
	public double getPrice()
	{
		return (price);
	}
	public String getColor()
	{
		return (color);
	}
	
	public boolean equals (Car car2) // Tests if car is a duplicate value
	{
		   if ((year==car2.year) && (make.equals(car2.make)) && (model.equals(car2.model)) && (price==car2.price) 
				   && (option ==car2.option) && (available==car2.available) && color.equals(car2.color))
			   return (true);
		   else
			   return (false);
	}
	public String initialToString() // Converts variables to a sentence to present to the customer
	   {
		return String.format("%-10s %d %-10s %-10s $%,.2f", color, year, make, model, price);
	   } 

	public String toString() // Converts variables to a sentence to send back to the file
	   {
		return String.format("%-10s %d %-10s %-10s %c %10.2f %s", color, year, make, model, option, price, available);
	   } 
}
