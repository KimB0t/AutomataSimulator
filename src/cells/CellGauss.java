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

import agents.AgGauss;
import data.DataGauss;
import java.util.ArrayList;
import misc.Params;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 * @version 1.0
 */
public class CellGauss extends Cell{

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    
    //Data transported by the cell
    private DataGauss data;
    //State of the cell
    private int state;
    //Neighbours of the cell
    private ArrayList<CellGauss> nghbrs;
    //Delta Agents moving to this cell
    private ArrayList<AgGauss> comingAgents;
    //Agents firing on this cell
    private ArrayList<AgGauss> firingAgents;
    //Agents firing on this cell
    private ArrayList<CellGauss> freeCells;
    //Agents firing on this cell
    private ArrayList<CellGauss> freeExcitedCells;
    
//</editor-fold>
    
    public void update(Params param){
        
        countNeighbours(param);
        //If there is agents firing choose one of them
        if(!firingAgents.isEmpty()){
            AgGauss firingAgent = chooseRandAgent(firingAgents);
            this.state = param.MLEVEL;
            this.data = firingAgent.getData();
        }
        else if (this.state == 0 && !freeExcitedCells.isEmpty()){
            CellGauss excitedCell = chooseRandCell(freeExcitedCells);
            this.state = param.MLEVEL;
            this.data = excitedCell.getData();
        }
        else if (this.state > 0){
            this.state -= 1;
        }
        else{
            this.state = 0;
        }
        //Move agents
        if(!comingAgents.isEmpty()){
            AgGauss chosenAgent = chooseRandAgent(comingAgents);
            chosenAgent.setI(this.getI());
            chosenAgent.setJ(this.getJ());
        }
    }
    
    
    @Override
    public void nextState(Params param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CellGauss getCopy(Params param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNbAgents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNbAgents(int k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void countNeighbours(Params p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void countNeighbours(Params p, int k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isExcited() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addDeltaAgent(AgGauss aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addFiringAgent(AgGauss aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<CellGauss> getNeighbours() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void evolve(Params param, CellGauss newMatrix) {
        this.state = newMatrix.getState();
        this.data = newMatrix.getData();
        this.setColor(param);
    }

    private AgGauss chooseRandAgent(ArrayList<AgGauss> firingAgents) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private CellGauss chooseRandCell(ArrayList<CellGauss> freeExcitedCells) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private DataGauss getData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int getState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setColor(Params param) {
        if(state > 0){
            this.setCouleur(param.getCOLOR(data));
        }
//        else if (this.state > 0){
//            this.setCouleur(param.getCOLOR(data));
//        }
        else{
            this.setCouleur(param.getCOLOR_DEFAULT());
        }
    }

}
