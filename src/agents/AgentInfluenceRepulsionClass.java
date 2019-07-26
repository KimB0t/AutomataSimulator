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

import misc.Neighbours;
import cells.Cell;
import static misc.Params.bernoulli;
import static misc.Params.RAND;
import misc.Params;
import cells.CellInfluenceRepulsionClass;

/**
 *
 * @author Karim
 */
public class AgentInfluenceRepulsionClass extends Agent{
    
    // The class of this Agent
    private int classe;

    /**
     *
     * @param cls
     * @param i
     * @param p
     * @param j
     * @param id
     */
    
    public AgentInfluenceRepulsionClass(int i, int j, int p, int id, int cls) {
        super(i, j, p, id);
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

    @Override
    public AgentInfluenceRepulsionClass move(Params param, Neighbours nghbrs, Cell new_cell) {
        
        Cell delta = null;
        AgentInfluenceRepulsionClass ag = null;
        
        //Déplacement des agents
        if(bernoulli(param.PA) == 1){
            if (!nghbrs.isEmptyFreeCells()) {
                //choose a random neighbour
                int randomCell = RAND.nextInt(nghbrs.getSizeFreeCells());
                delta = nghbrs.getElementFreeCells(randomCell);
            }
        }
        //if state of current cell == min && there are accecible neighbours to move to
        else if(((CellInfluenceRepulsionClass)new_cell).isMinStateAtK(param.MLEVEL, this.classe)) {
            
            //Add condition here to set repulsion
            if(!nghbrs.isEmptyFreeRepulsiveCells()){
                int randomCell = RAND.nextInt(nghbrs.getSizeFreeRepulsiveCells());
                delta = nghbrs.getElementFreeRepulsiveCells(randomCell);
            }
            else if (!nghbrs.isEmptyFreeExcitedCells()) {
                //choose a random neighbour
                int randomCell = RAND.nextInt(nghbrs.getSizeFreeExcitedCells());
                delta = nghbrs.getElementFreeExcitedCells(randomCell);
            }
        }
        //return new agent with new location
        if(delta != null) {
            // Calculate new position
            int position = calculatePosition(param, delta.getI(), delta.getJ());
            
            ag = new AgentInfluenceRepulsionClass(delta.getI(), delta.getJ(), 
                    position, this.getId(), this.classe);
        }
        return ag;
    }

    public int calculatePosition(Params param, int i, int j){
        return i * param.MATRIX_LENGTH + j;
    }
    
    @Override
    public Agent move(Params param, int s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
