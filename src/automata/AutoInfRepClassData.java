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

import agents.AgInfRepClassData;
import cells.CellInfRepClassData;
import data.Data;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import misc.CSV;
import static misc.Params.arrayToString;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 * @version 1.0
 */
public class AutoInfRepClassData extends Automaton{

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //Matrix of cells
    private CellInfRepClassData[][] matrix;
    //Matrix of cells - Copy
    private CellInfRepClassData[][] newMatrix;
    //List of agents
    private ArrayList<AgInfRepClassData> agents;
    //List of agents
    private ArrayList<Data> dataArray;
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public AutoInfRepClassData(CSV dataset) {
        super();
        this.matrix = new CellInfRepClassData[param.MATRIX_LENGTH][param.MATRIX_LENGTH];
        this.agents = new ArrayList<>();
        this.dataArray = dataset.dataArray;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
    @Override
    public void setAgent(int i, int j, int nb, Color cellColor, boolean wl) {
        System.out.println("NO AGENT CAN BE SET");
    }
    
    public void setAgent(int i, int j, int nb, Color cellColor, boolean wl, Data data) {
        //Make a cell with an agent
        this.matrix[i][j].makeAgent(nb, data.getCouleur(), wl);
        //Add and agent to the list
        int id = agents.size();
        agents.add(id, new AgInfRepClassData(param, i, j, id, data));
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
        model.addRow(new Object[]{"DataValue"});
        model.addRow(new Object[]{"DataClass"});
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Makers">
    @Override
    public void makeWallAt(int i, int j) {
        this.matrix[i][j] = new CellInfRepClassData(param, param.getCOLOR_OBSTACLE(), i, j, true);
        this.matrix[i][j].setParam(param);
    }
    
    @Override
    public void makeCellAt(int i, int j) {
        this.matrix[i][j] = new CellInfRepClassData(param, param.getCOLOR_DEFAULT(), i, j, false);
        this.matrix[i][j].setParam(param);
    }
//</editor-fold>
    
    @Override
    public void init_matrix() {
        super.init_matrix();
        //Restart agent list
        this.agents = new ArrayList<>();
    }
    
    @Override
    public void addNeighbour(int i, int j , int ii, int jj){
        matrix[i][j].addNeighbour(this.matrix[ii][jj]);
    }
    
    @Override
    public void step() {
        //Agents perceive environnement + decide to do something (fire or depl)
        //They put decisions directly on concerned cells
        //Then we choose which action to take
        
        //Agent precieve + decide
        for (AgInfRepClassData agent : agents) {
            int i = agent.getI();
            int j = agent.getJ();
            CellInfRepClassData currentCell = matrix[i][j];
            
            agent.perceive(currentCell);
        }
        
        //Cell update + agents' internal state update
        newMatrix = new CellInfRepClassData[param.MATRIX_LENGTH][param.MATRIX_LENGTH];
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
        for (AgInfRepClassData agent : agents) {
            int i = agent.getI();
            int j = agent.getJ();
            CellInfRepClassData currentCell = matrix[i][j];
            currentCell.increaseNbAgent(1);
            this.colorier(currentCell, agent);
        }
        
        param.increaseNBGeneration(1);
    }

    //<editor-fold defaultstate="collapsed" desc="Not used">    
    @Override
    public void deleteAgent(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>

    @Override
    public void printCell(int x, int y){
        String values;
        String classes;
        
        matrix[x][y].print(infoTable);
        
        if (matrix[x][y].getNbAgents() > 0) {
            values = "";
            classes = "";
            for (AgInfRepClassData agent : agents) {
                if (agent.getI() == x && agent.getJ() == y) {
                    values += agent.getData().getValueString() + "; ";
                    classes += agent.getData().getClasse() + "; ";
                }
            }
            DefaultTableModel model = (DefaultTableModel) infoTable.getModel();
            model.setValueAt(values, 6, 1);
            model.setValueAt(classes, 7, 1);
        }
    }
    
    @Override
    public void randomConfig(){
        this.init_matrix();
        int rn_x, rn_y;
        int nbr_cell = this.dataArray.size();
        
        System.out.println("nbr_cell (nb agents) = "+nbr_cell);
        param.reInitNBGeneration();
        
        for(int i=0; i<nbr_cell; i++){

            // Calcul des coordonnées
            rn_x = param.getRANDcoordinate();
            rn_y = param.getRANDcoordinate();
            
            // Créer l'agent
            this.setAgent(rn_x, rn_y, 1, param.getCOLOR_AGENT1(), false, dataArray.get(i));
        }
    }

    private void colorier(CellInfRepClassData currentCell, AgInfRepClassData agent) {
        
//        if(currentCell.getNbAgents() > 1)
//            currentCell.setCouleur(Color.BLACK);
//        else 
        if(param.SWITCH)
            currentCell.setCouleur(param.getCOLOR_at(agent.getData().getID(), 255));
        else
            currentCell.setCouleur(agent.getData().getCouleur());
    }
}
