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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * An automaton is a matrix of cells
 * the automata matrix (matrix of cells)
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 */
public abstract class Automaton{
    
    
    //InfoTable
    public JTable infoTable;
    //Params
    public Params param;
    
    /**
     *
     */
    public Automaton() {
        param = new Params();
        param.initParamsGlobal();
        param.printToScreen("===============INITIALIZATION========================");
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
        for(int i=0; i<param.MATRIX_LENGTH; i++) {
            for(int j=0; j<param.MATRIX_LENGTH; j++){
                makeCellAt(i, j);
            }
        }
        //applay boundaries if they are enabled
        if(param.isBOUNDARIESequalTo("Free")) makeBoundaries();
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
        for (int k = 0; k < param.MATRIX_LENGTH; k++) {
            makeWallAt(k, 0);
            makeWallAt(k, param.MATRIX_LENGTH-1);
            makeWallAt(0, k);
            makeWallAt(param.MATRIX_LENGTH-1, k);
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
        int nbr_cell = (int)((param.MATRIX_LENGTH) * (param.MATRIX_LENGTH) * param.DENSITY / 100);
        
        System.out.println("nbr_cell (nb agents) = "+nbr_cell);
        param.reInitNBGeneration();
        
        for(int i=0; i<nbr_cell; i++){

            // Calcul des coordonnées
            rn_x = param.getRANDcoordinate();
            rn_y = param.getRANDcoordinate();
            
            // Créer l'agent
            this.setAgent(rn_x, rn_y, 1, param.getCOLOR_AGENT1(), false);
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
        param.reInitNBGeneration();
    }

    public void printCell(int x, int y) {
        
    }
    
    public void setInfoTable(JTable infTbl) {
        this.infoTable = infTbl;
        DefaultTableModel model = (DefaultTableModel) this.infoTable.getModel();
        model.addRow(new Object[]{"x"});
        model.addRow(new Object[]{"y"});
        model.addRow(new Object[]{"Color"});
        model.addRow(new Object[]{"Type"});
    }
    
}
