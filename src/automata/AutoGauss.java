/*
 * Copyright (C) 2019 Karim BOUTAMINE <boutaminekarim06@gmail.com>
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

import agents.AgGauss;
import cells.Cell;
import cells.CellGauss;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 * @version 1.0
 */
public class AutoGauss extends Automaton{

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //Matrix of cells
    private CellGauss[][] matrix;
    //List of agents
    private ArrayList<AgGauss> agents;
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
    @Override
    public void setAgent(int i, int j, int nbAgents, Color cellColor, boolean wall) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Color getCellColor(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>

    public void putNeighboors(){
        //Do it inside initMatrix
    }
    
    @Override
    public void step() {
        //Agents perceive environnement + decide to do something (fire or depl)
        //They put decisions directly on concerned cells
        //Then we choose which action to take
        
        //Agent precieve + decide
        for (AgGauss agent : agents) {
            int i = agent.getI();
            int j = agent.getJ();
            CellGauss currentCell = matrix[i][j];
            
            agent.perceive(currentCell);
        }
        
        //Cell update + agents' internal state update
        CellGauss[][] newMatrix = new CellGauss[MATRIX_LENGTH][MATRIX_LENGTH];
        for (int i = 0; i < MATRIX_LENGTH; i++) {
            for (int j = 0; j < MATRIX_LENGTH; j++) {
                //We take a copy to achieve synchronism
                newMatrix[i][j] = matrix[i][j].getCopy(getParams());
                newMatrix[i][j].update(getParams());
            }
        }
        
        //Env evolve
        for (int i = 0; i < MATRIX_LENGTH; i++) {
            for (int j = 0; j < MATRIX_LENGTH; j++) {
                matrix[i][j].evolve(getParams(), newMatrix[i][j]);
            }
        }
        
        //Agents evolve
        for (AgGauss agent : agents) {
            int i = agent.getI();
            int j = agent.getJ();
            CellGauss currentCell = matrix[i][j];
            currentCell.setCouleur(getCOLOR(agent.getData()));
        }
    }

    @Override
    public void makeCellAt(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void makeWallAt(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAgent(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cell[] getListOfNeighbours(int i, int j, int nbNghbrs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
