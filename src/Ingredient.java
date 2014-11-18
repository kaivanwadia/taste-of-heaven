
public class Ingredient {
	private int IngredientID;
	private float Quantity;
	private String Name;;
	private String Unit;
	private String internalName;
	class info{
		public String Name;
		public String Department;
	}
	public String getName()
	{
		return this.Name;
	}
	public String getInternal()
	{
		return internalName;
	}
	public void setDisplay(String disp)
	{
		this.internalName=disp;
	}
}
