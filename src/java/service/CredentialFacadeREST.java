/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import HongdaRestsw.Credential;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author hongda
 */
@Stateless
@Path("hongdarestsw.credential")
public class CredentialFacadeREST extends AbstractFacade<Credential> {

    @PersistenceContext(unitName = "FIT5046Assignment1PU")
    private EntityManager em;

    public CredentialFacadeREST() {
        super(Credential.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Credential entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Credential entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Credential find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Credential> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    @GET
    @Path("findByUsername/{username}")
    @Produces("application/json")
    public List<Credential> findByUsername(@PathParam("username") String username){
        Query query = em.createNamedQuery("Credential.findByUsername");
        query.setParameter("username", username);
        return query.getResultList();
    } 
 
    @GET
    @Path("findByUserId/{userId}")
    @Produces("application/json")
    public List<Credential> findByUserId(@PathParam("userId") Integer userId){
        Query query = em.createNamedQuery("Credential.findByUserId");
        query.setParameter("userId", userId);
        return query.getResultList();
    } 

    @GET
    @Path("findByPassword/{password}")
    @Produces("application/json")
    public List<Credential> findByPassword(@PathParam("password") String password){
        Query query = em.createNamedQuery("Credential.findByPassword");
        query.setParameter("password", password);
        return query.getResultList();
    } 
    
    @GET
    @Path("findBySignUpDate/{signUpDate}")
    @Produces("application/json")
    public List<Credential> findBySignUpDate(@PathParam("signUpDate") String signUpDate) throws ParseException{
        Query query = em.createNamedQuery("Credential.findBySignUpDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date registerDate = dateFormat.parse(signUpDate);
        query.setParameter("signUpDate", registerDate);
        return query.getResultList();
    }     
    
}
