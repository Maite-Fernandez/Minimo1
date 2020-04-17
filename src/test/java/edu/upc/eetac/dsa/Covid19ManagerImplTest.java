package edu.upc.eetac.dsa;
import org.apache.log4j.Logger;
//Junit 4.13
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class Covid19ManagerImplTest {

    //Log4j Logger initialization
    private static Logger log = Logger.getLogger(Covid19ManagerImplTest.class);

    //ProductManager
    public Covid19Manager manager = null;

    //Data structures
    String outbreakId1;
    String outbreakId2;

    List<Outbreak> outbreakList;

    //SetUp
    @Before
    public void setUp() {
        //Configuring Log4j
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        log.debug("Debug Test Message!");
        log.info("Info Test Message!");
        log.warn("Warning Test Message!");
        log.error("Error Test Message!");

        //Instancing ProductManager Implementation
        manager = Covid19ManagerImpl.getInstance();

        //Initializing Test Outbreaks
        outbreakId1 = this.manager.addOutbreak();
        outbreakId2 = this.manager.addOutbreak();

    }

    //Tests
    @Test
    //Test to add a new outbreak
    public void addOutbreak() {
        //Check the initial number of outbreaks
        Assert.assertEquals(2, this.manager.getNumOutbreaks());
        //Adding Outbreak
        String outbreakId = this.manager.addOutbreak();
        //Check that the outbreak has been added
        Assert.assertEquals(3, this.manager.getNumOutbreaks());
    }

    @Test
    //Test to add a case to an outbreak
    public void addCase() {
        //Check the number of cases in one of the empty outbreaks
        Assert.assertEquals(0, this.manager.getOutbreak(outbreakId1).getListCases().size());
        //Adding Case
        this.manager.addCase(outbreakId1,"mary","smith","04/06/1989",07042020,2,"female","marysmith@gmail.com","+34663307504","Imaginary Street 7",2);
        Assert.assertEquals(1, this.manager.getOutbreak(outbreakId1).getListCases().size());

    }

    //Teardown
    @After
    public void tearDown() {
        this.manager.clearResources();
    }
}
