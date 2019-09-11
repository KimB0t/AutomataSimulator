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

import misc.Params;
import cells.Cell;
import java.util.ArrayList;
import static misc.Params.RAND;
import static misc.Params.bernoulli;

/**
 *
 * @author Karim
 */
public abstract class Agent{
    
    // Coordinates of the cell on which the agent is placed on the matrix.
    private int i = 0;
    private int j = 0;
    
    // Unique ID for this Agent.
    private int id = 0;
    
    //Parameters of the model
    protected Params param;
    
    public Agent(Params p, int i, int j, int id) {
        this.i = i;
        this.j = j;
        this.id = id;
        this.param = p;
    }

    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">

    /**
     *
     * @param i
     */
    public void setI(int i) {
        this.i = i;
    }

    /**
     *
     * @param j
     */
    public void setJ(int j) {
        this.j = j;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getI() {
        return i;
    }

    /**
     *
     * @return
     */
    public int getJ() {
        return j;
    }

    /**
     *
     * @param param
     */
    public void setParam(Params param) {
        this.param = param;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }
    //</editor-fold>
    
    public void perceive(Cell currentCell){
        //Need neighbours : pour verifier si y'a excitation et qu'on doit bouger
        //+ current cell : to check it's state and decide whether to fire or not
                        // + to check nb of agents on neighbooring cells to see whether it can move or not
        //returns nothing: applies influences directly on cells (where he wants to move or if he wants to fire)
        //L'objet ne retourne rien mais se rajoute directement dans les 
        //liste de fire pour cette cellule ou deplacement pour ces voisins
        ArrayList<Cell> cellsToMoveTo = new ArrayList<>();
        ArrayList<Cell> nghbrs = currentCell.getNeighbours();
        
        if(bernoulli(param.PA) == 1){
            //Pour toutes les cellules voisines
            for (Cell nghbr : nghbrs) {
                //Si cette cellule contient moins de 2 agents
                //alors l'ajouter à la liste
                if(this.isFreeCell(nghbr))
                    cellsToMoveTo.add(nghbr);
            }
        }
        else {
            //Pour toutes les cellules voisines
            for (Cell nghbr : nghbrs) {
                //Verifier si cette cellule voisine attire ou repousse
                this.checkRepulsionAttraction(currentCell, nghbr, cellsToMoveTo);
            }
        }
        
        //Si la liste est non vide, choisir une des cellules
        if(!cellsToMoveTo.isEmpty()){
            Cell chosenCell = chooseRandomCell(cellsToMoveTo);
            //Mettre à jour la liste des agents se deplaçant vers cette cellule
            chosenCell.addComingAgent(this);
        }
        
        //Sinon, il peut générer une vague
        else if(this.isNeutralCell(currentCell) && iWantToFire()){
            //Si il veut générer on l'ajoute à la liste
            currentCell.addFiringAgent(this);
        }
    }
    
    public Cell chooseRandomCell(ArrayList<Cell> cellsToMoveTo){
        int size = cellsToMoveTo.size();
        int key = RAND.nextInt(size);
        return cellsToMoveTo.get(key);
    }
    
    public boolean iWantToFire() {
        return (bernoulli(param.LAMBDA) == 1);
    }
    
    public abstract void checkRepulsionAttraction(Cell currentCell, Cell nghbr, ArrayList<Cell> cellsToMoveTo);

    public abstract boolean isFreeCell(Cell c);
    
    public abstract boolean isNeutralCell(Cell c);
}
