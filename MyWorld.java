import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * James
 * I love this game
 */
public class MyWorld extends World
{
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    GifImage background = new GifImage("BTD Background.gif");
    private int Score;
    private int Time = 0;
    private int TimeSyncer1 = 60;
    private int TimeSyncer2;
    private int redmultiplier = 1;
    private int bluemultiplier;
    private int greenmultiplier;
    private int yellowmultiplier;
    private int pinkmultiplier;
    
    public MyWorld()
    {    
        super(1000, 700, 1, false);
        showText("Objective: Survive for 150 seconds. Bloons progressively become more aggressive", 500, 300);
        showText("Every 15 seconds, reload speed increases. Game is meant for 1x speed", 500, 400);
        showText("Press Run to start", 500, 500);
        DartMonkey dartmonkey = new DartMonkey();
        addObject(dartmonkey, 500, 637);
        Dart dart = new Dart();
        addObject(dart, dartmonkey.getX() + 28, dartmonkey.getY() - 49);
        showScore();
        showTime();
    }
    /**
     * Sets background to pixel art stop motion (I made)
     * Randomly create Bloons (Intensity increases over time)
     * Increase the intensity of game with Bloons popped (tested with 10 trials, I believe I was a little generous with difficulty)
     * Internal clock for timer (Also used in reload)
     * Game ending method if player survives 2 and a half minutes
     */
    public void act()
    {
        setBackground(background.getCurrentImage());
        BloonGeneration();
        Intensity();
        TimeSyncing();
        TimeSyncer2++;
        if (TimeSyncer2 >= 20) {
            removeTexts();
        }
        //music();
        survived();
    }
    /*
     * Show the score in the top left
     */
    private void showScore()
    {
        showText("Bloons Popped: " + Score, 120, 25);
    }
    /*
     * Recalculate the score per Bloon popped (See Dart.class)
     */
    public void addScore(int points)
    {
        Score = Score + points;
        showText("Bloons Popped:" + points, 120, 25);
        showScore();
    }
    /*
     * Show the time in the top right
     */
    private void showTime()
    {
        showText("Time: " + Time, 900, 25);
    }
    /*
     * Every time the two values match, the second timer goes up by one. Since there are 60 frames/acts per second, TimeSyncer1 is set to 60.
     */
    private void TimeSyncing()
    {
        if (TimeSyncer2 >= TimeSyncer1)
        {
        Time++;
        TimeSyncer2 = 0;
        }
        showTime();
    }
    /*
     * Increases the intensity of the game by making certain Bloons more common. Distribution is based off of the first 5 levels of Pascal's Triangle.
     */
    public void Intensity()
    {
        if(Score >= 10 && Score < 100)
        {
            redmultiplier = 1;
            bluemultiplier = 1;
        }
        if(Score >= 100 && Score < 150)
        {
            redmultiplier = 1;
            bluemultiplier = 2;
            greenmultiplier = 1;
        }
        if(Score >= 150 && Score < 250)
        {
            redmultiplier = 1;
            bluemultiplier = 3;
            greenmultiplier = 3;
            yellowmultiplier = 1;
        }
        if(Score >= 350)
        {
            redmultiplier = 1;
            bluemultiplier = 4;
            greenmultiplier = 6;
            yellowmultiplier = 4;
            pinkmultiplier = 1;
        }
    }
    /*
     * Generates Bloons depending on the valyes listed above for each "stage". Game was tested by me only.
     */
    public void BloonGeneration()
    {
        if(Greenfoot.getRandomNumber(100) < redmultiplier)
        {
            addObject(new Red(), 1100, 64);
        }
        if(Greenfoot.getRandomNumber(100) < bluemultiplier)
        {
            addObject(new Blue(), 1100, 64);
        }
        if(Greenfoot.getRandomNumber(100) < greenmultiplier)
        {
            addObject(new Green(), 1100, 64);
        }
        if(Greenfoot.getRandomNumber(100) < yellowmultiplier)
        {
            addObject(new Yellow(), 1100, 64);
        }
        if(Greenfoot.getRandomNumber(100) < pinkmultiplier)
        {
            addObject(new Pink(), 1100, 64);
        }
    }
    /*
     * Removes the starting text within a second of running the program.
     */
    private void removeTexts()
    {
        showText("", 500, 300);
        showText("", 500, 400);
        showText("", 500, 500);
    }
    /*
     * Plays a short ambient background music after surviving 50 seconds. Meant to last until the end of the game, but I couldn't find out when to sync it. You can toggle this setting if you want to try for yourself, just change the Time variable below
     */
    private void music()
    {
        if(Time == 50 && TimeSyncer1 == TimeSyncer2)
        {
            Greenfoot.playSound("AmbientBloonTheme.mp3");
        }
    }
    /*
     * Ends the game if player survives for 2 and a half minutes. Displays cumulative score as well.
     */
    private void survived()
    {
        if(Time >= 150)
        {
            showText("You survived!", 500, 300);
            showText("Total Bloons Popped:" + Score, 500, 500);
            Greenfoot.stop();
        }
    }
}

