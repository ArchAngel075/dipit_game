/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpandedGame;
import dipit_game.*;
/**
 *
 * @author Arch
 */
public class ExpandedGame_PostWorldGenImpl extends dipit_game.events.PostWorldGenInterface {

    public void ModifyWorldGen(WorldBase World) {
        System.out.println ("Modifying...");
        for(int y = 0 ; y < World.GetHeight() ; y++){
            for(int x = 0 ; x < World.GetWidth() ; x++){
                if(x == 1 && y == 1){
                    World.GetTile(World.GetTileAtPosition(x,y)).SetRenderCharacter("W");
                }
            }  
        }
    }
}
