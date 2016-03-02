/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpandedGame;
import ExpandedGame.ExpandedGame_OnKeyPressedImpl;
import ExpandedGame.ExpandedGame_PostWorldGenImpl;
import dipit_game.*;

/**
 *
 * @author Arch
 */
public class ExpandedMain extends dipit_game.DipitModBase {
    static ExpandedGame_PostWorldGenImpl ExpandedMain_PostWorldGenImpl = new ExpandedGame_PostWorldGenImpl();
    static ExpandedGame_OnKeyPressedImpl ExpandedMain_OnKeyPressedImpl = new ExpandedGame_OnKeyPressedImpl();
    
    /**
     *
     */
    public static void InitMod(){
        System.out.println("hello");
        //ExpandedMain_PostWorldGenImpl.ModifyWorldGen();
        dipit_game.DIPIT_GAME_Main.add_postWorldGen_Listener(ExpandedMain_PostWorldGenImpl);
        
        dipit_game.DIPIT_GAME_Main.add_OnKeyPressed_Listener(ExpandedMain_OnKeyPressedImpl);
    }
    
    
    
}
