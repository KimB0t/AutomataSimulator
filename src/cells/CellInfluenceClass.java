/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cells;

import _diverse.AllColors;
import _diverse.Neighbours;
import static _diverse.Params.bernoulli;
import _diverse.Prm;
import java.awt.Color;

/**
 *
 * @author Karim
 */
public class CellInfluenceClass extends Cell{
    
    //Array of states of every classe i
    private int[] state;
    //nb agents pour chaque classe i
    private int[] nb_agents;
    //position
    private int position;
    
    public CellInfluenceClass(Prm param, int nbA, Color c, int i, int j, boolean w, int[] s, int[] nA, int pos) {
        super(nbA, c, i, j, w);
        
        this.position = pos;
        
        this.state = new int[param.NB_CLASSES];
        if(s!=null)
            System.arraycopy(s, 0, this.state, 0, param.NB_CLASSES);
        
        this.nb_agents = new int[param.NB_CLASSES];
        if(nA!=null)
            System.arraycopy(nA, 0, this.nb_agents, 0, param.NB_CLASSES);
    }
    
    /**
     *
     * @param s
     * @param cls
     */
    public CellInfluenceClass(Prm param, int[] s, int[] nA, int pos) {
        super();
        
        this.position = pos;

        this.state = new int[param.NB_CLASSES];
        if(s!=null)
            System.arraycopy(s, 0, this.state, 0, param.NB_CLASSES);
        
        this.nb_agents = new int[param.NB_CLASSES];
        if(nA!=null)
            System.arraycopy(nA, 0, this.nb_agents, 0, param.NB_CLASSES);
    }

    /**
     *
     */
    public CellInfluenceClass(Prm param) {
        super();
        this.stateInitializer(param);
        this.agentInitializer(param);
        this.position = -1;
        this.state = new int[param.NB_CLASSES];
        this.nb_agents = new int[param.NB_CLASSES];
    }
    
    public CellInfluenceClass(Prm param, int nbA, Color c, int i, int j, boolean w) {
        super(nbA, c, i, j, w);
        this.stateInitializer(param);
        this.agentInitializer(param);
        this.position = -1;
//        this.state = new int[param.NB_CLASSES];
//        this.nb_agents = new int[param.NB_CLASSES];
    }

    public CellInfluenceClass(Prm param, int nbA, Color c, int i, int j, boolean w, int pos) {
        super(nbA, c, i, j, w);
        this.position = pos;
        this.state = new int[param.NB_CLASSES];
        this.nb_agents = new int[param.NB_CLASSES];
    }
    
    public void setState(Prm param, int[] state) {
        this.state = new int[param.NB_CLASSES];
        if(state!=null)
            System.arraycopy(state, 0, this.state, 0, param.NB_CLASSES);
    }

    public CellInfluenceClass(Color c, int i, int j, boolean w) {
        super(c, i, j, w);
        this.state = null;
        this.nb_agents = null;
        this.position = -1;
    }
    
//    public void setNb_agents(int[] nb_agents) {
//        this.nb_agents = new int[NB_CLASSES];
//        if(nb_agents!=null)
//            System.arraycopy(nb_agents, 0, this.nb_agents, 0, NB_CLASSES);
//    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int[] getNb_agents() {
        return nb_agents;
    }

    public int getPosition() {
        return position;
    }
    
    public void setStateAtK(int k, int s) {
        this.state[k] = s;
    }

    public void setMaxAtK(int MLEVEL, int k) {
        this.state[k] = maxLevel(MLEVEL, k);
    }

    public void setMinAtK(int MLEVEL, int k) {
        this.state[k] = minLevel(MLEVEL, k);
    }
    
    public int[] getState() {
        return this.state;
    }
    
    public void increaseNbAgentsAtK(int k, int value){
        this.nb_agents[k] += value;
    }
    
    public boolean isNbAgentsAtKsupThen(int k, int value){
        return this.nb_agents[k] > value;
    }
    
    public int getNb_agentsAtK(int k){
        return this.nb_agents[k];
    }
    
    public void setNb_agentsAtK(int k, int value){
        this.nb_agents[k] = value;
    }
    
    private void stateInitializer(Prm param){
//        int[] a = new int[NB_CLASSES];
        for (int i = 0; i < param.NB_CLASSES; i++) {
            //initialisation avec le min de chaque classe
            this.state[i] = i * (param.MLEVEL+1); //i représente la classe
        }
//        return this.state;
    }
    
    public boolean isMaxStateAtK(int MLEVEL, int k){
        return this.state[k] == maxLevel(MLEVEL, k);
    }
    
    public boolean isMinStateAtK(int MLEVEL, int k){
        return this.state[k] == minLevel(MLEVEL, k);
    }
    
    public boolean isSupThenMinAtK(int MLEVEL, int k){
        return this.state[k] > minLevel(MLEVEL, k);
    }
    
    public void decreaseStateatK(int k, int value){
        this.state[k] -= value;
    }
    
    public boolean nbAgentsAtKisSupThen(int k, int value){
        return this.nb_agents[k] > value;
    }
    
    public boolean nbAgentsAtKisEqualTo(int k, int value){
        return this.nb_agents[k] == value;
    }
    
    public void agentInitializer(Prm param){
        
        this.nb_agents = new int[param.NB_CLASSES];
        
        for (int i = 0; i < param.NB_CLASSES; i++) {
            //initialisation avec le min de chaque classe
            this.nb_agents[i] = 0; //i représente la classe
        }
    }
    
    @Override
    public CellInfluenceClass nextState(Prm param, Neighbours nghbrs){
        
        return null;
    }
//    

    public boolean expandWave(Prm param, Neighbours nghbrs, int k) {
        
        if (this.isMinStateAtK(param.MLEVEL, k) //test min
                && (nghbrs.isSupThen(0)
                    || (this.nbAgentsAtKisSupThen(k, 0) //Comme ça il peuvent tous produire une vague
                        && bernoulli(param.LAMBDA) == 1))){
            this.setMaxAtK(param.MLEVEL, k); //put max
            //coloring
            this.setCouleur(new Color(param.getCOLOR_at(k).getRed(),
            param.getCOLOR_at(k).getGreen(),
            param.getCOLOR_at(k).getBlue(),
            100));
            param.VAGUE = true;
        }
        else if (this.isSupThenMinAtK(param.MLEVEL, k)){

            this.decreaseStateatK(k, 1);
            if(this.isSupThenMinAtK(param.MLEVEL, k)) {
                this.setCouleur(new Color(param.getCOLOR_at(k).getRed(),
                param.getCOLOR_at(k).getGreen(),
                param.getCOLOR_at(k).getBlue(),
                100));
//                    255/((new_cell.state[k]%NB_CLASSES)+1));
                param.VAGUE = true;
            }
        }
        else{
            this.setMinAtK(param.MLEVEL, k);
            if(!param.VAGUE) this.setCouleur(param.COLORS.COLOR_DEFAULT);
        }
        return param.VAGUE;
    }
    
//    
    /**
     *  Calculate Max level
     * @param k
     * @return
     */
    public int maxLevel(int MLEVEL, int k){
        return k * (MLEVEL+1) + MLEVEL;
    }
    
    /**
     * Caculate Min
     * @param k
     * @return
     */
    public int minLevel(int MLEVEL, int k){
        return k * (MLEVEL+1);
    }

//    @Override
//    public Cell setWall(Color co, int i, int j, boolean wall) {
//        return new CellInfluenceClass(co, i, j, wall);
//    }
    
}
