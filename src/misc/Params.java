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
package misc;

import java.awt.Color;
import java.util.Random;

/**
 * This classe contains all of the parameters of the program.
 * - Params for gui intefaces
 * - Params for Automata
 * - Enums 
 * 
 * Fields starting with the prefix MAIN_* means they are static.
 * Static variables are only used from the launcher class to initiate params
 * @author Karim
 */
public class Params {
    
//<editor-fold defaultstate="collapsed" desc="Static fields">
    /**
     * 
     */
    public static int MAIN_CELL_DIM;
    
    /**
     *
     */
    public static String MAIN_SHAPE;
    
    /**
     *
     */
    public static Color MAIN_COLOR_AGENT1;
    
    /**
     *
     */
    public static int MAIN_WALL;
    
    /**
     *
     */
    public static int MAIN_MATRIX_LENGTH;
    
    /**
     *
     */
    public static String MAIN_BOUNDARIES;
    
    /**
     *
     */
    public static int MAIN_NB_CLASSES;
    
    /**
     *
     */
    public static boolean MAIN_GRID;
    
    /**
     *
     */
    public static boolean MAIN_RAND_COLORS;
    
    /**
     * Different types of automata
     */
    public static enum Variante { 

            /**
             * Indicates a life game
             */
            LIFE, 

            /**
             * Indicates a gathering amoebae with reaction-diffusion
             */
            DIFFUSION_GATHERING, 

            /**
             *  for classifying amoebae with reaction-diffusion
             */
            DIFFUSION_CLASSIFICATION, 

            /**
             *  
             */
            TURMITES, 

            /**
             *  
             */
            INFLUENCE_CLASSIFICATION,
            
            /**
             *  
             */
            INFLUENCE_REPULSION,
            
            /**
             *  if none
             */
            DEFAULT};
    
    /**
     * 
     */
    public static enum Directions { 

            /**
             *
             */
            NORTH, 

            /**
             *
             */
            EAST, 

            /**
             *
             */
            SOUTH, 

            /**
             *
             */
            WEST, 

            /**
             *
             */
            DEFAULT};
    
    /**
     * 
     */
    public static enum Policy { 

            /**
             *
             */
            SYNCHRO, 

            /**
             *
             */
            CYCLIC, 

            /**
             *
             */
            RANDOM,
            
            /**
             *
             */
            DEFAULT};
    
    /**
     *  The current variante in (enum.Variante)
     */
    public static Variante VARIANTE;
    
    /**
     *  to print content of a cell
     */
    public static Random RAND;
    
    // For SimulatorInterface

    /**
     *
     */
    public static float MAIN_COEF_SPEED;

    /**
     *
     */
    public static int MAIN_PANEL_LENGTH;

    /**
     *
     */
    public static double MAIN_LAMBDA;

    /**
     *
     */
    public static double MAIN_DENSITY;

    /**
     *
     */
    public static int MAIN_MLEVEL;

    /**
     *
     */
    public static int MAIN_OBSTACLES_LENGTH;

    /**
     *
     */
    public static int MAIN_OBSTACLES_NBR;

    /**
     *
     */
    public static double MAIN_PA;

    /**
     *
     */
    public static Policy MAIN_POLICY;

    /**
     *
     */
    public static double MAIN_REPULSION;
    
    /**
     *
     */
    public static Color[] MAIN_COLORS_ARRAY;
    
    /**
     * Function that simulates a random bernoulli trial
     * @param lambda is the probability
     * @return
     */
    public static int bernoulli(double lambda){
        
        //Round Lambda to two dicimals before coma
        lambda = (double)Math.round(lambda * 100d) / 100d;
        
        if(lambda>=1 && lambda<=100){
            if (RAND.nextInt(100) < lambda){
                return 1;
            }
            return 0;
        }
        else if(lambda<1 && lambda>=0.1){
            if (RAND.nextInt(1000) < lambda*10){
                return 1;
            }
            return 0;
        }
        else if(lambda<0.1 && lambda>=0.01){
            if (RAND.nextInt(10000) < lambda*100){
                return 1;
            }
            return 0;
        }
        else {
            return 0;
        }
    }
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Non-static fields">
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
    public boolean HANDRAW_OBSTACLES;
    
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
    
    /**
     *
     */
    public AllColors COLORS;
    
    /**
     *
     */
    public double REPULSION;
    
    /**
     *
     */
    public boolean FIRST_TO_FIRE;
    
    /**
     *  to print content of a cell
     */
    public boolean RAND_COLORS;
    
    /**
     *
     */
    public void initParamsGlobal(){
        
        this.CELL_DIM           = MAIN_CELL_DIM;
        this.SHAPE              = MAIN_SHAPE;
        this.WALL               = MAIN_WALL;
        this.BOUNDARIES         = MAIN_BOUNDARIES;
        this.NB_CLASSES         = MAIN_NB_CLASSES;
        this.GRID               = MAIN_GRID;
        this.COEF_SPEED         = MAIN_COEF_SPEED;
        this.LAMBDA             = MAIN_LAMBDA;
        this.DENSITY            = MAIN_DENSITY;
        this.MLEVEL             = MAIN_MLEVEL;
        this.OBSTACLES_LENGTH   = MAIN_OBSTACLES_LENGTH;
        this.OBSTACLES_NBR      = MAIN_OBSTACLES_NBR;
        this.PA                 = MAIN_PA;
        this.RAND_COLORS        = MAIN_RAND_COLORS;
        this.REPULSION          = MAIN_REPULSION;
        this.PANEL_LENGTH       = MAIN_PANEL_LENGTH;
        this.MATRIX_LENGTH      = (MAIN_PANEL_LENGTH/CELL_DIM) + 2*WALL;
        
        this.OBSTACLES          = false;
        this.HANDRAW_OBSTACLES  = false;
        this.UNCOVER            = false;
        this.VAGUE              = false;
        this.FIRST_TO_FIRE      = true;
        
        this.NB_GENERATIONS     = 0;
        this.SPEED              = 100;
        this.OBSTACLES_SHAPE    = "Both";
        this.POLICY             = Policy.CYCLIC;
        
        this.COLORS = new AllColors(MAIN_COLOR_AGENT1, 
                Color.black, 
                Color.red, 
                Color.blue, 
                Color.white);
        this.COLORS.initColorTable(this.NB_CLASSES, this.RAND_COLORS);
    }
    
    /**
     *
     * @param key
     * @return
     */
    public Color getCOLOR_at(int key){
        return COLORS.getColorAt(key);
    }
    
    /**
     *
     * @param msg
     */
    public void printToScreen(String msg){
        System.out.println(msg);
        System.out.println("CELL_DIM : " + this.CELL_DIM);
        System.out.println("MATRIX_LENGTH : " + this.MATRIX_LENGTH);
    }
    
    /**
     *
     * @param k
     * @param opacity
     * @return
     */
    public Color getCOLOR_at(int k, int opacity){
        return new Color(
                COLORS.getRed_at(k),
                COLORS.getGreen_at(k),
                COLORS.getBlue_at(k),
                opacity);
    }
    
//</editor-fold>

}
