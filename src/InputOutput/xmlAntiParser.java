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


public class xmlAntiParser extends AntiParser{

    public xmlAntiParser(){
        super();
    }

    public void createFile(Model model, File file, String taxpayer) {
        String afm = model.getAFM(taxpayer);
        file = new File(file.toString() + "\\" + afm + "_RECEIPTS.txt");
        
        String head ="<?xml version=\"1.0\" encoding=\"UTF-8\"?/>\n\n" + "<Name " + taxpayer + "/>\n<AMF " +
        				model.getAFM(taxpayer) + "/>\n<Income: " + model.getIncome(taxpayer) + "/>\n<Receipts/>";
        String[][] nodes = model.allReceipts(taxpayer);
        
        try {
            FileWriter fwr = new FileWriter(file);
            fwr.write(head);

            for(int i=0; i<model.getReceiptsSize(taxpayer); i++) {
                String wr = "\n<Receipt ID " + nodes[i][0] + "/>\n<Company " + nodes[i][1] + "/>\n<CompanyOwer " + nodes[i][2] +
                            "/>\n<CompanyOwnerAFM " + nodes[i][3] + "/>\n<CompanyAddress " + nodes[i][4] + "/>\n<Category " + 
                            nodes[i][5] + "/>\n<Amount " + nodes[i][6] + "/>";
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
