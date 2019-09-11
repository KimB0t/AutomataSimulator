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
import misc.Params;
import cells.CellInfRepClass;
import java.util.ArrayList;

/**
 *
 * @author Karim
 */
public class AgInfRepClass extends Agent{
    
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    // The class of this Agent
    private int classe;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     *
     * @param cls
     * @param i
     * @param j
     * @param id
     */
    public AgInfRepClass(Params param, int i, int j, int id, int cls) {
        super(param, i, j, id);
        this.classe = cls;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
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
//</editor-fold>
    
    @Override
    public void checkRepulsionAttraction(Cell currentCell, Cell nghbr, ArrayList<Cell> cellsToMoveTo){
        if(((CellInfRepClass) nghbr).isRepulsing(this.classe))
            cellsToMoveTo.add(currentCell.getOpposantNghbr(nghbr));
        else if(this.isNeutralCell(currentCell) && ((CellInfRepClass) nghbr).isAttracting(this.classe)) 
            cellsToMoveTo.add(nghbr);
    }

    @Override
    public boolean isFreeCell(Cell c) {
        return ((CellInfRepClass) c).getNbAgentsAtK(this.classe) < 2;
    }

    @Override
    public boolean isNeutralCell(Cell c) {
        return ((CellInfRepClass) c).isMinStateAtK(this.classe);
    }
}
