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

import cells.CellDiffClass;
import java.awt.Color;
import java.awt.Point;
import static misc.Params.RAND;

/**
 *
 * @author Karim
 */
public class AutoDiffClass extends Automaton{
    
    //<editor-fold defaultstate="collapsed" desc="Declarations">
    private CellDiffClass[][] matrix;
    private CellDiffClass[][] newMatrix;
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public AutoDiffClass() {
        super();
        this.matrix = new CellDiffClass[param.MATRIX_LENGTH][param.MATRIX_LENGTH];
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
    @Override
    public void setAgent(int i, int j, int nb, Color co, boolean wl) {
        
        // Calcul de la classe
        int cl = RAND.nextInt(param.NB_CLASSES);
        this.matrix[i][j].makeAgent(i, j, nb, param.getCOLOR_at(cl), wl, cl);
    }
    
    @Override
    public Color getCellColor(int i, int j) {
        return this.matrix[i][j].getCouleur();
    }
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Makers">
    @Override
    public void makeWallAt(int i, int j) {
        this.matrix[i][j] = new CellDiffClass(param, param.getCOLOR_OBSTACLE(), i, j, true);
    }
    
    @Override
    public void makeCellAt(int i, int j) {
        this.matrix[i][j] = new CellDiffClass(param, 0, param.getCOLOR_DEFAULT(), i, j, false);
    }
//</editor-fold>
    
    @Override
    public void step() {
        
        newMatrix = new CellDiffClass[param.MATRIX_LENGTH][param.MATRIX_LENGTH];
        
        //For every cell calculate the next state
        for(int i=0; i<param.MATRIX_LENGTH; i++) {
            for(int j=0; j<param.MATRIX_LENGTH; j++){
                
                //Copier la cellule pour executer le nouvel état
                newMatrix[i][j] = this.matrix[i][j].getCopy();
//                newMatrix[i][j].update();
                
                if(!newMatrix[i][j].isWall()){
                    //Depl des agents + expansion de la vague
                    newMatrix[i][j].nextState();

                    //Si il ya depl on reserve la cellule
                    if(newMatrix[i][j].isThereADelta())
                        reserve(newMatrix[i][j].getDelta());
                }
            }
        }
        //For every cell check if there are additional steps to make:
        //updating agent locations and cell colors
        for(int i=0; i<param.MATRIX_LENGTH; i++) {
            for(int j=0; j<param.MATRIX_LENGTH; j++){
//                newMatrix = newMatrix[i][j].additional_step(newMatrix);
//                CellDiffClass c = newMatrix[i][j];
                
                double di = newMatrix[i][j].getDelta().getX();
                double dj = newMatrix[i][j].getDelta().getY();
                
                if (di != -1 && dj != -1){
                    //moving agents
                    newMatrix[i][j].decreaseNbAgents(1);
                    newMatrix[(int)di][(int)dj].MoveAgent(newMatrix[i][j].getClasse());
                }
                if (newMatrix[i][j].getNbAgents() == 1)
                    newMatrix[i][j].setCouleur(param.getCOLOR_at(newMatrix[i][j].getClasse()));
                else if (newMatrix[i][j].getNbAgents() > 1)
                    newMatrix[i][j].setCouleur(param.getCOLOR_at(newMatrix[i][j].getClasse(), 255));
                else //means this class became empty, so we make it a neutral class
                    newMatrix[i][j].setClasse(-1);
                //always un-reserve the cell
                newMatrix[i][j].setReserved(false);
                newMatrix[i][j].setDelta(new Point(-1, -1));
//                newMatrix[i][j].printCell("new_matrix_cell final step == ");
            }
        }
        this.matrix = newMatrix;
        param.increaseNBGeneration(1);
    }

//    public CellDiffClass[] getListOfNeighbours(int i, int j, int nbNghbrs) {
//        
//        CellDiffClass[] nb = new CellDiffClass[nbNghbrs];
//        int m = 0;
//        for(int di=-1; di<=1; di++) {
//            for(int dj=-1; dj<=1; dj++){
//                int ii = (i + di + param.MATRIX_LENGTH) % param.MATRIX_LENGTH;
//                int jj = (j + dj + param.MATRIX_LENGTH) % param.MATRIX_LENGTH;
//                if(!(i==ii && j==jj)){
//                    nb[m] = this.matrix[ii][jj];
//                    m++;
//                }
//            }
//        }
//        return nb;
//    }
    
    public void reserve(Point p){
        this.matrix[(int)p.getX()][(int)p.getY()].setReserved(true);
    }

    @Override
    public void deleteAgent(int i, int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printCell(int x, int y) {
        matrix[x][y].print(infoTable);
    }

    @Override
    public void addNeighbour(int i, int j, int ii, int jj) {
        matrix[i][j].addNeighbour(this.matrix[ii][jj]);
    }
}
