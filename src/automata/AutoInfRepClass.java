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
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 */
public class AutoInfRepClass extends Automaton{
    
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    // The matrix
    private CellInfRepClass[][] matrix;
    // The agent list
    private ArrayList<AgInfRepClass> agents;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Create new Influence-repultion automaton
     */
    public AutoInfRepClass() {
        super();
        this.matrix = new CellInfRepClass[MATRIX_LENGTH][MATRIX_LENGTH];
        this.agents = new ArrayList<>();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
    @Override
    public void setAgent(int i, int j, int nb, Color co, boolean wl) {
        
        // Calcul de la classe (aléatoire)
        int cls = RAND.nextInt(NB_CLASSES);
        //Make a cell with an agent
        this.matrix[i][j].makeAgent(nb, getCOLOR_at(cls), wl, cls);
        //Add and agent to the list
        int id = agents.size();
        agents.add(id, new AgInfRepClass(i, j, id, cls));
    }

    @Override
    public Color getCellColor(int i, int j) {
        return this.matrix[i][j].getCouleur();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Makers">
    @Override
    public void makeWallAt(int i, int j) {
        this.matrix[i][j] = new CellInfRepClass(getCOLOR_OBSTACLE(), i, j, true);
    }
    
    @Override
    public void makeCellAt(int i, int j) {
        this.matrix[i][j] = new CellInfRepClass(getParams(), getCOLOR_DEFAULT(), i, j, false);
    }
//</editor-fold>
    
    @Override
    public void init_matrix() {
        super.init_matrix();
        //Restart agent list
        this.agents = new ArrayList<>();
    }

    @Override
    public void step() {
        
        int thisi, thisj, thisClasse;
        CellInfRepClass thisCell;
        CellInfRepClass[][] new_matrix = new CellInfRepClass[MATRIX_LENGTH][MATRIX_LENGTH];
        
        //Moving Agents
        for (AgInfRepClass thisAgent : agents) {
            
            //Get some values
            thisi = thisAgent.getI();
            thisj = thisAgent.getJ();
            thisClasse = thisAgent.getClasse();
            thisCell = this.matrix[thisi][thisj];
            
            //Count neighbours
            CellInfRepClass[] nghbrsList = getListOfNeighbours(thisi, thisj, 8);
            thisCell.setNeighbours(nghbrsList);
            thisCell.countNeighbours(getParams(), thisClasse);
            
            //Percieve + decide to move
            Point deltaLocation = thisAgent.move(getParams(), thisCell);

            //If there is a new location for the agent (this agent is moving)
            if(deltaLocation != null){
                int di = (int)deltaLocation.getX();
                int dj = (int)deltaLocation.getY();
                //Add this agent to concurent list of this cell
                matrix[di][dj].addConcurent_agents(thisAgent);
            }
            matrix[thisi][thisj].reinitArrays(true);
        }
        
        //Conflicts + NEXT STATE System
        //UPDATE(Agents) + EVOLVE(System)
        for(int i=0; i<MATRIX_LENGTH; i++) {
            for(int j=0; j<MATRIX_LENGTH; j++){
                
                new_matrix[i][j] = this.matrix[i][j].getCopy(getParams());
                if (!new_matrix[i][j].isWall()) {
                    //Count neighbours
                    CellInfRepClass[] nghbrsList = getListOfNeighbours(i, j, 8);
                    new_matrix[i][j].setNeighbours(nghbrsList);
            
                    new_matrix[i][j].nextState(getParams());
                    AgInfRepClass choosenAgent = new_matrix[i][j].chooseAgent(getParams());
                    
                    if(choosenAgent != null){
                        choosenAgent.setNewLocation(i, j);
                        agents.set(choosenAgent.getId(), choosenAgent);
                    }
                    new_matrix[i][j].reinitArrays(false);
                    new_matrix[i][j].agentInitializer(getParams());
                }
            }
        }
        
        for(int i=0; i<agents.size(); i++) {
            
            int cl = agents.get(i).getClasse();
            int ii = agents.get(i).getI();
            int jj = agents.get(i).getJ();
            
            new_matrix[ii][jj].colorier(getParams(), cl);
        }
        this.matrix = new_matrix;
        increaseNBGeneration(1);
    }
    
    @Override
    public CellInfRepClass[] getListOfNeighbours(int i, int j, int nbNghbrs) {
        
        CellInfRepClass[] nb = new CellInfRepClass[nbNghbrs];
        int m = 0;
        for(int di=-1; di<=1; di++) {
            for(int dj=-1; dj<=1; dj++){
                int ii = (i + di + MATRIX_LENGTH) % MATRIX_LENGTH;
                int jj = (j + dj + MATRIX_LENGTH) % MATRIX_LENGTH;
                if(!(i==ii && j==jj)){
                    nb[m] = this.matrix[ii][jj];
                    m++;
                }
            }
        }
        return nb;
    }

    @Override
    public void deleteAgent(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
