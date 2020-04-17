package edu.upc.eetac.dsa;

import java.util.LinkedList;
import java.util.List;

public class Outbreak {
    //Basic order value
    private  String id;

    //Order products
    private List<Case> listCases=null;

    //Public constructor to initialize order
    public Outbreak(){

        this.id= RandomUtils.getId();
        this.listCases= new LinkedList<Case>();
    }

    //Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public List<Case> getListCases() { return listCases; }
    public void setListCases(List<Case> listCases) { this.listCases = listCases; }
    public void addCase(Case c){this.listCases.add(c);}

    //Returns in string format the order
    @Override
    public String toString(){ return "Outbreak [id=" + this.getId() + "]" ; }
}
