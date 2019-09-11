///* 
// * Copyright (C) 2019 Karim BOUTAMINE <boutaminekarim06 at gmail.com>
// *
// * This program is free software; you can redistribute it and/or
// * modify it under the terms of the GNU General Public License
// * as published by the Free Software Foundation; either version 2
// * of the License, or (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program; if not, write to the Free Software
// * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
// */
//package misc;
//
//import agents.Agent;
//import cells.Cell;
//import java.util.ArrayList;
//import java.util.HashMap;
//
///**
// *
// * @author Karim
// */
//public class Neighbours {
//    
//
//    //List of neighbouring cells.
//    protected Cell[] neighbours;
//    
//    //Number of neighbouring cells.
//    private int NB_NGHBRS;
//    
//    /**
//     *  nb of neighbours of cell c 
//     */
//    protected int excited_free_cells_count;
//    
//    /**
//     *  list of excited neighbouring free cells of cell c 
//     *  to where the agent of cell c can possibly move  
//     */
//    private ArrayList<Cell> excited_free_cells;
//    
//    /**
//     *  list of neighbouring free cells of cell c 
//     *  to where the agent of cell c can possibly move  
//     */
//    private ArrayList<Cell> free_cells;
//    
//    
//    private ArrayList<Cell> repulsive_free_cells;
//    
//    private HashMap<Integer, ArrayList<Agent>> concurent_cells;
//
//    
//    public Neighbours(){
//    }
//    
//    /**
//     * 
//     * @param c
//     * @param efs
//     * @param fs
//     */
//    public Neighbours(int c, ArrayList<Cell> efs, ArrayList<Cell> fs) {
//        this.excited_free_cells_count = c;
//        this.excited_free_cells = efs;
//        this.free_cells = fs;
//        this.NB_NGHBRS = 8;
//        this.neighbours = new Cell[this.NB_NGHBRS];
//    }
//    
//    public Neighbours(int c){
//        this.excited_free_cells_count = c;
//        this.NB_NGHBRS = 8;
//        this.neighbours = new Cell[this.NB_NGHBRS];
//    }
//    
//    public Neighbours(HashMap<Integer, ArrayList<Agent>> cc){
//        this.concurent_cells = cc;
//    }
//
//    public void setExcited_free_cells_count(int excited_free_cells_count) {
//        this.excited_free_cells_count = excited_free_cells_count;
//    }
//
//    public void setExcited_free_cells(ArrayList<Cell> excited_free_cells) {
//        this.excited_free_cells = excited_free_cells;
//    }
//
//    public void setFree_cells(ArrayList<Cell> free_cells) {
//        this.free_cells = free_cells;
//    }
//
//    public void setConcurent_cells(HashMap<Integer, ArrayList<Agent>> concurent_cells) {
//        this.concurent_cells = concurent_cells;
//    }
//
////    public int getExcited_free_cells_count() {
////        return excited_free_cells_count;
////    }
//
//    public ArrayList<Cell> getExcited_free_cells() {
//        return excited_free_cells;
//    }
//
//    public ArrayList<Cell> getFree_cells() {
//        return free_cells;
//    }
//
//    public HashMap<Integer, ArrayList<Agent>> getConcurent_cells() {
//        return concurent_cells;
//    }
//    
//    public boolean isSupThen(int value){
//        return this.excited_free_cells_count > value;
//    }
//    
//    public boolean isInfThen(int value){
//        return this.excited_free_cells_count < value;
//    }
//    
//    public boolean isEqualTo(int value){
//        return this.excited_free_cells_count == value;
//    }
//    
//    public boolean isEmptyFreeCells(){
//        return this.free_cells.isEmpty();
//    }
//    
//    public Cell getElementFreeCells(int i){
//        return this.free_cells.get(i);
//    }
//    
//    public int getSizeFreeCells(){
//        return this.free_cells.size();
//    }
//    
//    public boolean isEmptyFreeExcitedCells(){
//        return this.excited_free_cells.isEmpty();
//    }
//    
//    public Cell getElementFreeExcitedCells(int i){
//        return this.excited_free_cells.get(i);
//    }
//    
//    public int getSizeFreeExcitedCells(){
//        return this.excited_free_cells.size();
//    }
//    
//    public void increaseEFCount(int value){
//        this.excited_free_cells_count += value;
//    }
//    
//    public void addFreeExcitedCells(Cell cell){
//        this.excited_free_cells.add(cell);
//    }
//    
//    public void addFreeCells(Cell cell){
//        this.free_cells.add(cell);
//    }
//    
//    public boolean cCContainsKey(int key){
//        return this.concurent_cells.containsKey(key);
//    }
//    
//    public void cCPut(int key, ArrayList<Agent> agentArray){
//        this.concurent_cells.put(key, agentArray);
//    }
//    
//    public ArrayList<Agent> cCGet(int key){
//        return this.concurent_cells.get(key);
//    }
//
//    public void setRepulsive_free_cells(ArrayList<Cell> repulsive_free_cells) {
//        this.repulsive_free_cells = repulsive_free_cells;
//    }
//
//    public ArrayList<Cell> getRepulsive_free_cells() {
//        return repulsive_free_cells;
//    }
//    
//    public void addFreeRepulsiveCells(Cell cell){
//        this.repulsive_free_cells.add(cell);
//    }
//    
//    public boolean isEmptyFreeRepulsiveCells(){
//        return this.repulsive_free_cells.isEmpty();
//    }
//    
//    public Cell getElementFreeRepulsiveCells(int i){
//        return this.repulsive_free_cells.get(i);
//    }
//    
//    public int getSizeFreeRepulsiveCells(){
//        return this.repulsive_free_cells.size();
//    }
//    
//    public int getNB_NGHBRS(){
//        return this.NB_NGHBRS;
//    }
//    
//    public void setNeighbours(Cell[] nghbrs){
//        this.neighbours = nghbrs;
//    }
//}
