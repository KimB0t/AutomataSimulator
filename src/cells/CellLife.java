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
//import misc.Params;
//import java.awt.Color;
//
///**
// *
// * @author Karim BOUTAMINE <boutaminekarim06 at gmail.com>
// */
//public class CellLife extends Cell{
//    
//    //Number of agents on this cell
//    private int nbAgents;
//    
//    //List of neighbouring cells.
//    protected CellLife[] neighbours;
//    
//    /**
//     * 
//     * @param nbA Number of agents in [0,1,2,...]
//     * @param c Cell Color
//     * @param i Cell line coordinate
//     * @param j Cell column coordinate
//     * @param w true if this cell is a wall
//     */
//    public CellLife(int nbA, Color c, int i, int j, boolean w) {
//        super(c, i, j, w);
//        this.nbAgents = nbA;
//    }
//
//    /**
//     * Default constructor with default values and nbAgents = 0.
//     */
//    public CellLife() {
//        super();
//        this.nbAgents = 0;
//    }
//    
//    /**
//     * 
//     * @param c Cell Color
//     * @param i Cell line coordinate
//     * @param j Cell column coordinate
//     * @param w true if this cell is a wall
//     */
//    public CellLife(Color c, int i, int j, boolean w) {
//        super(c, i, j, w);
//        this.nbAgents = 0;
//    }
//
//    
//    /**
//     *
//     * @param nbAgents Number of agents
//     */
//    public void setNbAgents(int nbAgents) {
//        this.nbAgents = nbAgents;
//    }
//
//    /**
//     *
//     * @return Number of agents
//     */
//    @Override
//    public int getNbAgents() {
//        return this.nbAgents;
//    }
//    
//    /**
//     * Decrease number of agents on this cell.
//     * @param num Number of agents to substract.
//     */
//    public void decreaseNbAgents(int num){
//        this.nbAgents -= num;
//    }
//    
//    /**
//     * Increase number of agents on this cell
//     * @param num Number of agents to add.
//     */
//    public void increaseNbAgents(int num){
//        this.nbAgents += num;
//    }
//
//    
//    public CellLife getCopy(Params param) {
//        return new CellLife(this.nbAgents, this.getCouleur(),
//                                this.getI(), this.getJ(), this.isWall());
//    }
//    
////    @Override
//    public CellLife nextState1(Params param){
//        
//        CellLife new_cell = this.getCopy(param);
//        this.countNeighbours(null);
//        if(nbAgents==0 && excited_free_cells_count==3){
//            new_cell.setNbAgents(1);
//            new_cell.setCouleur(param.COLORS.COLOR_AGENT1);
//        }
//        else if(nbAgents==1 && (excited_free_cells_count==3
//                || excited_free_cells_count==2)){
//            new_cell.setNbAgents(1);
//            new_cell.setCouleur(param.COLORS.COLOR_AGENT1);
//        }
//        else {
//            new_cell.setNbAgents(0);
//            new_cell.setCouleur(param.COLORS.COLOR_DEFAULT);
//        }
//        return new_cell;
//    }
//    
//    
//    public void countNeighbours(Params p){
//        
//        excited_free_cells_count = 0;
//        for(int i=0; i<8; i++) {
//            if(!this.neighbours[i].isWall())
//                excited_free_cells_count += this.neighbours[i].getNbAgents();
//        }
//    }
//
//    @Override
//    public int getNbAgents(int k) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    public void setNeighbours(CellLife[] nghbrs){
//        this.neighbours = nghbrs;
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
//
//}
