/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cells;

import _diverse.AllColors;
import _diverse.Neighbours;
import _diverse.Prm;
import java.awt.Color;

/**
 *
 * @author Karim
 */
public class CellLife extends Cell{
    
    /**
     *
     * @param nbA
     * @param c
     * @param i
     * @param j
     * @param w
     */
    public CellLife(int nbA, Color c, int i, int j, boolean w) {
        super(nbA, c, i, j, w);
    }

    /**
     *
     */
    public CellLife() {
        super();
    }

    @Override
    public CellLife nextState(Prm param, Neighbours nghbrs) {
        
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
