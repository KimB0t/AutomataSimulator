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
package agents;

import misc.Neighbours;
import misc.Params;
import cells.Cell;
import cells.CellInfluenceClass;

/**
 *
 * @author Karim
 */
public abstract class Agent{
    
    // Coordinates of the cell in which the agent is placed on the matrix.
    private int i = 0;
    private int j = 0;
    
    // Position of an Agent. this is another way to locate agent on the matrix
    // Positions goes from 0 to matrix lenght²
    private int position = 0;
    
    // Unique ID for this Agent.
    private int id = 0;
    
    /**
     *
     * @param i
     * @param j
     * @param p
     * @param id
     */
    public Agent(int i, int j, int p, int id) {
        this.i = i;
        this.j = j;
        this.position = p;
        this.id = id;
    }

    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">

    /**
     *
     * @param i
     */
    public void setI(int i) {
        this.i = i;
    }

    /**
     *
     * @param j
     */
    public void setJ(int j) {
        this.j = j;
    }

    /**
     *
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getI() {
        return i;
    }

    /**
     *
     * @return
     */
    public int getJ() {
        return j;
    }

    /**
     *
     * @return
     */
    public int getPosition() {
        return position;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

//    public int getMATRIX_LENGTH() {
//        return MATRIX_LENGTH;
//    }
    
    //</editor-fold>
    
    /**
     * When an agent moves on the matrix.
     * @param s
     * @return
     */
    public abstract Agent move(Params param, int s);

    /**
     * Another moving fct for AgentInfluenceClass
     * @param nghbrs
     * @param new_cell
     * @return
     */
    public abstract Agent move(Params param, Neighbours nghbrs, Cell new_cell);
}
