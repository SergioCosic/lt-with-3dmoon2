import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.util.List;
import java.io.*;
import java.util.*;
import java.awt.Dimension;
/**
 * Write a description of class stars here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stars extends World
{


    private String ConfigFile="crater-config.txt";
    private int numObjs;
    static private int x;
 //   public sobj[] arySpaceObject  ;
    
    
    Sphere s = new Sphere();
    private int i, yloc, nextX, flashes;
    private int maxFlashes=6;
    private int titleX=100;
    private int titleY=100;
    private int flashM;
    private boolean titleAdded=false;
    private boolean meteorsAdded=false;
    private boolean moonRumbled=false;
     private boolean moonShook=false;
     private boolean initMeteors=false;
    public boolean meteorDropping =false;
    
    private String gameTitle[]={"P","e","r","s","e","i","d","s"};
    private String gameSubTitle[]={"R","e","m","i","x"};
    private int letterX[]={89,140, 178,216,259,298,334,382};
    private int letterY[]={ 81, 107, 77, 108, 81, 105, 80,105};
    private int letterSX[]={394, 452,  516,  569,  603};
    private int letterSY[]={ 452,  485,  456, 475,  446};
    
    private int meteorX[]={335,411,263,285,362,418,310,324,365,323};
    private int meteorY[]={216,252,287, 343,350,289,369,257,306,330};
    
    private GreenfootImage imgMeteor10= new GreenfootImage("rock10.png");
    private GreenfootImage imgMeteor20= new GreenfootImage("rock20.png");
    private GreenfootImage imgM10= new GreenfootImage("rock10.png");
    private GreenfootImage imgM15= new GreenfootImage("rock15.png");
    private GreenfootImage imgM20= new GreenfootImage("rock20.png");
    private GreenfootImage imgM25= new GreenfootImage("rock25.png");
    private GreenfootImage imgM30= new GreenfootImage("rock30.png");
    private GreenfootImage imgM35= new GreenfootImage("rock35.png");
    private GreenfootImage imgM40= new GreenfootImage("rock40.png");
    private GreenfootImage imgM45= new GreenfootImage("rock45.png");
    private GreenfootImage imgM50= new GreenfootImage("rock50.png");
    private GreenfootImage imgFlash10= new GreenfootImage("rock10flash.png");
    private GreenfootImage imgFlash20= new GreenfootImage("rock20flash.png");
    private GreenfootImage fb= new GreenfootImage("factboard.png");
    private GreenfootImage imgS[]= {imgM10 , imgM15 , imgM20 , imgM25 , imgM30 ,imgM35 , imgM40 , imgM40, imgM40, imgM40
    , imgM45, imgM45, imgM50, imgM50};
     
    
    private GreenfootSound sndRumble= new GreenfootSound("quake1.wav");
    private GreenfootSound sndExplsion= new GreenfootSound("explosion.wav");
     
    /**
     * Constructor for objects of class stars.
     * 
     */
    public Stars()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(700, 550, 1,false); 
     // Label l = new Label(fb);
    //    l.setSize(new Dimension(200, 50));
     
    //    l.setText("\n once upon a time, in a gallexy far far away there lived an animal with two heads");
      
    //   addObject(l,200,300);
      
        prepare1();
    }

    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     *
     */
    private void prepare1()
    {
           //    init_meteors();
             //  drop_meteors();
        Moon moon = new Moon();
        addObject(moon, 350, 950);
     
    }
    
    public void addGameTitle() {
     //  nextX=titleX;
        for (i=0;i<gameTitle.length;i++) {
            Letter l = new Letter(gameTitle[i],letterX[i],letterY[i]); 
            addObject(l, letterX[i],letterY[i]);
        }
        Greenfoot.delay(30);
        for (i=0;i<gameSubTitle.length;i++) {
            Letter l = new Letter(gameSubTitle[i],letterSX[i],letterSY[i]); 
            addObject(l, letterSX[i],letterSY[i]);
        }
    }
    public void act() {
                 
          List<Moon> moons =  getObjects (Moon.class);
          List<Letter> letters =  getObjects (Letter.class);         
                    
        for (Moon moon : moons)  {
            
          if (!titleAdded){
              sndRumble.play();
              addGameTitle();
              titleAdded=true;
 
          }
 
             if (moon.doneShaking()){ 
              if (! meteorsAdded) { 
                  addMeteors();
                  meteorsAdded=true;
                }
              if (! initMeteors){
               if (flashes <maxFlashes) {
                  flashMeteors();
                  flashes ++;
               }
              }
             // meteorsAdded=true;
              if (! moonRumbled) {
                 moonRumble();
                 moonRumbled=true;
                 moonExplode();
                 removeMeteors();
                }
              if (! initMeteors &&  moonRumbled && letters.isEmpty()) {
                 reset_moon();
                  init_meteors();
                
                 initMeteors=true;
              }
            
              if ( initMeteors ) {
              //   reset_moon();
                 drop_meteors();
                         
          }
          
        }
    }  
}
    
     public void addMeteors() {
     //  nextX=titleX;
        for (i=0;i<meteorX.length;i++) {
            Meteor m = new Meteor();           
            addObject(m, meteorX[i],meteorY[i]);
        }
    }
    
    public void flashMeteors() {
     //  nextX=titleX;
        List<Meteor> meteors =  getObjects (Meteor.class);
        for(Meteor m : meteors)
        {
             
             m.setImage(imgFlash10);
             Greenfoot.delay(1);
            m.setImage(imgFlash20);
            Greenfoot.delay(1);
             m.setImage(imgMeteor10);
            
        }      
        
    }
   
    public void moonRumble() {
  
   
      List<Moon> moons =  getObjects (Moon.class);

        for (Moon moon : moons)  {
           
            moonShook=moon.moonShake(10);
            moonShook=moon.moonShake(40);
             moonShook=moon.moonShake(20);
              moonShook=moon.moonShake(5);
        }
        
    }
    
     public void moonExplode() {
  
      int i,w, mX, mY;
      GreenfootImage imgM;
      
      sndExplsion.play();
       Greenfoot.delay(12);
      List<Meteor> meteors =  getObjects (Meteor.class);
        for(Meteor m : meteors)
        {
           
           mX=m.getX();
           mY=m.getY();
           if (mX>335 && mY< 290) {
                  m.setRotation(45);
                }
          if (mX<=335 && mY< 290) {
                  m.setRotation(135);
                }
          if (mX>335 && mY > 290) {
                  m.setRotation(330);
                }
          if (mX<=335 && mY< 290) {
                  m.setRotation(210);
                }
        }
       
        for (i=1;i<=10;i++){
         for(Meteor m : meteors)
        {
           
          imgM=m.getImage();
          w=imgM.getWidth();
          m.setImage(imgS[i]);
          m.move(42);
          Greenfoot.delay(1);
        }
        
    }
        
    }
     void init_meteors(){
        
         Properties configFile = new Properties();
          try {
            configFile.load(new FileInputStream(ConfigFile));
  
            }   
        catch(IOException e)
             {
             e.printStackTrace();
             }
             int numMeteors = Integer.parseInt(configFile.getProperty("numMeteors"));
            
             x=1;
             while(x<=numMeteors){
          // for(x=1;x<=numMeteors;x++){
            //    if (! meteorDropping) {  
                 String mSector= configFile.getProperty("sector["+x+"]");
                 String mName= configFile.getProperty("mName["+x+"]");
                 int mW =Integer.parseInt(configFile.getProperty("mW["+x+"]"));
                 int mH =Integer.parseInt(configFile.getProperty("mH["+x+"]"));
                 int mSize =Integer.parseInt(configFile.getProperty("mSize["+x+"]"));
                 int locX =Integer.parseInt(configFile.getProperty("locX["+x+"]"));
                 int locY =Integer.parseInt(configFile.getProperty("locY["+x+"]"));
                 String ff1= configFile.getProperty("ff1["+x+"]");
                 String ff2= configFile.getProperty("ff2["+x+"]");
                 String ff3= configFile.getProperty("ff3["+x+"]");
                 String ff4= configFile.getProperty("ff4["+x+"]");          

                 Meteor m = new Meteor(mName,mSector,mW,mH,mSize,locX,locY,ff1,ff2,ff3,ff4);
              
                 addObject(m, locX,locY);
              //   meteorDropping=true;
                 Factboard f = new Factboard();
                 f.updateTrans();
                 addObject(f,150,210);
                 f.setLabel(mName,mSize,ff2);
                  x+=1;
            //   }
            }
             

        }
       private void reset_moon(){
            List<Moon> moons =  getObjects (Moon.class);
            for (Moon moon : moons)  {
                moon.resetAtBottom();
             //   moon.Stretch(770);
            }
        }
       
        private void removeMeteors(){
            List<Meteor> meteors =  getObjects (Meteor.class);
          
           for(Meteor m : meteors)
        {
             removeObject(m);
        }
    }
       private void drop_meteors(){ 
        //   Factboard f = new Factboard();
           addObject(s, 328,580);
           List<Meteor> meteors =  getObjects (Meteor.class);
           for(Meteor m : meteors)
        {
       
            if (!meteorDropping){
              m.dropping=true;
              meteorDropping=true;
       
        
      }
    }
    }
  
     private void dropNextMeteor(Properties cf, int x) {
         
                 String mSector= cf.getProperty("sector["+x+"]");
                 String mName= cf.getProperty("mName["+x+"]");
                 int mW =Integer.parseInt(cf.getProperty("mW["+x+"]"));
                 int mH =Integer.parseInt(cf.getProperty("mH["+x+"]"));
                 int mSize =Integer.parseInt(cf.getProperty("mSize["+x+"]"));
                 int locX =Integer.parseInt(cf.getProperty("locX["+x+"]"));
                 int locY =Integer.parseInt(cf.getProperty("locY["+x+"]"));
                 String ff1= cf.getProperty("ff1["+x+"]");
                 String ff2= cf.getProperty("ff2["+x+"]");
                 String ff3= cf.getProperty("ff3["+x+"]");
                 String ff4= cf.getProperty("ff4["+x+"]");          

                 Meteor m = new Meteor(mName,mSector,mW,mH,mSize,locX,locY,ff1,ff2,ff3,ff4);
              
                 
                 
                 Factboard f = new Factboard();
                // f.updateTrans();
                 addObject(f,150,210);
                 f.setLabel(mName,mSize,ff2);
                 // addObject(m, locX,locY);
                 //m.dropping=true;
       
                }  
        public void setDropping(boolean condition){
            meteorDropping=condition;
        }
    
}
