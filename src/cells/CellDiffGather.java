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
//import misc.Neighbours;
//import static misc.Params.bernoulli;
//import java.awt.Color;
//import static misc.Params.RAND;
//import misc.Params;
//
///**
// *
// * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
// */
//public class CellDiffGather extends Cell{
//    
//    //State of the cell
//    private int state;
//    
//    //Next position for the agent of this cell to move on (delta)
//    public int di;
//    public int dj;
//    
//    //NB agents on this cell
//    private int nbAgents;
//    
//    public CellDiffGather(){
//        super();
//        this.nbAgents = 0;
//        this.state = 0;
//        this.di = -1;
//        this.dj = -1;
//    }
//    
//    public CellDiffGather(int nbA, Color c, int i, int j, boolean w){
//        super(c, i, j, w);
//        this.nbAgents = nbA;
//        this.state = 0;
//        this.di = -1;
//        this.dj = -1;
//    }
//    
//    public CellDiffGather(int nbA, Color c, int i, int j, boolean w, int s, int di, int dj) {
//        super(c, i, j, w);
//        this.nbAgents = nbA;
//        this.state = s;
//        this.di = di;
//        this.dj = dj;
//    }
//    
//    public CellDiffGather(int nbA, Color c, int i, int j, boolean w, int s){
//        super(c, i, j, w);
//        this.nbAgents = nbA;
//        this.state = s;
//        this.di = -1;
//        this.dj = -1;
//    }
//    
//    public CellDiffGather(int i, int j){
//        super(i, j);
//        this.nbAgents = 0;
//        this.state = 0;
//        this.di = -1;
//        this.dj = -1;
//    }
//    
//    public CellDiffGather(Color c, int i, int j, boolean w){
//        super(c, i, j, w);
//        this.nbAgents = 0;
//        this.state = -1;
//        this.di = -1;
//        this.dj = -1;
//    }
//
//    public void setState(int state) {
//        this.state = state;
//    }
//
//    public void setDi(int di) {
//        this.di = di;
//    }
//
//    public void setDj(int dj) {
//        this.dj = dj;
//    }
//    
//    public int getState() {
//        return state;
//    }
//
//    public int getDi() {
//        return di;
//    }
//
//    public int getDj() {
//        return dj;
//    }
//    
//    /**
//     *
//     * @param nbAgents
//     */
//        public void setNbAgents(int nbAgents) {
//        this.nbAgents = nbAgents;
//    }
//
//    /**
//     *
//     * @return
//     */
//    public int getNbAgents() {
//        return nbAgents;
//    }
//    
//    public void increaseState(int value){
//        this.state += value;
//    }
//    
//    public void decreaseState(int value){
//        this.state -= value;
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
////    @Override
//    public CellDiffGather nextState(Params param, Neighbours nghbrs) {
//        
////        System.out.println("NEXTSTATE=========BEFORE==============NEWCELL :");
//        
//        CellDiffGather new_cell = new CellDiffGather(this.nbAgents, this.getCouleur(),
//                                this.getI(), this.getJ(), false, this.state);
////        new_cell.printCell();
//        //Calculating new state
//        if (new_cell.getState() == 0 
//                && (nghbrs.isSupThen(0)
//                    || (new_cell.nbAgents > 0 && bernoulli(param.LAMBDA) == 1))){
//            new_cell.setState(param.MLEVEL);
//            new_cell.setCouleur(param.COLORS.COLOR_EXCITED);
//        }
//        else if (new_cell.getState() > 0){
//            new_cell.decreaseState(1);
//            if (new_cell.getState() > 0)
//                new_cell.setCouleur(new Color(255, 0, 0, 255/this.state));
//            else
//                new_cell.setCouleur(param.COLORS.COLOR_DEFAULT);
//        }
//        else{
//            new_cell.setState(0);
//            new_cell.setCouleur(param.COLORS.COLOR_DEFAULT);
//        }
//        
//        //movement of agents
//        if(bernoulli(param.PA) == 1){
//            if (!nghbrs.isEmptyFreeCells()) {
//                Cell delta = nghbrs.getElementFreeCells(
//                        RAND.nextInt(nghbrs.getSizeFreeCells()));
//                //save future position
//                new_cell.setDi(delta.getI());
//                new_cell.setDj(delta.getJ());
//            }
//        }
//        else if (this.state == 0 && this.nbAgents > 0){
//            if (!nghbrs.isEmptyFreeExcitedCells()) {
//                Cell delta = nghbrs.getElementFreeExcitedCells(
//                        RAND.nextInt(nghbrs.getSizeFreeExcitedCells()));
//                //save future position
//                new_cell.setDi(delta.getI());
//                new_cell.setDj(delta.getJ());
//            }
//        }
////        System.out.println("NEXTSTATE===========AFTER============NEWCELL :");
////        new_cell.printCell();
//        return new_cell;
//    }
//    
//    public void printCell(){
//        System.out.println("nbAgent: " + this.nbAgents+
//                    " | Color: " + this.getCouleur()+
//                    " | Wall: " + this.isWall()+
//                    " | Di: " + this.di+
//                    " | Dj: " + this.dj+
//                    " | State: " + this.state);
//    }
//
//    
//    public CellDiffGather getCopy(Params param) {
//        return new CellDiffGather(this.nbAgents, this.getCouleur(), 
//                this.getI(), this.getJ(), this.isWall(), this.state, this.di, 
//                this.dj);
//    }
//
//    @Override
//    public int getNbAgents(int k) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    
//    public void countNeighbours(Params p) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    
//    public void countNeighbours(Params p, int k) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    
//    public void nextState(Params param) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
