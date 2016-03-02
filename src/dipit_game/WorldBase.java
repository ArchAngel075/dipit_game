/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dipit_game;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arch
 */
public class WorldBase {
    
    private final int sizex;
    private final int sizey;
    private final List<TileBase> Tiles;
    private final List<String> SolidTileTags = new ArrayList<>();
    
    
    WorldBase(int sizex_, int sizey_) {
        Tiles = new ArrayList<TileBase>() {};
        sizex = sizex_;
        sizey = sizey_;
    }
    
    WorldBase( List<TileBase> _Tiles , int sizex_, int sizey_) {
        Tiles = new ArrayList<TileBase>() {};
        Tiles.addAll(_Tiles);
        sizex = sizex_;
        sizey = sizey_;
    }
    /**
     * Adds a Tile Object to the Tiles of this world;
     * @param Tile the tile to add.
     */
    public void AddTile(TileBase Tile){
        Tiles.add(Tile);
    }
    
    /**
     * Adds a Tag to the Solid list.
     * @param Tag The string Tag to add.
     */
    public void AddSolidTag(String Tag){
       SolidTileTags.add(Tag);
    }
    
    /**
     * Checks if the Tile Specified is a solid;
     * @param Index the Index of the Tile to check;
     * @return [boolean] true if the tile is solid, false if it is pathable;
     */
    public boolean IsTileSolid(int Index){
        return SolidTileTags.contains(Tiles.get(Index).GetRenderCharacter());
    }
    
    /**
     * Checks if the Tile Specified at the position is a solid;
     * @param xPos the x Position of the Tile to check;
     * @param yPos the y Position of the Tile to check;
     * @return [boolean] true if the tile is solid, false if it is pathable;
     */
    public boolean IsTileSolid(int xPos, int yPos) {
      return this.IsTileSolid(this.GetTileAtPosition(xPos,yPos));  
    }
    
    /**
     * Returns the index of the tile at the world position [@p1 position]
     * If the world does not contain a tile at the position provided it returns -1 instead.
     * @param position the position to check against.
     * @return [integer] the index of the tile at position.
     */
    public int GetTileAtPosition(int[] position){
        for(TileBase T : Tiles){
            if(T.GetPosition()[0] == position[0] && T.GetPosition()[1] == position[1]){
                return Tiles.indexOf(T);
            }
        }
        return -1;
    }
    
    /**
     * Returns the index of the tile at the world position [x,y]
     * If the world does not contain a tile at the position provided it returns -1 instead.
     * @param x the x position to check against.
     * @param y the y position to check against.
     * @return [integer] the index of the tile at position.
     */
    public int GetTileAtPosition(int x, int y){
        for(TileBase T : Tiles){
            if(T.GetPosition()[0] == x && T.GetPosition()[1] == y){
                return Tiles.indexOf(T);
            }
        }
        return -1;
    }
    
    /**
     * Returns the object reference of the tile at index.
     * If the world does not contain a tile at the Index provided it returns null instead.
     * @param index the index of the tile to fetch.
     * @return [TileBase] OR [null] The tile at index or null if no tile found.
     */
    public TileBase GetTile(int index){
        if(Tiles.size() < index){
            return null;
        } else {
            return Tiles.get(index);
        }
    }
    
    /**
     * @return the worlds width;
     */
    public int GetWidth(){
       return this.sizex;
    }
    
    /**
     * @return the worlds height;
     */
    public int GetHeight(){
       return this.sizey;
    }
    
}
