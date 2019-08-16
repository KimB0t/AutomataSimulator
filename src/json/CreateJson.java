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

package json;
import java.io.FileWriter;
import java.io.IOException;
import misc.Params;
import static misc.Params.RAND;
import static misc.Params.round;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 *
 * @author Karim BOUTAMINE <boutaminekarim06@gmail.com>
 * @version 1.0
 */
public class CreateJson {

    public void createRandUniformJson(Double[] means, Double[] variances, int nbSamples, String fileName){
        
        //Add employees to list
        JSONArray employeeList = new JSONArray();
        int lengthMeans = means.length;
        int meanIndex;
        double mean = 0.0, min = 0.0, max = 0.0, variance = 0.0, value = 0.0;
        
        for (int i = 0; i < nbSamples; i++) {
            meanIndex = RAND.nextInt(lengthMeans);
            mean = means[meanIndex];
            if(meanIndex >= variances.length){
                variance = variances[variances.length - 1];
            }
            else{
                variance = variances[meanIndex];
            }
            
            min = mean - variance/2;
            max = mean + variance/2;
            value = min + (max - min) * RAND.nextDouble();
            value = round(value, 2);
            //Create employee
            JSONObject employeeDetails = new JSONObject();
            employeeDetails.put("value", value);
            employeeDetails.put("class", mean);

            JSONObject employeeObject = new JSONObject();
            employeeObject.put("entry", employeeDetails);

            employeeList.add(employeeObject);
        }
        
        //Write JSON file
        try (FileWriter file = new FileWriter(fileName+".json")) {
 
            file.write(employeeList.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
