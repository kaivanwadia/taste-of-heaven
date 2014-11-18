import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;


@SuppressWarnings("deprecation")
public class CreditState extends BasicGameState {
    
    int stateID = -1;
    int mouseX = 0;
    int mouseY = 0;
    TrueTypeFont junebug = null;
    Image mainMenuPNG = null;
    MouseOverArea mainMenu = null;
    
    public CreditState(int stateID) {
        this.stateID = stateID; 
    }
    
    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        mainMenuPNG = new Image("res/buttons/Main menu.png");
        mainMenu = new MouseOverArea(gc, mainMenuPNG, 310, 500);
        mainMenu.setMouseOverColor(Color.red);
        mainMenu.setMouseDownColor(Color.red);
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/junebug.ttf");
            
            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(20f);
            junebug = new TrueTypeFont(awtFont, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        junebug.drawString(150, 55, "TEAM MEMBERS:", Color.black);
        junebug.drawString(150, 97, "BEN AGRE", Color.black);
        junebug.drawString(150, 119, "HAITAO MAO", Color.black);
        junebug.drawString(150, 141, "CORY LI", Color.black);
        junebug.drawString(150, 163, "KAIVAN WADIA", Color.black);
        junebug.drawString(150, 205, "GAME ENGINE:", Color.black);
        junebug.drawString(150, 230, "SLICK 2D - LWJGL", Color.black);
        junebug.drawString(150, 277, "BUTTONS AND FONT:", Color.black);
        junebug.drawString(150, 300, "WWW.COOLTEXT.COM", Color.black);
        junebug.drawString(150, 350, "THE NOUN PROJECT", Color.black);
        junebug.drawString(150, 390, "BIG OVEN API", Color.black);
        mainMenu.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input mouse = gc.getInput();
        
        if (mouse.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            mouseX = mouse.getMouseX();
            mouseY = mouse.getMouseY();
            if (enterMainMenuState(mouseX,mouseY)) {
                sbg.enterState(Main.MAIN_MENU_STATE);
            }
        }
    }

    private boolean enterMainMenuState(int mouseX, int mouseY) {
        
        if ((mouseX>310 && mouseX<497) && (mouseY>500 && mouseY<=555)) {
            return true;
        }
        return false;
    }
}
