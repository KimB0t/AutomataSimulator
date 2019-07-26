/* 
 * Copyright (C) 2019 Karim BOUTAMINE <boutaminekarim06 at gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package cells;

import misc.Neighbours;
import misc.Params;
import java.awt.Color;

/**
 * A cell is a component of an Automaton.
 * @author Karim BOUTAMINE
 */
public abstract class Cell {
    
    //Color of the cell
    private Color couleur;
    
    //Coordinates of the cell in the matrix
    private int i;
    private int j;
    
    //If this cell is a wall then 1 else 0
    private boolean wall;

    /**
     * Create a cell with default settings:
     * color = Color.WHITE, 
     * i = -1, 
     * j = -1, 
     * wall = false;
     */
    public Cell() {
        this.couleur = Color.WHITE;
        this.i = -1;
        this.j = -1;
        this.wall = false;
    }
    
    /**
     * Create a cell at position (i, j) with:
     * color = Color.WHITE,  
     * wall = false;
     * @param i line index
     * @param j column index
     */
    public Cell(int i, int j) {
        this.couleur = Color.WHITE;
        this.i = i;
        this.j = j;
        this.wall = false;
    }
    
    /**
     * Create a cell at position with specific settings:
     * @param co color of the cell
     * @param i line index
     * @param j column index
     * @param wall 1 if wall, 0 else
     */
    public Cell(Color co, int i, int j, boolean wall) {
        this.couleur = co;
        this.i = i;
        this.j = j;
        this.wall = wall;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">

    /**
     * Set the cell color cell
     * @param couleur color of the cell
     */
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    /**
     * 
     * @param i line index
     */
    public void setI(int i) {
        this.i = i;
    }

    /**
     *
     * @param j column index
     */
    public void setJ(int j) {
        this.j = j;
    }

    /**
     * true if this cell represents a wall, false if not
     * @param wall 
     */
    public void setWall(boolean wall) {
        this.wall = wall;
    }

    /**
     *
     * @return color
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     *
     * @return i
     */
    public int getI() {
        return i;
    }

    /**
     *
     * @return j
     */
    public int getJ() {
        return j;
    }

    /**
     *
     * @return true if this cell is a wall. false otherwise.
     */
    public boolean isWall() {
        return wall;
    }
    //</editor-fold>
    
    /**
     * Compute the next state of this cell.
     * @param param The simulation parameters.
     * @param nghbrs A neighbours object.
     * @return A new cell after computing the new state.
     */
    public abstract Cell nextState(Params param, Neighbours nghbrs);
}
