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
import cells.CellGauss;
import data.DataGauss;
import java.awt.Point;
import java.util.ArrayList;
import misc.Params;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 * @version 1.0
 */
public class AgGauss extends Agent{

    public AgGauss(int i, int j, int id) {
        super(i, j, id);
    }
    
    //Percieve + Decide
    public void perceive(CellGauss currentCell){
        //Need neighbours : pour verifier si y'a excitement et qu'on doit bouger
        //+ current state of cell : pour voir si on doit exciter (current cell de preference bech ye9der ymodifiyiha direct)
                //+ list of agents : pour savoir le nb d'agent voisin avant de se deplacer (pas la peine le nb ykoun fel cells)
                //return influence (where he wants to move or if he wants to excite)
        //L'objet ne retourne rien mais se rajoute directement dans les 
        //liste de fire pour cette cellule ou deplacement pour ces voisins
        ArrayList<CellGauss> cellsToMoveTo = new ArrayList<>();
        ArrayList<CellGauss> nghbrs = currentCell.getNeighbours();
        
        //Pour toutes les cellules voisines
        for (CellGauss nghbr : nghbrs) {
            //Si cette cellule est excitée alors l'ajouter à la liste
            if(nghbr.isExcited()) cellsToMoveTo.add(nghbr);
        }
        
        //Si la liste est non vide, choisir une des cellules
        if(!cellsToMoveTo.isEmpty()){
            CellGauss chosenCell = chooseRandomCell(cellsToMoveTo);
            //Mettre à jour la liste des agents se deplaçant vers cette cellule
            chosenCell.addDeltaAgent(this);
        }
        //Sinon, il peut générer une vague
        else if(!currentCell.isExcited()){
            //Si il veut générer on l'ajoute à la liste
            if(iWantToFire()) currentCell.addFiringAgent(this);
        }
    }
    
    @Override
    public Agent move(Params param, int s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Point move(Params param, Cell new_cell) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private CellGauss chooseRandomCell(ArrayList<CellGauss> cellsToMoveTo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean iWantToFire() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DataGauss getData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
