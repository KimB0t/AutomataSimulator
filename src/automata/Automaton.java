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
package automata;

import misc.Params;
import cells.Cell;
import java.awt.Color;

/**
 * An automaton is a matrix of cells
 * the automata matrix (matrix of cells)
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 */
public abstract class Automaton extends Params{
    
    /**
     *
     */
    public Automaton() {
        initParamsGlobal();
        printToScreen("===============INITIALIZATION========================");
    }
    
    /**
     * We use this function to initiate a cell with an agent 
     * @param i
     * @param j
     * @param nbAgents
     * @param cellColor
     * @param wall
     */
    public abstract void setAgent(int i, int j, int nbAgents, Color cellColor, boolean wall);
    
    /**
     * returns the color of this cell(i, j)
     * @param i
     * @param j
     * @return
     */
    public abstract Color getCellColor(int i, int j);
    
    /**
     * Initialize a matrix
     */
    public void init_matrix(){
        for(int i=0; i<MATRIX_LENGTH; i++) {
            for(int j=0; j<MATRIX_LENGTH; j++){
                makeCellAt(i, j);
            }
        }
        //applay boundaries if they are enabled
        if(isBOUNDARIESequalTo("Free")) makeBoundaries();
    }
    
    /**
     * The automaton next step (automaton update of all cells)
     */
    public abstract void step();
    
    public abstract void makeCellAt(int i, int j);
    
    /**
     * Creates a wall cell at position (i, j).
     * @param i line coordinate
     * @param j column coordinate
     */
    public abstract void makeWallAt(int i, int j);
    
    /**
     * Creates borders (of wall cells) to the matrix.
     */
    public void makeBoundaries(){
        for (int k = 0; k < MATRIX_LENGTH; k++) {
            makeWallAt(k, 0);
            makeWallAt(k, MATRIX_LENGTH-1);
            makeWallAt(0, k);
            makeWallAt(MATRIX_LENGTH-1, k);
        }
    }
    
    /**
     * Delete agent in cell (i, j). 
     * @param i
     * @param j
     */
    public abstract void deleteAgent(int i, int j);
    
    /**
     * Create a random Configuration
     */
    public void randomConfig(){
        this.init_matrix();
        int rn_x, rn_y;
        int nbr_cell = (int)((MATRIX_LENGTH) * (MATRIX_LENGTH) * DENSITY / 100);
        
        System.out.println("nbr_cell (nb agents) = "+nbr_cell);
        reInitNBGeneration();
        
        for(int i=0; i<nbr_cell; i++){

            // Calcul des coordonnées
            rn_x = getRANDcoordinate();
            rn_y = getRANDcoordinate();
            
            // Créer l'agent
            this.setAgent(rn_x, rn_y, 1, getCOLOR_AGENT1(), false);
        }
    }
    
    /**
     * Create a random Configuration
     * @param i
     * @param j
     * @param nbNghbrs
     * @return 
     */
    public abstract Cell[] getListOfNeighbours(int i, int j, int nbNghbrs);
    
    /**
     *
     */
    public void clear(){
        init_matrix();
        reInitNBGeneration();
    }
    
}
