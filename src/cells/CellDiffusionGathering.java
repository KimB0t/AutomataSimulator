/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cells;

import _diverse.AllColors;
import _diverse.Neighbours;
import static _diverse.Params.bernoulli;
import java.awt.Color;
import static _diverse.Params.RAND;
import _diverse.Prm;

/**
 *
 * @author Karim
 */
public class CellDiffusionGathering extends Cell{
    
    private int state;
    //Next position for the agent of this cell to move on (delta)
    public int di;
    public int dj;
    
    public CellDiffusionGathering(){
        super();
        this.state = 0;
        this.di = -1;
        this.dj = -1;
    }
    
    public CellDiffusionGathering(int nbA, Color c, int i, int j, boolean w){
        super(nbA, c, i, j, w);
        this.state = 0;
        this.di = -1;
        this.dj = -1;
    }
    
    public CellDiffusionGathering(int nbA, Color c, int i, int j, boolean w, int s, int di, int dj) {
        super(nbA, c, i, j, w);
        this.state = s;
        this.di = di;
        this.dj = dj;
    }
    
    public CellDiffusionGathering(int nbA, Color c, int i, int j, boolean w, int s){
        super(nbA, c, i, j, w);
        this.state = s;
        this.di = -1;
        this.dj = -1;
    }
    
    public CellDiffusionGathering(int i, int j){
        super(i, j);
        this.state = 0;
        this.di = -1;
        this.dj = -1;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setDi(int di) {
        this.di = di;
    }

    public void setDj(int dj) {
        this.dj = dj;
    }
    
    public int getState() {
        return state;
    }

    public int getDi() {
        return di;
    }

    public int getDj() {
        return dj;
    }
    
//    public CellRDClassification[][] additional_step(CellRDClassification[][] new_matrix_class){
//        
//        if (this.di != -1 && this.dj != -1){
//            //moving agents
//            this.decreaseNbAgents(1);
//            new_matrix_class[this.di][this.dj].increaseNbAgents(1);
//            //setting new classe
//            new_matrix_class[this.di][this.dj].setClasse(this.classe);
//            //setting color
//            new_matrix_class[this.di][this.dj].setCouleur(COLOR_TABLE.get(this.classe));
//        }
//        if (this.getNbAgents() == 1)
//            this.setCouleur(COLOR_TABLE.get(this.classe));
//        else if (this.getNbAgents() > 1)
//            this.setCouleur(COLOR_TABLE.get(this.classe));
//        else //means this class became empty, so we make it a neutral class
//            this.setClasse(-1);
//        //always un-reserve the cell
//        this.reserved = false;
//        
//        return new_matrix_class;
//    }
    
    public void increaseState(int value){
        this.state += value;
    }
    
    public void decreaseState(int value){
        this.state -= value;
    }

    @Override
    public CellDiffusionGathering nextState(Prm param, Neighbours nghbrs) {
        
//        System.out.println("NEXTSTATE=========BEFORE==============NEWCELL :");
        
        CellDiffusionGathering new_cell = new CellDiffusionGathering(this.getNbAgents(), this.getCouleur(),
                                this.getI(), this.getJ(), false, this.state);
//        new_cell.printCell();
        //Calculating new state
        if (new_cell.getState() == 0 
                && (nghbrs.isSupThen(0)
                    || (new_cell.getNbAgents() > 0 && bernoulli(param.LAMBDA) == 1))){
            new_cell.setState(param.MLEVEL);
            new_cell.setCouleur(param.COLORS.COLOR_EXCITED);
        }
        else if (new_cell.getState() > 0){
            new_cell.decreaseState(1);
            if (new_cell.getState() > 0)
                new_cell.setCouleur(new Color(255, 0, 0, 255/this.state));
            else
                new_cell.setCouleur(param.COLORS.COLOR_DEFAULT);
        }
        else{
            new_cell.setState(0);
            new_cell.setCouleur(param.COLORS.COLOR_DEFAULT);
        }
        
        //movement of agents
        if(bernoulli(param.PA) == 1){
            if (!nghbrs.isEmptyFreeCells()) {
                Cell delta = nghbrs.getElementFreeCells(
                        RAND.nextInt(nghbrs.getSizeFreeCells()));
                //save future position
                new_cell.setDi(delta.getI());
                new_cell.setDj(delta.getJ());
            }
        }
        else if (this.state == 0 && this.getNbAgents() > 0){
            if (!nghbrs.isEmptyFreeExcitedCells()) {
                Cell delta = nghbrs.getElementFreeExcitedCells(
                        RAND.nextInt(nghbrs.getSizeFreeExcitedCells()));
                //save future position
                new_cell.setDi(delta.getI());
                new_cell.setDj(delta.getJ());
            }
        }
//        System.out.println("NEXTSTATE===========AFTER============NEWCELL :");
//        new_cell.printCell();
        return new_cell;
    }
    
    public void printCell(){
        System.out.println("nbAgent: " + this.getNbAgents()+
                    " | Color: " + this.getCouleur()+
                    " | Wall: " + this.isWall()+
                    " | Di: " + this.di+
                    " | Dj: " + this.dj+
                    " | State: " + this.state);
    }
}
