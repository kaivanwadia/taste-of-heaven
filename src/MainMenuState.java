import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {
 
    int stateID = -1;
    int mouseX = 0;
    int mouseY = 0;
    Image playGamePNG = null;
    Image creditsPNG = null;
    Image logo = null;
    MouseOverArea playGame;
    MouseOverArea credits;
 
    MainMenuState( int stateID ) {
    	this.stateID = stateID;
    }
 
    @Override
    public int getID() {
    	return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	playGamePNG = new Image("res/buttons/Play game normal.png");
    	logo = new Image("res/sprites/logo.png");
        playGame = new MouseOverArea(gc, playGamePNG, 310, 300);
        playGame.setMouseOverColor(Color.red);
        playGame.setMouseDownColor(Color.red);
        creditsPNG = new Image("res/buttons/Credits.png");
        credits = new MouseOverArea(gc, creditsPNG, 310, 420);
        credits.setMouseOverColor(Color.red);
        credits.setMouseDownColor(Color.red);
    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.white);
        g.setColor(Color.black);
        logo.draw(230, 60);
//    	g.drawString("Taste of Heaven", 330, 50);
    	playGame.render(gc, g);
    	credits.render(gc, g);
    }
 
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input mouse = gc.getInput();
        
        if (mouse.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            mouseX = mouse.getMouseX();
            mouseY = mouse.getMouseY();
            if (enterGameState(mouseX,mouseY)) {
                sbg.enterState(Main.GAME_STATE);
            } else if (enterCreditState(mouseX,mouseY)) {
                sbg.enterState(Main.CREDIT_STATE);
            }
        }
    }

    private boolean enterCreditState(int mouseX, int mouseY) {
        if ((mouseX>310 && mouseX<=510) && (mouseY>420 && mouseY<=486)) {
            return true;
        }
        return false;
    }

    private boolean enterGameState(int mouseX, int mouseY) {
        if ((mouseX>310 && mouseX<=510) && (mouseY>300 && mouseY<=366)) {
            return true;
        }
        return false;
    }
}