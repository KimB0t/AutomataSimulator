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

package csv;

import data.DataGauss;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 * @version 1.0
 */
public class ReadCSV {

    public ArrayList<DataGauss> dataArray;
    
    public void readCSVSingleData(String pathToCsv){
        String row;
        BufferedReader csvReader;
        try {
            csvReader = new BufferedReader(new FileReader(pathToCsv));
            dataArray = new ArrayList<>();
            while ((row = csvReader.readLine()) != null) {
                String[] dataString = row.split(",");
                Double value = Double.parseDouble(dataString[0]);
                String classe = dataString[1];
                DataGauss data = new DataGauss(value, classe);
                dataArray.add(data);
            }
            csvReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadCSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void displayDataArray(){
        for (DataGauss dataArray1 : dataArray) {
            System.out.println("Value: "+dataArray1.getValue()
                    +", Class: "+dataArray1.getClasse());
        }
    }
}
