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

//import static misc.Params.MAIN_COLORS_ARRAY;
//import static misc.Params.RAND;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import static misc.Params.MAIN_COLORS_ARRAY;
import static misc.Params.RAND;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06 at gmail.com>
 */
public class AllColors {
    
    /**
     * Agents principal color (default : gray)
     */
    public Color COLOR_AGENT1;

    /**
     *  Agents second color (when nb agents in a cell > 1) (default : black)
     */
    public Color COLOR_AGENT2;

    /**
     * Color of an excited cell (default : red)
     */
    public Color COLOR_EXCITED;

    /**
     * Color of an excited cell (default : red)
     */
    public Color COLOR_EXCITED2;

    /**
     *  Color of obstacles (walls) (default : blue)
     */
    public Color COLOR_OBSTACLE;

    /**
     * Color of background (default : white)
     */
    public Color COLOR_DEFAULT;

    /**
     *  An array of colors for different agents of different classes
     * as many colors as there are classes
     */
    public ArrayList<Color> COLOR_TABLE;
    
    /**
     *
     * @param COLOR_AGENT1
     * @param COLOR_AGENT2
     * @param COLOR_EXCITED
     * @param COLOR_OBSTACLE
     * @param COLOR_DEFAULT
     */
    public AllColors(Color COLOR_AGENT1, Color COLOR_AGENT2, Color COLOR_EXCITED, Color COLOR_EXCITED2, Color COLOR_OBSTACLE, Color COLOR_DEFAULT) {
        this.COLOR_AGENT1       = COLOR_AGENT1;
        this.COLOR_AGENT2       = COLOR_AGENT2;
        this.COLOR_EXCITED      = COLOR_EXCITED;
        this.COLOR_EXCITED2     = COLOR_EXCITED2;
        this.COLOR_OBSTACLE     = COLOR_OBSTACLE;
        this.COLOR_DEFAULT      = COLOR_DEFAULT;
        this.COLOR_TABLE        = new ArrayList<>();
    }
    
    /**
     *
     * @param NB_CLASSES
     * @param RAND_COLORS
     */
    public void initColorTable(int NB_CLASSES, boolean RAND_COLORS){
        //Remplir les couleurs
        if(RAND_COLORS)
            for (int i = 0; i < NB_CLASSES; i++) {
                COLOR_TABLE.add(randomColor(200));
            }
        else
            for (int i = 0; i < NB_CLASSES; i++) {
                COLOR_TABLE.add(MAIN_COLORS_ARRAY[i]);
            }
    }
    
    /**
     *
     * @param opacity between 0 and 255
     * @return
     */
    public Color randomColor(int opacity){
        return new Color(RAND.nextInt(245)+5, 
                    RAND.nextInt(245)+5, RAND.nextInt(245)+5, opacity);
    }
    
    /**
     *
     * @param k
     * @return
     */
    public Color getColorAt(int k){
        return this.COLOR_TABLE.get(k);
    }
    
    /**
     *
     * @param k
     * @return
     */
    public int getRed_at(int k){
        return this.COLOR_TABLE.get(k).getRed();
    }
    
    /**
     *
     * @param k
     * @return
     */
    public int getBlue_at(int k){
        return this.COLOR_TABLE.get(k).getBlue();
    }
    
    /**
     *
     * @param k
     * @return
     */
    public int getGreen_at(int k){
        return this.COLOR_TABLE.get(k).getGreen();
    }
}
