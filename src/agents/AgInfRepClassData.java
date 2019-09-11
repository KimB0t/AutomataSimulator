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

package agents;

import cells.Cell;
import cells.CellInfRepClassData;
import data.Data;
import java.util.ArrayList;
import misc.Params;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 * @version 1.0
 */
public class AgInfRepClassData extends Agent{

    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private Data data;
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public AgInfRepClassData(Params p, int i, int j, int id, Data data) {
        super(p, i, j, id);
        this.data = data;
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
    public Data getData() {
        return this.data;
    }
//</editor-fold>
    
    @Override
    public void checkRepulsionAttraction(Cell currentCell, Cell nghbr, ArrayList<Cell> cellsToMoveTo){
        if(((CellInfRepClassData) nghbr).isRepulsing(this.data))
            cellsToMoveTo.add(currentCell.getOpposantNghbr(nghbr));
        else if(((CellInfRepClassData) currentCell).isNeutral() && ((CellInfRepClassData) nghbr).isAttracting(this.data)) 
            cellsToMoveTo.add(nghbr);
    }

    @Override
    public boolean isFreeCell(Cell c) {
        return ((CellInfRepClassData) c).getNbAgents() < 2;
    }

    @Override
    public boolean isNeutralCell(Cell c) {
        return ((CellInfRepClassData) c).isNeutral();
    }
}
