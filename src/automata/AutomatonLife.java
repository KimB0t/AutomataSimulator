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
package automata;

import misc.Neighbours;
import cells.Cell;
import cells.CellLife;
import java.awt.Color;

/**
 *
 * @author Karim
 */
public class AutomatonLife extends Automaton{
    
    private CellLife[][] matrix;
    
    public AutomatonLife() {
        super();
        this.matrix = new CellLife[getMATRIX_LENGTH()][getMATRIX_LENGTH()];
    }
    
    public AutomatonLife(CellLife[][] mx) {
        super();
        this.matrix = mx;
    }
    
    @Override
    public Color getCellColor(int i, int j){
        return this.matrix[i][j].getCouleur();
    }
    
    /**
     * init matrix with new cells
     */
    @Override
    public void init_matrix(){
        
        for(int i=0; i<getMATRIX_LENGTH(); i++) {
            for(int j=0; j<getMATRIX_LENGTH(); j++){
                this.matrix[i][j] = new CellLife(0, getCOLOR_DEFAULT(), i, j, false);
            }
        }
        //applay boundaries if they are enabled
        if(isBOUNDARIESequalTo("Free")) makeBoundaries();
    }
    
    @Override
    public void randomConfig(){
        
        this.init_matrix();
        int rn_x, rn_y;
        int nbr_cell = (int)((getMATRIX_LENGTH()) * (getMATRIX_LENGTH()) * getDENSITY() / 100);
        
        System.out.println("nbr_cell (nb agents) = "+nbr_cell);
        reInitNBGeneration();
        
        for(int i=0; i<nbr_cell; i++){

            // Calcul des coordonnées
            rn_x = getRANDcoordinate();
            rn_y = getRANDcoordinate();
            
            // Créer l'agent
            this.setAgent(rn_x, rn_y, 1, getCOLOR_AGENT1(), false);
        }
    }
    
    @Override
    public void step(){
        CellLife[][] new_matrix_life = new CellLife[getMATRIX_LENGTH()][getMATRIX_LENGTH()];
                
        //For every cell calculate the next state
        for(int i=0; i<getMATRIX_LENGTH(); i++) {
            for(int j=0; j<getMATRIX_LENGTH(); j++){
                if (!this.matrix[i][j].isWall()) {
                    Neighbours nghbrs = countNeighbours(this.matrix[i][j], -1);
                    new_matrix_life[i][j] = this.matrix[i][j].nextState(getP(), nghbrs);
                }
                else new_matrix_life[i][j] = makeWall(i, j);
            }
        }
        this.matrix = new_matrix_life;
        increaseNBGeneration(1);
    }
    
    /**
     *
     * @param c
     * @return
     */
    @Override
    public Neighbours countNeighbours(Cell cell, int k) {
        
        Neighbours nb = new Neighbours(0);
        
        for(int di=-1; di<=1; di++) {
            for(int dj=-1; dj<=1; dj++){
                int ii = (cell.getI() + di + getMATRIX_LENGTH()) % getMATRIX_LENGTH();
                int jj = (cell.getJ() + dj + getMATRIX_LENGTH()) % getMATRIX_LENGTH();
                if(!(cell.getI()==ii && cell.getJ()==jj) && !this.matrix[ii][jj].isWall())
                    nb.increaseEFCount(this.matrix[ii][jj].getNbAgents());
            }
        }
        return nb;
    }

    @Override
    public void setAgent(int i, int j, int nb, Color co, boolean wl) {
        this.matrix[i][j].setNbAgents(nb);
        this.matrix[i][j].setCouleur(co);
        this.matrix[i][j].setWall(wl);
    }
    
    @Override
    public void deleteAgent(int i, int j) {
        this.matrix[i][j].setNbAgents(0);
        this.matrix[i][j].setCouleur(getCOLOR_DEFAULT());
        this.matrix[i][j].setWall(false);
    }
    
    @Override
    public void makeBoundaries(){
        
        for (int k = 0; k < getMATRIX_LENGTH(); k++) {
            this.matrix[k][0] = makeWall(k, 0);
            this.matrix[k][getMATRIX_LENGTH()-1] = makeWall(k, getMATRIX_LENGTH()-1);
            this.matrix[0][k] = makeWall(0, k);
            this.matrix[getMATRIX_LENGTH()-1][k] = makeWall(getMATRIX_LENGTH()-1, k);
        }
    }

    @Override
    public CellLife makeWall(int i, int j) {
        return new CellLife(getCOLOR_OBSTACLE(), i, j, true);
    }
    
    @Override
    public void makeWallAt(int i, int j) {
        this.matrix[i][j] = new CellLife(getCOLOR_OBSTACLE(), i, j, true);
    }
    
}
