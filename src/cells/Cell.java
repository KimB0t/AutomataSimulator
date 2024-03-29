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
package cells;

import agents.Agent;
import misc.Params;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static misc.Params.RAND;

/**
 * A cell is a component of an Automaton.
 * @author Karim BOUTAMINE <boutaminekarim06 at gmail.com>
 */
public abstract class Cell{
    
    //Color of the cell
    private Color couleur;
    
    //Coordinates of the cell in the matrix
    private int i;
    private int j;
    
    //If this cell is a wall then 1 else 0
    private boolean wall;
    
    /**
     *  nb of neighbours of cell c 
     */
    protected int excited_free_cells_count;
    
    //Neighbours of the cell
    protected ArrayList<Cell> nghbrs;
    
    protected Params param;
    
    /**
     * Create a cell with default settings:
     * color = Color.WHITE, 
     * i = -1, 
     * j = -1, 
     * wall = false;
     */
    public Cell(Params p) {
        this.i = -1;
        this.j = -1;
        this.wall = false;
        this.couleur = Color.WHITE;
        this.param = p;
        this.nghbrs = new ArrayList<>();
    }
    
    /**
     * Create a cell at position (i, j) with:
     * color = Color.WHITE,  
     * wall = false;
     * @param i line index
     * @param j column index
     */
    public Cell(Params p, int i, int j) {
        this.i = i;
        this.j = j;
        this.wall = false;
        this.couleur = Color.WHITE;
        this.param = p;
        this.nghbrs = new ArrayList<>();
    }
    
    /**
     * Create a cell at position with specific settings:
     * @param co color of the cell
     * @param i line index
     * @param j column index
     * @param wall 1 if wall, 0 else
     */
    public Cell(Params p, Color co, int i, int j, boolean wall) {
        this.i = i;
        this.j = j;
        this.wall = wall;
        this.couleur = co;
        this.param = p;
        this.nghbrs = new ArrayList<>();
    }
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">

    /**
     * Set the cell color cell
     * @param couleur color of the cell
     */
    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    /**
     * 
     * @param i line index
     */
    public void setI(int i) {
        this.i = i;
    }

    /**
     *
     * @param j column index
     */
    public void setJ(int j) {
        this.j = j;
    }

    /**
     * true if this cell represents a wall, false if not
     * @param wall 
     */
    public void setWall(boolean wall) {
        this.wall = wall;
    }

    /**
     *
     * @return color
     */
    public Color getCouleur() {
        return couleur;
    }

    /**
     *
     * @return i
     */
    public int getI() {
        return i;
    }

    /**
     *
     * @return j
     */
    public int getJ() {
        return j;
    }

    /**
     *
     * @return true if this cell is a wall. false otherwise.
     */
    public boolean isWall() {
        return wall;
    }

    public void setParam(Params param) {
        this.param = param;
    }
    
    //</editor-fold>
    
    /**
     * Compute the next state of this cell.
     * @param param The simulation parameters.
     */
//    public abstract void nextState();
//    public abstract Cell nextState(Params param, int k);
    
    /**
     * This function returns a copy of the cell.
     * @param param Params of the automaton simulation
     * @return A copy of this cell.
     */
    public abstract Cell getCopy();
    
//    public abstract int getState();
    
//    public abstract void countNeighbours();
    
//    public abstract void countNeighbours(int k);
    
    public ArrayList<Cell> getNeighbours() {
        return this.nghbrs;
    }
    
    public void setExcited_free_cells_count(int excited_free_cells_count) {
        this.excited_free_cells_count = excited_free_cells_count;
    }

    public boolean isSupThen(int value){
        return this.excited_free_cells_count > value;
    }
    
    public boolean isInfThen(int value){
        return this.excited_free_cells_count < value;
    }
    
    public boolean isEqualTo(int value){
        return this.excited_free_cells_count == value;
    }
    
    public void increaseEFCount(int value){
        this.excited_free_cells_count += value;
    }
    
    public void print(JTable infoTable){
        DefaultTableModel model = (DefaultTableModel) infoTable.getModel();
        model.setValueAt(this.i, 0, 1);
        model.setValueAt(this.j, 1, 1);
        String hexColor = "#"+Integer.toHexString(this.couleur.getRGB()).substring(2);
        model.setValueAt(hexColor, 2, 1);
    }
    
    public int getOppositeCell(int cell, int wave){
        return (cell - ((cell - wave) * -1) + param.MATRIX_LENGTH) % param.MATRIX_LENGTH;
    }
    
    public String getCellType(){
        
        if(this.isWall()) return "Wall";
        else if(this.isAgent()) return "Agent";
        else if(this.isWave()) return "Wave";
        else return "Empty";
    }

    public Cell getOpposantNghbr(Cell nghbr) {
        
        int ii = getOppositeCell(this.getI(), nghbr.getI());
        int jj = getOppositeCell(this.getJ(), nghbr.getJ());
        
        for (Cell n : nghbrs) {
            if (n.getI() == ii && n.getJ() == jj) {
                return (Cell) n;
            }
        }
        return null;
    }
    
    public abstract void addComingAgent(Agent agent);
    
    public abstract void addFiringAgent(Agent agent);
    
    public Agent chooseRandAgent(ArrayList<Agent> agents) {
        int size = agents.size();
        int key = RAND.nextInt(size);
        return agents.get(key);
    }
    
    public Cell chooseRandCell(ArrayList<Cell> cells) {
        int size = cells.size();
        int key = RAND.nextInt(size);
        return cells.get(key);
    }

    public abstract boolean isAgent();

    public abstract boolean isWave();
    
    public void addNeighbour(Cell cell) {
        this.nghbrs.add(cell);
    }
    
    public abstract void colorier();
}
