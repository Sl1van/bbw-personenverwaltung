package ch.bbw.personenverwaltung.controller;

import ch.bbw.personenverwaltung.domain.Person;
import ch.bbw.personenverwaltung.service.PersonService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
public class PersonController {

    @Inject
    private PersonService personService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public List<Person> listAll() {
        return personService.findAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> list() {
        return personService.findPerson();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Person add(Person entry) {
        return personService.createPerson(entry);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void Person(@PathParam("id") long id) {
        personService.deletePerson(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void edit(@PathParam("id") long id, Person entry) {
        personService.editPerson(id, entry);
    }
}