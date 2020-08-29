/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.AnimalNoDB;

@Path("animals")
public class AnimalsDemo {

    @Context
    private UriInfo context;

    public AnimalsDemo() {
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJson() {
        //TODO return proper representation object
        return "Vuf";
    }

    @GET
    @Path("/animal_list")
    @Produces(MediaType.APPLICATION_JSON)
    public String fleredyr() {
        return "[\"Dog\", \"Cat\", \"Mouse\", \"Bird\"]";
    }
    @GET
    @Path("/animal")
    @Produces(MediaType.APPLICATION_JSON)
    public String animal() {
        AnimalNoDB dyr = new AnimalNoDB("dog","bark");
        
        return new Gson().toJson(dyr);
    }
    
    
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
