/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import static game.Game.HEIGHT;
import static game.Game.WIDTH;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import game.Game.STATE;
/**
 *
 * @author denise
 */
public class Menu extends MouseAdapter {
    
    private Game game;
    private Handler handler;
    private Random r = new Random();
    private HUD hud;
    
    public Menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }
    
    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();
        
        if(game.gameState == STATE.Menu){
            //play button
            if(mouseOver(mx, my, 210, 150, 200, 64)){
                game.gameState = STATE.Game;
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
            }

            //help button
            if(mouseOver(mx, my, 210, 250, 200, 64)){
                game.gameState = STATE.Help;
            }
             //quit button
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                System.exit(1);
            }
        }
        
        //back button for help
        if(game.gameState == STATE.Help){
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                game.gameState = STATE.Menu;
                return;
            }
        }
        //try again button for end
        if(game.gameState == STATE.End){
            if(mouseOver(mx, my, 210, 350, 200, 64)){
                game.gameState = STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
            }
        }
    }
    
    public void mouseReleased(MouseEvent e){
        
    }
    
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if(mx >= x && mx < x + width){
            if(my >= y && my < y + height){
                return true;
            }
        }
        return false;
    }
    
    public void tick(){
        
    }
    
    public void render(Graphics g){
        Font fnt = new Font("arial", 1, 50);
        Font fnt2 = new Font("arial", 1, 30);
        Font fnt3 = new Font("arial", 1, 20);
        
        if(game.gameState == STATE.Menu){
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Menu", 240, 70);

            g.setFont(fnt2);
            g.drawRect(210, 150, 200, 64);
            g.drawString("Play", 270, 190);

            g.setColor(Color.white);
            g.drawRect(210, 250, 200, 64);
            g.drawString("Help", 270, 290);

            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Quit", 270, 390);
        }else if(game.gameState == STATE.Help){
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 240, 70);
            
            g.setFont(fnt3);
            g.drawString("Use WASD keys to move to dodge enemies", 100, 200);
            
            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Back", 270, 390);
        }else if(game.gameState == STATE.End){
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Game Over", 180, 70);
            
            g.setFont(fnt3);
            g.drawString("You lost with a score of: " + hud.getScore(), 175, 200);
            
            g.setFont(fnt2);
            g.drawRect(210, 350, 200, 64);
            g.drawString("Try Again", 245, 390);
        }
        
    }
    
}
