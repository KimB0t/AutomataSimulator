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

import static misc.Params.RAND;
import agents.AgInfRepClass;
import cells.CellInfRepClass;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 */
public class AutoInfRepClass extends Automaton{
    
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    // The matrix
    private CellInfRepClass[][] matrix;
    //Matrix of cells - Copy
    private CellInfRepClass[][] newMatrix;
    // The agent list
    private ArrayList<AgInfRepClass> agents;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Create new Influence-repultion automaton
     */
    public AutoInfRepClass() {
        super();
        this.matrix = new CellInfRepClass[param.MATRIX_LENGTH][param.MATRIX_LENGTH];
        this.agents = new ArrayList<>();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
    @Override
    public void setAgent(int i, int j, int nb, Color co, boolean wl) {
        
        // Calcul de la classe (aléatoire)
        int cls = RAND.nextInt(param.NB_CLASSES);
        //Make a cell with an agent
        this.matrix[i][j].makeAgent(nb, param.getCOLOR_at(cls), wl, cls);
        //Add and agent to the list
        int id = agents.size();
        agents.add(id, new AgInfRepClass(param, i, j, id, cls));
    }

    @Override
    public Color getCellColor(int i, int j) {
        return this.matrix[i][j].getCouleur();
    }
    
    @Override
    public void setInfoTable(JTable infoTable) {
        super.setInfoTable(infoTable);
        DefaultTableModel model = (DefaultTableModel) infoTable.getModel();
        model.addRow(new Object[]{"States"});
        model.addRow(new Object[]{"Agents"});
        model.addRow(new Object[]{"Calsse"});
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Makers">
    @Override
    public void makeWallAt(int i, int j) {
        this.matrix[i][j] = new CellInfRepClass(param, param.getCOLOR_OBSTACLE(), i, j, true);
        this.matrix[i][j].setParam(param);
    }
    
    @Override
    public void makeCellAt(int i, int j) {
        this.matrix[i][j] = new CellInfRepClass(param, param.getCOLOR_DEFAULT(), i, j, false);
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
        
//        int thisi, thisj, thisClasse;
//        CellInfRepClass thisCell;
//        CellInfRepClass[][] new_matrix = new CellInfRepClass[param.MATRIX_LENGTH][param.MATRIX_LENGTH];
        
        //Agents perceive environnement + decide to do something (fire or depl)
        //They put decisions directly on concerned cells
        //Then we choose which action to take
        
        //Agent precieve + decide
        for (AgInfRepClass agent : agents) {
            int i = agent.getI();
            int j = agent.getJ();
            CellInfRepClass currentCell = matrix[i][j];
            
            agent.perceive(currentCell);
        }
        
        //Cell update + agents' internal state update
        newMatrix = new CellInfRepClass[param.MATRIX_LENGTH][param.MATRIX_LENGTH];
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
        for (AgInfRepClass agent : agents) {
            int i = agent.getI();
            int j = agent.getJ();
            CellInfRepClass currentCell = matrix[i][j];
            currentCell.increaseNbAgentsAtK(agent.getClasse(), 1);
            colorier(currentCell, agent);
        }
        
        param.increaseNBGeneration(1);
    }

    @Override
    public void deleteAgent(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void printCell(int x, int y){
        matrix[x][y].print(infoTable);
    }
    
    private void colorier(CellInfRepClass currentCell, AgInfRepClass agent) {
        
        if(currentCell.hasDifferentAgentClasses())
            currentCell.setCouleur(Color.BLACK);
        else if(currentCell.getNbAgentsAtK(agent.getClasse())> 1)
            currentCell.setCouleur(param.getCOLOR_at(agent.getClasse(), 255));
        else
            currentCell.setCouleur(param.getCOLOR_at(agent.getClasse(), 200));
    }
}
