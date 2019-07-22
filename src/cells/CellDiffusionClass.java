/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cells;

import _diverse.PointAndCell;
import _diverse.Neighbours;
import java.awt.Color;
import java.awt.Point;
import static _diverse.Params.RAND;
import static _diverse.Params.bernoulli;
import _diverse.Prm;

/**
 *
 * @author Karim
 */
public class CellDiffusionClass extends Cell{
    
    //Array of states
    private int[] state;
    //Next position for the agent of this cell to move on (delta)
    private int di;
    private int dj;
    //Classe of the cell
    private int classe;
    //for when the cell is reserved by the first agent that reserves it
    private boolean reserved;
    
    public CellDiffusionClass(Prm param){
        super();
        this.state = this.stateInitializer(param);
        this.di = -1;
        this.dj = -1;
        this.classe = -1;
        this.reserved = false;
    }
    
    public CellDiffusionClass(Prm param, int nbA, Color c, int i, int j, boolean w){
        super(nbA, c, i, j, w);
        this.state = this.stateInitializer(param);
        this.di = -1;
        this.dj = -1;
        this.classe = -1;
        this.reserved = false;
    }
    
    public CellDiffusionClass(Prm param, int nbA, Color c, int i, int j, boolean w, 
            int[] s, int di, int dj, int cl, boolean r) {
        super(nbA, c, i, j, w);
        this.state = new int[param.NB_CLASSES];
        if(s!=null)
            System.arraycopy(s, 0, this.state, 0, param.NB_CLASSES);
        this.di = di;
        this.dj = dj;
        this.classe = cl;
        this.reserved = r;
    }
    
//    public CellDiffusionClass(int nbA, Color c, int i, int j, boolean w, 
//            int[] s, int cl) {
//        super(nbA, c, i, j, w);
//        this.state = new int[NB_CLASSES];
//        if(s!=null)
//            System.arraycopy(s, 0, this.state, 0, NB_CLASSES);
//        this.classe = cl;
//        this.di = -1;
//        this.dj = -1;
//    }

    public void setState(Prm param, int[] state) {
        this.state = new int[param.NB_CLASSES];
        if(state!=null)
            System.arraycopy(state, 0, this.state, 0, param.NB_CLASSES);
    }

    public void setDi(int di) {
        this.di = di;
    }

    public void setDj(int dj) {
        this.dj = dj;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void setStateAtK(int k, int s) {
        this.state[k] = s;
    }

    public void setMaxAtK(Prm param, int k) {
        this.state[k] = maxLevel(param, k);
    }

    public void setMinAtK(Prm param, int k) {
        this.state[k] = minLevel(param, k);
    }
    
    public int[] getState() {
        return this.state;
    }

    public int getDi() {
        return di;
    }

    public int getDj() {
        return dj;
    }

    public int getClasse() {
        return classe;
    }

    public boolean isReserved() {
        return reserved;
    }
    
    private int [] stateInitializer(Prm param){
        int[] a = new int[param.NB_CLASSES];
        for (int i = 0; i < param.NB_CLASSES; i++) {
            //initialisation avec le min de chaque classe
            a[i] = i * (param.MLEVEL+1); //i représente la classe
        }
        return a;
    }
    
    public CellDiffusionClass[][] additional_step(Prm param, CellDiffusionClass[][] new_matrix_class){
        
        if (this.di != -1 && this.dj != -1){
            //moving agents
            this.decreaseNbAgents(1);
            new_matrix_class[this.di][this.dj].increaseNbAgents(1);
            //setting new classe
            new_matrix_class[this.di][this.dj].setClasse(this.classe);
            //setting color
            new_matrix_class[this.di][this.dj].setCouleur(param.getCOLOR_at(this.classe));
        }
        if (this.getNbAgents() == 1)
            this.setCouleur(param.getCOLOR_at(this.classe));
        else if (this.getNbAgents() > 1)
            this.setCouleur(param.getCOLOR_at(this.classe));
        else //means this class became empty, so we make it a neutral class
            this.setClasse(-1);
        //always un-reserve the cell
        this.reserved = false;
        
        return new_matrix_class;
    }
    
    public boolean isMaxStateAtK(Prm param, int k){
        return this.state[k] == maxLevel(param, k);
    }
    
    public boolean isMinStateAtK(Prm param, int k){
        return this.state[k] == minLevel(param, k);
    }
    
    public boolean isSupThenMinAtK(Prm param, int k){
        return this.state[k] > minLevel(param, k);
    }
    
    public void decreaseStateatK(int k, int value){
        this.state[k] -= value;
    }
    /**
     *  Calculate Max level
     * @param k
     * @return
     */
    public int maxLevel(Prm param, int k){
        return k * (param.MLEVEL+1) + param.MLEVEL;
    }
    
    /**
     * Caculate Min
     * @param k
     * @return
     */
    public int minLevel(Prm param, int k){
        return k * (param.MLEVEL+1);
    }

    @Override
    public CellDiffusionClass nextState(Prm param, Neighbours nghbrs) {
        return null;
    }
    
    public PointAndCell nextPoint(Prm param, Neighbours nghbrs) {
        Point p = new Point();
        CellDiffusionClass new_cell = new CellDiffusionClass(param, this.getNbAgents(), this.getCouleur(),
                                this.getI(), this.getJ(), false, this.state, this.di, this.dj,
                                this.classe, this.reserved);
        
        if(bernoulli(param.PA) == 1) {
            if (!nghbrs.isEmptyFreeCells()) {
                //choose a random neighbour
                Cell delta = nghbrs.getElementFreeCells(
                        RAND.nextInt(nghbrs.getSizeFreeCells()));
                //put new location
                new_cell.setDi(delta.getI());
                new_cell.setDj(delta.getJ());
                //reserve the cell 
                //(so the first to reserve it will get it)
                //also, knowing that we always run through the matrix the same way
                //the order is static
                p.setLocation(delta.getI(), delta.getJ());
//                    this.matrix[delta.getI()][delta.getJ()].setReserved(true);
            }
        }
        //if state of current cell == min && there are accecible neighbours to move to
        else if(new_cell.isMinStateAtK(param, new_cell.getClasse())) {
            if (!nghbrs.isEmptyFreeExcitedCells()) {
                //choose a random neighbour
                Cell delta = nghbrs.getElementFreeExcitedCells(
                        RAND.nextInt(nghbrs.getSizeFreeExcitedCells()));
                //put new location
                new_cell.setDi(delta.getI());
                new_cell.setDj(delta.getJ());
                //reserve the cell 
                //(so the first to reserve it will get it)
                //also, knowing that we always run through the matrix the same way
                //the order is static
//                    this.matrix[delta.getI()][delta.getJ()].setReserved(true);
                p.setLocation(delta.getI(), delta.getJ());
            }
        }
        
        return new PointAndCell(p, new_cell);
    }
    
    public boolean expandWave(Prm param, Neighbours nghbrs, int k) {
        
        if (this.isMinStateAtK(param, k) //test min
            && (nghbrs.isSupThen(0)
                || (this.getNbAgents() > 0 
                    && this.getClasse() == k 
                    && bernoulli(param.LAMBDA) == 1))){
            this.setMaxAtK(param, k); //put max
            //coloring
            this.setCouleur(param.getCOLOR_at(k, 100));
            param.VAGUE = true;
        }
        else if (this.isSupThenMinAtK(param, k)){

            this.decreaseStateatK(k, 1);
            if(this.isSupThenMinAtK(param, k)) {
                this.setCouleur(param.getCOLOR_at(k, 100));
                param.VAGUE = true;
            }
        }
        else{
            this.setMinAtK(param, k);
            if(!param.VAGUE) this.setCouleur(param.COLORS.COLOR_DEFAULT);
        }
        return param.VAGUE;
    }
    
    public void printCell(String msg){
//        System.out.println(msg);
//        System.out.println(
//                msg + 
//                " | i : " + this.getI()+
//                " | j: " + this.getJ()+
//                " | nbAgent: " + this.getNbAgents()+
//                " | Wall: " + this.isWall()+
//                " | Di: " + this.di+
//                " | Dj: " + this.dj+
//                " | State: " + Arrays.toString(this.state)+
//                " | Reserved: " + this.reserved+
//                " | classe: " + this.classe+
//                " | Color: " + this.getCouleur());
    }
    
    public void doStuff(Prm param, int cl){
        this.increaseNbAgents(1);
        //setting new classe
        this.setClasse(cl);
        //setting color
        this.setCouleur(param.getCOLOR_at(cl));
    }
}
