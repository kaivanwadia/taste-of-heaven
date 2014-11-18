


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BigOvenRecipe {
	public String Description;
	public int RecipeID;
	public String Title;
	public ArrayList<Ingredient> Ingredients;
	public String ImageURL;
	public String[] getIngredientNames()
	{
		String[] ret= new String[Ingredients.size()];
		int x =0;
		for(Ingredient i : Ingredients)
		{
			ret[x++]=i.getName();
		}
		return ret;
	}
	public String[] getIngredientTypes()
	{
		String[] ret= new String[Ingredients.size()];
		int x =0;
		for(Ingredient i : Ingredients)
		{
			ret[x++]=i.getInternal();
		}
		return ret;
	}
	private Boolean checkCheese(Ingredient s)
	{
		Pattern p= Pattern.compile("cheese", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(s.getName());
		if (m.find())
		{
			s.setDisplay("cheese");
			return true;
		}
		
		p= Pattern.compile("parmes", Pattern.CASE_INSENSITIVE);
		m = p.matcher(s.getName());
		if (m.find())
		{
			s.setDisplay("cheese");
			return true;
		}
		
		
		return false;

	}
	private Boolean checkBeef(Ingredient s)
	{
		Pattern p= Pattern.compile("beef", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("beef");
			return true;
		}
		
		p= Pattern.compile("steak", Pattern.CASE_INSENSITIVE);
		m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("beef");
			return true;
		}
		p= Pattern.compile("hamburger", Pattern.CASE_INSENSITIVE);
		m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("beef");
			return true;
		}
		p= Pattern.compile("london broil", Pattern.CASE_INSENSITIVE);
		m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("beef");
			return true;
		}

		
		return false;

	}
	private Boolean checkEgg(Ingredient s)
	{
		Pattern p= Pattern.compile("egg", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("eggs");
			return true;
		}
		
		return false;

	}
	private Boolean checkFlour(Ingredient s)
	{
		Pattern p= Pattern.compile("flour", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("flour");
			return true;
		}

		
		return false;

	}
	private Boolean checkButter(Ingredient s)
	{
		Pattern p= Pattern.compile("butter", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("butter");
			return true;
		}

		
		return false;

	}
	
	private Boolean checkChocolate(Ingredient s)
	{
		Pattern p= Pattern.compile("chocolate", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("chocolate");
			return true;
		}
		p= Pattern.compile("cocoa", Pattern.CASE_INSENSITIVE);
		m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("chocolate");
			return true;
		}
		
		return false;

	}
	private Boolean checkMilk(Ingredient s)
	{
		Pattern p= Pattern.compile("milk", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("milk");
			return true;
		}
		p= Pattern.compile("cream", Pattern.CASE_INSENSITIVE);
		m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("milk");
			return true;
		}
		p= Pattern.compile("juice", Pattern.CASE_INSENSITIVE);
		m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("milk");
			return true;
		}
		
		return false;

	}
	private Boolean checkSalt(Ingredient s)
	{
		Pattern p= Pattern.compile("salt", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("salt");
			return true;
		}
		return false;
	}
	private Boolean checkTomato(Ingredient s)
	{
		Pattern p= Pattern.compile("Tomato", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("tomato");
			return true;
		}
		return false;
	}
	private Boolean checkBasil(Ingredient s)
	{
		Pattern p= Pattern.compile("Basil", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("basil");
			return true;
		}
		 p= Pattern.compile("tarragon", Pattern.CASE_INSENSITIVE);
		 m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("basil");
			return true;
		}
		 p= Pattern.compile("parsley", Pattern.CASE_INSENSITIVE);
		 m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("basil");
			return true;
		}
		return false;
	}
	private Boolean checkSugar(Ingredient s)
	{
		Pattern p= Pattern.compile("Sugar", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("sugar");
			return true;
		}
		return false;
	}
	private Boolean checkBlueberry(Ingredient s)
	{
		Pattern p= Pattern.compile("blueberr", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("blueberry");
			return true;
		}
		p= Pattern.compile("blue berr", Pattern.CASE_INSENSITIVE);
		m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("blueberry");
			return true;
		}
		return false;
	}
	private Boolean checkGarlic(Ingredient s)
	{
		Pattern p= Pattern.compile("garlic", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("garlic");
			return true;
		}
		return false;
	}
	private Boolean checkOnion(Ingredient s)
	{
		Pattern p= Pattern.compile("Onion", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("Onion");
			return true;
		}
		return false;
	}
	private Boolean checkRedPepper(Ingredient s)
	{
		Pattern p= Pattern.compile("red ?pepper", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("red pepper");
			return true;
		}
		return false;
	}
	private Boolean checkJar(Ingredient s)
	{
		Pattern p= Pattern.compile("peanut ?butter", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("jar");
			return true;
		}
		p= Pattern.compile("meat ?sauce", Pattern.CASE_INSENSITIVE);
		m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("jar");
			return true;
		}
		p= Pattern.compile("spaghetti ?sauce", Pattern.CASE_INSENSITIVE);
		m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("jar");
			return true;
		}
		p= Pattern.compile(" jar ", Pattern.CASE_INSENSITIVE);
		m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("jar");
			return true;
		}
		
		return false;
	}
	private Boolean checkSpaghetti(Ingredient s)
	{
		Pattern p= Pattern.compile("spaghetti", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("spaghetti");
			return true;
		}
		return false;
	}
	private Boolean checkChicken(Ingredient s)
	{
		Pattern p= Pattern.compile("chicken", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("chicken");
			return true;
		}
		return false;
	}
	private Boolean checkHotSauce(Ingredient s)
	{
		String strry=s.getName();
		Pattern p= Pattern.compile("tabasco", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("hot sauce");
			return true;
		}
		p= Pattern.compile("Worcestershire", Pattern.CASE_INSENSITIVE);
		m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("hot sauce");
			return true;
		}
		p= Pattern.compile("hot ?sauce", Pattern.CASE_INSENSITIVE);
		m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("hot sauce");
			return true;
		}
		
		return false;
	}
	private Boolean checkWater(Ingredient s)
	{
		Pattern p= Pattern.compile("water", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("water");
			return true;
		}
		return false;
	}
	private Boolean checkPepper(Ingredient s)
	{
		Pattern p= Pattern.compile("pepper", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("pepper");
			return true;
		}
		return false;
	}
	private Boolean checkStrawberry(Ingredient s)
	{
		Pattern p= Pattern.compile("Strawberr", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("strawberry");
			return true;
		}
		return false;
	}
	
	private Boolean checkLemon(Ingredient s)
	{
		Pattern p= Pattern.compile("lemon", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("lemon");
			return true;
		}
		return false;
	}

	private Boolean checkStarch(Ingredient s)
	{
		Pattern p= Pattern.compile("starch", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("starch");
			return true;
		}
		return false;
	}

	private Boolean checkBread(Ingredient s)
	{
		Pattern p= Pattern.compile("bread", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("bread");
			return true;
		}
		return false;
	}
	private Boolean checkOil(Ingredient s)
	{
		Pattern p= Pattern.compile(" oil", Pattern.CASE_INSENSITIVE);
		String strry=s.getName();
		Matcher m = p.matcher(strry);
		if (m.find())
		{
			s.setDisplay("oil");
			return true;
		}
		return false;
	}

	public void initDisp()
	{
		
		for(Ingredient i : Ingredients)
		{ 
			if(checkCheese(i))
				continue;
			else if(checkBeef(i))
				continue;
			else if(checkButter(i))
				continue;
			else if(checkFlour(i))
				continue;
			else if(checkEgg(i))
				continue;
			else if(checkBasil(i))
				continue;
			else if(checkChocolate(i))
				continue;
			else if(checkSalt(i))
				continue;
			else if(checkTomato(i))
				continue;
			else if (checkMilk(i))
				continue;
			else if (checkChicken(i))
				continue;
			else if (checkSpaghetti(i))
				continue;
			else if (checkJar(i))
				continue;
			else if (checkSugar(i))
				continue;
			else if (checkOnion(i))
				continue;
			else if (checkRedPepper(i))
				continue;
			else if (checkBlueberry(i))
				continue;
			else if (checkGarlic(i))
				continue;
			else if(checkWater(i))
				continue;
			else if(checkLemon(i))
				continue;
			else if(checkStrawberry(i))
				continue;
			else if(checkHotSauce(i))
				continue;
			else if(checkPepper(i))
				continue;
			else if(checkOil(i))
				continue;
			else if(checkBread(i))
				continue;
			else if(checkStarch(i))
				continue;
			i.setDisplay("generic");
			
		}
	
	}
}
