/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
/**
 *
 * @author denise
 */
public class BasicEnemy extends GameObject {
    
    private Handler handler;
    
    public BasicEnemy(int x, int y, ID id, Handler handler){
        super(x,y,id);
        
        this.handler = handler;
        
        velX = 5;
        velY = 5;
        
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,16,16);
    }
    
    @Override
    public void tick(){
        x += velX;
        y += velY;
        
        if(y <= 0 || y >= Game.HEIGHT - 48) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
        
        handler.addObject(new Trail((int)x,(int)y,ID.Trail,Color.red,16,16,0.1f, handler));
    }
    
    @Override
    public void render(Graphics g){
        
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,16,16);
    }
}
