/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import _diverse.Neighbours;
import cells.Cell;
import static _diverse.Params.bernoulli;
import cells.CellInfluenceClass;
import static _diverse.Params.RAND;
import _diverse.Prm;

/**
 *
 * @author Karim
 */
public class AgentInfluenceClass extends Agent{
    
    // The class of this Agent
    private int classe;

    /**
     *
     * @param cls
     * @param i
     * @param p
     * @param j
     * @param id
     */
    
    public AgentInfluenceClass(int i, int j, int p, int id, int cls) {
        super(i, j, p, id);
        this.classe = cls;
    }
    
    /**
     *
     * @param classe
     */
    public void setClasse(int classe) {
        this.classe = classe;
    }

    /**
     *
     * @return
     */
    public int getClasse() {
        return classe;
    }

    @Override
    public AgentInfluenceClass move(Prm param, Neighbours nghbrs, Cell new_cell) {
        
        Cell delta = null;
        AgentInfluenceClass ag = null;
        
        //Déplacement des agents
        if(bernoulli(param.PA) == 1){
            if (!nghbrs.isEmptyFreeCells()) {
                //choose a random neighbour
                int randomCell = RAND.nextInt(nghbrs.getSizeFreeCells());
                delta = nghbrs.getElementFreeCells(randomCell);
            }
        }
        //if state of current cell == min && there are accecible neighbours to move to
        else if(((CellInfluenceClass)new_cell).isMinStateAtK(param.MLEVEL, this.classe)) {
            if (!nghbrs.isEmptyFreeExcitedCells()) {
                //choose a random neighbour
                int randomCell = RAND.nextInt(nghbrs.getSizeFreeExcitedCells());
                delta = nghbrs.getElementFreeExcitedCells(randomCell);
            }
        }
        //return new agent with new location
        if(delta != null) {
            // Calculate new position
            int position = calculatePosition(param, delta.getI(), delta.getJ());
            
            ag = new AgentInfluenceClass(delta.getI(), delta.getJ(), 
                    position, this.getId(), this.classe);
        }
        return ag;
    }

    public int calculatePosition(Prm param, int i, int j){
        return i * param.MATRIX_LENGTH + j;
    }
    
    @Override
    public Agent move(Prm param, int s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
