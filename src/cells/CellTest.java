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

package cells;

import agents.AgTest;
import data.DataTest;
import java.awt.Color;
import java.util.ArrayList;
import misc.Params;
import static misc.Params.bernoulli;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 * @version 1.0
 */
public class CellTest extends Cell{

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //State of the cell
    private int state;
    //nb agents
    private int nbAgents;
    //List of neighbouring cells
    private CellTest[] neighbours;
    //list of excited neighbouring free cells of this cell 
    //to where the agent can possibly move
    private ArrayList<CellTest> excited_free_cells;
    //list of neighbouring free cells 
    //to where the agent can possibly move
    private ArrayList<CellTest> free_cells;
    //list of neighbouring free cells 
    //to where the agent is repulsed
    private ArrayList<CellTest> repulsive_free_cells;
    //list of agents willing to move to this cell
    private ArrayList<AgTest> concurent_agents;
    //Data sent by agents
    private DataTest data;
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
    public CellTest(Params param, Color c, int i, int j, boolean w, int s, int nA) {
        super(c, i, j, w);
        this.state = s;
        this.nbAgents = nA;
    }
    
    /**
     *
     * @param param Parameters of the automaton
     * @param s array of states for all classes
     * @param nA array of nb of agents for every classe
     */
    public CellTest(Params param, int s, int nA) {
        super();
        this.state = s;
        this.nbAgents = nA;
    }

    /**
     *
     * @param param Parameters of the automaton
     */
    public CellTest(Params param) {
        super();
    }
    
    /**
     *
     * @param param Parameters of the automaton
     * @param c color of the cell
     * @param i line coordinate
     * @param j column coordinate
     * @param w if the cell is a wall
     */
    public CellTest(Params param, Color c, int i, int j, boolean w) {
        super(c, i, j, w);
    }
    
    /**
     *
     * @param c color of the cell
     * @param i line coordinate
     * @param j column coordinate
     * @param w if the cell is a wall
     */
        
    public CellTest(Color c, int i, int j, boolean w) {
        super(c, i, j, w);
        this.state = 0;
        this.nbAgents = 0;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
    /**
     *
     * @param param
     * @param state
     */
    public void setState(Params param, int state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    @Override
    public int getNbAgents() {
        return nbAgents;
    }

    /**
     *
     * @param MLEVEL
     * @param k
     */
    public void setMax(int MLEVEL) {
        this.state = maxLevel(MLEVEL);
    }

    /**
     *
     * @param MLEVEL
     * @param k
     */
    public void setMin(int MLEVEL) {
        this.state = minLevel(MLEVEL);
    }
    
    /**
     *
     * @return
     */
    public int getState() {
        return this.state;
    }
    
    /**
     *
     * @param k
     * @param value
     */
    public void increaseNbAgents(int value){
        this.nbAgents += value;
    }
    
    /**
     *
     * @param k
     * @param value
     * @return
     */
    public boolean isNbAgentssupThen(int value){
        return this.nbAgents > value;
    }
    
    /**
     *
     * @param k
     * @return
     */
    public int getNbAgent(){
        return this.nbAgents;
    }
    
    /**
     *
     * @param k
     * @param value
     */
    public void setNbAgents(int value){
        this.nbAgents = value;
    }
    
    /**
     *
     * @param MLEVEL
     * @param k
     * @return
     */
    public boolean isMaxState(int MLEVEL){
        return this.state == maxLevel(MLEVEL);
    }
    
    /**
     *
     * @param MLEVEL
     * @param k
     * @return
     */
    public boolean isMinState(int MLEVEL){
        return this.state == minLevel(MLEVEL);
    }
    
    /**
     *
     * @param MLEVEL
     * @param k
     * @return
     */
    public boolean isSupThenMin(int MLEVEL){
        return this.state > minLevel(MLEVEL);
    }
    
    /**
     *
     * @param k
     * @param value
     */
    public void decreaseState(int value){
        this.state -= value;
    }
    
    /**
     *
     * @param k
     * @param value
     * @return
     */
    public boolean nbAgentsIsSupThen(int value){
        return this.nbAgents > value;
    }
    
    /**
     *
     * @param k
     * @param value
     * @return
     */
    public boolean nbAgentsIsEqualTo(int value){
        return this.nbAgents == value;
    }
    
    @Override
    public CellTest getCopy(Params param){
        CellTest new_cell = new CellTest(param, this.getCouleur(),
                                this.getI(), this.getJ(), false, this.state,
                                this.nbAgents);
        
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
    public void setExcited_free_cells(ArrayList<CellTest> excited_free_cells) {
        this.excited_free_cells = excited_free_cells;
    }

    /**
     *
     * @param free_cells
     */
    public void setFree_cells(ArrayList<CellTest> free_cells) {
        this.free_cells = free_cells;
    }

    /**
     *
     * @param concurent_agents
     */
    public void setConcurent_cells(ArrayList<AgTest> concurent_agents) {
        this.concurent_agents = concurent_agents;
    }
    
    /**
     *
     * @return
     */
    public ArrayList<CellTest> getExcited_free_cells() {
        return excited_free_cells;
    }

    /**
     *
     * @return
     */
    public ArrayList<CellTest> getFree_cells() {
        return free_cells;
    }

    /**
     *
     * @return
     */
    public ArrayList<AgTest> getConcurent_agents() {
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
    public CellTest getElementFreeCells(int i){
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
    public CellTest getElementFreeExcitedCells(int i){
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
    public void addFreeExcitedCells(CellTest cell){
        this.excited_free_cells.add(cell);
    }
    
    /**
     *
     * @param cell
     */
    public void addFreeCells(CellTest cell){
        this.free_cells.add(cell);
    }

    /**
     *
     * @param repulsive_free_cells
     */
    public void setRepulsive_free_cells(ArrayList<CellTest> repulsive_free_cells) {
        this.repulsive_free_cells = repulsive_free_cells;
    }

    /**
     *
     * @return
     */
    public ArrayList<CellTest> getRepulsive_free_cells() {
        return repulsive_free_cells;
    }
    
    /**
     *
     * @param cell
     */
    public void addFreeRepulsiveCells(CellTest cell){
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
    public CellTest getElementFreeRepulsiveCells(int i){
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
    public void setState(int state) {
        this.state = state;
    }

    /**
     *
     * @param neighbours
     */
    public void setNeighbours(CellTest[] neighbours) {
        this.neighbours = neighbours;
    }

    /**
     *
     * @param concurent_agents
     */
    public void setConcurent_agents(ArrayList<AgTest> concurent_agents) {
        this.concurent_agents = concurent_agents;
    }

    /**
     *
     * @return
     */
    public CellTest[] getNeighbours() {
        return neighbours;
    }
    
    /**
     *
     * @return
     */
    public boolean isThereAgents(){
        return this.nbAgents > 0;
    }

    public void setData(DataTest data) {
        this.data = data;
    }

    public DataTest getData() {
        return data;
    }

    public int getExcited_free_cells_count() {
        return excited_free_cells_count;
    }
    
    //</editor-fold>
    
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
    public void countNeighbours(Params p) {
        
        //reinit all arrays
        this.setExcited_free_cells_count(0); 
        this.setFree_cells(new ArrayList<>());
        this.setExcited_free_cells(new ArrayList<>());
        this.setRepulsive_free_cells(new ArrayList<>());

        for(int i=0; i<8; i++) {
            //if this neighbours isn't a wall
            if(!this.neighbours[i].isWall()){
                //if his state is excited
                if (this.neighbours[i].isMaxState(p.MLEVEL)) {
                    //I count it
                    this.excited_free_cells_count++;
                    //if nb agents for this class on this neighbour is < 2
                    if (this.neighbours[i].nbAgents < 2)
                        //then I add it to neighbours set
                        this.addFreeExcitedCells(this.neighbours[i]);
                }
                //Repulsion
                //TEstet toutes les autres vagues pour voir si y'a une qui repousse
//                else{
//                    //Pour toutes les classes de vague
//                    for(int cl=0; cl<p.NB_CLASSES; cl++){
//                        //Si cette cell est excité pour cette classe et 
//                        //si la proba qu'elle repulse est vrai
//                        if(this.neighbours[i].isMaxState(p.MLEVEL) 
//                                && bernoulli(p.REPULSION) == 1){
//
//                            int di = (this.getI() - this.neighbours[i].getI()) * -1;
//                            int dj = (this.getJ() - this.neighbours[i].getJ()) * -1;
//                            
//                            int ii = (this.getI() - di + p.MATRIX_LENGTH) % p.MATRIX_LENGTH;
//                            int jj = (this.getJ() - dj + p.MATRIX_LENGTH) % p.MATRIX_LENGTH;
//                            for (int j = 0; j < neighbours.length; j++) {
//                                if(neighbours[j].getI() == ii && neighbours[j].getJ() == jj
//                                        && neighbours[j].getNbAgents() < 2)
//                                    this.addFreeRepulsiveCells(neighbours[j]);
//                            }
//                        }
//                    }
//                }
                //si sont voisin est libre 
                if (this.neighbours[i].getNbAgent() < 2) {
                    this.addFreeCells(this.neighbours[i]);
                }
            }
        }
    }

    @Override
    public void nextState(Params param) {
        
        //count excited neighbours for this class
        countNeighbours(param);
        
        
        /*
        MAKE THIS WORK
        NEEEDD TO SEE HEERRREEE
        NEEEDD TO SEE HEERRREEE
        NEEEDD TO SEE HEERRREEE
        NEEEDD TO SEE HEERRREEE
        NEEEDD TO SEE HEERRREEE
        */
        expandWave(param);
        /*
        NEEEDD TO SEE HEERRREEE
        NEEEDD TO SEE HEERRREEE
        NEEEDD TO SEE HEERRREEE
        NEEEDD TO SEE HEERRREEE
        NEEEDD TO SEE HEERRREEE
        NEEEDD TO SEE HEERRREEE
        NEEEDD TO SEE HEERRREEE
        */
    }
    
    /**
     *
     * @param param
     * @return
     */
    public AgTest chooseAgent(Params param){
        
        AgTest ag = null;
        
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
        if(ag != null) {
            this.data = ag.getData();
        }
        return ag;
    }
    
    /**
     *
     * @param param
     * @return
     */
    public DataTest chooseData(Params param){
        
        DataTest dt = null;
        
        //MàJ Agents + couleurs
        if(this.excited_free_cells != null) {
            
            switch(param.POLICY){
                case CYCLIC:
                    //Normalement qlq soit le nombre d'agent on prendra tjrs le 1er et le reste
                    //on les prends pas, ils ne vont pas changer dans _ag.
                    dt = this.excited_free_cells.get(0).getData();
                    break;
                case RANDOM:
                    int i = Params.RAND.nextInt(this.excited_free_cells.size());
                    dt = this.excited_free_cells.get(i).getData();
                    break;
                default:
                    break;
            }
        }
        return dt;
    }
    
    /**
     *
     * @param param
     * @param cl
     */
    public void colorier(Params param, int cl){
        
        increaseNbAgents(1);
        //Setting colors
        if(nbAgentsIsEqualTo(1)){
            setCouleur(param.getCOLOR_at(cl));
        }
        else{
            setCouleur(param.getCOLOR_at(cl, 255));
        }

        //This part is just for coloring with black when there are multiple classes in same cell
//        int a = 0, kk = 0;
//        while(a<2 && kk<param.NB_CLASSES){
//            if(isNbAgentssupThen(kk))
//                a++;
//            kk++;
//        }
//        if(a>=2) setCouleur(Color.BLACK);
    }
    
    /**
     *
     * @param param
     * @param k
     */
    public void expandWave(Params param) {
        
        //vague se deplace
        if (this.isMinState(param.MLEVEL) 
                && isSupThen(0)){
            this.setMax(param.MLEVEL); //put max
            this.data = chooseData(param);
            //coloring
            this.setCouleur(param.getCOLOR_EXCITED());
        }
        //la vague se crée
        else if(this.isMinState(param.MLEVEL)
                && this.nbAgentsIsSupThen(0) 
                        && bernoulli(param.LAMBDA) == 1){
            //choose an agent
            AgTest ag = chooseAgent(param);
            this.setMax(param.MLEVEL); //put max
            this.data = ag.getData();
            //coloring
            this.setCouleur(param.getCOLOR_EXCITED());
        }
        else if (this.isSupThenMin(param.MLEVEL)){

            this.decreaseState(1);
            this.data = null;
            if(this.isSupThenMin(param.MLEVEL)) {
                this.setCouleur(param.getCOLOR_EXCITED2());
            }
            else {
                this.setCouleur(param.getCOLOR_DEFAULT());
            }
        }
        else{
            this.setMin(param.MLEVEL);
            this.data = null;
            this.setCouleur(param.COLORS.COLOR_DEFAULT);
        }
    }
    
    /**
     *  Calculate Max level
     * @param MLEVEL
     * @return
     */
    public int maxLevel(int MLEVEL){
        return MLEVEL;
    }
    
    /**
     * Caculate Min
     * @param MLEVEL
     * @param k
     * @return
     */
    public int minLevel(int MLEVEL){
        return 0;
    }
    
    /**
     *
     * @param ag
     */
    public void addConcurent_agents(AgTest ag){
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
     * @param k
     * @return
     */
    @Override
    public int getNbAgents(int k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //<editor-fold defaultstate="collapsed" desc="Useless">

    /**
     *
     * @param msg
     */
        public void printCell(String msg){
//        System.out.println(msg);
//        if(concurent_agents != null)// && free_cells != null && excited_free_cells != null)
//        System.out.println(
//                msg + 
//                " | i : " + this.getI()+
//                " | j: " + this.getJ()+
//                " | nbAgent: " + Arrays.toString(this.nbAgents)+
//                " | Wall: " + this.isWall()+
//                " | State: " + Arrays.toString(this.state)+
//                " | Count: " + excited_free_cells_count+
//                " | FC: " + this.free_cells.toString()+
//                " | EFC: " + this.excited_free_cells.toString()+
//                " | Nghbrs: " + Arrays.toString(this.neighbours)+
//                " | CCAGENTS: " + this.concurent_agents.toString()+
//                " | Color: " + this.getCouleur());
//        else
//            System.out.println(
//                msg + 
//                " | i : " + this.getI()+
//                " | j: " + this.getJ()+
//                " | nbAgent: " + Arrays.toString(this.nbAgents)+
//                " | Wall: " + this.isWall()+
//                " | State: " + Arrays.toString(this.state)+
//                " | Count: " + excited_free_cells_count+
//                " | FC: " + this.free_cells.toString()+
//                " | EFC: " + this.excited_free_cells.toString()+
//                " | Nghbrs: " + Arrays.toString(this.neighbours)+
//                " | Color: " + this.getCouleur());
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
//</editor-fold>

    public void makeAgent(int nb, Color co, boolean wl, int cls) {
        setCouleur(co);
        setWall(wl);
        setNbAgents(nb);
    }

    @Override
    public void countNeighbours(Params p, int k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
