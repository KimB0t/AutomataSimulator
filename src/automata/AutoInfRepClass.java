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

import static misc.Params.RAND;
import agents.AgInfRepClass;
import cells.CellInfRepClass;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 */
public class AutoInfRepClass extends Automaton{
    
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    // The matrix
    private CellInfRepClass[][] matrix;
    // The agent list
    private ArrayList<AgInfRepClass> _ag;
    // Counter for ids
    private int idCounter;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     *
     */
    public AutoInfRepClass() {
        super();
        this.matrix = new CellInfRepClass[MATRIX_LENGTH][MATRIX_LENGTH];
        this._ag = new ArrayList<>();
        this.idCounter = 0;
    }
    
    /**
     *
     * @param mx
     * @param aa
     */
    public AutoInfRepClass(CellInfRepClass[][] mx, ArrayList<AgInfRepClass> aa) {
        super();
        this.matrix = mx;
        this._ag = aa;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
    @Override
    public void setAgent(int i, int j, int nb, Color co, boolean wl) {
        
        // calcul de la classe
        int cls = RAND.nextInt(NB_CLASSES);
        
        this.matrix[i][j].setCouleur(getCOLOR_at(cls));
        this.matrix[i][j].setWall(wl);
        this.matrix[i][j].setNb_agentsAtK(cls, nb);
//        int pos = j + i*MATRIX_LENGTH;
        _ag.add(this.idCounter, new AgInfRepClass(i, j, this.idCounter, cls));
        this.idCounter++;
    }

    @Override
    public Color getCellColor(int i, int j) {
        return this.matrix[i][j].getCouleur();
    }
    //</editor-fold>

    @Override
    public void init_matrix() {
        for(int i=0; i<MATRIX_LENGTH; i++) {
            for(int j=0; j<MATRIX_LENGTH; j++){
                this.matrix[i][j] = new CellInfRepClass(getParams(), getCOLOR_DEFAULT(), i, j, false);
            }
        }
        //applay boundaries if they are enabled
        if(isBOUNDARIESequalTo("Free")) makeBoundaries();
        //Restart the others too
        this._ag = new ArrayList<>();
        this.idCounter = 0;
    }

    @Override
    public void step() {
        
        int thisi, thisj, thisClasse;
        CellInfRepClass thisCell;
        CellInfRepClass[][] new_matrix_fier = new CellInfRepClass[MATRIX_LENGTH][MATRIX_LENGTH];
        
        //NEXT STATE Classifier
        for(int i=0; i<_ag.size(); i++) {

            AgInfRepClass thisAgent = _ag.get(i).getCopy();
            
            //Recupéré uniquement l'état pour cette classe de cet agent
            thisi = thisAgent.getI();
            thisj = thisAgent.getJ();
            thisClasse= thisAgent.getClasse();
            thisCell = this.matrix[thisi][thisj];
            
            //Count neighbours
            CellInfRepClass[] nghbrsList = getListOfNeighbours(thisi, thisj, 8);
            thisCell.setNeighbours(nghbrsList);
            thisCell.countNeighbours(getParams(), thisClasse);
            
            //PERCIEVE + DECIDE
            Point deltaLocation = thisAgent.move(getParams(), thisCell);

            //Si cet agent va se deplacer
            if(deltaLocation != null){
                //informe cells of decisions
                int di = (int)deltaLocation.getX();
                int dj = (int)deltaLocation.getY();
                matrix[di][dj].addConcurent_agents(thisAgent);
            }
            matrix[thisi][thisj].reinitArraysSaufConcurent();
        }
        
        //Conflicts + NEXT STATE System
        //UPDATE(Agents) + EVOLVE(System)
        for(int i=0; i<MATRIX_LENGTH; i++) {
            for(int j=0; j<MATRIX_LENGTH; j++){
                
                new_matrix_fier[i][j] = this.matrix[i][j].getCopy(getParams());
                if (!new_matrix_fier[i][j].isWall()) {
                    //Count neighbours
                    CellInfRepClass[] nghbrsList = getListOfNeighbours(i, j, 8);
                    new_matrix_fier[i][j].setNeighbours(nghbrsList);
            
                    new_matrix_fier[i][j].nextState(getParams());
                    AgInfRepClass choosenAgent = new_matrix_fier[i][j].chooseAgent(getParams());
                    
                    
                    
                    if(choosenAgent != null){
                        _ag.set(choosenAgent.getId(), choosenAgent);
                    }
                    new_matrix_fier[i][j].reinitArrays();
                    new_matrix_fier[i][j].agentInitializer(getParams());
                }
            }
        }
        
        for(int i=0; i<_ag.size(); i++) {
            
            int cl = _ag.get(i).getClasse();
            int ii = _ag.get(i).getI();
            int jj = _ag.get(i).getJ();
            
            new_matrix_fier[ii][jj].colorier(getParams(), cl);
        }
        this.matrix = new_matrix_fier;
        increaseNBGeneration(1);
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
    public CellInfRepClass getAWallCell(int i, int j) {
        return new CellInfRepClass(getCOLOR_OBSTACLE(), i, j, true);
    }

    @Override
    public void deleteAgent(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void makeWallAt(int i, int j) {
        this.matrix[i][j] = new CellInfRepClass(getCOLOR_OBSTACLE(), i, j, true);
    }
    
    @Override
    public CellInfRepClass[] getListOfNeighbours(int i, int j, int nbNghbrs) {
        
        CellInfRepClass[] nb = new CellInfRepClass[nbNghbrs];
        int m = 0;
        for(int di=-1; di<=1; di++) {
            for(int dj=-1; dj<=1; dj++){
                int ii = (i + di + MATRIX_LENGTH) % MATRIX_LENGTH;
                int jj = (j + dj + MATRIX_LENGTH) % MATRIX_LENGTH;
                if(!(i==ii && j==jj)){
                    nb[m] = this.matrix[ii][jj];
                    m++;
                }
            }
        }
        return nb;
    }
}
