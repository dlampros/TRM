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
        file = new File(file.toString()+"\\"+afm+"_RECEIPTS.txt");

        String head = "Name: " + taxpayer + "\nAMF: " + model.getAFM(taxpayer) + "\nIncome: " + model.getIncome(taxpayer) +"\n\nReceipts:";
        String[][] nodes = model.allReceipts(taxpayer);

        try {
            FileWriter fwr = new FileWriter(file);
            fwr.write(head);

            for(int i=0; i<model.getReceiptsSize(taxpayer); i++) {
                String wr = "\nReceipt ID: " + nodes[i][0] + "\nCompany: " + nodes[i][1] + "\nCompanyOwer: " + nodes[i][2] +
                            "\nCompanyOwnerAFM: " + nodes[i][3] + "\nCompanyAddress: " + nodes[i][4] + "\nCategory: " +
                            nodes[i][5] + "\nAmount: " + nodes[i][6];
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
