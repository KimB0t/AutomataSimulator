///* 
// * Copyright (C) 2019 Karim BOUTAMINE <boutaminekarim06 at gmail.com>
// *
// * This program is free software; you can redistribute it and/or
// * modify it under the terms of the GNU General Public License
// * as published by the Free Software Foundation; either version 2
// * of the License, or (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program; if not, write to the Free Software
// * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
// */
//package cells;
//
//import java.awt.Color;
//import java.awt.Point;
//import java.util.ArrayList;
//import static misc.Params.RAND;
//import static misc.Params.bernoulli;
//import misc.Params;
//
///**
// *
// * @author Karim BOUTAMINE <boutaminekarim06 at gmail.com>
// */
//public class CellDiffClass extends Cell{
//    
//    //<editor-fold defaultstate="collapsed" desc="Declarations">
//    //NB agents on this cell
//    private int nbAgents;
//    //Array of states
//    private int[] state;
//    //Next position for the agent of this cell to move on (delta)
//    private Point delta;
//    //Classe of the cell
//    private int classe;
//    //for when the cell is reserved by the first agent that reserves it
//    private boolean reserved;
//    //List of neighbouring cells
//    private CellDiffClass[] neighbours;
//    //list of excited neighbouring free cells of this cell 
//    //to where the agent can possibly move
//    private ArrayList<CellDiffClass> excited_free_cells;
//    //list of neighbouring free cells 
//    //to where the agent can possibly move
//    private ArrayList<CellDiffClass> free_cells;
//    //</editor-fold>
//    
//    //<editor-fold defaultstate="collapsed" desc="Constructors">
//    public CellDiffClass(Params param){
//        super();
//        this.nbAgents = 0;
//        this.state = this.stateInitializer(param);
//        this.delta = new Point(-1, -1);
//        this.classe = -1;
//        this.reserved = false;
//    }
//    
//    public CellDiffClass(Params param, int nbA, Color c, int i, int j, boolean w){
//        super(c, i, j, w);
//        this.nbAgents = nbA;
//        this.state = this.stateInitializer(param);
//        this.delta = new Point(-1, -1);
//        this.classe = -1;
//        this.reserved = false;
//    }
//    
//    public CellDiffClass(Params param, int nbA, Color c, int i, int j, boolean w, 
//            int[] s, Point p, int cl, boolean r) {
//        super(c, i, j, w);
//        this.nbAgents = nbA;
//        this.state = new int[param.NB_CLASSES];
//        if(s!=null)
//            System.arraycopy(s, 0, this.state, 0, param.NB_CLASSES);
//        this.delta = new Point(p);
//        this.classe = cl;
//        this.reserved = r;
//    }
//    
//    public CellDiffClass(Color co, int i, int j, boolean wall){
//        super(co, i, j, wall);
//        this.nbAgents = 0;
//        this.state = null;
//        this.delta = new Point(-1, -1);
//        this.classe = -1;
//        this.reserved = false;
//    }
//    //</editor-fold>
//    
//    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
//    public void setState(Params param, int[] state) {
//        this.state = new int[param.NB_CLASSES];
//        if(state!=null)
//            System.arraycopy(state, 0, this.state, 0, param.NB_CLASSES);
//    }
//
//    public void setDelta(Point p) {
//        this.delta.setLocation(p);
//    }
//
//    public void setClasse(int classe) {
//        this.classe = classe;
//    }
//
//    public void setReserved(boolean reserved) {
//        this.reserved = reserved;
//    }
//
//    public void setStateAtK(int k, int s) {
//        this.state[k] = s;
//    }
//
//    public void setMaxAtK(Params param, int k) {
//        this.state[k] = maxLevel(param, k);
//    }
//
//    public void setMinAtK(Params param, int k) {
//        this.state[k] = minLevel(param, k);
//    }
//    
//    public int[] getState() {
//        return this.state;
//    }
//
//    public Point getDelta() {
//        return this.delta;
//    }
//
//    public int getClasse() {
//        return classe;
//    }
//
//    public boolean isReserved() {
//        return reserved;
//    }
//    
//    /**
//     *
//     * @param nbAgents
//     */
//    public void setNbAgents(int nbAgents) {
//        this.nbAgents = nbAgents;
//    }
//
//    /**
//     *
//     * @return
//     */
//    @Override
//    public int getNbAgents() {
//        return nbAgents;
//    }
//    
//    /**
//     * decrease number of agents on this cell
//     * @param num - number of agents to substract
//     */
//    public void decreaseNbAgents(int num){
//        this.nbAgents -= num;
//    }
//    
//    /**
//     * increase number of agents on this cell
//     * @param num - number of agents to add
//     */
//    public void increaseNbAgents(int num){
//        this.nbAgents += num;
//    }
//    
//    private int [] stateInitializer(Params param){
//        int[] a = new int[param.NB_CLASSES];
//        for (int i = 0; i < param.NB_CLASSES; i++) {
//            //initialisation avec le min de chaque classe
//            a[i] = i * (param.MLEVEL+1); //i représente la classe
//        }
//        return a;
//    }
//    
//    /**
//     *  Calculate Max level
//     * @param k
//     * @return
//     */
//    public int maxLevel(Params param, int k){
//        return k * (param.MLEVEL+1) + param.MLEVEL;
//    }
//    
//    /**
//     * Caculate Min
//     * @param param
//     * @param k
//     * @return
//     */
//    public int minLevel(Params param, int k){
//        return k * (param.MLEVEL+1);
//    }
//    
//    
//    public CellDiffClass getCopy(Params param) {
//        
//        CellDiffClass new_cell = 
//                new CellDiffClass(param, this.nbAgents, this.getCouleur(), 
//                        this.getI(), this.getJ(), this.isWall(), this.state, 
//                        this.delta, this.classe, this.reserved);
//        if(this.nbAgents > 0){
//        new_cell.setExcited_free_cells(excited_free_cells);
//        new_cell.setFree_cells(free_cells);}
//        
//        return new_cell;
//    }
//
//    @Override
//    public int getNbAgents(int k) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    public void setExcited_free_cells(ArrayList<CellDiffClass> excited_free_cells) {
//        this.excited_free_cells = excited_free_cells;
//    }
//
//    public void setFree_cells(ArrayList<CellDiffClass> free_cells) {
//        this.free_cells = free_cells;
//    }
//
//    public ArrayList<CellDiffClass> getExcited_free_cells() {
//        return excited_free_cells;
//    }
//
//    public ArrayList<CellDiffClass> getFree_cells() {
//        return free_cells;
//    }
//    
//    public boolean isEmptyFreeCells(){
//        return this.free_cells.isEmpty();
//    }
//    
//    public Cell getElementFreeCells(int i){
//        return this.free_cells.get(i);
//    }
//    
//    public int getSizeFreeCells(){
//        return this.free_cells.size();
//    }
//    
//    public boolean isEmptyFreeExcitedCells(){
//        return this.excited_free_cells.isEmpty();
//    }
//    
//    public Cell getElementFreeExcitedCells(int i){
//        return this.excited_free_cells.get(i);
//    }
//    
//    public int getSizeFreeExcitedCells(){
//        return this.excited_free_cells.size();
//    }
//    
//    public void addFreeExcitedCells(CellDiffClass cell){
//        this.excited_free_cells.add(cell);
//    }
//    
//    public void addFreeCells(CellDiffClass cell){
//        this.free_cells.add(cell);
//    }
//    
//    public void setNeighbours(CellDiffClass[] nghbrs){
//        this.neighbours = nghbrs;
//    }
//    
//    public boolean isThereADelta(){
//        return (this.delta.getX() != -1 && this.delta.getY() != -1);
//    }
//    
//    public void makeAgent(int i, int j, int nb, Color co, boolean wl, int cl){
//        this.setNbAgents(nb);
//        this.setCouleur(co);
//        this.setWall(wl);
//        this.setClasse(cl);
//    }
//    //</editor-fold>
//    
//    //<editor-fold defaultstate="collapsed" desc="Comparators">
//    public boolean isMaxStateAtK(Params param, int k){
//        return this.state[k] == maxLevel(param, k);
//    }
//    
//    public boolean isMinStateAtK(Params param, int k){
//        return this.state[k] == minLevel(param, k);
//    }
//    
//    public boolean isSupThenMinAtK(Params param, int k){
//        return this.state[k] > minLevel(param, k);
//    }
//    
//    public void decreaseStateatK(int k, int value){
//        this.state[k] -= value;
//    }
//    //</editor-fold>
//
//    
//    public void nextState(Params param) {
//        if(nbAgents>0){
//            countNeighbours(param, this.classe);
//
//            //Agent Mvt
//            if(bernoulli(param.PA) == 1) {
//                if (!isEmptyFreeCells()) {
//                    //choose a random neighbour
//                    Cell delta = getElementFreeCells(
//                            RAND.nextInt(getSizeFreeCells()));
//                    Point new_Delta = new Point(delta.getI(), delta.getJ());
//                    //put new location
//                    setDelta(new_Delta);
//                }
//            }
//            //if state of current cell == min && there are accecible neighbours to move to
//            else if(isMinStateAtK(param, this.classe)) {
//                if (!isEmptyFreeExcitedCells()) {
//                    //choose a random neighbour
//                    Cell delta = getElementFreeExcitedCells(
//                            RAND.nextInt(getSizeFreeExcitedCells()));
//                    Point new_Delta = new Point(delta.getI(), delta.getJ());
//                    //put new location
//                    setDelta(new_Delta);
//                }
//            }
//        }
//        
//        //this variables assures that waves are displayed correctly
//        //if the first vague is diplayed, others do not
//        //if the first is not displayed, the second will be
//        //and the rest will not
//        //and so on...
//        param.VAGUE = false;
//
//        //Expantion de la Vague
//        //for all classes
//        for (int kk = 0; kk < param.NB_CLASSES; kk++) {
//            //count excited neighbours for this class
//            countNeighbours(param, kk);
//            param.VAGUE = expandWave(param, kk);
//        }
//    }
//    
//    public boolean expandWave(Params param, int k) {
//        
//        if (this.isMinStateAtK(param, k) //test min
//            && (isSupThen(0)
//                || (this.nbAgents > 0 
//                    && this.getClasse() == k 
//                    && bernoulli(param.LAMBDA) == 1))){
////            System.out.println("EXCITEDDDDDDDDDDDDDDD");
//            this.setMaxAtK(param, k); //put max
//            //coloring
//            this.setCouleur(param.getCOLOR_at(k, 100));
//            param.VAGUE = true;
//        }
//        else if (this.isSupThenMinAtK(param, k)){
//
//            this.decreaseStateatK(k, 1);
//            if(this.isSupThenMinAtK(param, k)) {
//                this.setCouleur(param.getCOLOR_at(k, 100));
//                param.VAGUE = true;
//            }
//        }
//        else{
//            this.setMinAtK(param, k);
//            if(!param.VAGUE) this.setCouleur(param.COLORS.COLOR_DEFAULT);
//        }
//        return param.VAGUE;
//    }
//    
//    
//    public void countNeighbours(Params p, int k) {
//        
//        excited_free_cells_count = 0;
//        excited_free_cells = new ArrayList<>();
//        free_cells = new ArrayList<>();
//        
//        for(int i=0; i<8; i++) {
//
////            this.neighbours[i].printCell("NEIGHBOUR == ");
//            //if this neighbours isn't me or isn't a wall
//            if(!this.neighbours[i].isWall()){
//
//                //if his state is excited
//                if (this.neighbours[i].isMaxStateAtK(p, k)) {
//                    //I count it
//                    increaseEFCount(1);
//                    // if nb agents on this neighbour is < 2
//                    // and the neighbour is not already reserved
//                    // and (the neighbour is of same class or of neutral class)
//                    if (this.neighbours[i].getNbAgents() < 2 
//                            && !this.neighbours[i].isReserved()
//                            && (this.neighbours[i].getClasse() == k
//                                || this.neighbours[i].getClasse() == -1)){
////                        System.out.println("ADDDDDEDEDEDED");
//                        //then I add it to neighbours set
//                        addFreeExcitedCells(this.neighbours[i]);
//                        
//                    }
//                }
//                //For PA
//                //Si cette cellule contient un agent && si son voisin est libre
//                if (this.nbAgents > 0 && this.neighbours[i].getNbAgents() < 2
//                        && !this.neighbours[i].isReserved()
//                            && (this.neighbours[i].getClasse() == k
//                                || this.neighbours[i].getClasse() == -1)) {
//                    addFreeCells(this.neighbours[i]);
//                }
//            }
//        }
//    }
//    
//    public void MouveAgent(Params param, int cl){
//        this.increaseNbAgents(1);
//        //setting new classe
//        this.setClasse(cl);
//        //setting color
//        this.setCouleur(param.getCOLOR_at(cl));
//    }
//
//    
//    public void countNeighbours(Params p) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    public void printCell(String msg){
////        System.out.println(msg);
////        System.out.println(
////                msg + 
////                " | i : " + this.getI()+
////                " | j: " + this.getJ()+
////                " | nbAgent: " + this.getNbAgents()+
////                " | Wall: " + this.isWall()+
////                " | Di: " + this.di+
////                " | Dj: " + this.dj+
////                " | State: " + Arrays.toString(this.state)+
////                " | Reserved: " + this.reserved+
////                " | classe: " + this.classe+
////                " | Color: " + this.getCouleur());
//    }
//
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
//}
