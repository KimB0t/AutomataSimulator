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

import automata.Automaton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06 at gmail.com>
 */
public class Painter extends JPanel{
    
//    private Cell[][] matrix;
    private Automaton ac;
    boolean erase = false;
    
    public Painter() {
        
    }
    
    public void setAutomaton(Automaton aa){
        this.ac = aa;
        setMouseAdapter();
    }
    
    /**
     * this function paint components on jPanel_screen from the matrix
     * It displays also the grid if it is activated
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //if shape oval draw circles
        if(ac.param.shapeIs("Oval")) {
            for(int i=ac.param.BOUNDARY_LENGTH; i<ac.param.getRelativeLength(); i++) {
                for(int j=ac.param.BOUNDARY_LENGTH; j<ac.param.getRelativeLength(); j++){
                    g2.setColor(ac.getCellColor(i, j));
                    int x = (i-ac.param.BOUNDARY_LENGTH)*ac.param.CELL_DIM;
                    int y = (j-ac.param.BOUNDARY_LENGTH)*ac.param.CELL_DIM;
                    int dim = ac.param.CELL_DIM;
                    g2.fillOval(x, y, dim, dim);
                }
            }
        }
        //if shape square draw squares
        else if(ac.param.shapeIs("Square")){
            for(int i=ac.param.BOUNDARY_LENGTH; i<ac.param.getRelativeLength(); i++) {
                for(int j=ac.param.BOUNDARY_LENGTH; j<ac.param.getRelativeLength(); j++){
                    g2.setColor(ac.getCellColor(i, j));
                    int x = (i-ac.param.BOUNDARY_LENGTH)*ac.param.CELL_DIM;
                    int y = (j-ac.param.BOUNDARY_LENGTH)*ac.param.CELL_DIM;
                    int dim = ac.param.CELL_DIM;
                    g2.fillRect(x, y, dim, dim);
                }
            }
        }
        //if shape square draw squares
//        else if(ac.shapeIs("Triangle")){
//            for(int i=0+ac.BOUNDARY_LENGTH; i<ac.MATRIX_LENGTH-ac.BOUNDARY_LENGTH; i++) {
//                for(int j=0+ac.BOUNDARY_LENGTH; j<ac.MATRIX_LENGTH-ac.BOUNDARY_LENGTH; j++){
//                    g2.setColor(ac.getCellColor(i, j));
//                    Polygon poly = new Polygon(new int[] { (i-ac.BOUNDARY_LENGTH)*ac.CELL_DIM, (i-ac.BOUNDARY_LENGTH)*ac.CELL_DIM, (i-ac.BOUNDARY_LENGTH)*ac.CELL_DIM + ac.CELL_DIM }, 
//                            new int[] { (j-ac.BOUNDARY_LENGTH)*ac.CELL_DIM, (j-ac.BOUNDARY_LENGTH)*ac.CELL_DIM + ac.CELL_DIM, (j-ac.BOUNDARY_LENGTH)*ac.CELL_DIM + (ac.CELL_DIM/2) }, 
//                            3);
//                    g2.fill(poly);
//                }
//            }
//        }
        
        if(ac.param.GRID){
            // draw horizontal & vertical rows for grid
            int nb_lines = ac.param.MATRIX_LENGTH;
            int rowDim = ac.param.CELL_DIM;
            g2.setColor(Color.BLACK);
            for (int i = 0; i < nb_lines; i++) {
                g2.drawLine(0, i * rowDim, ac.param.PANEL_LENGTH, i * rowDim);
                g2.drawLine(i * rowDim, 0, i * rowDim, ac.param.PANEL_LENGTH);
            }
        }
    }
    
    public void clear() {
        repaint();
    }
    
    private void setMouseAdapter(){
        
        setBackground(Color.WHITE);
        
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //DRAW
                if(!ac.param.UNCOVER){
                    if(!erase) {
                        //IF we are drawing obstacles
                        if(ac.param.OBSTACLES && ac.param.HANDRAW_OBSTACLES) 
                            drawObstacle(e.getX(), e.getY());
                        else 
                            drawAgent(e.getX(), e.getY());
                    }
                    //ERASE
                    else{
                        drawEmpty(e.getX(), e.getY());
                    }
                }
            }
        });
        addMouseListener(new MouseAdapter() {
             @Override
            public void mousePressed(MouseEvent e) {
                //IF We are drawing (button1==click gauche)
                if(e.getButton() == MouseEvent.BUTTON1) {
                    erase = false;

                    if(ac.param.UNCOVER){
//                        System.out.println("Painter->setMouseAdapter->addMouseListener->mousePressed"
//                                + " TO BE IMPLEMENTED");
                        printInfo(e.getX(), e.getY());
                    }
                    else{
                        //IF we are drawing obstacles
                        if(ac.param.OBSTACLES && ac.param.HANDRAW_OBSTACLES) 
                            drawObstacle(e.getX(), e.getY());
                        else 
                            drawAgent(e.getX(), e.getY());
                    }
                }
                else if(e.getButton() == MouseEvent.BUTTON3){
                    erase = true;
                    drawEmpty(e.getX(), e.getY());
                }
            }
        });
    }
    
    public void drawAgent(int x, int y){
        
        int i = x/ac.param.CELL_DIM+ac.param.BOUNDARY_LENGTH;
        int j = y/ac.param.CELL_DIM+ac.param.BOUNDARY_LENGTH;
        if(x>=0 && x<ac.param.PANEL_LENGTH && 
                y>=0 && y<ac.param.PANEL_LENGTH){

            //DRawing agents
            ac.setAgent(i, j, 1, ac.param.getCOLOR_AGENT1(), false);
            repaint();
        }
    }
    
    public void drawObstacle(int x, int y){
        
        int i = x/ac.param.CELL_DIM+ac.param.BOUNDARY_LENGTH;
        int j = y/ac.param.CELL_DIM+ac.param.BOUNDARY_LENGTH;
        if(x>=0 && x<ac.param.PANEL_LENGTH && 
                y>=0 && y<ac.param.PANEL_LENGTH){

            //DRawing agents
            ac.makeWallAt(i, j);
            repaint();
        }
    }
    
    public void drawEmpty(int x, int y){
        
        int i = x/ac.param.CELL_DIM+ac.param.BOUNDARY_LENGTH;
        int j = y/ac.param.CELL_DIM+ac.param.BOUNDARY_LENGTH;
        if(x>=0 && x<ac.param.PANEL_LENGTH && 
                y>=0 && y<ac.param.PANEL_LENGTH){

            //DRawing agents
            ac.deleteAgent(i, j);
            repaint();
        }
    }
    
    public void printInfo(int x, int y){
        
        int i = x/ac.param.CELL_DIM+ac.param.BOUNDARY_LENGTH;
        int j = y/ac.param.CELL_DIM+ac.param.BOUNDARY_LENGTH;
        if(x>=0 && x<ac.param.PANEL_LENGTH && 
                y>=0 && y<ac.param.PANEL_LENGTH){

            //DRawing agents
            ac.printCell(i, j);
            repaint();
        }
    }
    
}
