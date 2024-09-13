import greenfoot.*; 

public class Red extends Bloons
{
    private int distance = 1; 
    //private int layer = 1;
    //private int killcount = 0;
    //private int RNG = Greenfoot.getRandomNumber(5);
    public Red()
    {
    
    }
    /**
     * Act - do whatever the Red wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        movement();
        movementchange();
        contact();
    }
    /*
     * Moves bloon to the left or the right, depending on the sign of distance
     */
    public void movement()
    {
        setLocation(getX() - distance, getY());
    }
    /*
     * Changes direction of Bloon movement after reaching a side of the map, and shifts it down by one level
     */
    public void movementchange()
    {
        if(getX() <= -200)
        {
            distance = -1;
            setLocation(getX(), getY() + 128);
        }
        if(getX() >= 1200) 
        {
            distance = 1;
            setLocation(getX(), getY() + 128);
        }
    }
    /*
     * Removes both the dart and the bloon upon contact.
     */
    public void contact()
    {
        if(isTouching(Dart.class))
        {
            removeTouching(Dart.class);
            getWorld().removeObject(this);
        }
    }
}



