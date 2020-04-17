package edu.upc.eetac.dsa;
import java.util.List;
public interface Covid19Manager {

     //Add a new outbreak
     String addOutbreak();

     //Add a new case
     String addCase(String outbreakId,String name,String surname,String birthdate,int reportdate,int risklevel,String gender,String email,String telephone,String address,int classification);

     //Get a case
    Case getCase(String caseId);

    //Get an outbreak
    Outbreak getOutbreak(String outbreakId);

    //Get number of outbreaks
    int getNumOutbreaks();

    //List outbreaks
    List<Outbreak> getOutbreakList();

    //List cases of an outbreak ordered by classification and then by date of the report
     List<Case> getSortedCases(String outbreakId);

     //To clear resources
     void clearResources();


}
