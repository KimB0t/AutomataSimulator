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
import static misc.Params.bernoulli;
import misc.Params;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 */
public class CellInfRepClass extends Cell{
    
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //Array of states for every classe k
    private int[] state;
    //nb agents for every classe k
    private int[] nb_agents;
    //List of neighbouring cells
    private CellInfRepClass[] neighbours;
    //list of excited neighbouring free cells of this cell 
    //to where the agent can possibly move
    private ArrayList<CellInfRepClass> excited_free_cells;
    //list of neighbouring free cells 
    //to where the agent can possibly move
    private ArrayList<CellInfRepClass> free_cells;
    //list of neighbouring free cells 
    //to where the agent is repulsed
    private ArrayList<CellInfRepClass> repulsive_free_cells;
    //list of agents willing to move to this cell
    private ArrayList<AgInfRepClass> concurent_agents;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * 
     * @param param Parameters of the automaton
     * @param c color of the cell
     * @param i line coordinate
     * @param j column coordinate
     * @param w if the cell is a wall
     * @param s array of states for all classes
     * @param nA array of nb of agents for every classe
     */
    public CellInfRepClass(Params param, Color c, int i, int j, boolean w, int[] s, int[] nA) {
        super(c, i, j, w);
        this.state = new int[param.NB_CLASSES];
        if(s!=null)
            System.arraycopy(s, 0, this.state, 0, param.NB_CLASSES);
        this.nb_agents = new int[param.NB_CLASSES];
        if(nA!=null)
            System.arraycopy(nA, 0, this.nb_agents, 0, param.NB_CLASSES);
    }
    
    /**
     *
     * @param param Parameters of the automaton
     * @param s array of states for all classes
     * @param nA array of nb of agents for every classe
     */
    public CellInfRepClass(Params param, int[] s, int[] nA) {
        super();
        this.state = new int[param.NB_CLASSES];
        if(s!=null)
            System.arraycopy(s, 0, this.state, 0, param.NB_CLASSES);
        this.nb_agents = new int[param.NB_CLASSES];
        if(nA!=null)
            System.arraycopy(nA, 0, this.nb_agents, 0, param.NB_CLASSES);
    }

    /**
     *
     * @param param Parameters of the automaton
     */
    public CellInfRepClass(Params param) {
        super();
        this.stateInitializer(param);
        this.agentInitializer(param);
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
        super(c, i, j, w);
        this.stateInitializer(param);
        this.agentInitializer(param);
    }
    
    /**
     *
     * @param c color of the cell
     * @param i line coordinate
     * @param j column coordinate
     * @param w if the cell is a wall
     */
        
    public CellInfRepClass(Color c, int i, int j, boolean w) {
        super(c, i, j, w);
        this.state = null;
        this.nb_agents = null;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
    /**
     *
     * @param param
     * @param state
     */
    public void setState(Params param, int[] state) {
        this.state = new int[param.NB_CLASSES];
        if(state!=null)
            System.arraycopy(state, 0, this.state, 0, param.NB_CLASSES);
    }

    /**
     *
     * @return
     */
    public int[] getNb_agents() {
        return nb_agents;
    }
    
    /**
     *
     * @param k
     * @param s
     */
    public void setStateAtK(int k, int s) {
        this.state[k] = s;
    }

    /**
     *
     * @param MLEVEL
     * @param k
     */
    public void setMaxAtK(int MLEVEL, int k) {
        this.state[k] = maxLevel(MLEVEL, k);
    }

    /**
     *
     * @param MLEVEL
     * @param k
     */
    public void setMinAtK(int MLEVEL, int k) {
        this.state[k] = minLevel(MLEVEL, k);
    }
    
    /**
     *
     * @return
     */
    public int[] getState() {
        return this.state;
    }
    
    /**
     *
     * @param k
     * @param value
     */
    public void increaseNbAgentsAtK(int k, int value){
        this.nb_agents[k] += value;
    }
    
    /**
     *
     * @param k
     * @param value
     * @return
     */
    public boolean isNbAgentsAtKsupThen(int k, int value){
        return this.nb_agents[k] > value;
    }
    
    /**
     *
     * @param k
     * @return
     */
    public int getNb_agentsAtK(int k){
        return this.nb_agents[k];
    }
    
    /**
     *
     * @param k
     * @param value
     */
    public void setNb_agentsAtK(int k, int value){
        this.nb_agents[k] = value;
    }
    
    private void stateInitializer(Params param){
        
        this.state = new int[param.NB_CLASSES];        
        for (int k = 0; k < param.NB_CLASSES; k++) {
            //initialisation avec le min de chaque classe
            this.state[k] = k * (param.MLEVEL+1); //k représente la classe
        }
    }
    
    /**
     *
     * @param MLEVEL
     * @param k
     * @return
     */
    public boolean isMaxStateAtK(int MLEVEL, int k){
        return this.state[k] == maxLevel(MLEVEL, k);
    }
    
    /**
     *
     * @param MLEVEL
     * @param k
     * @return
     */
    public boolean isMinStateAtK(int MLEVEL, int k){
        return this.state[k] == minLevel(MLEVEL, k);
    }
    
    /**
     *
     * @param MLEVEL
     * @param k
     * @return
     */
    public boolean isSupThenMinAtK(int MLEVEL, int k){
        return this.state[k] > minLevel(MLEVEL, k);
    }
    
    /**
     *
     * @param k
     * @param value
     */
    public void decreaseStateatK(int k, int value){
        this.state[k] -= value;
    }
    
    /**
     *
     * @param k
     * @param value
     * @return
     */
    public boolean nbAgentsAtKisSupThen(int k, int value){
        return this.nb_agents[k] > value;
    }
    
    /**
     *
     * @param k
     * @param value
     * @return
     */
    public boolean nbAgentsAtKisEqualTo(int k, int value){
        return this.nb_agents[k] == value;
    }
    
    @Override
    public CellInfRepClass getCopy(Params param){
        CellInfRepClass new_cell = new CellInfRepClass(param, this.getCouleur(),
                                this.getI(), this.getJ(), false, this.state,
                                this.nb_agents);
        
        new_cell.setExcited_free_cells(excited_free_cells);
        new_cell.setFree_cells(free_cells);
        new_cell.setRepulsive_free_cells(repulsive_free_cells);
        new_cell.setConcurent_agents(concurent_agents);
        return new_cell;
    }

    /**
     *
     * @param excited_free_cells
     */
    public void setExcited_free_cells(ArrayList<CellInfRepClass> excited_free_cells) {
        this.excited_free_cells = excited_free_cells;
    }

    /**
     *
     * @param free_cells
     */
    public void setFree_cells(ArrayList<CellInfRepClass> free_cells) {
        this.free_cells = free_cells;
    }

    /**
     *
     * @param concurent_agents
     */
    public void setConcurent_cells(ArrayList<AgInfRepClass> concurent_agents) {
        this.concurent_agents = concurent_agents;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<CellInfRepClass> getExcited_free_cells() {
        return excited_free_cells;
    }

    /**
     *
     * @return
     */
    public ArrayList<CellInfRepClass> getFree_cells() {
        return free_cells;
    }

    /**
     *
     * @return
     */
    public ArrayList<AgInfRepClass> getConcurent_agents() {
        return concurent_agents;
    }
    
    /**
     *
     * @return
     */
    public boolean isEmptyFreeCells(){
        return this.free_cells.isEmpty();
    }
    
    /**
     *
     * @param i
     * @return
     */
    public CellInfRepClass getElementFreeCells(int i){
        return this.free_cells.get(i);
    }
    
    /**
     *
     * @return
     */
    public int getSizeFreeCells(){
        return this.free_cells.size();
    }
    
    /**
     *
     * @return
     */
    public boolean isEmptyFreeExcitedCells(){
        return this.excited_free_cells.isEmpty();
    }
    
    /**
     *
     * @param i
     * @return
     */
    public CellInfRepClass getElementFreeExcitedCells(int i){
        return this.excited_free_cells.get(i);
    }
    
    /**
     *
     * @return
     */
    public int getSizeFreeExcitedCells(){
        return this.excited_free_cells.size();
    }
    
    /**
     *
     * @param cell
     */
    public void addFreeExcitedCells(CellInfRepClass cell){
        this.excited_free_cells.add(cell);
    }
    
    /**
     *
     * @param cell
     */
    public void addFreeCells(CellInfRepClass cell){
        this.free_cells.add(cell);
    }

    /**
     *
     * @param repulsive_free_cells
     */
    public void setRepulsive_free_cells(ArrayList<CellInfRepClass> repulsive_free_cells) {
        this.repulsive_free_cells = repulsive_free_cells;
    }

    /**
     *
     * @return
     */
    public ArrayList<CellInfRepClass> getRepulsive_free_cells() {
        return repulsive_free_cells;
    }
    
    /**
     *
     * @param cell
     */
    public void addFreeRepulsiveCells(CellInfRepClass cell){
        this.repulsive_free_cells.add(cell);
    }
    
    /**
     *
     * @return
     */
    public boolean isEmptyFreeRepulsiveCells(){
        return this.repulsive_free_cells.isEmpty();
    }
    
    /**
     *
     * @param i
     * @return
     */
    public CellInfRepClass getElementFreeRepulsiveCells(int i){
        return this.repulsive_free_cells.get(i);
    }
    
    /**
     *
     * @return
     */
    public int getSizeFreeRepulsiveCells(){
        return this.repulsive_free_cells.size();
    }

    /**
     *
     * @param state
     */
    public void setState(int[] state) {
        this.state = state;
    }

    /**
     *
     * @param nb_agents
     */
    public void setNb_agents(int[] nb_agents) {
        this.nb_agents = nb_agents;
    }

    /**
     *
     * @param neighbours
     */
    public void setNeighbours(CellInfRepClass[] neighbours) {
        this.neighbours = neighbours;
    }

    /**
     *
     * @param concurent_agents
     */
    public void setConcurent_agents(ArrayList<AgInfRepClass> concurent_agents) {
        this.concurent_agents = concurent_agents;
    }

    /**
     *
     * @return
     */
    public CellInfRepClass[] getNeighbours() {
        return neighbours;
    }
    
    /**
     *
     * @return
     */
    public boolean isThereAgents(){
        
        for (int i = 0; i < nb_agents.length; i++) {
            if(nb_agents[i] > 0)
                return true;
        }
        return false;
    }
    //</editor-fold>
    
    /**
     *
     * @param param
     */
    public void agentInitializer(Params param){
        
        this.nb_agents = new int[param.NB_CLASSES];
        for (int i = 0; i < param.NB_CLASSES; i++) {
            //initialisation avec le min de chaque classe
            //i représente la classe
            this.nb_agents[i] = 0;
        }
    }
    
    /**
     * 
     * @param reinitConcurentAlso to reinitialize concurent array also
     */
    public void reinitArrays(boolean reinitConcurentAlso){
        neighbours = null;
        excited_free_cells = null;
        free_cells = null;
        repulsive_free_cells = null;
        if(reinitConcurentAlso) concurent_agents = null;
    }
    
    /**
     *
     * @param p
     * @param k
     */
    @Override
    public void countNeighbours(Params p, int k) {
        
        //reinit all arrays
        this.setExcited_free_cells_count(0); 
        this.setFree_cells(new ArrayList<>());
        this.setExcited_free_cells(new ArrayList<>());
        this.setRepulsive_free_cells(new ArrayList<>());

        for(int i=0; i<8; i++) {
            //if this neighbours isn't me or isn't a wall
            if(!this.neighbours[i].isWall()){
                //if his state is excited
                if (this.neighbours[i].isMaxStateAtK(p.MLEVEL, k)) {
                    //I count it
                    this.excited_free_cells_count++;
                    //if nb agents for this class on this neighbour is < 2
                    if (this.neighbours[i].nb_agents[k] < 2)
                        //then I add it to neighbours set
                        this.addFreeExcitedCells(this.neighbours[i]);
                }
                //Repulsion
                //TEstet toutes les autres vagues pour voir si y'a une qui repousse
                else{
                    //Pour toutes les classes de vague
                    for(int cl=0; cl<p.NB_CLASSES; cl++){
                        //Si cette cell est excité pour cette classe et 
                        //si la proba qu'elle repulse est vrai
                        if(this.neighbours[i].isMaxStateAtK(p.MLEVEL, cl) 
                                && bernoulli(p.REPULSION) == 1){

                            int di = (this.getI() - this.neighbours[i].getI()) * -1;
                            int dj = (this.getJ() - this.neighbours[i].getJ()) * -1;
                            
                            int ii = (this.getI() - di + p.MATRIX_LENGTH) % p.MATRIX_LENGTH;
                            int jj = (this.getJ() - dj + p.MATRIX_LENGTH) % p.MATRIX_LENGTH;
                            for (int j = 0; j < neighbours.length; j++) {
                                if(neighbours[j].getI() == ii && neighbours[j].getJ() == jj
                                        && neighbours[j].getNb_agentsAtK(k) < 2)
                                    this.addFreeRepulsiveCells(neighbours[j]);
                            }
                        }
                    }
                }
                //si sont voisin est libre 
                if (this.neighbours[i].getNb_agentsAtK(k) < 2) {
                    this.addFreeCells(this.neighbours[i]);
                }
            }
        }
    }

    @Override
    public void nextState(Params param) {
        
        param.VAGUE = false;
        param.FIRST_TO_FIRE = true;
        //for all classes
        for (int k = 0; k < param.NB_CLASSES; k++) {
            //count excited neighbours for this class
            countNeighbours(param, k);
            param.VAGUE = expandWave(param, k);
            param.FIRST_TO_FIRE = !param.VAGUE;
        }
    }
    
    /**
     *
     * @param param
     * @return
     */
    public AgInfRepClass chooseAgent(Params param){
        
        AgInfRepClass ag = null;
        
        //MàJ Agents + couleurs
        if(this.concurent_agents != null) {
            
            switch(param.POLICY){
                case CYCLIC:
                    //Normalement qlq soit le nombre d'agent on prendra tjrs le 1er et le reste
                    //on les prends pas, ils ne vont pas changer dans _ag.
                    ag = this.concurent_agents.get(0);
                    break;
                case RANDOM:
                    int i = Params.RAND.nextInt(this.concurent_agents.size());
                    ag = this.concurent_agents.get(i);
                    break;
                default:
                    break;
            }
        }
        return ag;
    }
    
    /**
     *
     * @param param
     * @param cl
     */
    public void colorier(Params param, int cl){
        
        increaseNbAgentsAtK(cl, 1);
        //Setting colors
        if(nbAgentsAtKisEqualTo(cl, 1)){
            setCouleur(param.getCOLOR_at(cl));
        }
        else{
            setCouleur(param.getCOLOR_at(cl, 255));
        }

        //This part is just for coloring with black when there are multiple classes in same cell
        int a = 0, kk = 0;
        while(a<2 && kk<param.NB_CLASSES){
            if(isNbAgentsAtKsupThen(kk, 0))
                a++;
            kk++;
        }
        if(a>=2) setCouleur(Color.BLACK);
    }
    
    /**
     *
     * @param param
     * @param k
     * @return
     */
    public boolean expandWave(Params param, int k) {
        
        if (this.isMinStateAtK(param.MLEVEL, k) //test min
                && (isSupThen(0)
                    || (param.FIRST_TO_FIRE //Comme ça seulement le premier qui fire peut fire
                        && this.nbAgentsAtKisSupThen(k, 0) 
                        && bernoulli(param.LAMBDA) == 1))){
            this.setMaxAtK(param.MLEVEL, k); //put max
            //coloring
            this.setCouleur(param.getCOLOR_at(k, 100));
            param.VAGUE = true;
        }
        else if (this.isSupThenMinAtK(param.MLEVEL, k)){

            this.decreaseStateatK(k, 1);
            if(this.isSupThenMinAtK(param.MLEVEL, k)) {
                this.setCouleur(param.getCOLOR_at(k, 100));
                param.VAGUE = true;
            }
        }
        else{
            this.setMinAtK(param.MLEVEL, k);
            if(!param.VAGUE) this.setCouleur(param.COLORS.COLOR_DEFAULT);
        }
        return param.VAGUE;
    }
    
    /**
     *  Calculate Max level
     * @param MLEVEL
     * @param k
     * @return
     */
    public int maxLevel(int MLEVEL, int k){
        return k * (MLEVEL+1) + MLEVEL;
    }
    
    /**
     * Caculate Min
     * @param MLEVEL
     * @param k
     * @return
     */
    public int minLevel(int MLEVEL, int k){
        return k * (MLEVEL+1);
    }
    
    /**
     *
     * @param ag
     */
    public void addConcurent_agents(AgInfRepClass ag){
        if(concurent_agents == null){
            concurent_agents = new ArrayList<>();
            concurent_agents.add(ag);
        }
        else{
            concurent_agents.add(ag);
        }
    }
    
    /**
     *
     * @return
     */
    @Override
    public int getNbAgents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param k
     * @return
     */
    @Override
    public int getNbAgents(int k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    @Override
    public void countNeighbours(Params p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //<editor-fold defaultstate="collapsed" desc="Useless">

    /**
     *
     * @param msg
     */
        public void printCell(String msg){
//        System.out.println(msg);
        if(concurent_agents != null)// && free_cells != null && excited_free_cells != null)
        System.out.println(
                msg + 
                " | i : " + this.getI()+
                " | j: " + this.getJ()+
                " | nbAgent: " + Arrays.toString(this.nb_agents)+
                " | Wall: " + this.isWall()+
                " | State: " + Arrays.toString(this.state)+
                " | Count: " + excited_free_cells_count+
                " | FC: " + this.free_cells.toString()+
                " | EFC: " + this.excited_free_cells.toString()+
                " | Nghbrs: " + Arrays.toString(this.neighbours)+
                " | CCAGENTS: " + this.concurent_agents.toString()+
                " | Color: " + this.getCouleur());
        else
            System.out.println(
                msg + 
                " | i : " + this.getI()+
                " | j: " + this.getJ()+
                " | nbAgent: " + Arrays.toString(this.nb_agents)+
                " | Wall: " + this.isWall()+
                " | State: " + Arrays.toString(this.state)+
                " | Count: " + excited_free_cells_count+
                " | FC: " + this.free_cells.toString()+
                " | EFC: " + this.excited_free_cells.toString()+
                " | Nghbrs: " + Arrays.toString(this.neighbours)+
                " | Color: " + this.getCouleur());
    }
    
    /**
     *
     * @return
     */
    public String printConcurents(){
        String msg = "[";
        
        for (int i = 0; i < concurent_agents.size(); i++) {
            msg += concurent_agents.get(i).getId()+", ";
        }
        msg+="]";
        return msg;
    }
    
    /**
     *
     * @return
     */
    public String getNb_agentsTostring(){
        String msg = "[";
        
        for (int i = 0; i < nb_agents.length; i++) {
            msg += nb_agents[i]+", ";
        }
        msg+="]";
        return msg;
    }
//</editor-fold>

    public void makeAgent(int nb, Color co, boolean wl, int cls) {
        setCouleur(co);
        setWall(wl);
        setNb_agentsAtK(cls, nb);
    }
    
}
