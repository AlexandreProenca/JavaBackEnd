/*
 * Projeto Borayu
 * RestFull Server  * 
 */
package com.borayu.resource;

import com.borayu.dao.jdbc.JDBCUserDAO;
import com.borayu.model.User;
import com.google.gson.Gson;
import java.net.URI;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


/**
 *
 * @author alexandre.proenca@borayu.com
 */
@Path("/users")
public class UserResource {

    //private IUserEntity userEntityDao;
    
    private final JDBCUserDAO dataBase = new JDBCUserDAO();

    //private JpaUserDao jpaUser = new JpaUserDao();
    //public UserResource() {
    //    this.userEntityDao = DaoFactory.getInstance().getUserJpa();
    //}

    
    
    @GET
    @Path("/listarXml")
    @Produces("application/xml")
    public List<User> listXml() {
        return dataBase.getList();
    }

    @GET
    @Produces("application/json")
    public String listJson() {
        //return userEntityDao.getList();
        Gson gson = new Gson();
        return gson.toJson(dataBase.getList());
        
    }

    @POST
    public Response addUser(User user) {
        try {
            dataBase.add(user);
        } catch (Exception e) {
            throw new WebApplicationException(e, 403);
        }
        Response response = Response.created(URI.create("/" + user.getId())).build();
        return response;
    }

    @DELETE
    @Path("{id}")
    public void removeUser(@PathParam("id") Long id) {
        try {
            dataBase.remove(id);
        } catch (Exception e) {
            throw new WebApplicationException(e, 404);
        }
    }

    @GET
    @Path(value = "{id}")
    @Produces(value = "application/json")
    public String getUser(@PathParam("id") Long id) {
        User user = dataBase.get(id);
        Gson gson = new Gson();
        return gson.toJson(user);

    }
    
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id,User user) {
        user.setId(id);
        try {
            dataBase.update(user);
        } catch (Exception e) {
            throw new WebApplicationException(e, 403);
        }
        Response response = Response.created(URI.create("/" + user.getId())).build();
        return response;
    }
}
