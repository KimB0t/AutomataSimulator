///*
// * Copyright (C) 2019 Karim BOUTAMINE <boutaminekarim06@gmail.com>
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
//
//package agents;
//
//import cells.Cell;
//import cells.CellTest;
//import data.DataTest;
//import java.awt.Point;
//import misc.Params;
//import static misc.Params.RAND;
//import static misc.Params.bernoulli;
//
///**
// *
// * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
// * @version 1.0
// */
//public class AgTest extends Agent{
//    
//    //data that the agent is transposrting
//    //this is the new information that will be transported on waves
//    //either fully or partially
//    //and other agents must decide weither to follow or not
//    private DataTest data;
//    
//    //The class of this Agent
//    //Here it will be used only to color the agent and to verify in 
//    //which class he is supposed to belong
//    private int hiddenClasse;
//
//    /**
//     *
//     * @param cls
//     * @param i
//     * @param j
//     * @param id
//     */
//    public AgTest(int i, int j, int id, int cls) {
//        super(i, j, id);
//        this.hiddenClasse = cls;
//    }
//    
//    /**
//     *
//     * @param hiddenClasse
//     */
//    public void setHiddenClasse(int hiddenClasse) {
//        this.hiddenClasse = hiddenClasse;
//    }
//
//    /**
//     *
//     * @return
//     */
//    public int getHiddenClasse() {
//        return hiddenClasse;
//    }
//
//    /**
//     *
//     * @return
//     */
//    public AgTest getCopy(){
//        return new AgTest(getI(), getJ(), 
//                getId(), getHiddenClasse());
//    }
//
//    public void setData(DataTest data) {
//        this.data = data;
//    }
//
//    public DataTest getData() {
//        return data;
//    }
//    
//    @Override
//    public Point move(Params param, Cell cell) {
//        
//        CellTest thisCell = (CellTest)cell;
//        CellTest delta = null;
//        Point deltaLocation = null;
//        
//        //Déplacement des agents
//        if(bernoulli(param.PA) == 1){
//            if (!thisCell.isEmptyFreeCells()) {
//                //choose a random neighbour
//                int randomCell = RAND.nextInt(thisCell.getSizeFreeCells());
//                delta = thisCell.getElementFreeCells(randomCell);
//            }
//        }
//        //if state of current cell == min && there are accecible neighbours to move to
//        else if(thisCell.isMinState(param.MLEVEL)) {
//            
//            //Add condition here to set repulsion
//            if(!thisCell.isEmptyFreeRepulsiveCells()){
//                int randomCell = RAND.nextInt(thisCell.getSizeFreeRepulsiveCells());
//                delta = thisCell.getElementFreeRepulsiveCells(randomCell);
//            }
//            else if (!thisCell.isEmptyFreeExcitedCells()) {
//                //choose a random neighbour
//                int randomCell = RAND.nextInt(thisCell.getSizeFreeExcitedCells());
//                delta = thisCell.getElementFreeExcitedCells(randomCell);
//            }
//        }
//        //return new agent with new location
//        if(delta != null) {
//            int i = delta.getI();
//            int j = delta.getJ();
//            deltaLocation = new Point(i, j);
//        }
//        return deltaLocation;
//    }
//
//    /**
//     *
//     * @param param
//     * @param i
//     * @param j
//     * @return
//     */
//    public int calculatePosition(Params param, int i, int j){
//        return i * param.MATRIX_LENGTH + j;
//    }
//    
//    @Override
//    public Agent move(Params param, int s) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    /**
//     *
//     * @param msg
//     */
//    public void printAgent(String msg){
////        System.out.println(msg);
//        System.out.println(msg + 
//                " | i : " + this.getI()+
//                " | j: " + this.getJ()+
//                " | ID: " + this.getId()+
//                " | Classe: " + this.hiddenClasse);
//    }
//    
//    public void setNewLocation(int i, int j){
//        this.setI(i);
//        this.setJ(j);
//    }
//}
