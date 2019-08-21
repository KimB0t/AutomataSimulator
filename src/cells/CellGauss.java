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
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import misc.Params;
import static misc.Params.RAND;
import static misc.Params.bernoulli;

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
    //nb Agents on this cell
    private int nbAgents;
    //Neighbours of the cell
    private ArrayList<CellGauss> nghbrs;
    //Delta Agents moving to this cell
    private ArrayList<AgGauss> comingAgents;
    //Agents firing on this cell
    private ArrayList<AgGauss> firingAgents;
    //Excited cells
    private ArrayList<CellGauss> excitedCells;
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public CellGauss(Params p, int i, int j, Color couleur, boolean wall, DataGauss data, int state) {
        super(p, couleur, i, j, wall);
        this.data = data;
        this.state = state;
        this.nghbrs = new ArrayList<>();
        this.comingAgents = new ArrayList<>();
        this.firingAgents = new ArrayList<>();
        this.excitedCells = new ArrayList<>();
    }
    
    public CellGauss(Params p, Color couleur, int i, int j, boolean wall) {
        super(p, couleur, i, j, wall);
        this.data = new DataGauss();
        this.state = 0;
        this.nghbrs = new ArrayList<>();
        this.comingAgents = new ArrayList<>();
        this.firingAgents = new ArrayList<>();
        this.excitedCells = new ArrayList<>();
    }
    
    public CellGauss(Params p, int i, int j, Color couleur, boolean wall, DataGauss data, 
            int state, ArrayList<CellGauss> nghbrs, ArrayList<AgGauss> comingAgents, 
            ArrayList<AgGauss> firingAgents, 
            ArrayList<CellGauss> excitedCells) {
        super(p, couleur, i, j, wall);
        this.data = data;
        this.state = state;
        this.nghbrs = nghbrs;
        this.comingAgents = comingAgents;
        this.firingAgents = firingAgents;
        this.excitedCells = new ArrayList<>();
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
    @Override
    public CellGauss getCopy() {
        return new CellGauss(param, this.getI(), this.getJ(), this.getCouleur(), 
                this.isWall(), this.data, this.state, this.nghbrs, 
                this.comingAgents,
                this.firingAgents,
                this.excitedCells);
    }
    
    private DataGauss getData() {
        return this.data;
    }
    
    public ArrayList<CellGauss> getNeighbours() {
        return this.nghbrs;
    }
    
    private int getState() {
        return this.state;
    }
    
    @Override
    public int getNbAgents() {
        return nbAgents;
    }
    
    private void setColor() {
        if(state == param.MLEVEL){
            this.setCouleur(param.getCOLOR(data, 150));
        }
        else if (this.state > 0){
            this.setCouleur(param.getCOLOR(data, 75));
        }
        else{
            this.setCouleur(param.getCOLOR_DEFAULT());
        }
    }
    
    public boolean isExcited() {
        return this.state == param.MLEVEL;
    }
    
    public void addComingAgent(AgGauss aThis) {
        this.comingAgents.add(aThis);
    }
    
    public void addFiringAgent(AgGauss aThis) {
        this.firingAgents.add(aThis);
    }
    
    public void addNeighbour(CellGauss cell) {
        this.nghbrs.add(cell);
    }
    
    public void makeAgent(int nb, Color color, boolean wl) {
        this.nbAgents = nb;
        this.setCouleur(color);
        this.setWall(wl);
    }
//</editor-fold>
    
    private AgGauss chooseRandAgent(ArrayList<AgGauss> agents) {
        int size = agents.size();
        int key = RAND.nextInt(size);
        return agents.get(key);
    }
    
    private CellGauss chooseRandCell(ArrayList<CellGauss> cells) {
        int size = cells.size();
        int key = RAND.nextInt(size);
        return cells.get(key);
    }
    
    public void update(){
        
        countNeighbours();
        //If there is agents firing choose one of them
        if(!firingAgents.isEmpty()){
//            System.out.println("the firing agents is not empty");
//            printFiringAgents();
            AgGauss firingAgent = chooseRandAgent(firingAgents);
            this.state = param.MLEVEL;
            this.data = firingAgent.getData();
        }
        //If neighbours are excited
        else if (this.state == 0 && !excitedCells.isEmpty()){
            CellGauss excitedCell = chooseRandCell(excitedCells);
            this.state = param.MLEVEL;
            this.data = excitedCell.getData();
        }
        else if (this.state > 0){
            this.state -= 1;
        }
        else{
            this.state = 0;
//            this.data.setValue(0);
        }
        //Move agents
        if(this.nbAgents<2 && !comingAgents.isEmpty()){
            AgGauss chosenAgent = chooseRandAgent(comingAgents);
            chosenAgent.setI(this.getI());
            chosenAgent.setJ(this.getJ());
        }
    }
    
    public void evolve(CellGauss newMatrix) {
        this.state = newMatrix.getState();
        this.data = newMatrix.getData();
        this.nbAgents = 0;
        this.setColor();
        
        //reinit all arrays
        this.comingAgents = new ArrayList<>();
        this.firingAgents = new ArrayList<>();
        this.excitedCells = new ArrayList<>();
    }
    
    @Override
    public void countNeighbours() {
        
        excited_free_cells_count = 0;
        excitedCells = new ArrayList<>();
        
        for(CellGauss c : nghbrs) {
            //if this neighbours isn't me or isn't a wall
            if(!c.isWall()){
                //if his state is excited
                if (c.isExcited()) {
                    //I add it
                    excitedCells.add(c);
                }
            }
        }
    }
    
//<editor-fold defaultstate="collapsed" desc="Not used">
    @Override
    public void nextState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getNbAgents(int k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void countNeighbours(int k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>

    public void printFiringAgents() {
        for(AgGauss ag : firingAgents){
            ag.print();
        }
    }

    public void increaseNbAgent(int i) {
        this.nbAgents++;
    }

    public boolean isNeutral() {
        return (this.state == 0);
    }

    public void print() {
        System.out.println(
                "i: " + this.getI()+
                " | j: " + this.getJ()+
                " | nbAgent: " + this.nbAgents+
                " | Color: " + this.getCouleur()+
                " | Wall: " + this.isWall()+
                " | State: " + this.state);
    }

    public boolean isAttracting(DataGauss data) {
        return (isExcited()
                    && nbAgents < 2
                    && this.data.alike(data));
    }
    
    public boolean isRepulsing(DataGauss data) {
        return (isExcited() && !this.data.alike(data) 
                && bernoulli(param.REPULSION) == 1);
    }

    public CellGauss getOpposantNghbr(CellGauss nghbr) {
        
        int ii = getOppositeCell(this.getI(), nghbr.getI());
        int jj = getOppositeCell(this.getJ(), nghbr.getJ());
        
        for (CellGauss n : nghbrs) {
            if (n.getI() == ii && n.getJ() == jj) {
                return n;
            }
        }
        return null;
    }

    @Override
    public void print(JTable infoTable) {
        super.print(infoTable);
        DefaultTableModel model = (DefaultTableModel) infoTable.getModel();
        
        model.setValueAt(getCellType(), 3, 1);
        model.setValueAt(state, 4, 1);
        model.setValueAt(nbAgents, 5, 1);
        model.setValueAt(data.getValue(), 6, 1);
        
    }
    
    public String getCellType(){
        
        if(this.isWall()) return "Wall";
        else if(nbAgents >= 1) return "Agent";
        else if(state > 0) return "Wave";
        else return "Empty";
    }
    
    public int getOppositeCell(int cell, int wave){
        return (cell - ((cell - wave) * -1) + param.MATRIX_LENGTH) % param.MATRIX_LENGTH;
    }
}
