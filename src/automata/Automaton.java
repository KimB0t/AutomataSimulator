/* 
 * Copyright (C) 2019 Karim BOUTAMINE <boutaminekarim06 at gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package automata;

import misc.Neighbours;
import misc.Params.Policy;
import static misc.Params.RAND;
import misc.Params;
import cells.Cell;
import java.awt.Color;

/**
 * An automaton is a matrix of cells
 * the automata matrix (matrix of cells)
 * @author Karim
 */
public abstract class Automaton {
     
    private Params p;

//    private Cell[][] matrix;
    
    /**
     *
     */
        
    public Automaton() {
        this.p = new Params();
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
    
    /**
     *
     */
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
     * @param k
     * @return
     */
    public abstract Neighbours countNeighbours(Cell cell, int k);
    
    /**
     *
     * @return
     */
    public long sleepTime(){
        return (long) (p.SPEED/p.COEF_SPEED);
    }
    
    /**
     *
     * @return
     */
    public String stringNBGeneration(){
        return String.valueOf(p.NB_GENERATIONS);
    }
    
    /**
     *
     */
    public void reInitNBGeneration(){
        p.NB_GENERATIONS = 0;
    }
    
    /**
     *
     * @param val
     */
    public void setLAMBDA(float val){
        p.LAMBDA = val;
    }
    
    /**
     *
     * @param val
     */
    public void setDENSITY(float val){
        p.DENSITY = val;
    }
    
    /**
     *
     * @param val
     */
    public void setMLEVEL(int val){
        p.MLEVEL = val;
    }
    
    /**
     *
     * @param val
     */
    public void setOBSTACLES_LENGTH(int val){
        p.OBSTACLES_LENGTH = val;
    }
    
    /**
     *
     * @param val
     */
    public void setOBSTACLES_NBR(int val){
        p.OBSTACLES_NBR = val;
    }
    
    /**
     *
     * @param val
     */
    public void setPA(double val){
        p.PA = val;
    }
    
    /**
     *
     * @param shape
     * @return
     */
    public boolean shapeIs(String shape){
        return p.SHAPE.equals(shape);
    }
    
    /**
     *
     * @return
     */
    public int getMATRIX_LENGTH(){
        return p.MATRIX_LENGTH;
    }
    
    /**
     *
     * @return
     */
    public int getPANEL_LENGTH(){
        return p.PANEL_LENGTH;
    }
    
    /**
     *
     * @return
     */
    public int getCELL_DIM(){
        return p.CELL_DIM;
    }
    
    /**
     *
     * @return
     */
    public boolean isGRID(){
        return p.GRID;
    }
    
    /**
     *
     * @return
     */
    public Color getCOLOR_AGENT1(){
        return p.COLORS.COLOR_AGENT1;
    }
    
    /**
     *
     * @return
     */
    public Color getCOLOR_DEFAULT(){
        return p.COLORS.COLOR_DEFAULT;
    }
    
    /**
     *
     * @return
     */
    public Color getCOLOR_AGENT2(){
        return p.COLORS.COLOR_AGENT2;
    }
    
    /**
     *
     * @return
     */
    public Color getCOLOR_OBSTACLE(){
        return p.COLORS.COLOR_OBSTACLE;
    }
    
    /**
     *
     * @return
     */
    public Color getCOLOR_EXCITED(){
        return p.COLORS.COLOR_EXCITED;
    }
    
    /**
     *
     * @return
     */
    public int getNB_CLASSES(){
        return p.NB_CLASSES;
    }
    
    /**
     *
     * @param key
     * @return
     */
    public Color getCOLOR_at(int key){
        return p.getCOLOR_at(key);
    }
    
    /**
     *
     * @param key
     * @param opacity
     * @return
     */
    public Color getCOLOR_at(int key, int opacity){
        return p.getCOLOR_at(key, opacity);
    }
    
    /**
     *
     * @return
     */
    public double getDENSITY(){
        return p.DENSITY;
    }
    
    /**
     *
     * @param val
     */
    public void increaseNBGeneration(int val){
        p.NB_GENERATIONS += val;
    }
    
    /**
     *
     * @return
     */
    public int getMLEVEL(){
        return p.MLEVEL;
    }
    
    /**
     *
     * @return
     */
    public double getPA(){
        return p.PA;
    }
    
    /**
     *
     * @return
     */
    public Policy getPOLICY(){
        return p.POLICY;
    }
    
    /**
     *
     * @return
     */
    public Params getP(){
        return this.p;
    }
    
    /**
     *
     * @param bol
     */
    public void setVAGUE(boolean bol){
        p.VAGUE = bol;
    }
    
    /**
     *
     * @param val
     */
    public void setCOEF_SPEED(float val){
        p.COEF_SPEED = val;
    }
    
    /**
     *
     * @param shape
     */
    public void setSHAPE(String shape){
        p.SHAPE = shape;
    }
    
    /**
     *
     * @param co
     */
    public void setCOLOR_AGENT1(Color co){
        p.COLORS.COLOR_AGENT1 = co;
    }
    
    /**
     *
     * @param grid
     */
    public void setGRID(boolean grid){
        p.GRID = grid;
    }
    
    /**
     *
     * @param pol
     */
    public void setPOLICY(Policy pol){
        p.POLICY = pol;
    }
    
    /**
     *
     * @param hdo
     */
    public void setHAND_DRAW_OBSTACLES(boolean hdo){
        p.HANDRAW_OBSTACLES = hdo;
    }
    
    /**
     *
     * @param uncover
     */
    public void setUNCOVER(boolean uncover){
        p.UNCOVER = uncover;
    }
    
    /**
     *
     * @param rep
     */
    public void setREPULSION(double rep){
        p.REPULSION = rep;
    }
    
    /**
     *
     * @return
     */
    public double getREPULSION(){
        return p.REPULSION;
    }
    
    /**
     *
     * @param bo
     */
    public void setFIRST_TO_FIRE(boolean bo){
        p.FIRST_TO_FIRE = bo;
    }
    
    /**
     *
     * @return
     */
    public boolean getFIRST_TO_FIRE(){
        return p.FIRST_TO_FIRE;
    }
    
    /**
     *
     * @return
     */
    public String getBOUNDARIES(){
        return p.BOUNDARIES;
    }
    
    /**
     *
     * @param str
     */
    public void setBOUNDARIES(String str){
        p.BOUNDARIES = str;
    }
    
    /**
     *
     * @param str
     * @return
     */
    public boolean isBOUNDARIESequalTo(String str){
        return p.BOUNDARIES.equals(str);
    }
    
    /**
     *
     * @return
     */
    public int getWALL(){
        return p.WALL;
    }
    
    /**
     *
     * @param val
     */
    public void setWALL(int val){
        p.WALL = val;
    }
    
    /**
     *
     * @return
     */
    public int getAbsoluteLength(){
        return p.MATRIX_LENGTH;
    }
    
    /**
     *
     * @return
     */
    public int getRelativeLength(){
        return p.MATRIX_LENGTH - p.WALL;
    }
    
    /**
     *
     * @return
     */
    public int getAbsoluteStarter(){
        return 0;
    }
    
    /**
     *
     * @return
     */
    public int getRelativeStarter(){
        return p.WALL;
    }
    
    /**
     *
     * @return
     */
    public int getRANDcoordinate(){
        return RAND.nextInt(p.MATRIX_LENGTH-2 * p.WALL) + p.WALL;
    }
    
    /**
     *
     * @return
     */
    public boolean getOBSTACLES(){
        return p.OBSTACLES;
    }
    
    /**
     *
     * @param bol
     */
    public void setOBSTACLES(boolean bol){
        p.OBSTACLES = bol;
    }
    
    /**
     *
     * @return
     */
    public boolean getHAND_DRAW_OBSTACLES(){
        return p.HANDRAW_OBSTACLES;
    }
    
    /**
     *
     * @return
     */
    public boolean getUNCOVER(){
        return p.UNCOVER;
    }
    
    /**
     * 
     * @param i
     * @param j
     * @return
     */
    public abstract Cell makeWall(int i, int j);
    
    /**
     * Creates a wall cell at position (i, j).
     * @param i line coordinate
     * @param j column coordinate
     */
    public abstract void makeWallAt(int i, int j);
    
    /**
     * Creates borders (of wall cells) to the matrix.
     */
    public abstract void makeBoundaries();
    
    /**
     * Delete agent in cell (i, j). 
     * @param i
     * @param j
     */
    public abstract void deleteAgent(int i, int j);
}
