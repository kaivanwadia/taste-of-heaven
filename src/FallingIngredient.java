import org.newdawn.slick.Image;


public class FallingIngredient {
    String name;
	float x;
	float y;
	float dx;
	float dy;
	/** Becomes true once the ingredient reaches the level of the top of the pot. */
	boolean potFlag;
	/** Is true if the ingredient is wanted in the current recipe */
	boolean wanted;
	
	public FallingIngredient(float x, float y, float dx, float dy, boolean wanted, String name) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.wanted = wanted;
		this.potFlag = false;
		this.name = name;
	}
}