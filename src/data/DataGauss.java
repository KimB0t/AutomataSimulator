/*
 * Copyright (C) 2019 Karim BOUTAMINE <boutaminekarim06@gmail.com>
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

package data;

import static misc.Params.RAND;
import static misc.Params.bernoulli;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 * @version 1.0
 */
public class DataGauss extends Data{

    //Value of data
    double value;
    //Class of this data
    private String classe;
    
    public DataGauss() {
        this.value = 0.0;
    }

    public DataGauss(double value) {
        this.value = value;
    }

    public DataGauss(double value, String classe) {
        this.value = value;
        this.classe = classe;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
    
    public boolean alike(DataGauss data){
        
        boolean bol = false;
        
        if(data == null){
            bol = false;
        }
        else{
            if(Math.abs(data.value - this.value) < 0.2)
                bol = true;
            else 
                bol = false;
        }
        
        return bol;
    }

    public void random() {
        //I need to generate un random number between min and max so that given 
        //a mean (m) and a variance (v):
        //min = m - v/2
        //max = m + v/2
        
        double mean = 0.0, min = 0.0, max = 0.0, variance = 0.2;
        if (bernoulli(50) == 1){
            mean = 0.25;
        }
        else{
            mean = 0.75;
        }
        min = mean - variance/2;
        max = mean + variance/2;
        value = min + (max - min) * RAND.nextDouble();
        
//        value = 0.0;
        
    }

    public String print() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getClasse() {
        return this.classe;
    }
}
