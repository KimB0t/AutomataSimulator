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
import static misc.Params.RAND;
import static misc.Params.bernoulli;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 * @version 1.0
 */
public class AgGauss extends Agent{

    private DataGauss data;
    
    public AgGauss(Params p, int i, int j, int id, DataGauss data) {
        super(p, i, j, id);
        this.data = data;
    }
    
    private boolean iWantToFire() {
        return (bernoulli(param.LAMBDA) == 1);
    }
    
    public DataGauss getData() {
        return this.data;
    }
    
    //Percieve + Decide
    public void perceive(CellGauss currentCell){
        //Need neighbours : pour verifier si y'a excitation et qu'on doit bouger
        //+ current cell : to check it's state and decide whether to fire or not
                        // + to check nb of agents on neighbooring cells to see whether it can move or not
        //returns nothing: applies influences directly on cells (where he wants to move or if he wants to excite)
        //L'objet ne retourne rien mais se rajoute directement dans les 
        //liste de fire pour cette cellule ou deplacement pour ces voisins
        ArrayList<CellGauss> cellsToMoveTo = new ArrayList<>();
        ArrayList<CellGauss> nghbrs = currentCell.getNeighbours();
        
        if(bernoulli(param.PA) == 1){
            //Pour toutes les cellules voisines
            for (CellGauss nghbr : nghbrs) {
                //Si cette cellule contient moins de 2 agents
                //alors l'ajouter à la liste
                if(nghbr.getNbAgents() < 2) 
                    cellsToMoveTo.add(nghbr);
            }
        }
        else {
            //Pour toutes les cellules voisines
            for (CellGauss nghbr : nghbrs) {
                //Si cette cellule est excitée 
                //et qu'elle contient moins de 2 agents
                //et que la donnée qu'elle contient est égale à ma donnée
                //alors l'ajouter à la liste
                if(nghbr.isRepulsing(this.data))
                    cellsToMoveTo.add(currentCell.getOpposantNghbr(nghbr));
                else if(currentCell.isNeutral() && nghbr.isAttracting(this.data)) 
                    cellsToMoveTo.add(nghbr);
            }
        }
        
        //Si la liste est non vide, choisir une des cellules
        if(!cellsToMoveTo.isEmpty()){
            CellGauss chosenCell = chooseRandomCell(cellsToMoveTo);
            //Mettre à jour la liste des agents se deplaçant vers cette cellule
            chosenCell.addComingAgent(this);
        }
        //Sinon, il peut générer une vague
        else if(currentCell.isNeutral()){
            //Si il veut générer on l'ajoute à la liste
            if(iWantToFire()) {
//                System.out.println("(" + getI() +","+ getJ() + ") this agent want to fire");
                currentCell.addFiringAgent(this);
            }
        }
    }
    
    private CellGauss chooseRandomCell(ArrayList<CellGauss> cellsToMoveTo) {
        int size = cellsToMoveTo.size();
        int key = RAND.nextInt(size);
        return cellsToMoveTo.get(key);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Not used">
    @Override
    public Agent move(int s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Point move(Cell new_cell) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//</editor-fold>

    public void print() {
        System.out.println("("+getI()+","+getJ()+") "+getId()+" "+data.getValue());
    }

}
