/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package References;

import InputOutput.textAntiParser;
import Model.Model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class textReference extends References {

    public textReference() {
        super();
    }

    public void createReferenceFile(Model model, File file, String taxpayer, String req, String gath, String reduc, String penal, String[] perCateg) {
        String afm = model.getAFM(taxpayer);
        file = new File(file.toString()+"\\" + afm + "_LOG.txt");

        String head = "Name: " + taxpayer + "\n" + 
        				"AMF: " + model.getAFM(taxpayer) + "\n" + 
        				"Income: " + model.getIncome(taxpayer) + "\n";

        try {
            FileWriter fwr = new FileWriter(file);
            fwr.write(head);

            String wr = "TotalRequiredReceipts: " + req + "\n" + 
            			"TotalReceiptsGathred: " + gath + "\n" + 
            			"AdditionalTaxReduction: " + reduc + "\n" + 
            			"AdditionalTaxPenalty: " + penal + "\n" + 
            			"Category1: " + perCateg[0] + "\n" + 
            			"Category2: " + perCateg[1] + "\n" + 
            			"Category3: " + perCateg[2] + "\n" + 
            			"Category4: " + perCateg[3] + "\n" + 
            			"Category5: " + perCateg[4] + "\n" + 
            			"Category6: " + perCateg[5] + "\n" + 
            			"\nCategory7: " + perCateg[6] + "\n";
            
            fwr.write(wr);
            fwr.flush();
            fwr.close();
        }
        catch (IOException ex) {
            Logger.getLogger(textAntiParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
