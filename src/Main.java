import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
    public static final int MAIN_MENU_STATE = 0;
    public static final int GAME_STATE = 1;
    public static final int CONTROL_STATE = 2;
    public static final int CREDIT_STATE = 3;


public Main() {
        super("Taste of Heaven");
        
        this.addState(new MainMenuState(MAIN_MENU_STATE));
        this.addState(new GameState(GAME_STATE));
        this.addState(new ControlState(CONTROL_STATE));
        this.addState(new CreditState(CREDIT_STATE));
        this.enterState(MAIN_MENU_STATE);
    }
 
    public static void main(String[] args) throws SlickException {
    	//BigOvenRecipe rr=APIInterface.getRandomRecipe();
    	AppGameContainer app = new AppGameContainer(new Main());
         
         app.setDisplayMode(800, 600, false);
         app.start();
    }
 
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.getState(MAIN_MENU_STATE).init(gameContainer, this);
        this.getState(GAME_STATE).init(gameContainer, this);
        this.getState(CONTROL_STATE).init(gameContainer, this);
        this.getState(CREDIT_STATE).init(gameContainer, this);
    }
}