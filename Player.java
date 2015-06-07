/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;

/**
 *
 * @author denise
 */
public class Player extends GameObject {
    
    Random r = new Random();
    Handler handler;
    
    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        
    }
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,32,32);
    }
    public void tick(){
        x += velX;
        y += velY;
        
        x = Game.clamp(x, 0, Game.WIDTH - 16);
        y = Game.clamp(y, 0, Game.HEIGHT - 48);
        
        collision();
        
        handler.addObject(new Trail(x,y,ID.Trail,Color.white,32,32,0.02f, handler));
    }
    private void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy){
                if(getBounds().intersects(tempObject.getBounds())){
                    //collision code
                    HUD.HEALTH -= 2;
                }
            }
        }
    }
    public void render(Graphics g){
        g.setColor(Color.white);
        g.fillRect((int)x,(int)y,32,32);
    }
}
