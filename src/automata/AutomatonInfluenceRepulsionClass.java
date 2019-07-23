/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

import _diverse.Neighbours;
import _diverse.Params;
import static _diverse.Params.RAND;
import static _diverse.Params.bernoulli;
import agents.Agent;
import agents.AgentInfluenceRepulsionClass;
import cells.Cell;
import cells.CellInfluenceRepulsionClass;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Karim
 */
public class AutomatonInfluenceRepulsionClass extends Automaton{
    
    // The matrix
    private CellInfluenceRepulsionClass[][] matrix;
    
    // The agent list
    private ArrayList<Agent> _ag;
    
    // The neighbours object
    private Neighbours nghbrs;
    
    // Counter for ids
    private int idCounter;
    
    /**
     *
     */
    public AutomatonInfluenceRepulsionClass() {
        super();
        this.matrix = new CellInfluenceRepulsionClass[getMATRIX_LENGTH()][getMATRIX_LENGTH()];
        this._ag = new ArrayList<>();
        this.nghbrs = new Neighbours(new HashMap<>());
        this.idCounter = 0;
    }
    
    /**
     *
     * @param mx
     * @param aa
     */
    public AutomatonInfluenceRepulsionClass(CellInfluenceRepulsionClass[][] mx, ArrayList<Agent> aa) {
        super();
        this.matrix = mx;
        this._ag = aa;
    }
    
    @Override
    public void setAgent(int i, int j, int nb, Color co, boolean wl) {
        
        // calcul de la classe
        int cls = RAND.nextInt(getNB_CLASSES());
        
        this.matrix[i][j].setCouleur(getCOLOR_at(cls));
        this.matrix[i][j].setWall(wl);
        this.matrix[i][j].setNb_agentsAtK(cls, nb);
        int pos = j + i*getMATRIX_LENGTH();
        _ag.add(this.idCounter, new AgentInfluenceRepulsionClass(i, j, pos, this.idCounter, cls));
        this.idCounter++;
    }

    @Override
    public Color getCellColor(int i, int j) {
        return this.matrix[i][j].getCouleur();
    }

    @Override
    public void init_matrix() {
        for(int i=0; i<getMATRIX_LENGTH(); i++) {
            for(int j=0; j<getMATRIX_LENGTH(); j++){
                this.matrix[i][j] = new CellInfluenceRepulsionClass(getP(), 0, getCOLOR_DEFAULT(), i, j, false, j + i*getMATRIX_LENGTH());
            }
        }
        //applay boundaries if they are enabled
        if(isBOUNDARIESequalTo("Free")) makeBoundaries();
        //REstart the others too
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
            rn_x = getRANDcoordinate();
            rn_y = getRANDcoordinate();
            
            //Créer l'agent (couleur null car je la calculerai plutard)
            this.setAgent(rn_x, rn_y, 1, null, false);
        }
    }

    @Override
    public void step() {
        
        Agent new_t;
        int thisi, thisj;
        
        CellInfluenceRepulsionClass[][] new_matrix_fier = new CellInfluenceRepulsionClass[getMATRIX_LENGTH()][getMATRIX_LENGTH()];
        
        
        //NEXT STATE Classifier
        for(int i=0; i<_ag.size(); i++) {

            //Recupéré uniquement l'état pour cette classe de cet agent
            thisi = _ag.get(i).getI();
            thisj = _ag.get(i).getJ();

            //Count neighbours
            this.countNeighbours(this.matrix[thisi][thisj], ((AgentInfluenceRepulsionClass)_ag.get(i)).getClasse());
            
            //PERCIEVE + DECIDE
            new_t = _ag.get(i).move(getP(), nghbrs, this.matrix[thisi][thisj]);
            //je vais le mettre directement pour l'instant

            //Si cet agent va se deplacer
            if(new_t != null){
                //informe cells of decisions
                ArrayList<Agent> arT = new ArrayList<>();
                if(nghbrs.cCContainsKey(new_t.getPosition())){
                    arT = nghbrs.cCGet(new_t.getPosition());
                }
                arT.add(new_t);
                nghbrs.cCPut(new_t.getPosition(), arT);
            }
        }
//                System.out.println("sizzzzze : "+_ag.size());
        //Conflicts + NEXT STATE System
        //UPDATE(Agents) + EVOLVE(System)
        for(int i=0; i<getMATRIX_LENGTH(); i++) {
            for(int j=0; j<getMATRIX_LENGTH(); j++){
                if (!this.matrix[i][j].isWall()) {
                    CellInfluenceRepulsionClass new_cell = new CellInfluenceRepulsionClass(getP(), this.matrix[i][j].getNbAgents(), this.matrix[i][j].getCouleur(),
                                this.matrix[i][j].getI(), this.matrix[i][j].getJ(), false, this.matrix[i][j].getState(),
                                this.matrix[i][j].getNb_agents(), this.matrix[i][j].getPosition());
//                    System.out.println("PPPPPPPPPP ++ " + this.matrix[i][j].getPosition());
                    //this variables assures that waves are displayed correctly
                    //if the first vague is diplayed, other do not
                    //if the first is not, the second will and the rest will not
                    //and so on...
                    setVAGUE(false);
                    setFIRST_TO_FIRE(true);
//                    new_matrix_fier[i][j] = this.matrix[i][j];
                    new_matrix_fier[i][j] = new_cell;
                    //Expantion de la Vague
                    //for all classes
                    for (int k = 0; k < getNB_CLASSES(); k++) {
                        //count excited neighbours for this class
                        this.countNeighbours(new_matrix_fier[i][j], k);
                        boolean new_VAGUE = new_matrix_fier[i][j].expandWave(getP(), nghbrs, k);
                        setVAGUE(new_VAGUE);
                        if(new_VAGUE) setFIRST_TO_FIRE(false);
                    }
                    new_matrix_fier[i][j].agentInitializer(getP());
                    this.nextState(new_matrix_fier[i][j].getPosition());
                    
//                    new_matrix_fier[i][j] = this.matrix[i][j].nextState(nghbrs);
//                    this.nextState(nghbrs, new_matrix_fier[i][j].getPosition());
                }
                else {
                    new_matrix_fier[i][j] = makeWall(i, j);
                }
            }
        }

        //UPDATE(Agents) + EVOLVE(System)
        for(int i=0; i<_ag.size(); i++) {
            int cl = ((AgentInfluenceRepulsionClass)_ag.get(i)).getClasse();
            int ii = _ag.get(i).getI();
            int jj = _ag.get(i).getJ();
//            new_matrix_fier[ii][jj].setCouleur(getCOLOR_at(cl));
            new_matrix_fier[ii][jj].increaseNbAgentsAtK(cl, 1);
            if(new_matrix_fier[ii][jj].nbAgentsAtKisEqualTo(cl, 1))
                new_matrix_fier[ii][jj].setCouleur(getCOLOR_at(cl));
            else{
                new_matrix_fier[ii][jj].setCouleur(getCOLOR_at(cl, 255));
            }
            
            //This part is just for coloring with black when there are multiple classes in same cell
            int a = 0, kk = 0;
            while(a<2 && kk<getNB_CLASSES()){
                if(new_matrix_fier[ii][jj].isNbAgentsAtKsupThen(kk, 0))
                    a++;
                kk++;
            }
            if(a>=2) new_matrix_fier[ii][jj].setCouleur(Color.BLACK);
        }
        this.matrix = new_matrix_fier;
//        _listConcurent = new HashMap<>();
        this.nghbrs.setConcurent_cells(new HashMap<>());
        increaseNBGeneration(1);
        
    }
    
    /**
     *
     * @param pos
     */
    public void nextState(int pos){
        
        //MàJ Agents + couleurs
        
        if(this.nghbrs.cCGet(pos) != null) {
            ArrayList<Agent> tempConcur = this.nghbrs.cCGet(pos);
            switch(getPOLICY()){
                case CYCLIC:
                    //Normalement qlq soit le nombre d'agent on prendra tjrs le 1er et le reste
                    //on les prends pas, ils ne vont pas changer dans _ag.
                    _ag.set(tempConcur.get(0).getId(), tempConcur.get(0));
                    break;
                case RANDOM:
                    //Si 1 seul agent veut se délpacer on le prend automatiquement
                    if(tempConcur.size() == 1)
                        _ag.set(tempConcur.get(0).getId(), tempConcur.get(0));
                    //Si nbr d'agents > 1 on choisis aléatoirement
                    else{
                        int i = RAND.nextInt(tempConcur.size());
                        _ag.set(tempConcur.get(i).getId(), tempConcur.get(i));
                    }
                    break;
                default:
                    break;
            }
        }
//        else{
//            System.out.println("HEEERRRE +++ "+ pos);
//        }
    }
    
    
    /**
     *
     * @param cell
     * @param k
     * @return 
     */
    @Override
    public Neighbours countNeighbours(Cell cell, int k) {
        
        this.nghbrs.setExcited_free_cells_count(0); 
        this.nghbrs.setFree_cells(new ArrayList<>());
        this.nghbrs.setExcited_free_cells(new ArrayList<>());
        this.nghbrs.setRepulsive_free_cells(new ArrayList<>());

        for(int di=-1; di<=1; di++) {
            for(int dj=-1; dj<=1; dj++){
                int ii = (cell.getI() + di + getMATRIX_LENGTH()) % getMATRIX_LENGTH();
                int jj = (cell.getJ() + dj + getMATRIX_LENGTH()) % getMATRIX_LENGTH();
                //if this neighbours isn't me or isn't a wall
                if(!(cell.getI()==ii && cell.getJ()==jj) && !this.matrix[ii][jj].isWall()){
                    //if his state is excited
                    if (this.matrix[ii][jj].isMaxStateAtK(getMLEVEL(), k)) {
                        //I count it
                        this.nghbrs.increaseEFCount(1);
                        //if nb agents for this class on this neighbour is < 2
                        if (this.matrix[ii][jj].getNb_agentsAtK(k) < 2)
                            //then I add it to neighbours set
                            this.nghbrs.addFreeExcitedCells(this.matrix[ii][jj]);
                    }
                    //Repulsion
                    //TEstet toutes les autres vagues pour voir si y'a une qui repousse
                    else{
                        //Pour toutes les classes de vague
                        for(int cl=0; cl<getNB_CLASSES(); cl++){
                            //Si c'est pas ma classe (je peux l'omettre car le else)
                            if(cl != k){
                                //Si cette cell est excité pour cette classe et 
                                //si la proba qu'elle repulse est vrai
                                if(this.matrix[ii][jj].isMaxStateAtK(getMLEVEL(), cl) 
                                        && bernoulli(getREPULSION()) == 1){
                                    //normalement j'utilise pas ce tableau
                                    //Mais pour l'instant c'est juste un test
                                    int iii = (cell.getI() - (di * -1) + getMATRIX_LENGTH()) % getMATRIX_LENGTH();
                                    int jjj = (cell.getJ() - (dj * -1) + getMATRIX_LENGTH()) % getMATRIX_LENGTH();
                                    if (this.matrix[iii][jjj].getNb_agentsAtK(k) < 2){
                                        this.nghbrs.addFreeRepulsiveCells(this.matrix[iii][jjj]);
//                                        System.out.println("YEEEEEEEEEEEEEAAAAH! moving : ("
//                                                +cell.getI()+","+cell.getJ()+") TO ("+iii+","+jjj+")");
                                    }
                                }
                            }
                        }
                    }
                    //si sont voisin est libre
//                    if (this.matrix[ii][jj].getNb_agentsAtK(k) > 0 
//                            && this.matrix[ii][jj].getNb_agentsAtK(k) < 2) 
                    if (this.matrix[ii][jj].getNb_agentsAtK(k) < 2) {
                        this.nghbrs.addFreeCells(this.matrix[ii][jj]);
                    }
                }
            }
        }
        return null;
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
    public CellInfluenceRepulsionClass makeWall(int i, int j) {
        return new CellInfluenceRepulsionClass(getCOLOR_OBSTACLE(), i, j, true);
    }
}
