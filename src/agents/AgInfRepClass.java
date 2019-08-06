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
package agents;

import cells.Cell;
import static misc.Params.bernoulli;
import static misc.Params.RAND;
import misc.Params;
import cells.CellInfRepClass;
import java.awt.Point;

/**
 *
 * @author Karim
 */
public class AgInfRepClass extends Agent{
    
    // The class of this Agent
    private int classe;

    /**
     *
     * @param cls
     * @param i
     * @param j
     * @param id
     */
    public AgInfRepClass(int i, int j, int id, int cls) {
        super(i, j, id);
        this.classe = cls;
    }
    
    /**
     *
     * @param classe
     */
    public void setClasse(int classe) {
        this.classe = classe;
    }

    /**
     *
     * @return
     */
    public int getClasse() {
        return classe;
    }

    /**
     *
     * @return
     */
    public AgInfRepClass getCopy(){
        return new AgInfRepClass(getI(), getJ(), 
                getId(), getClasse());
    }
    
    @Override
    public Point move(Params param, Cell cell) {
        
        CellInfRepClass thisCell = (CellInfRepClass)cell;
        CellInfRepClass delta = null;
        Point deltaLocation = null;
        
        //Déplacement des agents
        if(bernoulli(param.PA) == 1){
            if (!thisCell.isEmptyFreeCells()) {
                //choose a random neighbour
                int randomCell = RAND.nextInt(thisCell.getSizeFreeCells());
                delta = thisCell.getElementFreeCells(randomCell);
            }
        }
        //if state of current cell == min && there are accecible neighbours to move to
        else if(thisCell.isMinStateAtK(param.MLEVEL, this.classe)) {
            
            //Add condition here to set repulsion
            if(!thisCell.isEmptyFreeRepulsiveCells()){
                int randomCell = RAND.nextInt(thisCell.getSizeFreeRepulsiveCells());
                delta = thisCell.getElementFreeRepulsiveCells(randomCell);
            }
            else if (!thisCell.isEmptyFreeExcitedCells()) {
                //choose a random neighbour
                int randomCell = RAND.nextInt(thisCell.getSizeFreeExcitedCells());
                delta = thisCell.getElementFreeExcitedCells(randomCell);
            }
        }
        //return new agent with new location
        if(delta != null) {
            int i = delta.getI();
            int j = delta.getJ();
            deltaLocation = new Point(i, j);
        }
        return deltaLocation;
    }

    /**
     *
     * @param param
     * @param i
     * @param j
     * @return
     */
    public int calculatePosition(Params param, int i, int j){
        return i * param.MATRIX_LENGTH + j;
    }
    
    @Override
    public Agent move(Params param, int s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     *
     * @param msg
     */
    public void printAgent(String msg){
//        System.out.println(msg);
        System.out.println(
                msg + 
                " | i : " + this.getI()+
                " | j: " + this.getJ()+
                " | ID: " + this.getId()+
                " | Classe: " + this.classe);
    }
    
    public void setNewLocation(int i, int j){
        this.setI(i);
        this.setJ(j);
    }
}
