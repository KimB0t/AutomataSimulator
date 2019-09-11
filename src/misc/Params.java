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

import data.Data;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

/**
 * This classe contains all of the parameters of the program.
 * - Params for gui intefaces
 * - Params for Automata
 * - Enums 
 * 
 * Fields starting with the prefix MAIN_* means they are static.
 * Static variables are only used from the launcher class to initiate params
 * @author Karim BOUTAMINE <boutaminekarim06 at gmail.com>
 */
public class Params {
    
//<editor-fold defaultstate="collapsed" desc="Static fields">
    
    /**
     * Length of a cell.
     */
    public static int MAIN_CELL_LENGTH;
    
    /**
     * Text describtion of a cell's shape ("Square", "Oval", etc.).
     */
    public static String MAIN_SHAPE;
    
    /**
     * Color of cell when it contains an Agent.
     */
    public static Color MAIN_COLOR_AGENT1;
    
    /**
     * A global variable same as <code>BOUNDATY_LENGTH</code>.
     * @see BOUNDARY_LENGTH
     */
    public static int MAIN_BOUNDARY_LENGTH;
    
    /**
     * Length of a matrix.
     */
    public static int MAIN_MATRIX_LENGTH;
    
    /**
     * Text description of an automaton boundaries ("Free", "Toric", etc.).
     */
    public static String MAIN_BOUNDARIES;
    
    /**
     * Number of classes in a classification type of automata.
     */
    public static int MAIN_NB_CLASSES;
    
    /**
     * <code>true</code> to display a grid on <code>jPanel_screen</code>
     * <code>false</code> otherwise.
     * used by <code>Painter</code>
     */
    public static boolean MAIN_GRID;
    
    /**
     * <code>true</code> to use random colors when coloring classes of agents
     * <code>false</code> to use pre-defined colors.
     */
    public static boolean MAIN_RAND_COLORS;

    public static Color getCOLOR(ArrayList<Double> values, double min, double max, int opacity) {
        
        double addedValues = 0.0;
        for (double value : values) {
            addedValues += value;
        }
        
        int a = (int) ((addedValues - min) * (255 - 0) / (max - min));
        
        int r = (int)(Math.abs(255 - a) % 255);
        int g = (int)(a % 255);
        int b = (int)((a - 100 + 255) % 255);
//        int r = (int)(255 - data.getValue() * 100);
//        int g = (int)(0 + (100 - data.getValue() * 100));
//        int b = (int)(100 + data.getValue() * 100);
        Color c = new Color(r, g, b, opacity);
        return c;
    }
    
    public Color getCOLOR(Data data, int opacity) {
        
        int r = data.getCouleur().getRed();
        int g = data.getCouleur().getGreen();
        int b = data.getCouleur().getBlue();
        
//        int r = (int)(255 - data.getValue() * 100);
//        int g = (int)(0 + (100 - data.getValue() * 100));
//        int b = (int)(100 + data.getValue() * 100);
        Color c = new Color(r, g, b, opacity);
        return c;
    }
    
    /**
     * Variantes of automata.
     */
    public static enum Variante { 

            /**
             * Conway's game of life (1970).
             */
            LIFE, 

            /**
             * Reaction-diffusion-chemotaxis decentralised gathering 
             * (Nazim Fatès).
             */
            DIFFUSION_GATHERING, 

            /**
             * Reaction-diffusion-chemotaxis decentralised classification
             * (Karim Boutamine).
             */
            DIFFUSION_CLASSIFICATION, 

            /**
             * Turmites model (Selma Belgacem, Nazim Fatès).
             */
            TURMITES, 

            /**
             * Influence-diffusion-chemotaxis decentralised classification
             * (Karim Boutamine).
             */
            INFLUENCE_CLASSIFICATION,
            
            /**
             * Influence-repulsion-diffusion-chemotaxis decentralised 
             * classification (Karim Boutamine).
             */
            INFLUENCE_REPULSION,
            
            /**
             * First test for real data classification (Karim Boutamine).
             */
            TEST,
            
            /**
             * Default value if none of the above.
             */
            DEFAULT};
    
    /**
     * Turmites orientations on the matrix. 
     * (see Selma Belgacem, Nazim Fatès. Robustness of multi-agent models: 
     * the example of collaboration between turmites with synchronous and 
     * asynchronous updating. Complex Systems, Complex Systems Publications, 
     * 2012, 21 (3), pp.165-182. 
     * {http://www.complex-systems.com/abstracts/v21_i03_a01.html}. 
     * {https://hal.inria.fr/inria-00462438v2})
     */
    public static enum Directions { 

            /**
             * Agent is north oriented (looking north).
             */
            NORTH, 

            /**
             * Agent is east oriented (looking east).
             */
            EAST, 

            /**
             * Agent is south oriented (looking south).
             */
            SOUTH, 

            /**
             * Agent is west oriented (looking west).
             */
            WEST, 

            /**
             * Default value if no orientation is specified.
             */
            DEFAULT};
    
    /**
     * Policy used when choosing agents' influences to update the system.
     */
    public static enum Policy { 

            /**
             * Synchronous updating: Checking all the agents' influences 
             * at the same time and then taking decision.
             */
            SYNCHRO, 

            /**
             * Cyclic updating: Checking agents' inluences one by one in 
             * a given order.
             */
            CYCLIC, 

            /**
             * Random updating: Randomly checking of agents' inluences. 
             */
            RANDOM,
            
            /**
             * Default value if no <code>Policy</code> is choosen.
             */
            DEFAULT};
    
    /**
     * The current variante of the automaton simulation.
     */
    public static Variante VARIANTE;
    
    /**
     * A global random Variable for all instances of all classes.
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
    
    
    public static String MAIN_NEIGHBOURHOOD;
    
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
    
    public static String arrayToString(ArrayList array){
        String str = "[";
        for (Object element : array) {
            str += element + ", ";
        }
        str = str.substring(0, str.length() - 2);
        str += "]";
        return str;
    }
    
    public static String getVarianteCode(){
        switch(VARIANTE){
            case LIFE:
                return "LIF";
            case TURMITES:
                return "TUR";
            case DIFFUSION_CLASSIFICATION:
                return "DIFC";
            case DIFFUSION_GATHERING:
                return "DIFG";
            case INFLUENCE_CLASSIFICATION:
                return "INFC";
            case INFLUENCE_REPULSION:
                return "INFR";
            case TEST:
                return "INFD";
            default:
                return "NULL";
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
     * The matrix's boundary length in number of cells. IT is used when 
     * displaying automaton matrix on screen 
     * <code>jPanel_screen</code>. If <code>BOUNDARY_LENGTH == 0</code>, 
     * then we start drawing the matrix from Cell(0,0) to 
     * Cell(MATRIX_LENGTH, MATRIX_LENGTH).
     * If <code>BOUNDARY_LENGTH > 0</code>, this means that there is a wall 
     * surronding the matrix with length <code>BOUNDARY_LENGTH</code>, 
     * so we don't when to display it. So we start 
     * drawing from Cell(0 + BOUNDARY_LENGTH, 0 + BOUNDARY_LENGTH) to 
     * Cell(MATRIX_LENGTH - BOUNDARY_LENGTH, MATRIX_LENGTH - BOUNDARY_LENGTH).
     * <p>
     * Note: (in general the boundary length is 1 cell)
     */
    public int BOUNDARY_LENGTH;
    
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
     * true/false: switch displayed colors
     */
    public boolean SWITCH;
    
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
    
    
    public String NEIGHBOURHOOD;
    
    /**
     *
     */
    public void initParamsGlobal(){
        
        this.CELL_DIM           = MAIN_CELL_LENGTH;
        this.SHAPE              = MAIN_SHAPE;
        this.BOUNDARY_LENGTH    = MAIN_BOUNDARY_LENGTH;
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
        this.NEIGHBOURHOOD       = MAIN_NEIGHBOURHOOD;
        this.PANEL_LENGTH       = MAIN_PANEL_LENGTH;
        this.MATRIX_LENGTH      = (MAIN_PANEL_LENGTH/CELL_DIM) + 2*BOUNDARY_LENGTH;
        
        this.OBSTACLES          = false;
        this.HANDRAW_OBSTACLES  = false;
        this.UNCOVER            = false;
        this.VAGUE              = false;
        this.FIRST_TO_FIRE      = true;
        this.SWITCH             = false;
        
        this.NB_GENERATIONS     = 0;
        this.SPEED              = 100;
        this.OBSTACLES_SHAPE    = "Both";
        this.POLICY             = Policy.CYCLIC;
        
        this.COLORS = new AllColors(MAIN_COLOR_AGENT1, 
                Color.black, 
                Color.red, 
                new Color(255, 0, 0, 100), 
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

    public Params getParams(){
        return this;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Setters & Getters">
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
     * @return
     */
    public Color getCOLOR_EXCITED2(){
        return COLORS.COLOR_EXCITED2;
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
    public void setSWITCH(boolean b) {
        this.SWITCH = b;
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
//</editor-fold>
    
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
