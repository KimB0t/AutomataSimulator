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
//import agents.Agent;
//import agents.AgentTurmite;
//import cells.Cell;
//import cells.CellTurmites;
//import java.awt.Color;
//import java.util.ArrayList;
//import java.util.HashMap;
//import misc.Params.Directions;
//import static misc.Params.RAND;
//
///**
// *
// * @author Karim
// */
//public class AutoTurmites extends Automaton{
//
//    //<editor-fold defaultstate="collapsed" desc="Declarations">
//    private CellTurmites[][] matrix;
//    private ArrayList<Agent> _ag;
//    private Neighbours nghbrs;
//    // Counter for ids
//    private int idCounter;
////</editor-fold>
//    
//    //<editor-fold defaultstate="collapsed" desc="Constructors">
//    public AutoTurmites() {
//        super();
//        this.matrix = new CellTurmites[MATRIX_LENGTH][MATRIX_LENGTH];
//        this._ag = new ArrayList<>();
//        this.nghbrs = new Neighbours(new HashMap<>());
//        this.idCounter = 0;
//        
//    }
//    
//    public AutoTurmites(CellTurmites[][] mx, ArrayList<Agent> a, HashMap<Integer, ArrayList<Agent>> lsc) {
//        super();
//        this.matrix = mx;
//        this._ag = a;
//        this.nghbrs = new Neighbours(lsc);
//    }
////</editor-fold>
//    
//    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
//    public CellTurmites getCellTurmite(int i, int j){
//        return this.matrix[i][j];
//    }
//
//    @Override
//    public void setAgent(int i, int j, int nb, Color co, boolean wl) {
//        
//        this.matrix[i][j].setCouleur(co);
//        this.matrix[i][j].setWall(wl);
//        int pos = j + i*MATRIX_LENGTH;
//        this._ag.add(this.idCounter, new AgentTurmite(i, j, Directions.EAST, this.idCounter));
//        this.idCounter++;
//    }
//    
//    @Override
//    public Color getCellColor(int i, int j){
//        return this.matrix[i][j].getCouleur();
//    }
//    
//    public void setStateCell(int i, int j, int s){
//        this.matrix[i][j].setState(s);
//    }
//    
//    public int getAgSize(){
//        return _ag.size();
//    }
//    
//    public void agAddElem(int id, AgentTurmite ag){
//        this._ag.add(id, ag);
//    }
////</editor-fold>
//    
//    //<editor-fold defaultstate="collapsed" desc="Makers">
//    @Override
//    public void makeWallAt(int i, int j) {
//        this.matrix[i][j] = new CellTurmites(getCOLOR_OBSTACLE(), i, j, true);
//    }
//    
//    @Override
//    public void makeCellAt(int i, int j) {
//        this.matrix[i][j] = new CellTurmites(getCOLOR_DEFAULT(), i, j, false, 0, j + i*MATRIX_LENGTH);
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
//    public void step() {
//        
//        Agent new_t;
//        int thisi, thisj, thisState;
//        
//        //NEXT STATE Turmites
//        for(int i=0; i<this._ag.size(); i++) {
//
//            thisi = this._ag.get(i).getI();
//            thisj = this._ag.get(i).getJ();
//            thisState = this.matrix[thisi][thisj].getState();
//
//            //PERCIEVE + DECIDE
//            new_t = this._ag.get(i).move(thisState);
//
//            //informe cells of decisions
////            ArrayList<Agent> arT = new ArrayList<>();
////            if(this.nghbrs.cCContainsKey(new_t.getPosition())){
////                arT = this.nghbrs.cCGet(new_t.getPosition());
////            }
////            arT.add(new_t);
////            this.nghbrs.cCPut(new_t.getPosition(), arT);
//        }
//
//        //Conflicts + NEXT STATE System
//        //UPDATE(Agents) + EVOLVE(System)
//        for(int i=0; i<MATRIX_LENGTH; i++) {
//            for(int j=0; j<MATRIX_LENGTH; j++){
//                if (!this.matrix[i][j].isWall()) {
//                    this.nextStateCell(matrix[i][j]);
//                }
//            }
//        }
//
//        //UPDATE(Agents) + EVOLVE(System)
//        for(int i=0; i<_ag.size(); i++) {
//            this.matrix[_ag.get(i).getI()][this._ag.get(i).getJ()].setCouleur(getCOLOR_AGENT1());
//        }
//        
//        //Reinit the array
//        this.nghbrs.setConcurent_cells(new HashMap<>());
//        increaseNBGeneration(1);
//    }
//
//    public Cell nextStateCell(Cell c) {
//        
//        if(nghbrs.cCGet(((CellTurmites)c).getPosition()) != null) {
//            ArrayList<Agent> tempConcur = nghbrs.cCGet(((CellTurmites)c).getPosition());
//            int oldi, oldj;
//            switch(POLICY){
//                case CYCLIC:
//                    //Normalement qlq soit le nombre d'agent on prendra tjrs le 1er et le reste
//                    //on les prends pas, ils ne vont pas changer dans _ag.
//                    oldi = _ag.get(tempConcur.get(0).getId()).getI();
//                    oldj = _ag.get(tempConcur.get(0).getId()).getJ();
//                    if(!this.matrix[tempConcur.get(0).getI()][tempConcur.get(0).getJ()].isWall()){
//                        this.matrix[oldi][oldj].changeState(getParams());
//                        //get(0) pour recupèrer le 1er agent
//                        _ag.set(tempConcur.get(0).getId(), tempConcur.get(0));
//                    }
//                    break;
//                case RANDOM:
//                    //Choisir 1 aléatoirement
//                    int i = RAND.nextInt(tempConcur.size());
//                    oldi = _ag.get(tempConcur.get(i).getId()).getI();
//                    oldj = _ag.get(tempConcur.get(i).getId()).getJ();
//                    if(!this.matrix[tempConcur.get(0).getI()][tempConcur.get(0).getJ()].isWall()){
//                        this.matrix[oldi][oldj].changeState(getParams());
//                        //get(0) pour recupèrer le 1er agent
//                        _ag.set(tempConcur.get(i).getId(), tempConcur.get(i));
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//        return null;
//    }
//    
//    @Override
//    public CellTurmites[] getListOfNeighbours(int i, int j, int nbNghbrs) {
//        
//        CellTurmites[] nb = new CellTurmites[nbNghbrs];
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
//    
//    @Override
//    public void deleteAgent(int i, int j) {
//        
////        this.matrix[i][j].setNbAgents(0);
////        this.matrix[i][j].setCouleur(getCOLOR_DEFAULT());
////        this.matrix[i][j].setWall(false);
////        int pos = j + i*MATRIX_LENGTH;
////        Iterator<Agent> it = this._ag.iterator();
////        while(it.hasNext()){
////            Agent a = it.next();
////            if(a.getPosition() == pos){
////                this._ag.remove(a);
////                System.out.println("HAS BEEN DELETED");
////            }
////        }
////        if(this._ag.contains(this.matrix[i][j])){
////            this._ag.remove(this.matrix[i][j]);
////            System.out.println("HAS BEEN DELETED");
////        }
////        this.idCounter--;
//    }
//}
