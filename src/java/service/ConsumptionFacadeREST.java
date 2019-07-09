/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import HongdaRestsw.Consumption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author hongda
 */
@Stateless
@Path("hongdarestsw.consumption")
public class ConsumptionFacadeREST extends AbstractFacade<Consumption> {

    @PersistenceContext(unitName = "FIT5046Assignment1PU")
    private EntityManager em;

    public ConsumptionFacadeREST() {
        super(Consumption.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Consumption entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Consumption entity) {
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
    public Consumption find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("findByDate/{date}")
    @Produces("application/json")
    public List<Consumption> findByDate(@PathParam("date") String dateStr)throws ParseException{
        Query query = em.createNamedQuery("Consumption.findByDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateStr);
        query.setParameter("date", date);
        return query.getResultList();
    }
  
    @GET
    @Path("findByUserId/{userId}")
    @Produces("application/json")
    public List<Consumption> findByUserId(@PathParam("userId") Integer userId){
        Query query = em.createNamedQuery("Consumption.findByUserId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }    
    
    @GET
    @Path("findByFoodId/{foodId}")
    @Produces("application/json")
    public List<Consumption> findByFoodId(@PathParam("foodId") Integer foodId){
        Query query = em.createNamedQuery("Consumption.findByFoodId");
        query.setParameter("foodId", foodId);
        return query.getResultList();
    }    
    
    @GET
    @Path("findByQuantity/{quantity}")
    @Produces("application/json")
    public List<Consumption> findByQuantity(@PathParam("quantity") Integer quantity){
        Query query = em.createNamedQuery("Consumption.findByQuantity");
        query.setParameter("quantity", quantity);
        return query.getResultList();
    }
    
    
    // DYNAMIC querying two tables using a combination of two attributes
    @GET
    @Path("findByQuantityAndUserName/{quantity}/{userName}")
    @Produces("application/json")
    public List<Consumption> findByQuantityAndUserName(@PathParam("quantity") Integer quantity, @PathParam("userName") String userName){
        TypedQuery query = em.createQuery("Select c FROM Consumption c,Users u WHERE c.quantity=:quantity AND u.name=:userName AND c.userId.id = u.id", Consumption.class);
        query.setParameter("quantity", quantity);
        query.setParameter("userName", userName);
        return query.getResultList();
    }
    
    
    // STATIC querying two tables using a combination of two attributes
    @GET
    @Path("findByQuantityAndFat/{quantity}/{fat}")
    @Produces("application/json")
    public List<Consumption> findByQuantityAndFat(@PathParam("quantity") Integer quantity, @PathParam("fat") Integer fat){
        Query query = em.createNamedQuery("Consumption.findByQuantityAndFat");
        query.setParameter("quantity", quantity);
        query.setParameter("fat", fat);
        return query.getResultList();
    }
   
    // calculate the total calories consumed
    @GET
    @Path("calculateTotalCaloriesConsumed/{userId}/{date}")
    @Produces("text/plain")
    public Integer calculateTotalCaloriesConsumed(@PathParam("userId") Integer userId,@PathParam("date") String dateStr) throws ParseException{
        TypedQuery query = em.createQuery("SELECT c from Consumption c where c.userId.id = :userId AND c.date = :date", Consumption.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateStr);
        query.setParameter("userId", userId);
        query.setParameter("date", date);
        List<Consumption> queryList = query.getResultList();
        Integer totalCaloriesConsumed = 0;
        for (Consumption consumption : queryList) {
            Integer quantity = consumption.getQuantity();
            Integer calories = consumption.getFoodId().getCalorieAmount();
            totalCaloriesConsumed += calories * quantity;
        }
        return totalCaloriesConsumed;
    }
    
    
}








