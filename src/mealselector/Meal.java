package mealselector;

public class Meal
{
	private String	mealType;
	private String	mealName;
	private int		calories	= 0;
	
	
	public Meal(String type, String name, String cals)
	{
		mealType = type;
		mealName = name;
		calories = Integer.parseInt(cals);
	}
	
	
	public String getType()
	{
		return mealType;
	}
	
	
	public String getName()
	{
		return mealName;
	}
	
	
	public int getCalories()
	{
		return calories;
	}
}
