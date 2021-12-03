package ch.bbw.controller;

import ch.bbw.domain.Person;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/person")
public class PersonController {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public List<Person> listAll() {
        authenticationService.checkJWT();
        return entryService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all Entries of the User with the id", description = "Lists all Entries of the User with the id of the user that sent the request.")
    public List<Person> list() {
        authenticationService.checkJWT();
        return entryService.findEntries(authenticationService.getIdFromJWT());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Entry", description = "The newly created entry is returned. The id may not be passed.")
    public Person add(Person entry) {
        authenticationService.checkJWT();
        return entryService.createEntry(entry);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Operation(summary = "Delete an Entry", description = "Deletes an existing Entry with the id given")
    public void Person(@PathParam("id") long id) {
        entryService.deleteEntry(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Operation(summary = "Updates an Entry", description = "Updates an existing Entry with the id given")
    public void edit(@PathParam("id") long id, Entry entry) {
        authenticationService.checkJWT();
        entryService.editEntry(id, entry);
    }
}