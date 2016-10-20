/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package InputOutput;

import Model.Model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class textAntiParser extends AntiParser {

    public textAntiParser() {
        super();
    }

    public void createFile(Model model, File file, String taxpayer) {
        String afm = model.getAFM(taxpayer);
        String[][] nodes = model.allReceipts(taxpayer);
        
        file = new File(file.toString() + "\\" + afm + "_RECEIPTS.txt");
        String head = "Name: " + taxpayer + "\n" + 
        				"AMF: " + model.getAFM(taxpayer) + "\n" + 
        				"Income: " + model.getIncome(taxpayer) +"\n\n" + 
        				"Receipts:\n";

        try {
            FileWriter fwr = new FileWriter(file);
            fwr.write(head);

            String wr;
            for(int i=0; i<model.getReceiptsSize(taxpayer); i++) {
                wr = "Receipt ID: " + nodes[i][0] + "\n" + 
                		"Company: " + nodes[i][1] + "\n" + 
                		"CompanyOwer: " + nodes[i][2] + "\n" + 
                		"CompanyOwnerAFM: " + nodes[i][3] + "\n" + 
                		"CompanyAddress: " + nodes[i][4] + "\n" + 
                		"Category: " + nodes[i][5] + "\n" + 
                		"Amount: " + nodes[i][6] + "\n";
                
                fwr.write(wr);
            }
            
            fwr.flush();
            fwr.close();
        }
        catch (IOException ex) {
            Logger.getLogger(textAntiParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
