/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automata;

import _diverse.Neighbours;
import _diverse.Params.Policy;
import static _diverse.Params.RAND;
import _diverse.Prm;
import cells.Cell;
import java.awt.Color;

/**
 * An automaton is a matrix of cells
 * the automata matrix (matrix of cells)
 * @author Karim
 */
public abstract class Automaton {
     
    private Prm p;

//    private Cell[][] matrix;
    
    public Automaton() {
        this.p = new Prm();
        p.initParamsGlobal();
        p.printToScreen("===============INITIALIZATION========================");
    }
    
    /**
     * We use this function to initiate a cell with an agent 
     * @param i
     * @param j
     * @param nbAgents
     * @param cellColor
     * @param wall
     */
    public abstract void setAgent(int i, int j, int nbAgents, Color cellColor, boolean wall);
    
    /**
     * returns the color of this cell(i, j)
     * @param i
     * @param j
     * @return
     */
    public abstract Color getCellColor(int i, int j);
    
    /**
     * Initialize a matrix
     */
    public abstract void init_matrix();
    
    /**
     * Create a random Configuration
     */
    public abstract void randomConfig();
    
    /**
     * The automaton next step (automaton update of all cells)
     */
    public abstract void step();
    
    public void clear(){
        init_matrix();
        reInitNBGeneration();
    }
    
    /**
     * Execute operations on neighbourhood of cell
     * In general :
     *  - Make a list of Free Cells
     *  - Make a list of Free Excited Cells
     *  - Count the Free Excited Cells
     *  - ...
     * @param cell
     * @return
     */
    public abstract Neighbours countNeighbours(Cell cell, int k);
    
    public long sleepTime(){
        return (long) (p.SPEED/p.COEF_SPEED);
    }
    
    public String stringNBGeneration(){
        return String.valueOf(p.NB_GENERATIONS);
    }
    
    public void reInitNBGeneration(){
        p.NB_GENERATIONS = 0;
    }
    
    public void setLAMBDA(float val){
        p.LAMBDA = val;
    }
    
    public void setDENSITY(float val){
        p.DENSITY = val;
    }
    
    public void setMLEVEL(int val){
        p.MLEVEL = val;
    }
    
    public void setOBSTACLES_LENGTH(int val){
        p.OBSTACLES_LENGTH = val;
    }
    
    public void setOBSTACLES_NBR(int val){
        p.OBSTACLES_NBR = val;
    }
    
    public void setPA(double val){
        p.PA = val;
    }
    
    public boolean shapeIs(String shape){
        return p.SHAPE.equals(shape);
    }
    
    public int getMATRIX_LENGTH(){
        return p.MATRIX_LENGTH;
    }
    
    public int getPANEL_LENGTH(){
        return p.PANEL_LENGTH;
    }
    
    public int getCELL_DIM(){
        return p.CELL_DIM;
    }
    
    public boolean isGRID(){
        return p.GRID;
    }
    
    public Color getCOLOR_AGENT1(){
        return p.COLORS.COLOR_AGENT1;
    }
    
    public Color getCOLOR_DEFAULT(){
        return p.COLORS.COLOR_DEFAULT;
    }
    
    public Color getCOLOR_AGENT2(){
        return p.COLORS.COLOR_AGENT2;
    }
    
    public Color getCOLOR_OBSTACLE(){
        return p.COLORS.COLOR_OBSTACLE;
    }
    
    public Color getCOLOR_EXCITED(){
        return p.COLORS.COLOR_EXCITED;
    }
    
    public int getNB_CLASSES(){
        return p.NB_CLASSES;
    }
    
    public Color getCOLOR_at(int key){
        return p.getCOLOR_at(key);
    }
    
    public Color getCOLOR_at(int key, int opacity){
        return p.getCOLOR_at(key, opacity);
    }
    
    public double getDENSITY(){
        return p.DENSITY;
    }
    
    public void increaseNBGeneration(int val){
        p.NB_GENERATIONS += val;
    }
    
    public int getMLEVEL(){
        return p.MLEVEL;
    }
    
    public double getPA(){
        return p.PA;
    }
    
    public Policy getPOLICY(){
        return p.POLICY;
    }
    
    public Prm getP(){
        return this.p;
    }
    
    public void setVAGUE(boolean bol){
        p.VAGUE = bol;
    }
    
    public void setCOEF_SPEED(float val){
        p.COEF_SPEED = val;
    }
    
    public void setSHAPE(String shape){
        p.SHAPE = shape;
    }
    
    public void setCOLOR_AGENT1(Color co){
        p.COLORS.COLOR_AGENT1 = co;
    }
    
    public void setGRID(boolean grid){
        p.GRID = grid;
    }
    
    public void setPOLICY(Policy pol){
        p.POLICY = pol;
    }
    
    public void setHAND_DRAW_OBSTACLES(boolean hdo){
        p.HAND_DRAW_OBSTACLES = hdo;
    }
    
    public void setUNCOVER(boolean uncover){
        p.UNCOVER = uncover;
    }
    
    public void setREPULSION(double rep){
        p.REPULSION = rep;
    }
    
    public double getREPULSION(){
        return p.REPULSION;
    }
    
    public void setFIRST_TO_FIRE(boolean bo){
        p.FIRST_TO_FIRE = bo;
    }
    
    public boolean getFIRST_TO_FIRE(){
        return p.FIRST_TO_FIRE;
    }
    
    public String getBOUNDARIES(){
        return p.BOUNDARIES;
    }
    
    public void setBOUNDARIES(String str){
        p.BOUNDARIES = str;
    }
    
    public boolean isBOUNDARIESequalTo(String str){
        return p.BOUNDARIES.equals(str);
    }
    
    public int getWALL(){
        return p.WALL;
    }
    
    public void setWALL(int val){
        p.WALL = val;
    }
    
    public int getAbsoluteLength(){
        return p.MATRIX_LENGTH;
    }
    
    public int getRelativeLength(){
        return p.MATRIX_LENGTH - p.WALL;
    }
    
    public int getAbsoluteStarter(){
        return 0;
    }
    
    public int getRelativeStarter(){
        return p.WALL;
    }
    
    public int getRANDcoordinate(){
        return RAND.nextInt(p.MATRIX_LENGTH-2 * p.WALL) + p.WALL;
    }
    
    public abstract Cell makeWall(int i, int j);
    
    public abstract void makeBoundaries();
}
