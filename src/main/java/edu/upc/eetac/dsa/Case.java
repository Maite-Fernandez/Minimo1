package edu.upc.eetac.dsa;

public class Case {
   String name;
   String surname;
   String id;
   String birthdate;
   int reportdate;
   int risklevel; //Can be high(3)/medium(2)/low(1)
   String gender;
   String email;
   String telephone;
   String address;
   int classification; //Can be notacase(0)/suspect(1)/confirmed(2)

    //Public constructor to initialize product
    public Case(String name,String surname,String birthdate,int reportdate,int risklevel,String gender,String email,String telephone,String address,int classification){
        this.name=name;
        this.surname=surname;
        this.birthdate=birthdate;
        this.id=RandomUtils.getId();
        this.reportdate=reportdate;
        this.address=address;
        this.email=email;
        this.telephone=telephone;
        this.gender=gender;
        this.risklevel=risklevel;
        this.classification=classification;
    }

    //Empty constructor for the API REST
    public Case(){
    }

    //Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getBirthdate() { return birthdate; }
    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }
    public int getReportdate() { return reportdate; }
    public void setReportdate(int reportdate) { this.reportdate = reportdate; }
    public int getRisklevel() { return risklevel; }
    public void setRisklevel(int risklevel) { this.risklevel = risklevel; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender= gender; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int getClassification() { return classification; }
    public void setClassification(int classification) { this.classification = classification; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }


    //Returns in string format the product
    @Override
    public String toString(){
        return "Case [id="+this.getId()+ ", name=" + this.getName() + ", surname=" + this.getSurname()+ ", birthday="+this.getBirthdate()+
                ", gender="+this.getGender()+", telephone="+this.getTelephone()+ ", email="+ this.getEmail()+ ", address=" + this.getAddress()+
                ", reportdate=" + this.getReportdate()+ ", classification [notacase(0)/suspect(1)/confirmed(2)]=" +this.getClassification()+ ", risklevel [high(3)/medium(2)/low(1)] ="+ this.getRisklevel()+"]" ;
    }
}


