/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dipit_game.events;

import dipit_game.WorldBase;

/**
 *
 * @author Arch
 */
public class PostWorldGenInterface {

    public void ModifyWorldGen(WorldBase World) {
        System.out.println("DIPIT_Modifying");
    }

}
