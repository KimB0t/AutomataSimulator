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

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import static misc.Params.arrayToString;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 * @version 1.0
 */
public class DataArray extends Data{

    //Value of data
    private ArrayList<Double> values;
    
    public DataArray(int nbValues) {
        valuesInitializer(nbValues);
    }

    public DataArray(ArrayList<Double> values, Color couleur, double threshold) {
        setValue(values);
        this.couleur = couleur;
        this.threshold = threshold;
    }

    public DataArray(ArrayList<Double> values, String classe, int id, Color couleur, double threshold) {
        setValue(values);
        this.classe = classe;
        this.couleur = couleur;
        this.threshold = threshold;
        this.id = id;
    }

    private void setValue(ArrayList<Double> values) {
        if(values != null){
            valuesInitializer(values.size());
            Collections.copy(this.values, values);
        }
    }

    public ArrayList<Double> getValues() {
        return values;
    }
    
    @Override
    public boolean compare(Data data){
        
        double difference = 0.0;
        int cpt = 0;
//        for (int i = 0; i < this.values.size(); i++) {
////            difference += Math.abs(this.values[i] - data.getValueAt(i));
//            if(Math.abs(this.values.get(i) - ((DataArray)data).getValueAt(i)) < threshold)
//                cpt++;
//        }
//        return (cpt > this.values.size()/2);
        
        //Euclidienne distance
        double distance = Double.MAX_VALUE;
        double somme = 0;
        for (int i = 0; i < this.values.size(); i++) {
            difference = this.values.get(i) - ((DataArray)data).getValueAt(i);
            somme += Math.pow(difference, 2);
        }
        distance = Math.sqrt(somme);
//        System.out.println("dist: " + distance);
        return (distance < threshold);
//        System.out.println("diff =====> " + difference);
//        return (difference < threshold);
    }

    @Override
    public void random() {
        //I need to generate un random number between min and max so that given 
        //a mean (m) and a variance (v):
        //min = m - v/2
        //max = m + v/2
        
//        double mean = 0.0, min = 0.0, max = 0.0, variance = 0.2;
//        if (bernoulli(50) == 1){
//            mean = 0.25;
//        }
//        else{
//            mean = 0.75;
//        }
//        min = mean - variance/2;
//        max = mean + variance/2;
//        value = min + (max - min) * RAND.nextDouble();
        
//        value = 0.0;
        
    }

    @Override
    public String print() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void valuesInitializer(int nbValues){
        this.values = new ArrayList<>();
        for (int i = 0; i < nbValues; i++) {
            //initialisation avec le min de chaque classe
            this.values.add(0.0); //i représente la classe
        }
    }
    
    public double getValueAt(int i) {
        return this.values.get(i);
    }
    
    @Override
    public String getValueString(){
        return arrayToString(this.values);
    }
}
