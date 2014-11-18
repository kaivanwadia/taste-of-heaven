import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.ResourceLoader;

@SuppressWarnings("deprecation")
public class GameState extends BasicGameState {
    BigOvenRecipe currentRecipe = new BigOvenRecipe();
    
	float ingredientDensity;
	float cloudDensity;
	float wantedDensity = 0.25f;
	static final float initDensity = 0.003f;
	/** Density increase per second. */
	static final float dDensity = initDensity/20;
	int stateID = -1;
	float potX, potY;
	float potMinX, potMaxX;
	float potWidth = 120, potHeight = 150;
	float screenWidth, screenHeight;
	float playWidth, playHeight;
	static final float gravity = 300;
	static final float airResistance = 0.5f;
	static final float ingredientSize = 12;
	static final float cloudVelocity = 10;
	
	public static int score;
	int currentStreak;
	int lives;
	long startTime;
	long timeLastGeneratedIngredient;
	long timeLastGeneratedCloud;
	
	private Image curImg;
	boolean gameRunning = true;
	
	ArrayList<FallingIngredient> fallingIngredients;
	ArrayList<String> listOfAllIngredients;
	ArrayList<FallingIngredient> cloudList;
	
	HashMap<String, Integer> wantedIngredients;
	HashMap<String, ArrayList<String>> informationIngredients;
	HashMap<String, Image> mapOfAllIngredients;
	
	SpriteSheet ingredientSprites;
	SpriteSheet cloudSprites;
	
	Animation char_stand, char_left, char_right, char_cry;
	int move_type;
	
	Sound chime, complete, lose, wrong;
	
	Random rn = new Random();
	
	//Stuff for end game
	int mouseX = 0;
    int mouseY = 0;
    TrueTypeFont junebug = null;
    Image mainMenuPNG = null;
    MouseOverArea mainMenu = null;
	
    GameState( int stateID ) {
    	this.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        cloudSprites = new SpriteSheet("res/sprites/clouds.png", 128, 73);
        cloudDensity = 10;
        cloudList = new ArrayList<FallingIngredient>();
        listOfAllIngredients = new ArrayList<String>();
        mapOfAllIngredients = new HashMap<String, Image>();
        initializeIngredientList();
        
        mainMenuPNG = new Image("res/buttons/Main menu.png");
        mainMenu = new MouseOverArea(gc, mainMenuPNG, 310, 400);
        mainMenu.setMouseOverColor(Color.red);
        mainMenu.setMouseDownColor(Color.red);
        
        char_cry = new Animation(
        		new Image[]{
        				new Image("res/sprites/char_sad.png")
        		},1);
        char_stand = new Animation(
        		new Image[]{
        				new Image("res/sprites/char_standing.png")
        		},1);
        char_right = new Animation(
        		new Image[] {
        				new Image("res/sprites/char_moving.png")
        		},1);
        char_left = new Animation(
        		new Image[] {
        				new Image("res/sprites/char_moving.png").getFlippedCopy(true, false)
        		},1);
        
        chime = new Sound("res/snd/chime.wav");
        complete = new Sound("res/snd/complete.wav");
        lose = new Sound("res/snd/lose.wav");
        wrong = new Sound("res/snd/wrong.wav");
        
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/junebug.ttf");
            
            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(20f);
            junebug = new TrueTypeFont(awtFont, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        screenWidth = gc.getWidth();
        screenHeight = gc.getHeight();
        playWidth = screenWidth - 300;
        playHeight = screenHeight;
        potMinX = potWidth/2+10;
        potMaxX = playWidth;
    }
    
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {        
        score = 0;
        currentStreak = 0;
        lives = 3;
        startTime = System.currentTimeMillis();
        timeLastGeneratedCloud=0;
        
        ingredientDensity = initDensity;
        fallingIngredients = new ArrayList<FallingIngredient>();
        
        timeLastGeneratedIngredient = -55555;
        initializeNewRecipe(gc, sbg);
    }
    
    private void initializeIngredientList() throws SlickException{
        ingredientSprites = new SpriteSheet("res/sprites/ingredients.png", 160, 160);
        int x=0,y=0;
        InputStream is = GameState.class.getClassLoader().getResourceAsStream("sprites/ingredients1.txt");
        BufferedReader br;
        String ing;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((ing=br.readLine()) != null) {
                ing = ing.toLowerCase();
                Image img = ingredientSprites.getSprite(x, y);
                img = img.getScaledCopy(32, 32);
                mapOfAllIngredients.put(ing, img);
                listOfAllIngredients.add(ing);
                x++;
                if (x>9) {
                    x=0;
                    y++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    
    private void initializeNewRecipe(GameContainer gc, StateBasedGame sbg) {
        currentRecipe = APIInterface.getRandomRecipe();
        //Initializing set of wanted ingredients
        wantedIngredients = new HashMap<String, Integer>();
        informationIngredients= new HashMap<String, ArrayList<String>>();
        String[] ings = currentRecipe.getIngredientTypes();
        for (String s: ings) {
            s = s.toLowerCase();
            if (wantedIngredients.containsKey(s)) {
                int value = wantedIngredients.get(s) + 1;
                wantedIngredients.put(s, value);
            } else {
                wantedIngredients.put(s, 1);
            }
        }
        for (Ingredient ing: currentRecipe.Ingredients)
        {
        	if( informationIngredients.containsKey(ing.getInternal())) {
        	    informationIngredients.get(ing.getInternal()).add(ing.getName());
        	}
        	else
        	{
        		informationIngredients.put(ing.getInternal(), new ArrayList<String>());
        		informationIngredients.get(ing.getInternal()).add(ing.getName());
        	}
        }
        potX = (potMinX+potMaxX)/2;
        potY = gc.getHeight() - potHeight/2;
        fallingIngredients = new ArrayList<FallingIngredient>();
        while (cloudList.size()<cloudDensity) {
            generateCloud(rn.nextInt(400));
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	
    	if(!gc.isPaused()){
        g.setBackground(Color.white);
        
        if (lives==0) {
        	if(gameRunning){
        		lose.play();
        	}
        	gameRunning = false;
    		char_cry.draw(potX-potWidth/2, potY-potHeight/2);
            gameEndRender(gc, sbg, g);
            return;
        }
        
        gameRunning = true;
        
    	switch(move_type){
    	case 0:
    		char_stand.draw(potX-potWidth/2, potY-potHeight/2);
    		break;
    	case 1:
    		char_left.draw(potX-potWidth/2, potY-potHeight/2);
    		break;
    	case 2:
    		char_right.draw(potX-potWidth/2, potY-potHeight/2);
    		break;
    	}
    	for(FallingIngredient cloud: cloudList) {
            Image img = cloudSprites.getSprite((int)cloud.dx, 0);
            img.draw(cloud.x, cloud.y);
        }
    	
    	for(FallingIngredient i: fallingIngredients) {
    	    Image img = mapOfAllIngredients.get(i.name);
    	    if (i.wanted) {
    	        img.draw(i.x-16, i.y-16, Color.green);
    	    } else {
    	        img.draw(i.x-16, i.y-16, Color.black);
    	    }
    	}
    	
    	//DRAW LINE TO MARK BOUNDARY
    	g.setColor(Color.black);
    	g.drawLine(playWidth+50, 0, playWidth+50, playHeight);
    	
    	}
    	else
    	{
    		float x = Math.max(this.curImg.getHeight(), this.curImg.getWidth());
    		float scale;
    		if(x==this.curImg.getWidth())
    			scale=(float)(gc.getWidth()/(1.5*x));
    		else
    			scale=(float)(gc.getHeight()/(1.5*x));
    		this.curImg.getScaledCopy(scale).drawCentered(gc.getWidth()/2, gc.getHeight()/2);
        	g.drawString(currentRecipe.Title, gc.getWidth()/2-currentRecipe.Title.length()*10, 50);
        	g.drawString("Press space to continue", 330, gc.getHeight()-20);
        	g.drawString("Your score is "+score, 315, gc.getHeight()-60);
        	return;
    	}
    		
    	
    	//RENDERING STUFF ON THE SIDE. GENERAL INFORMATION
    	g.setColor(Color.black);
    	g.drawString("SCORE: "+score, playWidth+55, 10);
    	g.drawString("STREAK:"+currentStreak, playWidth+55, 30);
    	g.drawString("LIVES:"+lives, playWidth+55, 50);
    	g.drawString("INGREDIENTS", playWidth+55, 70);
    	g.setColor(Color.blue);
    	int y = 90;
	    for(ArrayList<String> arrs: informationIngredients.values())
    	{
	    	
	    	HashMap<String, Integer> sk=new HashMap<String, Integer>();
     
	    	for(String s: arrs)	
	    	{
	    	       if (sk.containsKey(s)) {
	                   int value = sk.get(s) + 1;
	                   sk.put(s, value);
	               } else {
	            	   sk.put(s, 1);
	               }
	    	}
	    	for(String s : sk.keySet())
	    	{
	    		g.drawString(s+"  1", playWidth+55, y);
	    		y=y+20;
	    	}
    	}
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if(gc.getInput().isKeyDown(Input.KEY_SPACE) && gc.isPaused())
        {
            initializeNewRecipe(gc, sbg);
            gc.setPaused(false);
            
        }
        if(gc.isPaused())
        {
        	return;
        }
        if (wantedIngredients.isEmpty()) {
        	complete.play();
        	this.curImg = APIInterface.getImage(currentRecipe);
        	gc.pause();
        }
        if (lives==0) {
            gameEndUpdate(gc, sbg);
            return;
        }
        
        //Pot Movement
        move_type = 0;
    	if(gc.getInput().isKeyDown(Input.KEY_LEFT) || gc.getInput().isKeyDown(Input.KEY_A)) {
    		move_type = 1;
    		potX-=delta*0.4;
    		potX = Math.max(potX, potMinX);
    	} else if(gc.getInput().isKeyDown(Input.KEY_RIGHT) || gc.getInput().isKeyDown(Input.KEY_D)) {
    		move_type = 2;
    		potX+=delta*0.4;
    		potX = Math.min(potX, potMaxX);
    	}
    	
    	//GENERATING RANDOM INGREDIENT AS AND WHEN NEEDED
    	ingredientDensity+=dDensity/1000*delta;
    	if(System.currentTimeMillis() > timeLastGeneratedIngredient + 5 && 
    			Math.random()<ingredientDensity*delta) 
    		generateRandomFallingIngredient();
    	
    	if (cloudList.size()<cloudDensity && Math.abs(System.currentTimeMillis()-timeLastGeneratedCloud) > 2000) {
    	    generateCloud();
    	}
    	updateClouds(delta);
    	updateIngredientPositions(delta);
    	collideIngredient();
    }
    
    private void collideIngredient() {
        for(int n=0; n<fallingIngredients.size(); n++) {
            FallingIngredient collidingIngredient = fallingIngredients.get(n);
            if(collidingIngredient.y > playHeight) {
                fallingIngredients.remove(n);
                n--;
            } else if(collidingIngredient.y > potY) {
                if(collidingIngredient.potFlag)
                    continue;
                if(collidingIngredient.x > potX-20 && collidingIngredient.x < potX+20 && collidingIngredient.y<potY+78) {
                    if (wantedIngredients.containsKey(collidingIngredient.name)) {
                    	
                    	try{ // HACK TO GET THE GAME TO WORK  (the try catch)
                    		informationIngredients.get(collidingIngredient.name).remove(0);
                    	} catch(Exception e) {
                    		e.printStackTrace();
                    	}
                        
                    	if (wantedIngredients.get(collidingIngredient.name)==1) {
                            wantedIngredients.remove(collidingIngredient.name);
                        } else {
                            int newValue = wantedIngredients.get(collidingIngredient.name) - 1;
                            wantedIngredients.put(collidingIngredient.name, newValue);
                        }
                        score = score + 100;
                        chime.play();
                        currentStreak++;
                    } else {
                        score = score - 100;
                        wrong.play();
                        currentStreak = 0;
                        lives--;
                    }
                    
                    fallingIngredients.remove(n);
                    // Make sure the ingredients of the same type on the screen are no longer wanted
                    for (FallingIngredient ing: fallingIngredients) {
                        if (ing.name.equalsIgnoreCase(collidingIngredient.name)) {
                            ing.wanted = false;
                        }
                    }
                    n--;
                } else {
                    collidingIngredient.potFlag = true;
                }
            }
        }
    }

    private void updateIngredientPositions(int delta) {
        for(FallingIngredient i: fallingIngredients) {
            i.dx += (-airResistance*i.dx) *delta/1000;
            i.dy += (gravity - (airResistance*i.dy) )*delta/1000;
            i.x += i.dx*delta/1000;
            i.y += i.dy*delta/1000;
        }
    }

    private void generateRandomFallingIngredient() {
        int n = rn.nextInt(listOfAllIngredients.size());
    	float x = rn.nextInt((int)playWidth-40) + 40;
    	float y = 100;
    	float dx = 0; //(float)(Math.random()-0.5)*200;
    	float dy = (float)(-250);
    	boolean wanted;
    	String s = listOfAllIngredients.get(n);
    	s = s.toLowerCase();
    	if (wantedIngredients.containsKey(s)) {
            wanted = true;
        } else {
            wanted = false;
        }
    	fallingIngredients.add(new FallingIngredient(x, y, dx, dy, wanted, s));
    	timeLastGeneratedIngredient = System.currentTimeMillis();
    }
    
    private void gameEndRender(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        mainMenu.render(gc, g);
        junebug.drawString(300, 100, "YOUR SCORE IS", Color.cyan);
        junebug.drawString(350, 150, Integer.toString(score), Color.cyan);
    }
    
    private void gameEndUpdate(GameContainer gc, StateBasedGame sbg) throws SlickException {
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
        
        if ((mouseX>310 && mouseX<497) && (mouseY>400 && mouseY<=455)) {
            return true;
        }
        return false;
    }
    
    private void generateCloud() {
        cloudList.add(new FallingIngredient(playWidth, rn.nextInt(300), rn.nextInt(4), 0, false, "cloud"));
        timeLastGeneratedCloud = System.currentTimeMillis();
    }
    
    private void generateCloud(int x) {
        cloudList.add(new FallingIngredient(x, rn.nextInt(300), rn.nextInt(4), 0, false, "cloud"));
    }
    
    private void updateClouds(int delta) {
        for (int i=0;i<cloudList.size();i++) {
            FallingIngredient cloud = cloudList.get(i);
            cloud.x = cloud.x - cloudVelocity*delta/1000;
            if (cloud.x<-125) {
                cloudList.remove(i);
            }
        }
    }
}