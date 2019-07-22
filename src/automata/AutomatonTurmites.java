/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

import _diverse.Neighbours;
import agents.Agent;
import agents.AgentTurmite;
import cells.Cell;
import cells.CellTurmites;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import _diverse.Params.Directions;
import static _diverse.Params.RAND;

/**
 *
 * @author Karim
 */
public class AutomatonTurmites extends Automaton{

    private CellTurmites[][] matrix;
    private ArrayList<Agent> _ag;
//    private HashMap<Integer, ArrayList<AgentTurmite>> _listConcurent;
    private Neighbours nghbrs;
    
    // Counter for ids
    private int idCounter;
    
    
    public AutomatonTurmites() {
        super();
        this.matrix = new CellTurmites[getMATRIX_LENGTH()][getMATRIX_LENGTH()];
        this._ag = new ArrayList<>();
        this.nghbrs = new Neighbours(new HashMap<>());
        this.idCounter = 0;
        
    }
    
    public AutomatonTurmites(CellTurmites[][] mx, ArrayList<Agent> a, HashMap<Integer, ArrayList<Agent>> lsc) {
        super();
        this.matrix = mx;
        this._ag = a;
        this.nghbrs = new Neighbours(lsc);
    }
    
    public CellTurmites getCellTurmite(int i, int j){
        return this.matrix[i][j];
    }

    @Override
    public void setAgent(int i, int j, int nb, Color co, boolean wl) {
        
        this.matrix[i][j].setNbAgents(nb);
        this.matrix[i][j].setCouleur(co);
        this.matrix[i][j].setWall(wl);
        int pos = j + i*getMATRIX_LENGTH();
        this._ag.add(this.idCounter, new AgentTurmite(i, j, Directions.EAST, pos, this.idCounter));
        this.idCounter++;
    }
    
    @Override
    public Color getCellColor(int i, int j){
        return this.matrix[i][j].getCouleur();
    }
    
    @Override
    public void init_matrix() {
        for(int i=0; i<getMATRIX_LENGTH(); i++) {
            for(int j=0; j<getMATRIX_LENGTH(); j++){
                this.matrix[i][j] = 
                        new CellTurmites(0, getCOLOR_DEFAULT(), i, j, false, 0, j + i*getMATRIX_LENGTH());
            }
        }
        this._ag = new ArrayList<>();
        this.nghbrs = new Neighbours(new HashMap<>());
        this.idCounter = 0;
    }

    @Override
    public void randomConfig() {
        
        this.init_matrix();
        int rn_x, rn_y;
        int nbr_cell = (int)((getMATRIX_LENGTH()) * (getMATRIX_LENGTH()) * getDENSITY() / 100);
        
        System.out.println("nbr_cell (nb agents) = "+nbr_cell);
        reInitNBGeneration();
        
        for(int i=0; i<nbr_cell; i++){
            
            // Calcul des coordonnées
            rn_x = RAND.nextInt(getMATRIX_LENGTH());
            rn_y = RAND.nextInt(getMATRIX_LENGTH());
            
            // Créer l'agent (cet n'agent n'a pas de classe mais a un id)
            this.setAgent(rn_x, rn_y, 1, getCOLOR_AGENT1(), false);
        }
    }

    @Override
    public void step() {
        
        Agent new_t;
        int thisi, thisj, thisState;
        
        //NEXT STATE Turmites
        for(int i=0; i<this._ag.size(); i++) {

            thisi = this._ag.get(i).getI();
            thisj = this._ag.get(i).getJ();
            thisState = this.matrix[thisi][thisj].getState();

            //PERCIEVE + DECIDE
            new_t = this._ag.get(i).move(getP(), thisState);

            //informe cells of decisions
            ArrayList<Agent> arT = new ArrayList<>();
            if(this.nghbrs.cCContainsKey(new_t.getPosition())){
                arT = this.nghbrs.cCGet(new_t.getPosition());
            }
            arT.add(new_t);
            this.nghbrs.cCPut(new_t.getPosition(), arT);
        }

        //Conflicts + NEXT STATE System
        //UPDATE(Agents) + EVOLVE(System)
        for(int i=0; i<getMATRIX_LENGTH(); i++) {
            for(int j=0; j<getMATRIX_LENGTH(); j++){
                if (!this.matrix[i][j].isWall()) {
                    this.nextStateCell(matrix[i][j]);
                }
            }
        }

        //UPDATE(Agents) + EVOLVE(System)
        for(int i=0; i<_ag.size(); i++) {
            this.matrix[_ag.get(i).getI()][this._ag.get(i).getJ()].setCouleur(getCOLOR_AGENT1());
        }
        
        //Reinit the array
        this.nghbrs.setConcurent_cells(new HashMap<>());
        increaseNBGeneration(1);
    }

    public Cell nextStateCell(Cell c) {
        
        if(nghbrs.cCGet(((CellTurmites)c).getPosition()) != null) {
            ArrayList<Agent> tempConcur = nghbrs.cCGet(((CellTurmites)c).getPosition());
            int oldi, oldj;
            switch(getPOLICY()){
                case CYCLIC:
                    //Normalement qlq soit le nombre d'agent on prendra tjrs le 1er et le reste
                    //on les prends pas, ils ne vont pas changer dans _ag.
                    oldi = _ag.get(tempConcur.get(0).getId()).getI();
                    oldj = _ag.get(tempConcur.get(0).getId()).getJ();
                    if(!this.matrix[tempConcur.get(0).getI()][tempConcur.get(0).getJ()].isWall()){
                        this.matrix[oldi][oldj].changeState(getP());
                        //get(0) pour recupèrer le 1er agent
                        _ag.set(tempConcur.get(0).getId(), tempConcur.get(0));
                    }
                    break;
                case RANDOM:
                    //Choisir 1 aléatoirement
                    int i = RAND.nextInt(tempConcur.size());
                    oldi = _ag.get(tempConcur.get(i).getId()).getI();
                    oldj = _ag.get(tempConcur.get(i).getId()).getJ();
                    if(!this.matrix[tempConcur.get(0).getI()][tempConcur.get(0).getJ()].isWall()){
                        this.matrix[oldi][oldj].changeState(getP());
                        //get(0) pour recupèrer le 1er agent
                        _ag.set(tempConcur.get(i).getId(), tempConcur.get(i));
                    }
                    break;
                default:
                    break;
            }
        }
        return null;
    }
    
    public void setStateCell(int i, int j, int s){
        this.matrix[i][j].setState(s);
    }
    
    public int getAgSize(){
        return _ag.size();
    }
    
    public void agAddElem(int id, AgentTurmite ag){
        this._ag.add(id, ag);
    }

    @Override
    public Neighbours countNeighbours(Cell c, int k) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}