/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

import _diverse.Neighbours;
import cells.Cell;
import cells.CellDiffusionGathering;
import java.awt.Color;
import java.util.ArrayList;
import static _diverse.Params.RAND;

/**
 *
 * @author Karim
 */
public class AutomatonDiffusionGathering extends Automaton{

    private CellDiffusionGathering[][] matrix;
    
    public AutomatonDiffusionGathering() {
        super();
        this.matrix = new CellDiffusionGathering[getMATRIX_LENGTH()][getMATRIX_LENGTH()];
    }
    
    public AutomatonDiffusionGathering(CellDiffusionGathering[][] mx) {
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
        for(int i=0; i<getMATRIX_LENGTH(); i++) {
            for(int j=0; j<getMATRIX_LENGTH(); j++){
                this.matrix[i][j] = new CellDiffusionGathering(i, j);
            }
        }
    }

    @Override
    public void randomConfig() {
        
        this.init_matrix();
        int rn_x, rn_y;
        int nbr_cell = (int)((getMATRIX_LENGTH()) * (getMATRIX_LENGTH()) * getDENSITY() / 100);
        
        System.out.println("INITIALIZATION THISMatrix: nbr_cell (nb agents) = "+nbr_cell);
        reInitNBGeneration();
        
        for(int i=0; i<nbr_cell; i++){

            // Calcul des coordonnées
            rn_x = RAND.nextInt(getMATRIX_LENGTH());
            rn_y = RAND.nextInt(getMATRIX_LENGTH());
            
            // Créer l'agent
            this.setAgent(rn_x, rn_y, 1, getCOLOR_AGENT1(), false);
        }
    }

    @Override
    public void step() {
        CellDiffusionGathering[][] new_matrix_gathr = new CellDiffusionGathering[getMATRIX_LENGTH()][getMATRIX_LENGTH()];
        
//        System.out.println("STEP=========================NEw_MAtrix:");
        //For every cell calculate the next state
        for(int i=0; i<getMATRIX_LENGTH(); i++) {
            for(int j=0; j<getMATRIX_LENGTH(); j++){
                Neighbours nghbrs = this.countNeighbours(this.matrix[i][j], -1);
                new_matrix_gathr[i][j] = this.matrix[i][j].nextState(getP(), nghbrs);
//                new_matrix_gathr[i][j].printCell();
            }
        }
            
        //For every cell check if there are additional steps to make:
        //updating agent locations and cell colors
//        System.out.println("ADDITIONAL STEP=========================NEw_MAtrix:");
        for(int i=0; i<getMATRIX_LENGTH(); i++) {
            for(int j=0; j<getMATRIX_LENGTH(); j++){
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

    @Override
    public Neighbours countNeighbours(Cell cell, int k) {
        
        Neighbours nb = new Neighbours(0, new ArrayList<>(), new ArrayList<>());
        
        for(int di=-1; di<=1; di++) {
            for(int dj=-1; dj<=1; dj++){
                int ii = (cell.getI() + di + getMATRIX_LENGTH()) % getMATRIX_LENGTH();
                int jj = (cell.getJ() + dj + getMATRIX_LENGTH()) % getMATRIX_LENGTH();
                if (!(cell.getI()==ii && cell.getJ()==jj) && !this.matrix[ii][jj].isWall()) {
                    if(this.matrix[ii][jj].getState() == getMLEVEL() ){
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
}
