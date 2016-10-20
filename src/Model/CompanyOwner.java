/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;


public class CompanyOwner {
    private String name;
    private String vat;
    //private int numComp;

    public CompanyOwner(String n, String afm, int nComp) {
        name = n;
        vat = afm;
        //numComp = nComp;
    }

    public String getName() {
        return name;
    }
    public String getVAT() {
        return vat;
    }

}
