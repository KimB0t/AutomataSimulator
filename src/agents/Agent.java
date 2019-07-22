/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import _diverse.Neighbours;
import _diverse.Prm;
import cells.Cell;
import cells.CellInfluenceClass;

/**
 *
 * @author Karim
 */
public abstract class Agent{
    
    // Coordinates of the cell in which the agent is placed on the matrix.
    private int i = 0;
    private int j = 0;
    
    // Position of an Agent. this is another way to locate agent on the matrix
    // Positions goes from 0 to matrix lenght²
    private int position = 0;
    
    // Unique ID for this Agent.
    private int id = 0;
    
    /**
     *
     * @param i
     * @param j
     * @param p
     * @param id
     */
    public Agent(int i, int j, int p, int id) {
        this.i = i;
        this.j = j;
        this.position = p;
        this.id = id;
    }

    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">

    /**
     *
     * @param i
     */
    public void setI(int i) {
        this.i = i;
    }

    /**
     *
     * @param j
     */
    public void setJ(int j) {
        this.j = j;
    }

    /**
     *
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getI() {
        return i;
    }

    /**
     *
     * @return
     */
    public int getJ() {
        return j;
    }

    /**
     *
     * @return
     */
    public int getPosition() {
        return position;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

//    public int getMATRIX_LENGTH() {
//        return MATRIX_LENGTH;
//    }
    
    //</editor-fold>
    
    /**
     * When an agent moves on the matrix.
     * @param s
     * @return
     */
    public abstract Agent move(Prm param, int s);

    /**
     * Another moving fct for AgentInfluenceClass
     * @param nghbrs
     * @param new_cell
     * @return
     */
    public abstract Agent move(Prm param, Neighbours nghbrs, Cell new_cell);
}
