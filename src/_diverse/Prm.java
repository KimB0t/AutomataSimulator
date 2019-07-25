/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _diverse;

import static _diverse.Params.MAIN_BOUNDARIES;
import static _diverse.Params.MAIN_CELL_DIM;
import static _diverse.Params.MAIN_COEF_SPEED;
import static _diverse.Params.MAIN_COLOR_AGENT1;
import static _diverse.Params.MAIN_DENSITY;
import static _diverse.Params.MAIN_GRID;
import static _diverse.Params.MAIN_LAMBDA;
import static _diverse.Params.MAIN_MLEVEL;
import static _diverse.Params.MAIN_NB_CLASSES;
import static _diverse.Params.MAIN_OBSTACLES_LENGTH;
import static _diverse.Params.MAIN_OBSTACLES_NBR;
import static _diverse.Params.MAIN_PA;
import static _diverse.Params.MAIN_PANEL_LENGTH;
import static _diverse.Params.MAIN_RAND_COLORS;
import static _diverse.Params.MAIN_REPULSION;
import static _diverse.Params.MAIN_SHAPE;
import static _diverse.Params.MAIN_WALL;
//import static _diverse.Params.MAIN_WALL;
import _diverse.Params.Policy;
import java.awt.Color;

/**
 *
 * @author Karim
 */
public class Prm {
    
    /**
     * A cell dimention in pixels
     */
    public int CELL_DIM;

    /**
     * The number of steps
     */
    public int SPEED;

    /**
     * The acceleration coefficient
     */
    public float COEF_SPEED;

    /**
     * Shape of cells (agents) in {oval, square}
     */
    public String SHAPE;
    
    /**
     * The main panel length (jPanel_screen) (Actually : 540px)
     */
    public int PANEL_LENGTH;
    
    /**
     * 1: the cell is a wall
     * 0: othewise
     */
    public int WALL;
        
    /**
     * The matrix length = PANEL_LENGTH / CELL_DIM
     */
    public int MATRIX_LENGTH;
    
    /**
     * LAMBDA
     */
    public double LAMBDA;
    
    /**
     * Density of agents in the grid
     */
    public double DENSITY;
    
    /**
     * MLEVEL
     */
    public int MLEVEL;
    
    /**
     * Free: if there are boundaries
     * Toric: othewise
     */
    public String BOUNDARIES;
    
    /**
     * true: if there are obstacles
     * false: othewise
     */
    public boolean OBSTACLES;
    
    /**
     * Length of an obstacle
     */
    public int OBSTACLES_LENGTH;
    
    /**
     * nb of obstacles
     */
    public int OBSTACLES_NBR;
    
    /**
     * in {Horizontal, Vertical}
     */
    public String OBSTACLES_SHAPE;
    
    /**
     *  nb of classes
     */
    public int NB_CLASSES;
    
    /**
     *  The current variante in (enum.Variante)
     */
    public Policy POLICY;
    
    /**
     * true: display a grid in background
     * false: hide
     */
    public boolean GRID;
    
    /**
     * true: when drawing an obstacle
     * false: otherwise
     */
    public boolean HAND_DRAW_OBSTACLES;
    
    /**
     *  nbr of generation
     */
    public int NB_GENERATIONS;
    
    /**
     *  to print content of a cell
     */
    public boolean UNCOVER;
    
    /**
     *  to print content of a cell
     */
    public double PA;
    
    /**
     *  To draw Vagues
     */
    public boolean VAGUE;
    
    public AllColors COLORS;
    
    public double REPULSION;
    
    public boolean FIRST_TO_FIRE;
    
    /**
     *  to print content of a cell
     */
    public boolean RAND_COLORS;
    
    public void initParamsGlobal(){
        
        this.CELL_DIM = MAIN_CELL_DIM;
        this.SHAPE = MAIN_SHAPE;
        this.WALL = MAIN_WALL;
        this.BOUNDARIES = MAIN_BOUNDARIES;
        this.NB_CLASSES = MAIN_NB_CLASSES;
        this.GRID = MAIN_GRID;
        this.OBSTACLES = false;
        this.OBSTACLES_SHAPE = "Both";
        this.HAND_DRAW_OBSTACLES = false;
        this.NB_GENERATIONS = 0;
        this.POLICY = Policy.CYCLIC;
        this.UNCOVER = false;
        this.VAGUE = false;
        this.SPEED = 100;
        
        this.COEF_SPEED = MAIN_COEF_SPEED;
        
        this.PANEL_LENGTH = MAIN_PANEL_LENGTH;
        this.MATRIX_LENGTH = (MAIN_PANEL_LENGTH/CELL_DIM) + 2*WALL;
        
        this.LAMBDA = MAIN_LAMBDA;
        this.DENSITY = MAIN_DENSITY;
        this.MLEVEL = MAIN_MLEVEL;
        this.OBSTACLES_LENGTH = MAIN_OBSTACLES_LENGTH;
        this.OBSTACLES_NBR = MAIN_OBSTACLES_NBR;
        this.PA = MAIN_PA;
        
        this.COLORS = new AllColors(MAIN_COLOR_AGENT1, Color.black, Color.red, Color.blue, Color.white);
        this.COLORS.initColorTable(this.NB_CLASSES, this.RAND_COLORS);
        
        this.REPULSION = MAIN_REPULSION;
        this.FIRST_TO_FIRE = true;
        
        this.RAND_COLORS = MAIN_RAND_COLORS;
    }
    
    public Color getCOLOR_at(int key){
        return COLORS.getColorAt(key);
    }
    
    public void printToScreen(String msg){
        System.out.println(msg);
        System.out.println("CELL_DIM : " + this.CELL_DIM);
        System.out.println("MATRIX_LENGTH : " + this.MATRIX_LENGTH);
    }
    
    public Color getCOLOR_at(int k, int opacity){
        return new Color(
                COLORS.getRed_at(k),
                COLORS.getGreen_at(k),
                COLORS.getBlue_at(k),
                opacity);
    }
}
