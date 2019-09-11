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

import agents.AgInfRepClassData;
import agents.Agent;
import data.Data;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import misc.Params;
import static misc.Params.bernoulli;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 * @version 1.0
 */
public class CellInfRepClassData extends Cell{

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    
    //Data transported by the cell
    private Data data;
    //State of the cell
    private int state;
    //nb Agents on this cell
    private int nbAgents;
    //Delta Agents moving to this cell
    private ArrayList<Agent> comingAgents;
    //Agents firing on this cell
    private ArrayList<Agent> firingAgents;
    //Excited cells
    private ArrayList<Cell> excitedCells;
    
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public CellInfRepClassData(Params p, int i, int j, Color couleur, boolean wall, Data data, int state) {
        super(p, couleur, i, j, wall);
        this.data = data;
        this.state = state;
//        this.nghbrs = new ArrayList<>();
        this.comingAgents = new ArrayList<>();
        this.firingAgents = new ArrayList<>();
        this.excitedCells = new ArrayList<>();
    }
    
    public CellInfRepClassData(Params p, Color couleur, int i, int j, boolean wall) {
        super(p, couleur, i, j, wall);
        this.data = null;
        this.state = 0;
//        this.nghbrs = new ArrayList<>();
        this.comingAgents = new ArrayList<>();
        this.firingAgents = new ArrayList<>();
        this.excitedCells = new ArrayList<>();
    }
    
    public CellInfRepClassData(Params p, int i, int j, Color couleur, boolean wall, Data data, 
            int state, ArrayList<Cell> nghbrs, ArrayList<Agent> comingAgents, 
            ArrayList<Agent> firingAgents, ArrayList<Cell> excitedCells) {
        
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
    public CellInfRepClassData getCopy() {
        return new CellInfRepClassData(param, this.getI(), this.getJ(), this.getCouleur(), 
                this.isWall(), this.data, this.state, this.nghbrs, 
                this.comingAgents,
                this.firingAgents,
                this.excitedCells);
    }
    
    private Data getData() {
        return this.data;
    }
    
    public int getState() {
        return this.state;
    }
    
    public int getNbAgents() {
        return nbAgents;
    }
    
    public boolean isExcited() {
        return this.state == param.MLEVEL;
    }
    
    @Override
    public void addComingAgent(Agent aThis) {
        this.comingAgents.add(aThis);
    }
    
    @Override
    public void addFiringAgent(Agent aThis) {
        this.firingAgents.add(aThis);
    }
    
    public void makeAgent(int nb, Color color, boolean wl) {
        this.nbAgents = nb;
        this.setCouleur(color);
        this.setWall(wl);
    }
    
    public boolean isNeutral() {
        return (this.state == 0);
    }

    public boolean isAttracting(Data data) {
        return (isExcited()
                    && nbAgents < 2
                    && this.data.alike(data));
    }
    
    public boolean isRepulsing(Data data) {
        return (isExcited() && !this.data.alike(data) 
                && bernoulli(param.REPULSION) == 1);
    }
//</editor-fold>
    
    public void update(){
        
        countNeighbours();
        //If there is agents firing choose one of them
        if(!firingAgents.isEmpty()){
//            System.out.println("the firing agents is not empty");
//            printFiringAgents();
            AgInfRepClassData firingAgent = (AgInfRepClassData) chooseRandAgent(firingAgents);
            this.state = param.MLEVEL;
            this.data = firingAgent.getData();
        }
        //If neighbours are excited
        else if (this.state == 0 && !excitedCells.isEmpty()){
            CellInfRepClassData excitedCell = (CellInfRepClassData) chooseRandCell(excitedCells);
            this.state = param.MLEVEL;
            this.data = excitedCell.getData();
        }
        else if (this.state > 0){
            this.state -= 1;
        }
        else{
            this.state = 0;
//            this.data = null;
        }
        //Move agents
        if(this.nbAgents<2 && !comingAgents.isEmpty()){
            AgInfRepClassData chosenAgent = (AgInfRepClassData) chooseRandAgent(comingAgents);
            chosenAgent.setI(this.getI());
            chosenAgent.setJ(this.getJ());
        }
    }
    
    public void evolve(CellInfRepClassData newMatrix) {
        if(this.isWall()){
            setCouleur(param.getCOLOR_OBSTACLE());
        }
        else{
            this.state = newMatrix.getState();
            this.data = newMatrix.getData();
            this.nbAgents = 0;
            this.colorier();

            //reinit all arrays
            this.comingAgents = new ArrayList<>();
            this.firingAgents = new ArrayList<>();
            this.excitedCells = new ArrayList<>();
        }
    }
    
    public void countNeighbours() {
        
        excited_free_cells_count = 0;
        excitedCells = new ArrayList<>();
        
        for(Cell c : nghbrs) {
            //if this neighbours isn't me or isn't a wall
            if(!c.isWall()){
                //if his state is excited
                if (((CellInfRepClassData) c).isExcited()) {
                    //I add it
                    excitedCells.add(c);
                }
            }
        }
    }

//    public void printFiringAgents() {
//        for(AgInfRepClassData ag : firingAgents){
//            ag.print();
//        }
//    }

    public void increaseNbAgent(int i) {
        this.nbAgents++;
    }

    @Override
    public void print(JTable infoTable) {
        super.print(infoTable);
        DefaultTableModel model = (DefaultTableModel) infoTable.getModel();
        
        model.setValueAt(getCellType(), 3, 1);
        model.setValueAt(state, 4, 1);
        model.setValueAt(nbAgents, 5, 1);
        model.setValueAt(data.getValueString(), 6, 1);
        model.setValueAt(data.getClasse(), 7, 1);
        
    }
    
    @Override
    public boolean isAgent(){
        return this.nbAgents >= 1;
    }
    
    @Override
    public boolean isWave(){
        return this.state > 0;
    }
    
    @Override
    public void colorier() {
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
}
