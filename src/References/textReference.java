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

        String head = "Name: " + taxpayer + "\nAMF: " + model.getAFM(taxpayer) + "\nIncome: " + model.getIncome(taxpayer);

        try {
            FileWriter fwr = new FileWriter(file);
            fwr.write(head);

            String wr = "\nTotalRequiredReceipts: " + req + "\nTotalReceiptsGathred: " + gath +
            			"\nAdditionalTaxReduction: " + reduc + "\nAdditionalTaxPenalty: " + penal +
            			"\nCategory1: " + perCateg[0] + "\nCategory2: " + perCateg[1] + "\nCategory3: " + perCateg[2] +
            			"\nCategory4: " + perCateg[3] + "\nCategory5: " + perCateg[4] + 
                        "\nCategory6: " + perCateg[5] + "\nCategory7: " + perCateg[6];
            fwr.write(wr);
            fwr.flush();
            fwr.close();
        }
        catch (IOException ex) {
            Logger.getLogger(textAntiParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
