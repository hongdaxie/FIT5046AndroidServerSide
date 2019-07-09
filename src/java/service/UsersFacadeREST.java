/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import HongdaRestsw.Users;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
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
@Path("hongdarestsw.users")
public class UsersFacadeREST extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "FIT5046Assignment1PU")
    private EntityManager em;

    public UsersFacadeREST() {
        super(Users.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Users entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Users entity) {
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
    public Users find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("findByName/{name}")
    @Produces("application/json")
    public List<Users> findByName(@PathParam("name") String name){
        Query query = em.createNamedQuery("Users.findByName");
        query.setParameter("name", name);
        return query.getResultList();
    }
    
  
    @GET
    @Path("findBySurname/{surname}")
    @Produces("application/json")
    public List<Users> findBySurname(@PathParam("surname") String surname){
        Query query = em.createNamedQuery("Users.findBySurname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @GET
    @Path("findByEmail/{email}")
    @Produces("application/json")
    public List<Users> findByEmail(@PathParam("email") String email){
        Query query = em.createNamedQuery("Users.findByEmail");
        query.setParameter("email", email);
        return query.getResultList();
    }

    @GET
    @Path("findByDob/{dob}")
    @Produces("application/json")
    public List<Users> findByDob(@PathParam("dob") String dob) throws ParseException{
        Query query = em.createNamedQuery("Users.findByDob");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = dateFormat.parse(dob);
        query.setParameter("dob", dateOfBirth);
        return query.getResultList();
    }

    @GET
    @Path("findByHeight/{height}")
    @Produces("application/json")
    public List<Users> findByHeight(@PathParam("height") Integer height){
        Query query = em.createNamedQuery("Users.findByHeight");
        query.setParameter("height", height);
        return query.getResultList();
    }

    @GET
    @Path("findByWeight/{weight}")
    @Produces("application/json")
    public List<Users> findByWeight(@PathParam("weight") Double weight){
        Query query = em.createNamedQuery("Users.findByWeight");
        query.setParameter("weight", weight);
        return query.getResultList();
    }

    @GET
    @Path("findByGender/{gender}")
    @Produces("application/json")
    public List<Users> findByGender(@PathParam("gender") String gender){
        Query query = em.createNamedQuery("Users.findByGender");
        query.setParameter("gender", gender);
        return query.getResultList();
    }

    @GET
    @Path("findByAddress/{address}")
    @Produces("application/json")
    public List<Users> findByAddress(@PathParam("address") String address){
        Query query = em.createNamedQuery("Users.findByAddress");
        query.setParameter("address", address);
        return query.getResultList();
    }

    @GET
    @Path("findByPostcode/{postcode}")
    @Produces("application/json")
    public List<Users> findByPostcode(@PathParam("postcode") String postcode){
        Query query = em.createNamedQuery("Users.findByPostcode");
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }

    @GET
    @Path("findByLevelOfActivity/{levelOfActivity}")
    @Produces("application/json")
    public List<Users> findByLevelOfActivity(@PathParam("levelOfActivity") Integer levelOfActivity){
        Query query = em.createNamedQuery("Users.findByLevelOfActivity");
        query.setParameter("levelOfActivity", levelOfActivity);
        return query.getResultList();
    }

    @GET
    @Path("findByStepsPerMile/{stepsPerMile}")
    @Produces("application/json")
    public List<Users> findByStepsPerMile(@PathParam("stepsPerMile") Integer stepsPerMile){
        Query query = em.createNamedQuery("Users.findByStepsPerMile");
        query.setParameter("stepsPerMile", stepsPerMile);
        return query.getResultList();
    }
     
    // DYNAMIC query using a combination of two attributes: name and surname
    @GET
    @Path("findByNameAndSurname/{name}/{surname}")
    @Produces("application/json")
    public List<Users> findByNameAndSurname(@PathParam("name") String name, @PathParam("surname") String surname){
        TypedQuery query = em.createQuery("SELECT u FROM Users u WHERE u.name=:name AND u.surname=:surname",Users.class);
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        return query.getResultList();
    }
    
    // calculates the calories burned per step for a user
    // ONE parameter (user id)
    // return the calories burned per step 
    @GET
    @Path("calculateBurnedPerStep/{userId}")
    @Produces("text/plain") 
    public Double calculateBurnedPerStep(@PathParam("userId") Integer userId){
        TypedQuery query = em.createQuery("SELECT u from Users u where u.id =:id", Users.class);
        query.setParameter("id", userId);
        List<Users> queryList = query.getResultList();
        BigDecimal weightDecimal = queryList.get(0).getWeight();
        Double weightPound = weightDecimal.doubleValue();
        Integer stepsPerMile = queryList.get(0).getStepsPerMile();
        Double caloriesBurnedPerStep = weightPound * 0.49 / stepsPerMile;
        return Math.round(caloriesBurnedPerStep*1000.0)/1000.0;
    }
    
    @GET
    @Path("calculateBMR/{userId}")
    @Produces("text/plain") 
    public Double calculateBMR(@PathParam("userId") Integer userId){
        TypedQuery query = em.createQuery("SELECT u from Users u where u.id =:id", Users.class);
        query.setParameter("id", userId);
        List<Users> queryList = query.getResultList();
        Double BMR = calBMR(queryList);
        return BMR;
    }
    
    //This method is to calculate BMR from a List of Users object
    public Double calBMR(List<Users> queryList){
        //get weight by kilograms
        BigDecimal weightDecimal = queryList.get(0).getWeight();
        Double weightPound = weightDecimal.doubleValue();
        Double weight = weightPound * 0.454;
        //get height in centimetres 
        Integer height = queryList.get(0).getHeight();
        String gender = queryList.get(0).getGender();
        //get age
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = queryList.get(0).getDob();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dob);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; //month start with 0
        int date = calendar.get(Calendar.DATE);
        LocalDate birthday = LocalDate.of(year, month, date);
        LocalDate now = LocalDate.now();
        Period diff = Period.between(birthday, now);
        Integer age = diff.getYears();
        //calculate BMR
        Double BMR = 0.0;
        if(("male").equals(gender.toLowerCase())){
            BMR = (13.75 * weight) + (5.003 * height) - (6.755 * age) +66.5;
        }else if(("female").equals(gender.toLowerCase())){
            BMR = (9.563 * weight) + (1.85 * height) - (4.676 * age) + 655.1; 
        }
        return BMR;
    }
    
    
    
    // this method is to calculate total calories consumed for the user for that day
    @GET
    @Path("calculateDailyCaloriesBurned/{userId}")
    @Produces("text/plain") 
    public Double calculateDailyCaloriesBurned(@PathParam("userId") Integer userId){
        TypedQuery query = em.createQuery("SELECT u from Users u where u.id =:id", Users.class);
        query.setParameter("id", userId);
        List<Users> queryList = query.getResultList();
        Double BMR = calBMR(queryList);
        Double totalCalories = 0.0;
        Integer activeLevel = (int) queryList.get(0).getLevelOfActivity();
        switch(activeLevel){
            case 1:
                totalCalories = Math.round(BMR * 1.2*1000.0)/1000.0;
                break;
            case 2:
                totalCalories = Math.round(BMR * 1.375 * 1000.0) / 1000.0;
                break;
            case 3:
                totalCalories = Math.round(BMR * 1.55 * 1000.0) / 1000.0;
                break;
            case 4:
                totalCalories = Math.round(BMR * 1.725 * 1000.0) / 1000.0;
                break;
            case 5:
                totalCalories = Math.round(BMR * 1.9 * 1000.0) / 1000.0;
                break;
        }
        return totalCalories;
    }    
    
}





















