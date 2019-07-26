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

import misc.Neighbours;
import misc.Params;
import java.awt.Color;

/**
 *
 * @author Karim
 */
public class CellLife extends Cell{
    
    //NB agents on this cell
    private int nbAgents;
    
    /**
     * 
     * @param nbA
     * @param c
     * @param i
     * @param j
     * @param w
     */
    public CellLife(int nbA, Color c, int i, int j, boolean w) {
        super(c, i, j, w);
        this.nbAgents = nbA;
    }

    /**
     *
     */
    public CellLife() {
        super();
        this.nbAgents = 0;
    }
    
    public CellLife(Color c, int i, int j, boolean w) {
        super(c, i, j, w);
        this.nbAgents = 0;
    }

    
    /**
     *
     * @param nbAgents
     */
        public void setNbAgents(int nbAgents) {
        this.nbAgents = nbAgents;
    }

    /**
     *
     * @return
     */
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
    
    @Override
    public CellLife nextState(Params param, Neighbours nghbrs) {
        
        CellLife new_cell;
        new_cell = new CellLife(this.getNbAgents(), this.getCouleur(),
                                this.getI(), this.getJ(), false);
        
        if(new_cell.getNbAgents()==0 && nghbrs.isEqualTo(3)){
            new_cell.setNbAgents(1);
            new_cell.setCouleur(param.COLORS.COLOR_AGENT1);
        }
        else if(new_cell.getNbAgents()==1 && (nghbrs.isEqualTo(3)
                || nghbrs.isEqualTo(2))){
            new_cell.setNbAgents(1);
            new_cell.setCouleur(param.COLORS.COLOR_AGENT1);
        }
        else {
            new_cell.setNbAgents(0);
            new_cell.setCouleur(param.COLORS.COLOR_DEFAULT);
        }
        return new_cell;
    }
}
