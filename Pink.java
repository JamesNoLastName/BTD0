import greenfoot.*; 

public class Pink extends Bloons
{
    private int distance = 1;
    private int speedmultiplier = 5;
    public int hitcount = 0;
    public Pink()
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
        setLocation(getX() - (distance * speedmultiplier), getY());
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
     * Removes both the dart and the bloon upon contact. If it is a layer before red, register the hitcount and remove a layer. Also reduce speed by 1 per lost layer.
     */
    public void contact()
    {
        if(isTouching(Dart.class) && hitcount == 0)
        {
            removeTouching(Dart.class);
            setImage("YellowLoon.png");
            speedmultiplier = 4;
            hitcount++;
        }
        if(isTouching(Dart.class) && hitcount == 1)
        {
            removeTouching(Dart.class);
            setImage("GreenLoon.png");
            speedmultiplier = 3;
            hitcount++;
        }
        if(isTouching(Dart.class) && hitcount == 2)
        {
            removeTouching(Dart.class);
            setImage("BlueLoon.png");
            speedmultiplier = 2;
            hitcount++;
        }
        if(isTouching(Dart.class) && hitcount == 3)
        {
            removeTouching(Dart.class);
            setImage("RedLoon.png");
            speedmultiplier = 1;
            hitcount++;
        }
        if(isTouching(Dart.class) && hitcount == 4)
        {
            removeTouching(Dart.class);
            getWorld().removeObject(this);
        }
    }
}