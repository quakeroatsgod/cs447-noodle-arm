package noodlearm;

import jig.Entity;
import jig.ResourceManager;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class Noodlearm extends StateBasedGame{
    public static final int STARTUPSTATE = 0;
    public static final int PLAYINGSTATE  = 1;
    public static final int GAMEOVERSTATE  = 2;

    public final int ScreenWidth;
    public final int ScreenHeight;

    Client client;
    Server server;
    public ArrayList<Grid> grid;
    public Player player;
    //Resource strings
    public static final String STARTUP_SCREEN_RES = "noodlearm/res/";
    public static final String GAMEOVER_SCREEN_RES = "noodlearm/res/";
    public static final String BLANK_RES = "noodlearm/res/img/blank.png";
    public static final String WALL_RES = "noodlearm/res/img/wall.png";
    public static final String SWORD_RES = "noodlearm/res/img/sword.png";
    public static final String SPEAR_RES = "noodlearm/res/img/club.png";
    public static final String CLUB_RES = "noodlearm/res/img/spear.png";
    public static final String KNIGHT_FORWARD_RES = "noodlearm/res/img/knight.png";

    /*
    * Creates the Noodle Arm game frame.
    */
    public Noodlearm(String title, int width, int height){
        super(title);
        ScreenHeight = height;
        ScreenWidth = width;
        Entity.setCoarseGrainedCollisionBoundary(Entity.AABB);
        grid = new ArrayList<Grid>(50);

        // below is for networking testing

        // create and start server thread
        server = new Server();
        server.start();

        // create and start client thread
        client = new Client();
        client.start();
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(new StartupState());
        addState(new PlayingState());
        addState(new GameoverState());

        //Preload resources here
        //TODO
        // ResourceManager.loadImage(STARTUP_SCREEN_RES);
        // ResourceManager.loadImage(GAMEOVER_SCREEN_RES);
        ResourceManager.loadImage(BLANK_RES);
        ResourceManager.loadImage(WALL_RES);
        ResourceManager.loadImage(SWORD_RES);
        ResourceManager.loadImage(SPEAR_RES);
        ResourceManager.loadImage(CLUB_RES);
        ResourceManager.loadImage(KNIGHT_FORWARD_RES);

    }

    public static void main(String[] args){
        AppGameContainer app;
        try{
            app = new AppGameContainer((new Noodlearm("Noodle Arm", 600, 800)));
            app.setDisplayMode(600,800,false);
            Entity.antiAliasing=false;
            app.setVSync(true);
            app.start();
        } catch(SlickException e){
            e.printStackTrace();
        }
    }
}