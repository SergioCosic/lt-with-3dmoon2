import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class moon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Moon extends Actor
{
    /**
     * Act - do whatever the moon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //private int yloc=this.getY();
    private boolean moonMoved=false;
    private boolean moonScaled=false;
    private boolean moonShook=false;
    public void act() 
    {
      //  stars=Greenfoot.GetWorld(); 
        // Add your action code here.
        if (!moonMoved){
             moonMoved=moonMove();
            }   

        if (!moonScaled){
             moonScaled=moonScale();
        }
         if (!moonShook){
             moonShook=moonShake(10);
             moonShook=moonShake(20);
             moonShook=moonShake(30);
            // moonShook=moonShake(80);
           //  moonShook=moonShake(30);
             moonShook=moonShake(10);
        }
    //    if (!addedTitle){
      //      stars.addGameTitle();
     //   }
  
     }
        private boolean moonMove(){
            
             int yloc=this.getY();
             for(int i = 0; i < 12; i++) {
                yloc=yloc-(i * 10);
                 this.setLocation(335, yloc);
                 Greenfoot.delay(3);
                }
                return true;
            }
     
        private boolean moonScale(){
            GreenfootImage imgMoon=getImage();
            for(int i = 0; i < 13; i++) {
                imgMoon.scale((int)(imgMoon.getHeight()*.9),(int)(imgMoon.getHeight()*.9));
                turn(25);
                Greenfoot.delay(3);
            }
            return true;
        }
        
         public boolean moonShake(int shakeAmount){
            int origX=this.getX();
            int origY=this.getY();
            int shakeX,shakeY,newX,newY,mOrigX,mOrigY,mNewX,mNewY;
            
           List<Meteor> meteors =  getWorld().getObjects (Meteor.class);
                   
                    
        
          
            
            for(int i = 0; i < 60; i++) {
                shakeX=Greenfoot.getRandomNumber(shakeAmount);
                shakeY=Greenfoot.getRandomNumber(shakeAmount);
                
                newX=origX+shakeX;
                newY=origY+shakeY;
                setLocation(newX,newY);
                for (Meteor m : meteors)  {
                  mOrigX=  m.getX();
                  mOrigY=  m.getY();
                  mNewX=mOrigX+shakeX;
                  mNewY=mOrigY+shakeY;
                  m.setLocation(mNewX,mNewY);
                }
                Greenfoot.delay(1);
                setLocation(origX,origY); 
                
                 for (Meteor m : meteors)  {
                  mNewX=  m.getX();
                  mNewY=  m.getY();
                  mOrigX=mNewX-shakeX;
                  mOrigY=mNewY-shakeY;
                  m.setLocation(mOrigX,mOrigY);
                }
                
            }
            return true;
        }
            
        public boolean doneShaking() {
            if (moonShook) {
                return true;
            }
            else {
                return false;
            }
        }
    
        public void resetAtBottom() {
            int xloc=this.getX();
            int yloc=this.getY();
            while(yloc<560){
              yloc+=20;
              setLocation(xloc,yloc);
              Greenfoot.delay(10);
        }
            
        } 
       public void Stretch(int w) {
            GreenfootImage img=getImage();
            setRotation(0);
            int iw=img.getWidth();
            int ih=img.getHeight();
            while(iw<w){
              iw+=20;
              img.scale(iw,ih);
              Greenfoot.delay(10);
        }
            
        }
}
