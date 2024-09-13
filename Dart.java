import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dart extends Actor
{    
    public Dart()
    {
    }
    /**
     * Moves sideways, launches upward, disappears after contact, and deletes darts outside of map
     */
    public void act()
    {
        LateralMovement();
        Upwards();
        RemoveDart();
        OutOfBounds();
    }
    /*
     * Move sideways, and automatically upward if thrown
     */
    public void LateralMovement()
    {
        if(!Greenfoot.isKeyDown("space") && getY() >= 588 && !isTouching(Red.class))
        {
            if(Greenfoot.isKeyDown("left") && getX() >= 76)
            {
                setLocation(getX()-5, getY());
            }
            if(Greenfoot.isKeyDown("right") && getX() <= 990)
            {
                setLocation(getX()+5, getY());
            }
        }
        else if(getY() <= 1000)
        {
            setLocation(getX(), getY() - 30);
        }
    }
    /*
     * Move upwards by one (Counts as being thrown)
     */
    public void Upwards()
    {
        if(Greenfoot.isKeyDown("space"))
        { 
            setLocation(getX(), getY() -1);

        }
    }
    /*
     * Remove if in contact with a Bloon, and add score
     */
    public void RemoveDart()
    {
        if(isTouching(Bloons.class) || isTouching(Bloons.class))
        {
            removeTouching(Dart.class);
            MyWorld myworld = (MyWorld)getWorld();
            myworld.addScore(+1);
        }
    }
    /*
     * Remove if outside of visible boundaries
     */
    public void OutOfBounds()
    {
        if(getY() <= -100)
        {
            MyWorld myworld = (MyWorld)getWorld();
            myworld.removeObject(this);
        }
    }
}
