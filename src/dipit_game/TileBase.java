/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dipit_game;

import javax.swing.JLabel;

/**
 *
 * @author Arch
 */
public class TileBase {
    private final int[] position;
    private String RenderCharacter = "[x]";
    private javax.swing.JLabel Label;

    public JLabel GetLabel() {
        return Label;
    }

    public void SetLabel(JLabel Label) {
        this.Label = Label;
    }
    
    public int[] GetPosition(){
        return this.position;
    }
    
    public int GetPositionX(){
        return this.position[0];
    }
    
    public int GetPositionY(){
        return this.position[1];
    }
    
    public String GetRenderCharacter(){
        return this.RenderCharacter;
    }
    
    TileBase(int[] _position){
        this.Label = new JLabel();
        this.position = new int[]{_position[0], _position[1]};
    }
    
    TileBase(int x, int y){
        this.Label = new JLabel();
        this.position = new int[]{x, y};
    }

    public void SetRenderCharacter(String _RenderCharacter) {
      this.RenderCharacter =   _RenderCharacter;
    }
    
}
