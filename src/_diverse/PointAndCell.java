/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _diverse;

import cells.CellDiffusionClass;
import java.awt.Point;

/**
 *
 * @author Karim
 */
public class PointAndCell {
    
    public Point point;
    public CellDiffusionClass cell;
    
    public PointAndCell(){
        this.point = new Point();
        this.cell = null;
    }
    
    
    public PointAndCell(Point p, CellDiffusionClass c){
        this.point = p;
        this.cell = c;
    }
}
