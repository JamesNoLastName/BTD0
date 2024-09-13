import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DartMonkey here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DartMonkey extends Actor
{
    private int ReloadThreshold = 60;      // The first component of the internal clock for dart reload
    private int ReloadPotential;           // The second component of the internal clock for dart reload
    private int TimeSyncer1 = 60;          // The first component of the internal clock for reload speed
    private int TimeSyncer2;               // The second component of the internal clock for reload speed
    private int FasterBlaster = 1;         // The third component of the internal clock for reload speed
    public DartMonkey()
    {
         
    }
    /**
     * Act - The DartMonkey will be able to move side to side, reload darts, and gradually increase its reload speed. it will also be able to detect if it touches a Bloon. If it does, its bloodline goes extinct.
     */
    public void act()
    {
        Movement();
        GameOver();
        ReloadPotential++;
        TimeSyncer2++;
        fire();
        InternalClock();
        MoreReload();
    }
    /*
     * The DartMonkey moves left or right
     */
    public void Movement()
    {
        if(Greenfoot.isKeyDown("left") && getX() >= 48)
        {
            setLocation(getX()-5, getY());
        }
        if(Greenfoot.isKeyDown("right") && getX() <= 952)
        {
            setLocation(getX()+5, getY());
        }
    }
    /*
     * The DartMonkey reloads a dart in the position relative to its right hand after a criteria for reloading is reached.
     */
    private void fire() 
    {
        if (ReloadPotential >= ReloadThreshold) 
        {
            Actor otherObject = (Actor)getWorld().getObjects(DartMonkey.class).get(0); 
            Dart dart = new Dart();
            int MonkeyX = otherObject.getX(); 
            int MonkeyY = otherObject.getY(); 
            getWorld().addObject(new Dart(),MonkeyX + 28, MonkeyY - 49);
            ReloadPotential = 0;   // time since last shot fired
        }
    }
    /*
     * The DartMonkey loses the game if it touches a Bloon.
     */
    private void GameOver(){
        if(isTouching(Bloons.class))
        {
            Greenfoot.stop();
            getWorld().showText("Game over... nobody besides the Bloons will know that you ever existed.", 500, 350);
        }
    }
    /*
     * The clock that determines when reload speed automatically increases.
     */
    private void InternalClock()
    {
        if(TimeSyncer2 >= TimeSyncer1)
        {
            FasterBlaster++;
            TimeSyncer2 = 0;
        }
    }
    /*
     * Increases the reload speed whenever the clock reaches a certain threshold.
     */
    private void MoreReload()
    {
        if (FasterBlaster % 16 == 0 && ReloadThreshold >= 7)
        {
            ReloadThreshold = ReloadThreshold - 9;
            FasterBlaster++;
        }
    }
}
