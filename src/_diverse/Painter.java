/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _diverse;

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
 * @author Karim
 */
public class Painter extends JPanel{
    
//    private Cell[][] matrix;
    private Automaton ac;
    
    public Painter(Automaton aa) {
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
        if(ac.shapeIs("Oval")) {
            for(int i=0; i<ac.getMATRIX_LENGTH(); i++) {
                for(int j=0; j<ac.getMATRIX_LENGTH(); j++){
                    g2.setColor(ac.getCellColor(i, j));
                    g2.fillOval((i)*ac.getCELL_DIM(), (j)*ac.getCELL_DIM(), ac.getCELL_DIM(), ac.getCELL_DIM());
                }
            }
        }
        //if shape square draw squares
        else if(ac.shapeIs("Square")){
            for(int i=0; i<ac.getMATRIX_LENGTH(); i++) {
                for(int j=0; j<ac.getMATRIX_LENGTH(); j++){
                    g2.setColor(ac.getCellColor(i, j));
                    g2.fillRect((i)*ac.getCELL_DIM(), (j)*ac.getCELL_DIM(), ac.getCELL_DIM(), ac.getCELL_DIM());
                }
            }
        }
        //if shape square draw squares
        else if(ac.shapeIs("Triangle")){
            for(int i=0; i<ac.getMATRIX_LENGTH(); i++) {
                for(int j=0; j<ac.getMATRIX_LENGTH(); j++){
                    g2.setColor(ac.getCellColor(i, j));
                    Polygon poly = new Polygon(new int[] { (i)*ac.getCELL_DIM(), (i)*ac.getCELL_DIM(), (i)*ac.getCELL_DIM() + ac.getCELL_DIM() }, 
                            new int[] { (j)*ac.getCELL_DIM(), (j)*ac.getCELL_DIM() + ac.getCELL_DIM(), (j)*ac.getCELL_DIM() + (ac.getCELL_DIM()/2) }, 
                            3);
                    g2.fill(poly);
                }
            }
        }
        
        if(ac.isGRID()){
            // draw horizontal & vertical rows for grid
            int nb_lines = ac.getMATRIX_LENGTH();
            int rowDim = ac.getCELL_DIM();
            g2.setColor(Color.BLACK);
            for (int i = 0; i < nb_lines; i++) {
                g2.drawLine(0, i * rowDim, ac.getPANEL_LENGTH(), i * rowDim);
                g2.drawLine(i * rowDim, 0, i * rowDim, ac.getPANEL_LENGTH());
            }
        }
    }
    
    public void clear() {
        repaint();
    }
    
    public void setAutomaton(Automaton aa){
        this.ac = aa;
    }
    
    private void setMouseAdapter(){
        
        setBackground(Color.WHITE);
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int i = e.getX()/ac.getCELL_DIM();
                int j = e.getY()/ac.getCELL_DIM();
                if(e.getX()>=0 && e.getX()<ac.getPANEL_LENGTH() && 
                        e.getY()>=0 && e.getY()<ac.getPANEL_LENGTH()){
                    
                    //DRAW
                    ac.setAgent(i, j, 1, ac.getCOLOR_AGENT1(), false);
                    repaint();
                }
            }
            
            //To display coordiantes on the screen
//            @Override
//            public void mouseMoved(MouseEvent e){
//                jLabel_coordinates.setText("<html>X="+e.getX()+"<br>Y="+e.getY()+"</html>");
//            }
        });
        addMouseListener(new MouseAdapter() {
             @Override
            public void mousePressed(MouseEvent e) {
                int i = e.getX()/ac.getCELL_DIM();
                int j = e.getY()/ac.getCELL_DIM();
                if(e.getX()>=0 && e.getX()<ac.getPANEL_LENGTH() && 
                        e.getY()>=0 && e.getY()<ac.getPANEL_LENGTH()){
                    
                    //DRawing agents
                    ac.setAgent(i, j, 1, ac.getCOLOR_AGENT1(), false);
                    repaint();
                }
            }
        });
    }
    
}
