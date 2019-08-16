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
import data.DataGauss;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 * @version 1.0
 */
public class AutoGauss extends Automaton{

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //Matrix of cells
    private CellGauss[][] matrix;
    //Matrix of cells - Copy
    private CellGauss[][] new_matrix;
    //List of agents
    private ArrayList<AgGauss> agents;
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
    @Override
    public void setAgent(int i, int j, int nb, Color cellColor, boolean wl) {
        DataGauss data = new DataGauss();
        data.random();
        //Make a cell with an agent
        this.matrix[i][j].makeAgent(nb, param.getCOLOR(data, 255), wl);
        //Add and agent to the list
        int id = agents.size();
        agents.add(id, new AgGauss(param, i, j, id, data));
    }
    
    @Override
    public Color getCellColor(int i, int j) {
        return this.matrix[i][j].getCouleur();
    }
    
    @Override
    public void setInfoTable(JTable infoTable) {
        super.setInfoTable(infoTable);
        DefaultTableModel model = (DefaultTableModel) infoTable.getModel();
        model.addRow(new Object[]{"State"});
        model.addRow(new Object[]{"Agents"});
        model.addRow(new Object[]{"Data"});
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Makers">
    @Override
    public void makeWallAt(int i, int j) {
        this.matrix[i][j] = new CellGauss(param, param.getCOLOR_OBSTACLE(), i, j, true);
        this.matrix[i][j].setParam(param);
    }
    
    @Override
    public void makeCellAt(int i, int j) {
        this.matrix[i][j] = new CellGauss(param, param.getCOLOR_DEFAULT(), i, j, false);
        this.matrix[i][j].setParam(param);
    }
//</editor-fold>
    
    public AutoGauss() {
        super();
        this.matrix = new CellGauss[param.MATRIX_LENGTH][param.MATRIX_LENGTH];
        this.agents = new ArrayList<>();
    }
    
    @Override
    public void init_matrix() {
        super.init_matrix();
        putNeighboors();
        //Restart agent list
        this.agents = new ArrayList<>();
    }
    
    public void putNeighboors(){
        //Do it inside initMatrix
        for (int i = 0; i < param.MATRIX_LENGTH; i++) {
            for (int j = 0; j < param.MATRIX_LENGTH; j++) {
                for(int di=-1; di<=1; di++) {
                    for(int dj=-1; dj<=1; dj++){
                        int ii = (i + di + param.MATRIX_LENGTH) % param.MATRIX_LENGTH;
                        int jj = (j + dj + param.MATRIX_LENGTH) % param.MATRIX_LENGTH;
                        if(!(i==ii && j==jj)){
                            matrix[i][j].addNeighbour(this.matrix[ii][jj]);
                        }
                    }
                }
            }
        }
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
//            matrix[3][3].printFiringAgents();
        }
        
        //Cell update + agents' internal state update
        CellGauss[][] newMatrix = new CellGauss[param.MATRIX_LENGTH][param.MATRIX_LENGTH];
        for (int i = 0; i < param.MATRIX_LENGTH; i++) {
            for (int j = 0; j < param.MATRIX_LENGTH; j++) {
                //We take a copy to achieve synchronism
                newMatrix[i][j] = matrix[i][j].getCopy();
                newMatrix[i][j].update();
            }
        }
        
        //Env evolve (Copying Cells)
        for (int i = 0; i < param.MATRIX_LENGTH; i++) {
            for (int j = 0; j < param.MATRIX_LENGTH; j++) {
                matrix[i][j].evolve(newMatrix[i][j]);
            }
        }
        
        //Agents evolve (Setting Colors)
        for (AgGauss agent : agents) {
            int i = agent.getI();
            int j = agent.getJ();
            CellGauss currentCell = matrix[i][j];
            currentCell.setCouleur(param.getCOLOR(agent.getData(), 255));
            currentCell.increaseNbAgent(1);
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Not used">    
    @Override
    public void deleteAgent(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Cell[] getListOfNeighbours(int i, int j, int nbNghbrs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>

    @Override
    public void printCell(int x, int y){
        matrix[x][y].print(infoTable);
    }
}
