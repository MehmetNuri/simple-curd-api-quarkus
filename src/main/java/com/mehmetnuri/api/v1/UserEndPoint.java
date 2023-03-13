package com.mehmetnuri.api.v1;

import com.mehmetnuri.dao.UserDao;
import com.mehmetnuri.domain.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserEndPoint {

    @Inject
    UserDao userDao;

    @GET
    @Path("/")
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @GET
    @Path("/user/{id}")
    public User findUserById(@PathParam("id") Long id) {
        return userDao.findById(id);
    }

    @GET
    @Path("/user/email/{email}")
    public User findUserByEmail(@PathParam("email") String email) {
        return userDao.findByEmail(email);
    }

    @POST
    @Path("/user/save")
    public User saveUser(User user) {
        userDao.save(user);
        return user;
    }

    @PUT
    @Path("/user/update/{id}")
    public User updateUser(@PathParam("id") Long id, User user) {
        userDao.updateUser(id, user);
        return user;
    }

    @DELETE
    @Path("/user/delete/{id}")
    public void deleteUser(@PathParam("id") Long id) {
        userDao.deleteUser(id);
    }
}
