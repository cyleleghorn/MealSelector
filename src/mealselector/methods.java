package mealselector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class methods
{
	public static boolean checkFile()
	{
		File file = new File("meals.txt");
		return file.exists();
	}
	
	
	public static void createFile()
	{
		try
		{
			File file = new File("meals.txt");
			FileWriter fw = new FileWriter(file);
			BufferedWriter bfw = new BufferedWriter(fw);
			
			bfw.write("1200");
			bfw.newLine();
			bfw.write("50,50");
			bfw.newLine();
			bfw.write("Lunch,Hamburgers,800");
			bfw.newLine();
			bfw.write("Dinner,Spaghetti,300");
			bfw.newLine();
			bfw.write("Snack,Apple,150");
			bfw.flush();
			bfw.close();
			fw.close();
		}
		catch (Exception e)
		{
			System.out.println("I just caught that shit.  Now what??\n\n");
			e.printStackTrace();
		}
	}
	
	
	public static int readCalories()
	{
		File file = new File("meals.txt");
		int calories = 0;
		try
		{
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			calories = Integer.parseInt(bfr.readLine().trim());
			bfr.close();
			fr.close();
		}
		catch (NumberFormatException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return calories;
	}
	
	
	public static int readLowerTolerance()
	{
		File file = new File("meals.txt");
		int lower = 0;
		try
		{
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			bfr.readLine();
			lower = Integer.parseInt(bfr.readLine().split(",")[0].trim());
			bfr.close();
			fr.close();
		}
		catch (NumberFormatException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lower;
	}
	
	
	public static int readUpperTolerance()
	{
		File file = new File("meals.txt");
		int upper = 0;
		try
		{
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			bfr.readLine();
			upper = Integer.parseInt(bfr.readLine().split(",")[1].trim());
			bfr.close();
			fr.close();
		}
		catch (NumberFormatException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return upper;
	}
	
	
	public static ArrayList<Meal> readMeals()
	{
		ArrayList<Meal> meals = new ArrayList<Meal>();
		File file = new File("meals.txt");
		try
		{
			FileReader fr = new FileReader(file);
			BufferedReader bfr = new BufferedReader(fr);
			String newMeal;
			
			// Throw away the first two lines, which only contain the calorie
			// limits.
			bfr.readLine();
			bfr.readLine();
			
			while ((newMeal = bfr.readLine()) != null)
			{
				String[] newEntry = newMeal.split(",");
				meals.add(new Meal(newEntry[0].trim(), newEntry[1].trim(), newEntry[2].trim()));
			}
			
			bfr.close();
			fr.close();
		}
		catch (NumberFormatException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return meals;
	}
	
	
	public static void pickMeals(ArrayList<Meal> meals, int calorieLimit, int lowerCalTol, int upperCalTol)
	{
		ArrayList<Meal> lunches = new ArrayList<Meal>();
		ArrayList<Meal> dinners = new ArrayList<Meal>();
		ArrayList<Meal> snacks = new ArrayList<Meal>();
		
		for (int x = 0; x < meals.size(); x++)
		{
			if (meals.get(x).getType().equalsIgnoreCase("Lunch"))
			{
				lunches.add(meals.get(x));
			}
			else if (meals.get(x).getType().equalsIgnoreCase("Dinner"))
			{
				dinners.add(meals.get(x));
			}
			else if (meals.get(x).getType().equalsIgnoreCase("Snack"))
			{
				snacks.add(meals.get(x));
			}
			else
			{
				JOptionPane.showMessageDialog(MealSelectorGUI.frmKellysMealPicker,
						"Unknown meal type on line " + (x + 3) + " of meals.txt!", "Error with the meals!",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
		boolean acceptable = false;
		Meal lunch = null;
		Meal dinner = null;
		Meal snack = null;
		int total = 0;
		int ticker = 0;
		
		while (!acceptable && ticker < 100000)
		{
			lunch = lunches.get((int) Math.round(Math.random() * (lunches.size() - 1)));
			dinner = dinners.get((int) Math.round(Math.random() * (dinners.size() - 1)));
			snack = snacks.get((int) Math.round(Math.random() * (snacks.size() - 1)));
			
			total = lunch.getCalories() + dinner.getCalories() + snack.getCalories();
			if (total >= (calorieLimit - lowerCalTol) && total <= (calorieLimit + upperCalTol))
			{
				acceptable = true;
			}
			ticker++;
		}
		
		if (ticker < 100000)
		{
			if (total < calorieLimit)
			{
				JOptionPane.showMessageDialog(MealSelectorGUI.frmKellysMealPicker,
						"Your meals for the week are as follows:\n\n" + lunch.getName() + " for lunch, which has "
								+ lunch.getCalories() + " calories,\n" + snack.getName() + " as a snack, which has "
								+ snack.getCalories() + " calories, and\n" + dinner.getName()
								+ " for dinner, which has " + dinner.getCalories() + " calories!\n\n"
								+ "Total calories: " + total + "\n" + "You are " + (calorieLimit - total)
								+ " calories under your daily limit!  I hope you have a good week babe!",
						"Your Meals for the Week", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(MealSelectorGUI.frmKellysMealPicker,
						"Your meals for the week are as follows:\n\n" + lunch.getName() + " for lunch, which has "
								+ lunch.getCalories() + " calories,\n" + snack.getName() + " as a snack, which has "
								+ snack.getCalories() + " calories, and\n" + dinner.getName()
								+ " for dinner, which has " + dinner.getCalories() + " calories!\n\n"
								+ "Total calories: " + total + "\n" + "You are only " + (total - calorieLimit)
								+ " calories over your daily limit!  I hope you have a good week babe!",
						"Your Meals for the Week", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(MealSelectorGUI.frmKellysMealPicker,
					"The program just tried 100,000 meal combinations (yeah, really) and couldn't\nfind one that fit your caloric restrictions.. Unobtainable standards much?\nJust kidding! Try changing the tolerances or adding more meals!",
					"No combination available", JOptionPane.WARNING_MESSAGE);
		}
	}
}
