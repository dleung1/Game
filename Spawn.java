/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Random;

/**
 *
 * @author denise
 */
public class Spawn {
    
    private Handler handler;
    private HUD hud;
    private int scoreKeep = 0;
    private Random r = new Random ();
    
    public Spawn(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }
    
    public void tick(){
        scoreKeep++;
        
        if(scoreKeep >= 100){
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            
            if(hud.getLevel() == 2){
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
            }else if(hud.getLevel() == 3){
                handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.FastEnemy, handler));
            }else if(hud.getLevel() == 4){
                handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
            }else if(hud.getLevel() == 5){
                handler.clearEnemys(); 
                handler.addObject(new EnemyBoss((Game.WIDTH/2), -120, ID.EnemyBoss, handler));
            }
        }
    }
}
