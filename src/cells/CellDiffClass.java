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

import agents.Agent;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import static misc.Params.RAND;
import static misc.Params.bernoulli;
import misc.Params;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06 at gmail.com>
 */
public class CellDiffClass extends Cell{
    
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    //NB agents on this cell
    private int nbAgents;
    //Array of states
    private ArrayList<Integer> states;
    //Next position for the agent of this cell to move on (delta)
    private Point delta;
    //Classe of the cell
    private int classe;
    //for when the cell is reserved by the first agent that reserves it
    private boolean reserved;
    //list of excited neighbouring free cells of this cell 
    //to where the agent can possibly move
    private ArrayList<Cell> excited_free_cells;
    //list of neighbouring free cells 
    //to where the agent can possibly move
    private ArrayList<Cell> free_cells;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public CellDiffClass(Params param){
        super(param);
        this.nbAgents = 0;
        stateInitializer();
        this.delta = new Point(-1, -1);
        this.classe = -1;
        this.reserved = false;
    }
    
    public CellDiffClass(Params param, int nbA, Color c, int i, int j, boolean w){
        super(param, c, i, j, w);
        this.nbAgents = nbA;
        stateInitializer();
        this.delta = new Point(-1, -1);
        this.classe = -1;
        this.reserved = false;
    }
    
    public CellDiffClass(Params param, int nbA, Color c, int i, int j, boolean w, 
            ArrayList<Integer> s, Point p, int cl, boolean r, 
            ArrayList<Cell> nghbrs, ArrayList<Cell> excited_free_cells,
            ArrayList<Cell> free_cells) {
        super(param, c, i, j, w);
        this.nbAgents = nbA;
        stateInitializer();
        Collections.copy(this.states, s);
        this.delta = new Point(p);
        this.classe = cl;
        this.reserved = r;
        this.nghbrs = nghbrs;
        this.excited_free_cells = excited_free_cells;
        this.free_cells = free_cells;
    }
    
    public CellDiffClass(Params param, Color co, int i, int j, boolean wall){
        super(param, co, i, j, wall);
        this.nbAgents = 0;
        this.states = null;
        this.delta = new Point(-1, -1);
        this.classe = -1;
        this.reserved = false;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public void setState(ArrayList<Integer> state) {
        this.stateInitializer();
        Collections.copy(this.states, states);
    }

    public void setDelta(Point p) {
        this.delta.setLocation(p);
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void setStateAtK(int k, int s) {
        this.states.set(k, s);
    }

    public void setMaxAtK(int k) {
        this.states.set(k, maxLevel(k));
    }

    public void setMinAtK(int k) {
        this.states.set(k, minLevel(k));
    }
    
    public ArrayList getStates() {
        return this.states;
    }

    public Point getDelta() {
        return this.delta;
    }

    public int getClasse() {
        return classe;
    }

    public boolean isReserved() {
        return reserved;
    }
    
    /**
     *
     * @param nbAgents
     */
    public void setNbAgents(int nbAgents) {
        this.nbAgents = nbAgents;
    }
    
    public int getNbAgents() {
        return nbAgents;
    }
    
    /**
     * decrease number of agents on this cell
     * @param num - number of agents to substract
     */
    public void decreaseNbAgents(int num){
        this.nbAgents -= num;
    }
    
    /**
     * increase number of agents on this cell
     * @param num - number of agents to add
     */
    public void increaseNbAgents(int num){
        this.nbAgents += num;
    }
    
    private void stateInitializer(){
        states = new ArrayList<>();
        for (int i = 0; i < param.NB_CLASSES; i++) {
            //initialisation avec le min de chaque classe
            states.add(i * (param.MLEVEL+1)); //i représente la classe
        }
    }
    
    /**
     *  Calculate Max level
     * @param k
     * @return
     */
    public int maxLevel(int k){
        return k * (param.MLEVEL+1) + param.MLEVEL;
    }
    
    /**
     * Caculate Min
     * @param param
     * @param k
     * @return
     */
    public int minLevel(int k){
        return k * (param.MLEVEL+1);
    }
    
    
    public CellDiffClass getCopy() {
        
        return new CellDiffClass(param, this.nbAgents, this.getCouleur(),
                this.getI(), this.getJ(), this.isWall(), this.states, 
                this.delta, this.classe, this.reserved, this.nghbrs,
                this.excited_free_cells, this.free_cells);
    }
    
    public void setExcited_free_cells(ArrayList<Cell> excited_free_cells) {
        this.excited_free_cells = excited_free_cells;
    }

    public void setFree_cells(ArrayList<Cell> free_cells) {
        this.free_cells = free_cells;
    }

    public ArrayList<Cell> getExcited_free_cells() {
        return excited_free_cells;
    }

    public ArrayList<Cell> getFree_cells() {
        return free_cells;
    }
    
    public boolean isEmptyFreeCells(){
        return this.free_cells.isEmpty();
    }
    
    public Cell getElementFreeCells(int i){
        return this.free_cells.get(i);
    }
    
    public int getSizeFreeCells(){
        return this.free_cells.size();
    }
    
    public boolean isEmptyFreeExcitedCells(){
        return this.excited_free_cells.isEmpty();
    }
    
    public Cell getElementFreeExcitedCells(int i){
        return this.excited_free_cells.get(i);
    }
    
    public int getSizeFreeExcitedCells(){
        return this.excited_free_cells.size();
    }
    
    public void addFreeExcitedCells(Cell cell){
        this.excited_free_cells.add(cell);
    }
    
    public void addFreeCells(Cell cell){
        this.free_cells.add(cell);
    }
    
    public boolean isThereADelta(){
        return (this.delta.getX() != -1 && this.delta.getY() != -1);
    }
    
    public void makeAgent(int i, int j, int nb, Color co, boolean wl, int cl){
        this.setNbAgents(nb);
        this.setCouleur(co);
        this.setWall(wl);
        this.setClasse(cl);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Comparators">
    public boolean isMaxStateAtK(int k){
        return this.states.get(k) == maxLevel(k);
    }
    
    public boolean isMinStateAtK(int k){
        return this.states.get(k) == minLevel(k);
    }
    
    public boolean isSupThenMinAtK(int k){
        return this.states.get(k) > minLevel(k);
    }
    
    public void decreaseStateatK(int k, int value){
        int a = this.states.get(k) - value;
        this.states.set(k, a);
    }
    //</editor-fold>

    
    public void nextState() {
        if(nbAgents>0){
            countNeighbours(this.classe);

            //Agent Mvt
            if(bernoulli(param.PA) == 1) {
                if (!isEmptyFreeCells()) {
                    //choose a random neighbour
                    Cell delta = getElementFreeCells(
                            RAND.nextInt(getSizeFreeCells()));
                    Point new_Delta = new Point(delta.getI(), delta.getJ());
                    //put new location
                    setDelta(new_Delta);
                }
            }
            //if state of current cell == min && there are accecible neighbours to move to
            else if(isMinStateAtK(this.classe)) {
                if (!isEmptyFreeExcitedCells()) {
                    //choose a random neighbour
                    Cell delta = getElementFreeExcitedCells(
                            RAND.nextInt(getSizeFreeExcitedCells()));
                    Point new_Delta = new Point(delta.getI(), delta.getJ());
                    //put new location
                    setDelta(new_Delta);
                }
            }
        }
        
        //this variables assures that waves are displayed correctly
        //if the first vague is diplayed, others do not
        //if the first is not displayed, the second will be
        //and the rest will not
        //and so on...
        param.VAGUE = false;

        //Expantion de la Vague
        //for all classes
        for (int kk = 0; kk < param.NB_CLASSES; kk++) {
            //count excited neighbours for this class
            countNeighbours(kk);
            param.VAGUE = expandWave(kk);
        }
    }
    
    public boolean expandWave(int k) {
        
        if (this.isMinStateAtK(k) //test min
            && (isSupThen(0)
                || (this.nbAgents > 0 
                    && this.getClasse() == k 
                    && bernoulli(param.LAMBDA) == 1))){
//            System.out.println("EXCITEDDDDDDDDDDDDDDD");
            this.setMaxAtK(k); //put max
            //coloring
            this.setCouleur(param.getCOLOR_at(k, 100));
            param.VAGUE = true;
        }
        else if (this.isSupThenMinAtK(k)){

            this.decreaseStateatK(k, 1);
            if(this.isSupThenMinAtK(k)) {
                this.setCouleur(param.getCOLOR_at(k, 100));
                param.VAGUE = true;
            }
        }
        else{
            this.setMinAtK(k);
            if(!param.VAGUE) this.setCouleur(param.COLORS.COLOR_DEFAULT);
        }
        return param.VAGUE;
    }
    
    
    public void countNeighbours(int cls) {
        
        excited_free_cells_count = 0;
        excited_free_cells = new ArrayList<>();
        free_cells = new ArrayList<>();
        
        
        excited_free_cells_count = 0;
        
        for(Cell c : nghbrs) {
            //if this neighbours isn't me or isn't a wall
            if(!c.isWall()){
                //if his state is excited
                if (((CellDiffClass) c).isMaxStateAtK(cls)) {
                    //I count it
                    increaseEFCount(1);
                    // if nb agents on this neighbour is < 2
                    // and the neighbour is not already reserved
                    // and (the neighbour is of same class or of neutral class)
                    if (((CellDiffClass) c).getNbAgents() < 2 
                            && !((CellDiffClass) c).isReserved()
                            && (((CellDiffClass) c).getClasse() == cls
                                || ((CellDiffClass) c).getClasse() == -1)){
//                        System.out.println("ADDDDDEDEDEDED");
                        //then I add it to neighbours set
                        addFreeExcitedCells(c);
                        
                    }
                }
                //For PA
                //Si cette cellule contient un agent && si son voisin est libre
                if (this.nbAgents > 0 && ((CellDiffClass) c).getNbAgents() < 2
                        && !((CellDiffClass) c).isReserved()
                            && (((CellDiffClass) c).getClasse() == cls
                                || ((CellDiffClass) c).getClasse() == -1)) {
                    addFreeCells(c);
                }
            }
        }
    }
    
    public void MoveAgent(int cl){
        this.increaseNbAgents(1);
        //setting new classe
        this.setClasse(cl);
        //setting color
        this.setCouleur(param.getCOLOR_at(cl));
    }
    
    public void printCell(String msg){
//        System.out.println(msg);
//        System.out.println(
//                msg + 
//                " | i : " + this.getI()+
//                " | j: " + this.getJ()+
//                " | nbAgent: " + this.getNbAgents()+
//                " | Wall: " + this.isWall()+
//                " | Di: " + this.di+
//                " | Dj: " + this.dj+
//                " | State: " + Arrays.toString(this.state)+
//                " | Reserved: " + this.reserved+
//                " | classe: " + this.classe+
//                " | Color: " + this.getCouleur());
    }

//    @Override
//    public void nextState() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Cell getCopy() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void countNeighbours() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void countNeighbours(int k) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void addComingAgent(Agent agent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addFiringAgent(Agent agent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAgent() {
        return (nbAgents > 0);
    }

    @Override
    public boolean isWave() {
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
}
