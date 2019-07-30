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
import cells.CellDiffGather;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Automata for decentralised gathering using Reaction-diffusion-chemotaxis 
 * scheme. 
 * (See Nazim A. Fatès. Solving the Decentralised Gathering 
 * Problem with a Reaction-Diffusion-Chemotaxis scheme - 
 * Social amoebae as a source of inspiration.
 * http://hal.inria.fr/inria-00132266.)
 * @author Karim BOUTAMINE <boutaminekarim06 at gmail.com>
 */
public class AutoDiffGather extends Automaton{

    private CellDiffGather[][] matrix;
    
    public AutoDiffGather() {
        super();
        this.matrix = new CellDiffGather[MATRIX_LENGTH][MATRIX_LENGTH];
    }
    
    public AutoDiffGather(CellDiffGather[][] mx) {
        super();
        this.matrix = mx;
    }
    
    @Override
    public void setAgent(int i, int j, int nb, Color co, boolean wl) {
        this.matrix[i][j].setNbAgents(nb);
        this.matrix[i][j].setCouleur(co);
        this.matrix[i][j].setWall(wl);
    }

    @Override
    public Color getCellColor(int i, int j) {
        return this.matrix[i][j].getCouleur();
    }

    @Override
    public void init_matrix() {
        for(int i=0; i<MATRIX_LENGTH; i++) {
            for(int j=0; j<MATRIX_LENGTH; j++){
                this.matrix[i][j] = new CellDiffGather(i, j);
            }
        }
        //applay boundaries if they are enabled
        if(isBOUNDARIESequalTo("Free")) makeBoundaries();
        
    }

    @Override
    public void step() {
        CellDiffGather[][] new_matrix_gathr = new CellDiffGather[MATRIX_LENGTH][MATRIX_LENGTH];
        
//        System.out.println("STEP=========================NEw_MAtrix:");
        //For every cell calculate the next state
        for(int i=0; i<MATRIX_LENGTH; i++) {
            for(int j=0; j<MATRIX_LENGTH; j++){
                if (!this.matrix[i][j].isWall()) {
                    Neighbours nghbrs = this.countNeighbours(this.matrix[i][j], -1);
                    new_matrix_gathr[i][j] = this.matrix[i][j].nextState(getParams(), nghbrs);
//                new_matrix_gathr[i][j].printCell();
                }
                else new_matrix_gathr[i][j] = getAWallCell(i, j);
            }
        }
            
        //For every cell check if there are additional steps to make:
        //updating agent locations and cell colors
//        System.out.println("ADDITIONAL STEP=========================NEw_MAtrix:");
        for(int i=0; i<MATRIX_LENGTH; i++) {
            for(int j=0; j<MATRIX_LENGTH; j++){
//                new_matrix_gathr = new_matrix_gathr[i][j].additional_step(new_matrix_gathr);
                if (new_matrix_gathr[i][j].getDi() != -1 && new_matrix_gathr[i][j].getDj() != -1){
                    
                    new_matrix_gathr[i][j].decreaseNbAgents(1);
                    int a = new_matrix_gathr[i][j].getDi();
                    int b = new_matrix_gathr[i][j].getDj();
                    new_matrix_gathr[a][b].increaseNbAgents(1);
                    new_matrix_gathr[a][b].setCouleur(getCOLOR_AGENT1());
                }
                if (new_matrix_gathr[i][j].getNbAgents() == 1)
                    new_matrix_gathr[i][j].setCouleur(getCOLOR_AGENT1());
                else if (new_matrix_gathr[i][j].getNbAgents() > 1)
                    new_matrix_gathr[i][j].setCouleur(getCOLOR_AGENT2());
//                new_matrix_gathr[i][j].printCell();
            }
        }
        this.matrix = new_matrix_gathr;
        increaseNBGeneration(1);
    }

//    @Override
    public Neighbours countNeighbours(Cell cell, int k) {
        
        Neighbours nb = new Neighbours(0, new ArrayList<>(), new ArrayList<>());
        
        for(int di=-1; di<=1; di++) {
            for(int dj=-1; dj<=1; dj++){
                int ii = (cell.getI() + di + MATRIX_LENGTH) % MATRIX_LENGTH;
                int jj = (cell.getJ() + dj + MATRIX_LENGTH) % MATRIX_LENGTH;
                if (!(cell.getI()==ii && cell.getJ()==jj) && !this.matrix[ii][jj].isWall()) {
                    if(this.matrix[ii][jj].getState() == MLEVEL ){
                        nb.increaseEFCount(1);
                        if (this.matrix[ii][jj].getNbAgents() < 2) 
                            nb.addFreeExcitedCells(this.matrix[ii][jj]);
                    }
                    //Si cette cellule contient un agent && si sont voisin est libre
                    if (this.matrix[ii][jj].getNbAgents() > 0 && this.matrix[ii][jj].getNbAgents() < 2) 
                        nb.addFreeCells(this.matrix[ii][jj]);
                }
            }
        }
        return nb;
    }
    
    @Override
    public void makeBoundaries(){
        
        for (int k = 0; k < MATRIX_LENGTH; k++) {
            this.matrix[k][0] = getAWallCell(k, 0);
            this.matrix[k][MATRIX_LENGTH-1] = getAWallCell(k, MATRIX_LENGTH-1);
            this.matrix[0][k] = getAWallCell(0, k);
            this.matrix[MATRIX_LENGTH-1][k] = getAWallCell(MATRIX_LENGTH-1, k);
        }
    }

    @Override
    public CellDiffGather getAWallCell(int i, int j) {
        return new CellDiffGather(getCOLOR_OBSTACLE(), i, j, true);
    }

    @Override
    public void deleteAgent(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void makeWallAt(int i, int j) {
        this.matrix[i][j] = new CellDiffGather(getCOLOR_OBSTACLE(), i, j, true);
    }
    
    @Override
    public CellDiffGather[] getListOfNeighbours(int i, int j, int nbNghbrs) {
        
        CellDiffGather[] nb = new CellDiffGather[nbNghbrs];
        int m = 0;
        for(int di=-1; di<=1; di++) {
            for(int dj=-1; dj<=1; dj++){
                int ii = (i + di + MATRIX_LENGTH) % MATRIX_LENGTH;
                int jj = (j + dj + MATRIX_LENGTH) % MATRIX_LENGTH;
                if(!(i==ii && j==jj) && !this.matrix[ii][jj].isWall()){
                    nb[m] = this.matrix[ii][jj];
                    m++;
                }
            }
        }
        return nb;
    }
}
