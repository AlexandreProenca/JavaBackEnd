/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.borayu.resource;

import com.borayu.dao.jdbc.JDBCUserDAO;
import com.borayu.model.User;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author Borayu04
 */
@Path("/login")
public class LoginResource {
    
    private final JDBCUserDAO dataBase = new JDBCUserDAO();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkLogin(User user) {
        try {
            user = dataBase.checkLogin(user);
        } catch (Exception e) {
            throw new WebApplicationException(e, 403);
        }
        return Response.status(200).entity(user).build();
    }
}
