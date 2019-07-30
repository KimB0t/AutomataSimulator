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
import static misc.Params.RAND;
import misc.Params;
import cells.Cell;
import java.awt.Color;

/**
 * An automaton is a matrix of cells
 * the automata matrix (matrix of cells)
 * @author Karim BOUTAMINE <boutaminekarim06 at gmail.com>
 */
public abstract class Automaton extends Params{
    
    /**
     *
     */
    public Automaton() {
        initParamsGlobal();
        printToScreen("===============INITIALIZATION========================");
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
     * The automaton next step (automaton update of all cells)
     */
    public abstract void step();
    
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
//    public abstract Neighbours countNeighbours(Cell cell, int k);
    
    /**
     * 
     * @param i
     * @param j
     * @return
     */
    public abstract Cell getAWallCell(int i, int j);
    
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
    
    /**
     * Create a random Configuration
     */
    public void randomConfig(){
        this.init_matrix();
        int rn_x, rn_y;
        int nbr_cell = (int)((MATRIX_LENGTH) * (MATRIX_LENGTH) * DENSITY / 100);
        
        System.out.println("nbr_cell (nb agents) = "+nbr_cell);
        reInitNBGeneration();
        
        for(int i=0; i<nbr_cell; i++){

            // Calcul des coordonnées
            rn_x = getRANDcoordinate();
            rn_y = getRANDcoordinate();
            
            // Créer l'agent
            this.setAgent(rn_x, rn_y, 1, getCOLOR_AGENT1(), false);
        }
    }
    
    /**
     * Create a random Configuration
     * @param i
     * @param j
     * @param nbNghbrs
     * @return 
     */
    public abstract Cell[] getListOfNeighbours(int i, int j, int nbNghbrs);
    
    /**
     *
     */
    public void clear(){
        init_matrix();
        reInitNBGeneration();
    }
    
    /**
     *
     * @return
     */
    public long sleepTime(){
        return (long) (SPEED/COEF_SPEED);
    }
    
    /**
     *
     * @return
     */
    public String stringNBGeneration(){
        return String.valueOf(NB_GENERATIONS);
    }
    
    /**
     *
     */
    public void reInitNBGeneration(){
        NB_GENERATIONS = 0;
    }
    
    /**
     *
     * @param shape
     * @return
     */
    public boolean shapeIs(String shape){
        return SHAPE.equals(shape);
    }
    
    /**
     *
     * @return
     */
    public Color getCOLOR_AGENT1(){
        return COLORS.COLOR_AGENT1;
    }
    
    /**
     *
     * @return
     */
    public Color getCOLOR_DEFAULT(){
        return COLORS.COLOR_DEFAULT;
    }
    
    /**
     *
     * @return
     */
    public Color getCOLOR_AGENT2(){
        return COLORS.COLOR_AGENT2;
    }
    
    /**
     *
     * @return
     */
    public Color getCOLOR_OBSTACLE(){
        return COLORS.COLOR_OBSTACLE;
    }
    
    /**
     *
     * @return
     */
    public Color getCOLOR_EXCITED(){
        return COLORS.COLOR_EXCITED;
    }
    
    /**
     *
     * @param val
     */
    public void increaseNBGeneration(int val){
        NB_GENERATIONS += val;
    }
    
    /**
     *
     * @param co
     */
    public void setCOLOR_AGENT1(Color co){
        COLORS.COLOR_AGENT1 = co;
    }
    
    /**
     *
     * @param str
     * @return
     */
    public boolean isBOUNDARIESequalTo(String str){
        return BOUNDARIES.equals(str);
    }
    
    /**
     *
     * @return
     */
    public int getAbsoluteLength(){
        return MATRIX_LENGTH;
    }
    
    /**
     *
     * @return
     */
    public int getRelativeLength(){
        return MATRIX_LENGTH - BOUNDARY_LENGTH;
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
        return BOUNDARY_LENGTH;
    }
    
    /**
     *
     * @return
     */
    public int getRANDcoordinate(){
        return RAND.nextInt(MATRIX_LENGTH-2 * BOUNDARY_LENGTH) + BOUNDARY_LENGTH;
    }

    /**
     *
     * @param text
     */
    public void setLAMBDA(String text) {
        this.LAMBDA = Float.parseFloat(text);
    }

    /**
     *
     * @param text
     */
    public void setDENSITY(String text) {
        this.DENSITY = Float.parseFloat(text);
    }

    /**
     *
     * @param text
     */
    public void setMLEVEL(String text) {
        this.MLEVEL = Integer.parseInt(text);
    }

    /**
     *
     * @param text
     */
    public void setOBSTACLES_LENGTH(String text) {
        this.OBSTACLES_LENGTH = Integer.parseInt(text);
    }

    /**
     *
     * @param text
     */
    public void setOBSTACLES_NBR(String text) {
        this.OBSTACLES_NBR = Integer.parseInt(text);
    }

    /**
     *
     * @param text
     */
    public void setPA(String text) {
        this.PA = Float.parseFloat(text);
    }

    /**
     *
     * @param text
     */
    public void setREPULSION(String text) {
        this.REPULSION = Float.parseFloat(text);
    }

    /**
     *
     * @param substring
     */
    public void setCOEF_SPEED(String substring) {
        this.COEF_SPEED = Float.parseFloat(substring);
    }

    /**
     *
     * @param toString
     */
    public void setSHAPE(String toString) {
        this.SHAPE = toString;
    }

    /**
     *
     * @param b
     */
    public void setOBSTACLES(boolean b) {
        this.OBSTACLES = b;
    }

    /**
     *
     * @param b
     */
    public void setGRID(boolean b) {
        this.GRID = b;
    }

    /**
     *
     * @param b
     */
    public void setHANDRAW_OBSTACLES(boolean b) {
        this.HANDRAW_OBSTACLES = b;
    }

    /**
     *
     * @param policy
     */
    public void setPOLICY(Policy policy) {
        this.POLICY = policy;
    }

    /**
     *
     * @param b
     */
    public void setUNCOVER(boolean b) {
        this.UNCOVER = b;
    }
}
