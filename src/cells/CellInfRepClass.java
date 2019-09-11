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

import agents.AgInfRepClass;
import agents.Agent;
import static misc.Params.bernoulli;
import misc.Params;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static misc.Params.arrayToString;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 */
public class CellInfRepClass extends Cell{
    
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //Array of states for every classe k
    private ArrayList<Integer> states;
    //nb agents for every classe k
    private ArrayList<Integer> nbsAgents;
    //Delta Agents moving to this cell
    private ArrayList<Agent> comingAgents;
    //Agents firing on this cell
    private ArrayList<Agent> firingAgents;
    //Excited cells
    private ArrayList<Boolean> excitedCells;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">

    /**
     *
     * @param param Parameters of the automaton
     */
    public CellInfRepClass(Params param) {
        super(param);
        agentInitializer();
        statesInitializer();
        this.comingAgents = new ArrayList<>();
        this.firingAgents = new ArrayList<>();
        excitedCellsInitializer();
    }
    
    /**
     *
     * @param param Parameters of the automaton
     * @param c color of the cell
     * @param i line coordinate
     * @param j column coordinate
     * @param w if the cell is a wall
     */
    public CellInfRepClass(Params param, Color c, int i, int j, boolean w) {
        super(param, c, i, j, w);
        agentInitializer();
        statesInitializer();
        this.comingAgents = new ArrayList<>();
        this.firingAgents = new ArrayList<>();
        excitedCellsInitializer();
    }
    
    CellInfRepClass(Params p, int i, int j, Color c, boolean wl, 
            ArrayList<Integer> states, ArrayList<Integer> nbsAgents, 
            ArrayList<Cell> nghbrs, ArrayList<Agent> comingAgents, 
            ArrayList<Agent> firingAgents, 
            ArrayList<Boolean> excitedCells){
        super(p, c, i, j, wl);
        statesInitializer();
        agentInitializer();
        Collections.copy(this.states, states);
        Collections.copy(this.nbsAgents, nbsAgents);
        this.nghbrs = nghbrs;
        this.comingAgents = comingAgents;
        this.firingAgents = firingAgents;
        excitedCellsInitializer();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
    /**
     *
     * @param param
     * @param state
     */
    public void setStates(ArrayList<Integer> states) {
        Collections.copy(this.states, states);
    }

    /**
     *
     * @param nb_agents
     */
    public void setNbsAgents(ArrayList<Integer> nbsAgents) {
        Collections.copy(this.nbsAgents, nbsAgents);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Integer> getNbsAgents() {
        return nbsAgents;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<Integer> getStates() {
        return this.states;
    }
    
    /**
     *
     * @param k
     * @param s
     */
    public void setStateAtK(int k, int s) {
        this.states.set(k, s);
    }

    /**
     *
     * @param k
     * @return
     */
    public int getNbAgentsAtK(int k){
        return this.nbsAgents.get(k);
    }
    
    /**
     *
     * @param k
     * @param value
     */
    public void setNbAgentsAtK(int k, int value){
        this.nbsAgents.set(k, value);
    }
    
    /**
     *
     * @param MLEVEL
     * @param k
     */
    public void setMaxStateAtK(int k) {
        this.states.set(k, maxLevel(k));
    }

    /**
     *
     * @param MLEVEL
     * @param k
     */
    public void setMinStateAtK(int k) {
        this.states.set(k, minLevel(k));
    }
    
    /**
     *
     * @param k
     * @param value
     */
    public void increaseNbAgentsAtK(int k, int value){
        int count = this.nbsAgents.get(k) + value;
        this.nbsAgents.set(k, count);
    }
    
    /**
     *
     * @param k
     * @param value
     */
    public void decreaseStateAtK(int k, int value){
        int count = this.states.get(k) - value;
        this.states.set(k, count);
    }
    
    /**
     *
     * @param k
     * @param value
     * @return
     */
    public boolean isNbAgentsSupThenAtK(int k, int value){
        return this.nbsAgents.get(k) > value;
    }
    
    /**
     *
     * @param MLEVEL
     * @param k
     * @return
     */
    public boolean isMaxStateAtK(int k){
        return this.states.get(k) == maxLevel(k);
    }
    
    /**
     *
     * @param MLEVEL
     * @param k
     * @return
     */
    public boolean isMinStateAtK(int k){
        return this.states.get(k) == minLevel(k);
    }
    
    /**
     *
     * @param MLEVEL
     * @param k
     * @return
     */
    public boolean isStateSupThenMinAtK(int k){
        return this.states.get(k) > minLevel(k);
    }
    
    /**
     *
     * @param k
     * @param value
     * @return
     */
    public boolean isNbAgentsEqualToAtK(int k, int value){
        return this.nbsAgents.get(k) == value;
    }
    
    @Override
    public CellInfRepClass getCopy(){
        return new CellInfRepClass(param, this.getI(), this.getJ(), this.getCouleur(), 
                this.isWall(), this.states, this.nbsAgents, this.nghbrs, 
                this.comingAgents,
                this.firingAgents,
                this.excitedCells);
    }

    /**
     *  Calculate Max level
     * @param MLEVEL
     * @param k
     * @return
     */
    public int maxLevel(int k){
        return k * (param.MLEVEL+1) + param.MLEVEL;
    }
    
    /**
     * Caculate Min
     * @param MLEVEL
     * @param k
     * @return
     */
    public int minLevel(int k){
        return k * (param.MLEVEL+1);
    }
    //</editor-fold>
    
    private void statesInitializer(){
        
        //initialisation avec le min de chaque classe
        //k représente la classe
        this.states = new ArrayList<>();        
        for (int k = 0; k < param.NB_CLASSES; k++) {
            int state = minLevel(k);
            this.states.add(state);  
        }
    }
    
    /**
     *
     * @param param
     */
    public void agentInitializer(){
        
        this.nbsAgents = new ArrayList<>();
        for (int i = 0; i < param.NB_CLASSES; i++) {
            //initialisation avec le min de chaque classe
            //i représente la classe
            this.nbsAgents.add(0);
        }
    }
    
    private void excitedCellsInitializer(){
        
        this.excitedCells = new ArrayList<>();        
        for (int k = 0; k < param.NB_CLASSES; k++) {
            this.excitedCells.add(false);  
        }
    }
    
    public void update(){
        
        int firingClasse = -1;
        //If there is agents firing choose one of them
        if(!firingAgents.isEmpty()){
            AgInfRepClass firingAgent = (AgInfRepClass) chooseRandAgent(firingAgents);
            firingClasse = firingAgent.getClasse();
            this.setMaxStateAtK(firingClasse);
        }
        //If neighbours are excited
        for (int k=0; k < param.NB_CLASSES; k++) {
            if(k != firingClasse){
                countNeighbours(k);
                if (this.isMinStateAtK(k) && excitedCells.get(k)){
                    this.setMaxStateAtK(k);
                }
                else if (this.isStateSupThenMinAtK(k)){
                    this.decreaseStateAtK(k, 1);
                }
                else{
                    this.setMinStateAtK(k);
                }
                //Move agents
                if(!comingAgents.isEmpty()){
                    ArrayList<Agent> comingAgentsClassK = getComingAgentsClassK(k);
                    if(this.nbsAgents.get(k) < 2 && !comingAgentsClassK.isEmpty()){
                        AgInfRepClass chosenAgent = (AgInfRepClass) chooseRandAgent(comingAgentsClassK);
                        chosenAgent.setI(this.getI());
                        chosenAgent.setJ(this.getJ());
                    }
                }
            }
        }
    }
    
    public void evolve(CellInfRepClass newMatrix) {
        
        if(this.isWall()){
            setCouleur(param.getCOLOR_OBSTACLE());
        }
        else{
            this.setStates(newMatrix.getStates());
            this.agentInitializer();
            this.colorier();

            //reinit all arrays
            this.comingAgents = new ArrayList<>();
            this.firingAgents = new ArrayList<>();
            excitedCellsInitializer();
        }
    }
    
    public void countNeighbours(int cls) {
        
        excited_free_cells_count = 0;
        excitedCellsInitializer();
        
        for(Cell c : nghbrs) {
            //if this neighbours isn't me or isn't a wall
            if(!c.isWall()){
                //if his state is excited
                if (((CellInfRepClass) c).isMaxStateAtK(cls)) {
                    //I add it
                    excitedCells.set(cls, true);
                }
            }
        }
    }
    
    @Override
    public void print(JTable infoTable) {
        super.print(infoTable);
        DefaultTableModel model = (DefaultTableModel) infoTable.getModel();
        
        model.setValueAt(getCellType(), 3, 1);
        model.setValueAt(arrayToString(this.states), 4, 1);
        model.setValueAt(arrayToString(this.nbsAgents), 5, 1);
        model.setValueAt("NEED TO BE IMPLEMENTED", 6, 1);
        
    }
    
    @Override
    public boolean isAgent(){
        for (Integer nbAgents : nbsAgents) {
            if (nbAgents >= 1)
                return true;
        }
        return false;
    }
    
    @Override
    public boolean isWave(){
        for (int k=0; k<param.NB_CLASSES; k++) {
            if (!isMinStateAtK(k))
                return true;
        }
        return false;
    }

    @Override
    public void colorier() {
        for(int k=0; k<param.NB_CLASSES; k++){
            if(isMaxStateAtK(k)){
                this.setCouleur(param.getCOLOR_at(k, 150));
                return;
            }
            else if (!isMinStateAtK(k)){
                this.setCouleur(param.getCOLOR_at(k, 75));
                return;
            }
            else{
                this.setCouleur(param.getCOLOR_DEFAULT());
            }
        }
    }

    public void makeAgent(int nb, Color color, boolean wl, int cls) {
        this.nbsAgents.set(cls, nb);
        this.setCouleur(color);
        this.setWall(wl);
    }
    
    public boolean isAttracting(int cls) {
        return (isMaxStateAtK(cls)
                    && nbsAgents.get(cls) < 2);
    }
    
    public boolean isRepulsing(int cls) {
        for (int k = 0; k < param.NB_CLASSES; k++) {
            if(isMaxStateAtK(k) && k != cls)
                return (bernoulli(param.REPULSION) == 1);
        }
        return false;
    }
    
    @Override
    public void addComingAgent(Agent aThis) {
        this.comingAgents.add(aThis);
    }
    
    @Override
    public void addFiringAgent(Agent aThis) {
        this.firingAgents.add(aThis);
    }

    private ArrayList<Agent> getComingAgentsClassK(int k) {
        ArrayList<Agent> cmnAgCK = new ArrayList<>();
        for (Agent cmnAg : comingAgents) {
            if(((AgInfRepClass)cmnAg).getClasse() == k){
                cmnAgCK.add(cmnAg);
            }
        }
        for (Agent cmnAg : cmnAgCK) {
                comingAgents.remove(cmnAg);
        }
        return cmnAgCK;
    }

    public boolean hasDifferentAgentClasses() {
        int cpt = 0;
        for (Integer nbAgent : nbsAgents) {
            if(nbAgent > 0) cpt++;
        }
        return (cpt > 1);
    }
}
