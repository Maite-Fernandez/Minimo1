package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;

import java.util.*;

public class Covid19ManagerImpl implements Covid19Manager {
    //Basic Covid19Manager values
    private static Covid19Manager instance;
    private List<Case> caseList;
    private HashMap<String, Outbreak> mapOutbreak;
    private static Logger log = Logger.getLogger(Covid19ManagerImpl.class);

    //Private Constructor for Singleton
    private Covid19ManagerImpl() {
        this.caseList = new LinkedList<Case>();
        this.mapOutbreak = new HashMap<String, Outbreak>();
    }

    //Singleton implementation for the instance of the ProductManager
    public static Covid19Manager getInstance() {
        if (instance == null) {
            instance = new Covid19ManagerImpl();
        }
        return instance;
    }

    @Override
    public String addOutbreak() {
        log.info("Add a new outbreak ");
        Outbreak o = new Outbreak();
        this.mapOutbreak.put(o.getId(), o);
        log.info("New outbreak added");
        return o.getId();
    }

    @Override
    public String addCase(String outbreakId, Case c) {
        this.caseList.add(c);
        this.mapOutbreak.get(outbreakId).addCase(c);
        return c.getId();
    }

    @Override
    public String addCase(String outbreakId, String name, String surname, String birthdate, int reportdate, int risklevel, String gender, String email, String telephone, String address, int classification) {
        Outbreak o = this.mapOutbreak.get(outbreakId);
        Case c = new Case(name, surname, birthdate, reportdate, risklevel, gender, email, telephone, address, classification);
        log.info("Add a new case to outbreak (" + outbreakId + ")");
        if (o != null) {
            log.info("Case added");
            this.addCase(outbreakId,c);
            return c.getId();
        } else {
            log.error("Not found outbreak " + outbreakId);
            return null;
        }
    }

    @Override
    public Case getCase(String caseId) {
        int index = caseList.size();
        log.info("Get Case(" + caseId + ")");
        for (int i = 0; i < caseList.size(); i++) {
            if (caseList.get(i).getId().equals(caseId)) {
                index = i;
            }
        }
        if (index != caseList.size()) {
            return caseList.get(index);
        } else {
            log.error("Not found Case " + caseId);
            return null;
        }
    }

    @Override
    public Outbreak getOutbreak(String outbreakId) {
        Outbreak o = this.mapOutbreak.get(outbreakId);
        log.info("Get Outbreak(" + outbreakId + ")");
        if (o != null) {
            log.info("Get Outbreak(" + outbreakId + "): " + o);
        } else {
            log.error("Not found Outbreak " + outbreakId);
            return null;
        }
        return o;
    }

    @Override
    public int getNumOutbreaks() {
        return this.mapOutbreak.size();
    }

    @Override
    public List<Outbreak> getOutbreakList() {
        return new LinkedList<Outbreak>(mapOutbreak.values());
    }

    @Override
    public void clearResources() {
        this.caseList.clear();
        this.mapOutbreak.clear();
    }

    @Override
    public List<Case> getSortedCases(String outbreakId) {
        List<Case> cases = this.mapOutbreak.get(outbreakId).getListCases();
        if (cases != null) {
            //Comparator for report date
            Comparator<Case> byReportDate =
                    Comparator.comparingInt(Case::getReportdate); //HELP TONI!!
            cases.sort(byReportDate);
            log.info("List of cases by report date in descending order:" + cases.toString());
            //Comparator for classification
            Comparator<Case> byClassification =
                    Comparator.comparingInt(Case::getClassification);
            cases.sort(byClassification);
            log.info("List of cases by classification in descending order: " + cases.toString());

            //PREVIOUS UGLIER METHODS
          /*  class SortbyReportDate implements Comparator<Case> {
                public int compare(Case c1, Case c2) {
                    //Compare report dates in descending order
                    return Integer.compare(c1.getReportdate(), c2.getReportdate());
                    //DATE OBJECT FOR MORE ADVANCED VERSION

                    public static Date createDate(String date) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        Date ret = null;
                        try {
                            ret = sdf.parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                            return ret;
                    }

                }
            }*/
            /*class SortbyClassification implements Comparator<Case> {
                public int compare(Case c1, Case c2) {
                    //Compare classification in descending order
                    return c1.getClassification() - c2.getClassification();
                }
            }
            Comparator c1 = Collections.reverseOrder(new SortbyClassification());
            Collections.sort(cases, c1);

            log.info("List of cases by classification in descending order: " + cases.toString());*/

            return cases; //200 OK PETITION
        } else {
            log.warn("The list of cases is empty");
            return null; //404 (Empty Table)
        }
    }
}
