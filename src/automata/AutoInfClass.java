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
//import misc.Neighbours;
//import static misc.Params.RAND;
//import agents.Agent;
//import agents.AgInfClass;
//import cells.Cell;
//import cells.CellInfClass;
//import java.awt.Color;
//import java.util.ArrayList;
//import java.util.HashMap;
//
///**
// *
// * @author Karim
// */
//public class AutoInfClass extends Automaton{
//    
//    //<editor-fold defaultstate="collapsed" desc="Declarations">
//    // The matrix
//    private CellInfClass[][] matrix;
//    
//    // The agent list
//    private ArrayList<Agent> _ag;
//    
//    // The neighbours object
//    private Neighbours nghbrs;
//    
//    // Counter for ids
//    private int idCounter;
////</editor-fold>
//    
//    //<editor-fold defaultstate="collapsed" desc="Constructors">
//    /**
//     *
//     */
//    public AutoInfClass() {
//        super();
//        this.matrix = new CellInfClass[MATRIX_LENGTH][MATRIX_LENGTH];
//        this._ag = new ArrayList<>();
//        this.nghbrs = new Neighbours(new HashMap<>());
//        this.idCounter = 0;
//    }
//    
//    /**
//     *
//     * @param mx
//     * @param aa
//     */
//    public AutoInfClass(CellInfClass[][] mx, ArrayList<Agent> aa) {
//        super();
//        this.matrix = mx;
//        this._ag = aa;
//    }
////</editor-fold>
//    
//    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
//    @Override
//    public void setAgent(int i, int j, int nb, Color co, boolean wl) {
//        
//        // calcul de la classe
//        int cls = RAND.nextInt(NB_CLASSES);
//        
//        this.matrix[i][j].setCouleur(getCOLOR_at(cls));
//        this.matrix[i][j].setWall(wl);
//        this.matrix[i][j].setNb_agentsAtK(cls, nb);
//        int pos = j + i*MATRIX_LENGTH;
//        _ag.add(this.idCounter, new AgInfClass(i, j, this.idCounter, cls));
//        this.idCounter++;
//    }
//
//    @Override
//    public Color getCellColor(int i, int j) {
//        return this.matrix[i][j].getCouleur();
//    }
////</editor-fold>
//    
//    //<editor-fold defaultstate="collapsed" desc="Makers">
//    @Override
//    public void makeWallAt(int i, int j) {
//        this.matrix[i][j] = new CellInfClass(getCOLOR_OBSTACLE(), i, j, true);
//    }
//    
//    @Override
//    public void makeCellAt(int i, int j) {
//        this.matrix[i][j] = new CellInfClass(getParams(), getCOLOR_DEFAULT(), i, j, false, j + i*MATRIX_LENGTH);
//    }
////</editor-fold>
//    
//    @Override
//    public void init_matrix() {
//        super.init_matrix();
//        this._ag = new ArrayList<>();
//        this.nghbrs = new Neighbours(new HashMap<>());
//        this.idCounter = 0;
//    }
//
//    @Override
//    public void randomConfig() {
//        
//        super.randomConfig();
//        
//        for(int i=0; i<_ag.size(); i++) {
//            //This part is just for coloring with black when there are multiple classes in same cell
//            int a = 0, j = 0;
//            while(a<2 && j<NB_CLASSES){
//                if(this.matrix[_ag.get(i).getI()][_ag.get(i).getJ()].isNbAgentsAtKsupThen(j, 0))
//                    a++;
//                j++;
//            }
//            if(a>=2) this.matrix[_ag.get(i).getI()][_ag.get(i).getJ()].setCouleur(Color.BLACK);
//        }
//    }
//
//    @Override
//    public void step() {
//        
//        Agent new_t;
//        int thisi, thisj;
//        
//        CellInfClass[][] new_matrix_fier = new CellInfClass[MATRIX_LENGTH][MATRIX_LENGTH];
//        
//        
//        //NEXT STATE Classifier
//        for(int i=0; i<_ag.size(); i++) {
//
//            //Recupéré uniquement l'état pour cette classe de cet agent
//            thisi = _ag.get(i).getI();
//            thisj = _ag.get(i).getJ();
//
//            //Count neighbours
//            this.countNeighbours(this.matrix[thisi][thisj], ((AgInfClass)_ag.get(i)).getClasse());
//            
//            //PERCIEVE + DECIDE
////            new_t = _ag.get(i).move(getParams(), nghbrs, this.matrix[thisi][thisj]);
//            //je vais le mettre directement pour l'instant
//
//            //Si cet agent va se deplacer
////            if(new_t != null){
////                //informe cells of decisions
////                ArrayList<Agent> arT = new ArrayList<>();
////                if(nghbrs.cCContainsKey(new_t.getPosition())){
////                    arT = nghbrs.cCGet(new_t.getPosition());
////                }
////                arT.add(new_t);
////                nghbrs.cCPut(new_t.getPosition(), arT);
////            }
//        }
////                System.out.println("sizzzzze : "+_ag.size());
//        //Conflicts + NEXT STATE System
//        //UPDATE(Agents) + EVOLVE(System)
//        for(int i=0; i<MATRIX_LENGTH; i++) {
//            for(int j=0; j<MATRIX_LENGTH; j++){
//                new_matrix_fier[i][j] = this.matrix[i][j].getCopy(getParams());
//                if (!new_matrix_fier[i][j].isWall()) {
////                    System.out.println("PPPPPPPPPP ++ " + this.matrix[i][j].getPosition());
//                    //this variables assures that waves are displayed correctly
//                    //if the first vague is diplayed, other do not
//                    //if the first is not, the second will and the rest will not
//                    //and so on...
//                    VAGUE = false;
//                    //Expantion de la Vague
//                    //for all classes
//                    for (int k = 0; k < NB_CLASSES; k++) {
//                        //count excited neighbours for this class
//                        this.countNeighbours(new_matrix_fier[i][j], k);
//                        VAGUE = new_matrix_fier[i][j].expandWave(getParams(), nghbrs, k);
//                    }
//                    new_matrix_fier[i][j].agentInitializer(getParams());
//                    this.nextState(new_matrix_fier[i][j].getPosition());
//                    
////                    new_matrix_fier[i][j] = this.matrix[i][j].nextState(nghbrs);
////                    this.nextState(nghbrs, new_matrix_fier[i][j].getPosition());
//                }
//            }
//        }
//
//        //UPDATE(Agents) + EVOLVE(System)
//        for(int i=0; i<_ag.size(); i++) {
//            int cl = ((AgInfClass)_ag.get(i)).getClasse();
//            int ii = _ag.get(i).getI();
//            int jj = _ag.get(i).getJ();
////            new_matrix_fier[ii][jj].setCouleur(getCOLOR_at(cl));
//            
//            new_matrix_fier[ii][jj].increaseNbAgentsAtK(cl, 1);
//            if(new_matrix_fier[ii][jj].nbAgentsAtKisEqualTo(cl, 1))
//                new_matrix_fier[ii][jj].setCouleur(getCOLOR_at(cl));
//            else{
//                new_matrix_fier[ii][jj].setCouleur(getCOLOR_at(cl, 255));
//            }
//            
//            //This part is just for coloring with black when there are multiple classes in same cell
//            int a = 0, kk = 0;
//            while(a<2 && kk<NB_CLASSES){
//                if(new_matrix_fier[ii][jj].isNbAgentsAtKsupThen(kk, 0))
//                    a++;
//                kk++;
//            }
//            if(a>=2) new_matrix_fier[ii][jj].setCouleur(Color.BLACK);
//        }
//        this.matrix = new_matrix_fier;
////        _listConcurent = new HashMap<>();
//        this.nghbrs.setConcurent_cells(new HashMap<>());
//        increaseNBGeneration(1);
//        
//    }
//    
//    /**
//     *
//     * @param pos
//     */
//    public void nextState(int pos){
//        
//        //MàJ Agents + couleurs
//        
//        if(this.nghbrs.cCGet(pos) != null) {
//            ArrayList<Agent> tempConcur = this.nghbrs.cCGet(pos);
//            switch(POLICY){
//                case CYCLIC:
//                    //Normalement qlq soit le nombre d'agent on prendra tjrs le 1er et le reste
//                    //on les prends pas, ils ne vont pas changer dans _ag.
//                    _ag.set(tempConcur.get(0).getId(), tempConcur.get(0));
//                    break;
//                case RANDOM:
//                    //Si 1 seul agent veut se délpacer on le prend automatiquement
//                    if(tempConcur.size() == 1)
//                        _ag.set(tempConcur.get(0).getId(), tempConcur.get(0));
//                    //Si nbr d'agents > 1 on choisis aléatoirement
//                    else{
//                        int i = RAND.nextInt(tempConcur.size());
//                        _ag.set(tempConcur.get(i).getId(), tempConcur.get(i));
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
////        else{
////            System.out.println("HEEERRRE +++ "+ pos);
////        }
//    }
//    
//    
//    /**
//     *
//     * @param cell
//     * @param k
//     * @return 
//     */
////    @Override
//    public Neighbours countNeighbours(Cell cell, int k) {
//        
//        this.nghbrs.setExcited_free_cells_count(0); 
//        this.nghbrs.setFree_cells(new ArrayList<>());
//        this.nghbrs.setExcited_free_cells(new ArrayList<>());
//
//        for(int di=-1; di<=1; di++) {
//            for(int dj=-1; dj<=1; dj++){
//                int ii = (cell.getI() + di + MATRIX_LENGTH) % MATRIX_LENGTH;
//                int jj = (cell.getJ() + dj + MATRIX_LENGTH) % MATRIX_LENGTH;
//                //if this neighbours isn't me or isn't a wall
//                if(!(cell.getI()==ii && cell.getJ()==jj) && !this.matrix[ii][jj].isWall()){
//                    //if his state is excited
//                    if (this.matrix[ii][jj].isMaxStateAtK(MLEVEL, k)) {
//                        //I count it
//                        this.nghbrs.increaseEFCount(1);
//                        //if nb agents for this class on this neighbour is < 2
//                        if (this.matrix[ii][jj].getNb_agentsAtK(k) < 2)
//                            //then I add it to neighbours set
//                            this.nghbrs.addFreeExcitedCells(this.matrix[ii][jj]);
//                    }
//                    //Si cette cellule contient un agent && si sont voisin est libre
//                    if (this.matrix[ii][jj].getNb_agentsAtK(k) > 0 
//                            && this.matrix[ii][jj].getNb_agentsAtK(k) < 2) 
//                        this.nghbrs.addFreeCells(this.matrix[ii][jj]);
//                }
//            }
//        }
//        
//        return null;
//    }
//
//    @Override
//    public void deleteAgent(int i, int j) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    @Override
//    public CellInfClass[] getListOfNeighbours(int i, int j, int nbNghbrs) {
//        
//        CellInfClass[] nb = new CellInfClass[nbNghbrs];
//        int m = 0;
//        for(int di=-1; di<=1; di++) {
//            for(int dj=-1; dj<=1; dj++){
//                int ii = (i + di + MATRIX_LENGTH) % MATRIX_LENGTH;
//                int jj = (j + dj + MATRIX_LENGTH) % MATRIX_LENGTH;
//                if(!(i==ii && j==jj) && !this.matrix[ii][jj].isWall()){
//                    nb[m] = this.matrix[ii][jj];
//                    m++;
//                }
//            }
//        }
//        return nb;
//    }
//}
