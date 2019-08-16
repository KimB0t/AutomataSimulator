///* 
// * Copyright (C) 2019 Karim BOUTAMINE <boutaminekarim06 at gmail.com>
// *
// * This program is free software; you can redistribute it and/or
// * modify it under the terms of the GNU General Public License
// * as published by the Free Software Foundation; either version 2
// * of the License, or (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program; if not, write to the Free Software
// * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
// */
//package automata;
//
//import cells.CellLife;
//import java.awt.Color;
//
///**
// *
// * @author Karim BOUTAMINE <boutaminekarim06 at gmail.com>
// */
//public class AutoLife extends Automaton{
//    
//    //<editor-fold defaultstate="collapsed" desc="Declarations">
//    private CellLife[][] matrix;
////</editor-fold>
//    
//    //<editor-fold defaultstate="collapsed" desc="Constructors">
//    public AutoLife() {
//        super();
//        this.matrix = new CellLife[MATRIX_LENGTH][MATRIX_LENGTH];
//    }
//    
//    public AutoLife(CellLife[][] mx) {
//        super();
//        this.matrix = mx;
//    }
////</editor-fold>
//    
//    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
//    @Override
//    public Color getCellColor(int i, int j){
//        return this.matrix[i][j].getCouleur();
//    }
////</editor-fold>
//    
//    //<editor-fold defaultstate="collapsed" desc="Makers">
//    @Override
//    public void makeWallAt(int i, int j) {
//        this.matrix[i][j] = new CellLife(getCOLOR_OBSTACLE(), i, j, true);
//    }
//    
//    @Override
//    public void makeCellAt(int i, int j) {
//        this.matrix[i][j] = new CellLife(0, getCOLOR_DEFAULT(), i, j, false);
//    }
////</editor-fold>
//    
//    @Override
//    public void step(){
//        
//        CellLife[][] new_matrix_life = new CellLife[MATRIX_LENGTH][MATRIX_LENGTH];
//                
//        //For every cell calculate the next state
//        for(int i=0; i<MATRIX_LENGTH; i++) {
//            for(int j=0; j<MATRIX_LENGTH; j++){
//                new_matrix_life[i][j] = this.matrix[i][j].getCopy(getParams());
//                if (!this.matrix[i][j].isWall()) {
//                    CellLife[] listNghbrs = getListOfNeighbours(i, j, 8);
//                    this.matrix[i][j].setNeighbours(listNghbrs);
//                    new_matrix_life[i][j] = this.matrix[i][j].nextState1(getParams());
//                }
//            }
//        }
//        this.matrix = new_matrix_life;
//        increaseNBGeneration(1);
//    }
//
//    @Override
//    public void setAgent(int i, int j, int nb, Color co, boolean wl) {
//        this.matrix[i][j].setNbAgents(nb);
//        this.matrix[i][j].setCouleur(co);
//        this.matrix[i][j].setWall(wl);
//        System.out.println("An Agent is added");
//    }
//    
//    @Override
//    public void deleteAgent(int i, int j) {
//        this.matrix[i][j].setNbAgents(0);
//        this.matrix[i][j].setCouleur(getCOLOR_DEFAULT());
//        this.matrix[i][j].setWall(false);
//    }
//
//    @Override
//    public CellLife[] getListOfNeighbours(int i, int j, int nbNghbrs) {
//        
//        CellLife[] nb = new CellLife[nbNghbrs];
//        int m = 0;
//        for(int di=-1; di<=1; di++) {
//            for(int dj=-1; dj<=1; dj++){
//                int ii = (i + di + MATRIX_LENGTH) % MATRIX_LENGTH;
//                int jj = (j + dj + MATRIX_LENGTH) % MATRIX_LENGTH;
//                if(!(i==ii && j==jj)){
//                    nb[m] = this.matrix[ii][jj];
//                    m++;
//                }
//            }
//        }
//        return nb;
//    }
//    
//}
