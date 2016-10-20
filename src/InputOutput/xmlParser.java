/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package InputOutput;

import Model.Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class xmlParser extends Parser {
    private String setLine;
    private String Name, AFM, ReceiptID ,Company, CompanyOwner;
    private String CompanyOwnerAFM, CompanyAddress, Category;
    private int Income, Amount, CompanyOwnerComps;

    public xmlParser() {
        super();
    }

    @Override
    public void parsing(Model model, File fileName) {
        FileReader fr = null ;
        try {
            fr = new FileReader(fileName);
            Scanner scanner = new Scanner(fr);
            try {
                while (scanner.hasNextLine()) {
                    setLine = scanner.nextLine();
                    parsLine(setLine, model);
                }
            }
            finally {
                try {
                    fr.close();
                    scanner.close();
                }
                catch (IOException ex) {
                    Logger.getLogger(textParser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(textParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(textParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void parsLine(String aLine, Model model) {
    	
        if(aLine.startsWith("<N")) {
            String temp[] = aLine.split(" ", 2);
            Name = temp[1].substring(0, temp[1].indexOf("/"));
        }
        else if(aLine.startsWith("<AF")) {
            String temp[] = aLine.split(" ", 2);
            AFM = temp[1].substring(0, temp[1].indexOf("/"));
        }
        else if(aLine.startsWith("<I")) {
            String temp[] = aLine.split(" ", 2);
            String temp1 = temp[1].substring(0, temp[1].indexOf("/"));
            Income = Integer.parseInt(temp1);
            model.addTaxpayer(Name, AFM, Income);
        }
        else if(aLine.startsWith("<Receipt ID")) {
            String temp[] = aLine.split(" ", 3);
            ReceiptID = temp[2].substring(0, temp[2].indexOf("/"));
        }
        else if(aLine.startsWith("<Company ")) {
            String temp[] = aLine.split(" ", 2);
            Company = temp[1].substring(0, temp[1].indexOf("/"));
        }
        else if(aLine.startsWith("<CompanyOwner ")) {
            String temp[] = aLine.split(" ", 2);
            CompanyOwner = temp[1].substring(0, temp[1].indexOf("/"));
        }
        else if(aLine.startsWith("<CompanyOwnerA")) {
            String temp[] = aLine.split(" ", 2);
            CompanyOwnerAFM = temp[1].substring(0, temp[1].indexOf("/"));
        }
        else if(aLine.startsWith("<CompanyOwnerComps")) {
            String temp[] = aLine.split(" ", 2);
            String num = temp[1].substring(0, temp[1].indexOf("/"));
            CompanyOwnerComps = Integer.parseInt(num);
        }
        else if(aLine.startsWith("<CompanyA")) {
            String temp[] = aLine.split(" ", 2);
            CompanyAddress = temp[1].substring(0, temp[1].indexOf("/"));
        }
        else if(aLine.startsWith("<Ca")) {
            String temp[] = aLine.split(" ", 2);
            Category = temp[1].substring(0, temp[1].indexOf("/"));
        }
        else if(aLine.startsWith("<Am")) {
            String temp[] = aLine.split(" ", 2);
            String num = temp[1].substring(0, temp[1].indexOf("/"));
            Amount = Integer.parseInt(num);
            model.setReceipt(Name, ReceiptID, Category, Amount, Company, CompanyAddress, CompanyOwner, CompanyOwnerAFM, CompanyOwnerComps);
        }
    }

}
