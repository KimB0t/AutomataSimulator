/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cells;

import _diverse.AllColors;
import _diverse.Neighbours;
import _diverse.Prm;
import java.awt.Color;

/**
 * A cell is a component of an Automaton. A cell in the matrix
 * @author Karim
 */
public abstract class Cell {
    
    //NB agents on this cell
    private int nbAgents;
    
    //Color of the cell
    private Color couleur;
    
    //Coordinates of the cell
    private int i;
    private int j;
    
    //If this cell is a wall or not
    private boolean wall;

    /**
     * 
     * @param nbA
     * @param c
     * @param i
     * @param j
     * @param w
     */
    public Cell(int nbA, Color c, int i, int j, boolean w) {
        this.couleur = c;
        this.nbAgents = nbA;
        this.i = i;
        this.j = j;
        this.wall = w;
    }

    /**
     * 
     */
    public Cell() {
        this.couleur = Color.WHITE;
        this.nbAgents = 0;
        this.i = -1;
        this.j = -1;
        this.wall = false;
    }
    
    /**
     * 
     * @param i
     * @param j
     */
    public Cell(int i, int j) {
        this.couleur = Color.WHITE;
        this.nbAgents = 0;
        this.i = i;
        this.j = j;
        this.wall = false;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">

    /**
     *
     * @param nbAgents
     */
        public void setNbAgents(int nbAgents) {
        this.nbAgents = nbAgents;
    }

    /**
     *
     * @param couleur
     */
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

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
     * @param wall
     */
    public void setWall(boolean wall) {
        this.wall = wall;
    }

    /**
     *
     * @return
     */
    public int getNbAgents() {
        return nbAgents;
    }

    /**
     *
     * @return
     */
    public Color getCouleur() {
        return couleur;
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
    public boolean isWall() {
        return wall;
    }
    //</editor-fold>
    
    /**
     * 
     * @param num
     */
    public void decreaseNbAgents(int num){
        this.nbAgents -= num;
    }
    
    /**
     * 
     * @param num
     */
    public void increaseNbAgents(int num){
        this.nbAgents += num;
    }
    
    /**
     * Caculate the next state of this cell. Needs info about the neighbourhood (Free cells, ...).
     * @param nghbrs
     * @return
     */
    public abstract Cell nextState(Prm param, Neighbours nghbrs);
    
//    public abstract CellDiffusionGathering nextState(Prm param, Neighbours nghbrs, int PA, int MLEVEL);
}
