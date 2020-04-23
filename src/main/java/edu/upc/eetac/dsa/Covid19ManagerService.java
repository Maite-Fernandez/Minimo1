package edu.upc.eetac.dsa;

//Swagger Imports
import io.swagger.annotations.Api;

//Log4j imports
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/Covid19Manager", description = "Endpoint to Covid19 Manager Service")
@Path("/Covid19Manager")
public class Covid19ManagerService {

    static final Logger logger = Logger.getLogger(Covid19ManagerService.class);
    private Covid19Manager manager;

    public Covid19ManagerService() {
        //Configuring Log4j, location of the log4j.properties file and must always be inside the src folder
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        this.manager = Covid19ManagerImpl.getInstance();
        if (this.manager.getNumOutbreaks() == 0) {

            //Adding outbreaks
            String outbreakId1 = this.manager.addOutbreak();
            String outbreakId2 = this.manager.addOutbreak();

            //Adding cases to the outbreaks
            this.manager.addCase(outbreakId1,"mary","smith","04/06/1989",11032020,2,"female","marysmith@gmail.com","+34663307504","Imaginary Street 7",2);
            this.manager.addCase(outbreakId1,"peter","pan","04/07/1970",12032020,1,"male","peterpan@gmail.com","+34663301424","Neverland Street 8",0);
            this.manager.addCase(outbreakId2,"john","wick","05/11/1994",7232020,3,"male","johnwick@gmail.com","+34663485504","Lollipop Street 11",1);
            this.manager.addCase(outbreakId2,"lisa","simpson","12/23/1959",5042020,1,"female","lisasimpson@gmail.com","+34669487504","Candy Street 19",2);
            this.manager.addCase(outbreakId2,"mike","johnson","11/14/1979",11042020,2,"male","mikejohnson@gmail.com","+34663395604","Box Street 7",1);
        }
    }
    //When multiple GET, PUT, POSTS & DELETE EXIST on the same SERVICE, path must be aggregated
    @GET
    @ApiOperation(value = "Get all Outbreaks", notes = "Retrieves the list of Outbreaks")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Outbreak.class, responseContainer="List"),
    })
    @Path("/listOutbreaks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOutbreaks() {

        List<Outbreak> outbreaks = this.manager.getOutbreakList();

        GenericEntity<List<Outbreak>> entity = new GenericEntity<List<Outbreak>>(outbreaks) {};
        return Response.status(201).entity(entity).build()  ;
    }
    //New Outbreak
    @POST
    @ApiOperation(value = "Create a new outbreak", notes = "Adds a new outbreak and returns the outbreak")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Outbreak.class),
    })
    @Path("/newOutbreak")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newOutbreak() {
        String outbreakId = this.manager.addOutbreak();
        return Response.status(201).entity(this.manager.getOutbreak(outbreakId)).build();
    }

    //Cases list of an outbreak sorted by risk
    @GET
    @ApiOperation(value = "Get cases sorted by risk", notes = "Retrieves the list of cases of a given outbreak sorted by risk")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Case.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Outbreak not found"),
    })
    @Path("/listCasesByRisk/{outbreakId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCasesByRisk(@PathParam("outbreakId") String outbreakId) {
        if(this.manager.getOutbreak(outbreakId)==null) return Response.status(404).build();
        List<Case> cases = this.manager.getSortedCases(outbreakId);
        GenericEntity<List<Case>> entity = new GenericEntity<List<Case>>(cases) {};
        return Response.status(201).entity(entity).build()  ;
    }


    //Add a case to an outbreak
    @PUT
    @ApiOperation(value = "Add cases to an outbreak", notes = "Edits an existing outbreak")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Outbreak.class),
            @ApiResponse(code = 404, message = "Outbreak not found"),
    })
    @Path("/addCaseToOutbreak")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProductToOutbreak(CaseToOutbreak caseToOutbreak) {
        logger.info("AddCaseToOutbreak [outbreakId= "+caseToOutbreak.getOutbreakId()+ ", name= "+caseToOutbreak.getInsertCase().getName()+ "]");
       String outbreak = caseToOutbreak.getOutbreakId();
        Case insertCase=caseToOutbreak.getInsertCase();
        insertCase.setId( RandomUtils.getId());
        String caseId = this.manager.addCase(outbreak,insertCase);
        if (caseId == null) return Response.status(404).build();
        return Response.status(201).build();
    }
    //Ad-hoc object
    public static class CaseToOutbreak{
        String outbreakId;
        Case insertCase;
        //Empty constructor
        public  CaseToOutbreak(){}
        //Setters and Getters
        public String getOutbreakId() { return outbreakId; }
        public void setOutbreakId(String outbreakId) { this.outbreakId = outbreakId; }
        public Case getInsertCase() { return insertCase; }
        public void setInsertCase(Case insertCase) { this.insertCase = insertCase; }
    }

}
