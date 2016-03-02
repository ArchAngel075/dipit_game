/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dipit_game;

/**
 *
 * @author Arch
 */
public class PlayerBase {
    
    private final WorldBase PlayerWorld;
    
    public PlayerBase(int PositionX, int PositionY, WorldBase PlayerWorld) {
        this.PositionX = PositionX;
        this.PositionY = PositionY;
        this.PlayerWorld = PlayerWorld;
    }
    private int PositionX;
    private int PositionY;
    
    private final String RenderCharacter = "@";

    public String GetRenderCharacter() {
        return RenderCharacter;
    }
    
    public int GetPositionX() {
        return PositionX;
    }

    public void SetPositionX(int PositionX) {
        this.PositionX = PositionX;
    }

    public int GetPositionY() {
        return PositionY;
    }

    public void SetPositionY(int PositionY) {
        this.PositionY = PositionY;
    }
    
    public void MoveRight(){
        if(PlayerWorld.GetTileAtPosition(this.PositionX+1, PositionY) > -1 &&
                !PlayerWorld.IsTileSolid(this.PositionX+1, PositionY)){
            this.PositionX++;
        }
    }
    public void MoveUp(){
        if(PlayerWorld.GetTileAtPosition(this.PositionX, PositionY-1) > -1 &&
                !PlayerWorld.IsTileSolid(this.PositionX, PositionY-1)){
            this.PositionY--;
        }
    }
    public void MoveLeft(){
        if(PlayerWorld.GetTileAtPosition(this.PositionX-1, PositionY) > -1 &&
                !PlayerWorld.IsTileSolid(this.PositionX-1, PositionY)){
            this.PositionX--;
        }
    }
    public void MoveDown(){
        if(PlayerWorld.GetTileAtPosition(this.PositionX, PositionY+1) > -1 &&
                !PlayerWorld.IsTileSolid(this.PositionX, PositionY+1)){
            this.PositionY++;
        }
    }
    
    
}
