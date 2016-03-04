import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Dimension;
/**
 * Write a description of class Factboard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Factboard extends Actor
{
    /**
     * Act - do whatever the Factboard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage bg=getImage();
    private int trans=0;
    public Factboard(){
    }
   
    public void act() 
    {
        
        if (trans<240){
            trans+=5;
        }
        // Add your action code here.
      bg.setTransparency(trans);  
    }    
    public void updateTrans(){
         if (trans<245){
            trans+=10;
        }
        // Add your action code here.
      bg.setTransparency(trans);  
    }
    public void setLabel(String n , int s, String t) {
        Label l = new Label(n + "\n" + s + " km \n" + t);
        l.setSize(new Dimension(200, 100));
        getWorld().addObject(l,super.getX(),super.getY());
    }    
}
