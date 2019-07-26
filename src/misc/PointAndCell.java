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
