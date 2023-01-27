package org.acme.openapi.swaggerui;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

    private Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public FruitResource() {
        fruits.add(new Fruit(1, "Apple", "Winter fruit"));
        fruits.add(new Fruit(2, "Pineapple", "Tropical fruit"));
        fruits.add(new Fruit(3, "Orange", "Tropical fruit"));
    }

    @GET
    public Set<Fruit> list() {
        return fruits;
    }

    @GET
    @Path("{id}")
    public Fruit list(@PathParam("id") Integer id) {
        return fruits.stream().filter(f -> f.id.equals(id)).findFirst().orElse(null);
    }

    @POST
    public Set<Fruit> add(Fruit fruit) {
        fruits.add(fruit);
        return fruits;
    }

    @DELETE
    @Path("{id}")
    public Set<Fruit> delete(@PathParam("id") Integer id) {
        fruits.removeIf(existingFruit -> existingFruit.id.equals(id));
        return fruits;
    }
}
