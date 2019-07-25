/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _diverse;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Karim
 */
public class Params {
    
    public static int MAIN_CELL_DIM;
    
    
    public static String MAIN_SHAPE;
    
    
    public static Color MAIN_COLOR_AGENT1;
    
    
    public static int MAIN_WALL;
    
    
    public static int MAIN_MATRIX_LENGTH;
    
    
    public static String MAIN_BOUNDARIES;
    
    
    public static int MAIN_NB_CLASSES;
    
    
    public static boolean MAIN_GRID;
    
    
    public static boolean MAIN_RAND_COLORS;
    /**
     *  Different types of automata
     */
    public static enum Variante { 

            /**
             *  for life game
             *
             */
            LIFE, 

            /**
             *  for gathering amoebae with reaction-diffusion
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
    public static float MAIN_COEF_SPEED;
    public static int MAIN_PANEL_LENGTH;
    public static double MAIN_LAMBDA;
    public static double MAIN_DENSITY;
    public static int MAIN_MLEVEL;
    public static int MAIN_OBSTACLES_LENGTH;
    public static int MAIN_OBSTACLES_NBR;
    public static double MAIN_PA;
    public static Policy MAIN_POLICY;
    public static double MAIN_REPULSION;
    
    
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
    
}
