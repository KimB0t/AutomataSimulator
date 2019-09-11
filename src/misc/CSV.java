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

package misc;

import data.Data;
import data.DataArray;
import data.DataSingle;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static misc.Params.RAND;
import static misc.Params.round;
import static misc.Params.getCOLOR;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 * @version 1.0
 */
public class CSV {
    
    public ArrayList<Data> dataArray;
    public int rows;
    public int columns;
    public int dataLength;
    public double maxValue;
    public double minValue;
    public ArrayList<String> classesArray;
    public int classes;
    
    public void readCSVData(String pathToCsv){
        String row, classe;
        ArrayList<Double> values;
        int id;
        Color c;
        BufferedReader csvReader;
        try {
            csvReader = new BufferedReader(new FileReader(pathToCsv));
            row = csvReader.readLine();
            String[] dataString = row.split(",");
            
            if(dataString.length > 1) {
                columns = dataString.length;
                //last column represents the class
                dataLength = columns - 1;
                minValue = maxValue = Double.parseDouble(dataString[0]);

                dataArray = new ArrayList<>();
                classesArray = new ArrayList<>();

                classe = dataString[dataLength];
                classesArray.add(classe);
                values = new ArrayList<>();
                id = 0;

                for(int i=0; i<dataLength; i++){
                    double val = Double.parseDouble(dataString[i]);
                    values.add(val);
                    if(values.get(i) > maxValue) maxValue = values.get(i);
                    if(values.get(i) < minValue) minValue = values.get(i);
                }
                c = getCOLOR(values, minValue, maxValue, 255);
                if(dataLength == 2) {
                    DataSingle data1 = new DataSingle(values.get(0), classe, id, c, 0.4);
                    dataArray.add(data1);
                }
                else {
                    DataArray data2 = new DataArray(values, classe, id, c, 0.4);
                    dataArray.add(data2);
                }
            }
            
            while ((row = csvReader.readLine()) != null) {
                dataString = row.split(",");
                if(dataString.length > 1) {
                    classe = dataString[dataLength];
                    if (!classesArray.contains(classe)) classesArray.add(classe);

                    id = classesArray.indexOf(classe);
                    
                    values = new ArrayList<>();
                    for(int i=0; i<dataLength; i++){
                        double val = Double.parseDouble(dataString[i]);
                        values.add(val);
                        if(values.get(i) > maxValue) maxValue = values.get(i);
                        if(values.get(i) < minValue) minValue = values.get(i);
                    }
                    c = getCOLOR(values, minValue, maxValue, 255);
                    if(dataLength == 2) {
                        DataSingle data1 = new DataSingle(values.get(0), classe, id, c, 0.4);
                        dataArray.add(data1);
                    }
                    else {
                            DataArray data2 = new DataArray(values, classe, id, c, 0.4);
                        dataArray.add(data2);
                    }
                }
            }
            
            csvReader.close();
            rows = dataArray.size();
            classes = classesArray.size();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getInfoData(JTable table){
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if(model.getRowCount() > 0) model.removeRow(0);
        model.insertRow(0, new Object[]{rows, columns, dataLength, classes});
//        model.addRow(new Object[]{rows, columns, dataLength, classes});
    }
    
    public void createRandDataCSV(String type, Double[] means, Double[] intervalLengths, int nbSamples, String fileName) {
        
        FileWriter csvWriter;
        try {
            csvWriter = new FileWriter("./datasets/"+fileName+".csv");
        
            int lengthMeans = means.length;
            int meanIndex;
            double mean = 0.0, interval = 0.0, value = 0.0;

            for (int i = 0; i < nbSamples; i++) {
                meanIndex = RAND.nextInt(lengthMeans);
                mean = means[meanIndex];
                if(meanIndex >= intervalLengths.length){
                    interval = intervalLengths[intervalLengths.length - 1];
                }
                else{
                    interval = intervalLengths[meanIndex];
                }

                if (type.equals("Gauss")) 
                    value = randGauss(mean, interval);
                else if (type.equals("Unif"))
                    value = randUnif(mean, interval);
                
                //Create CSV
                csvWriter.append(String.valueOf(value));
                csvWriter.append(",");
                csvWriter.append(String.valueOf(mean));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(CSV.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private double randGauss(Double mean, Double stdDev){
        double value = RAND.nextGaussian() * stdDev + mean;
        value = round(value, 2);
        return value;
    }
    
    private double randUnif(Double mean, Double variance){
        double min = mean - variance/2;
        double max = mean + variance/2;
        
        double value = min + (max - min) * RAND.nextDouble();
        value = round(value, 2);
        
        return value;
    }
    
}
