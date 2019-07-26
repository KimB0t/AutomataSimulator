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
package cells;

import misc.Neighbours;
import misc.Params;
import java.awt.Color;

/**
 *
 * @author Karim
 */
public class CellTurmites extends Cell{

    private int state = 0;
    private int position = 0;
    
    public CellTurmites(Color c, int i, int j, boolean w, int s, int p) {
        super(c, i, j, w);
        this.state = s;
        this.position = p;
    }

    public CellTurmites() {
        super();
        this.state = -1;
        this.position = -1;
    }

    public CellTurmites(int s, int p) {
        super();
        this.state = s;
        this.position = p;
    }
    
    public CellTurmites(Color c, int i, int j, boolean w) {
        super(c, i, j, w);
        this.state = -1;
        this.position = -1;
    }
    
    public void setState(int state) {
        this.state = state;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getState() {
        return state;
    }

    public int getPosition() {
        return position;
    }
    
    public void changeState(Params param){
        this.state = 1 - this.state;
        if(state == 0)
            this.setCouleur(param.COLORS.COLOR_DEFAULT);
        else
            this.setCouleur(param.COLORS.COLOR_EXCITED);
    }

    @Override
    public Cell nextState(Params param, Neighbours nghbrs) {
        return null;
    }
}
