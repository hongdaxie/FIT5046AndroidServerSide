/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import HongdaRestsw.Report;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
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
@Path("hongdarestsw.report")
public class ReportFacadeREST extends AbstractFacade<Report> {

    @PersistenceContext(unitName = "FIT5046Assignment1PU")
    private EntityManager em;

    public ReportFacadeREST() {
        super(Report.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Report entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Report entity) {
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
    public Report find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("findByUserId/{userId}")
    @Produces("application/json")
    public List<Report> findByUserId(@PathParam("userId") Integer userId){
        Query query = em.createNamedQuery("Report.findByUserId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }    
    
    @GET
    @Path("findByDate/{date}")
    @Produces("application/json")
    public List<Report> findByDate(@PathParam("date") String dateStr)throws ParseException{
        Query query = em.createNamedQuery("Report.findByDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateStr);
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    @GET
    @Path("findByTotalCaloriesConsumed/{totalCaloriesConsumed}")
    @Produces("application/json")
    public List<Report> findByTotalCaloriesConsumed(@PathParam("totalCaloriesConsumed") Integer totalCaloriesConsumed){
        Query query = em.createNamedQuery("Report.findByTotalCaloriesConsumed");
        query.setParameter("totalCaloriesConsumed", totalCaloriesConsumed);
        return query.getResultList();
    }
    
    @GET
    @Path("findByTotalCaloriesBurned/{totalCaloriesBurned}")
    @Produces("application/json")
    public List<Report> findByTotalCaloriesBurned(@PathParam("totalCaloriesBurned") Integer totalCaloriesBurned){
        Query query = em.createNamedQuery("Report.findByTotalCaloriesBurned");
        query.setParameter("totalCaloriesBurned", totalCaloriesBurned);
        return query.getResultList();
    }


    @GET
    @Path("findByTotalStepsTaken/{totalStepsTaken}")
    @Produces("application/json")
    public List<Report> findByTotalStepsTaken(@PathParam("totalStepsTaken") Integer totalStepsTaken){
        Query query = em.createNamedQuery("Report.findByTotalStepsTaken");
        query.setParameter("totalStepsTaken", totalStepsTaken);
        return query.getResultList();
    }    
    
    @GET
    @Path("findByCalorieGoal/{calorieGoal}")
    @Produces("application/json")
    public List<Report> findByCalorieGoal(@PathParam("calorieGoal") Integer calorieGoal){
        Query query = em.createNamedQuery("Report.findByCalorieGoal");
        query.setParameter("calorieGoal", calorieGoal);
        return query.getResultList();
    }      
    

    //This method is to return total calories consumed, total calories burned and remaining calorie 
    //(based on the set goal by user id and date) 
    @GET
    @Path("makeReprot/{userId}/{date}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object makeReprot(@PathParam("userId") Integer userId, @PathParam("date") String dateStr) throws ParseException{
        TypedQuery query = em.createQuery("Select r.totalCaloriesBurned, r.totalCaloriesConsumed, r.calorieGoal FROM Report r where r.userId.id =:userId AND r.date =:date", Object[].class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateStr);
        query.setParameter("userId", userId);
        query.setParameter("date", date);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Object[] row : queryList){
            JsonObject reportObject = Json.createObjectBuilder()
                    .add("totalCaloriesBurned",(Integer) row[0])
                    .add("totalCaloriesConsumed", (Integer) row[1])
                    .add("remainingCalorie", (Integer) row[2] + (Integer) row[0] - (Integer) row[1])
                    .build();
            arrayBuilder.add(reportObject);
        }
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    
    
    @GET
    @Path("makeReprotPeriod/{userId}/{startDate}/{endDate}")
    @Produces({MediaType.APPLICATION_JSON})
    public Object makeReprotPeriod(@PathParam("userId") Integer userId, @PathParam("startDate") String startDateStr,@PathParam("endDate") String endDateStr) throws ParseException{
        TypedQuery query = em.createQuery("Select r.totalCaloriesBurned, r.totalCaloriesConsumed, r.totalStepsTaken FROM Report r where r.userId.id =:userId AND r.date >= :startDate AND r.date <= :endDate", Object[].class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse(startDateStr);
        Date endDate = dateFormat.parse(endDateStr);
        query.setParameter("userId", userId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        List<Object[]> queryList = query.getResultList();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        Integer totalCaloriesBurned = 0;
        Integer totalCaloriesConsumed = 0;
        Integer totalStepsTaken = 0;
        for (Object[] row : queryList){
            totalCaloriesBurned += (Integer) row[0];
            totalCaloriesConsumed += (Integer) row[1];
            totalStepsTaken += (Integer) row[2];
        }
        JsonObject reportObject;
        reportObject = Json.createObjectBuilder()
                .add("totalCaloriesBurned",totalCaloriesBurned)
                .add("totalCaloriesConsumed", totalCaloriesConsumed)
                .add("totalStepsTaken", totalStepsTaken)
                .build();
        arrayBuilder.add(reportObject);
        JsonArray jArray = arrayBuilder.build();
        return jArray;
    }
    

}



