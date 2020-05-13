package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Company;
import es.uji.ei1027.majorsacasa.model.Disponibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class DisponibilityDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }


    /**
     * add a Disponibility
     * @param disponibility
     */
    public void addDisponibility(Disponibility disponibility){
        jdbcTemplate.update("INSERT INTO disponibility VALUES (?,?,?,?,?,?,?)",
                disponibility.getDniVolunteer(),disponibility.getDniElderlyPeople(),disponibility.getDayOfWeek(),disponibility.getHour(),disponibility.getInitialTime(),disponibility.getFinalTime(),'P'
                );
    }

    /**
     * update a Disponibility, servira para adjudicar a un elderlypeople
     * @param disponibility
     */
    public void updateDisponibility(Disponibility disponibility){
        System.out.println(disponibility.toString());
        jdbcTemplate.update("UPDATE disponibility SET dnielderlypeople=?,dayofweek=?, hour=?, initialtime=?, finaltime=?, state=? WHERE dnivolunteer=? AND dayOfWeek=?",
               disponibility.getDniElderlyPeople(), disponibility.getDayOfWeek(),disponibility.getHour(),disponibility.getInitialTime(),disponibility.getFinalTime(),disponibility.getState(),disponibility.getDniVolunteer(),disponibility.getDayOfWeek()
        );
    }




    /**
     * remove a Disponibility
     * @param dayOfWeek, dniVolunteer
     */
    public void removeDisponibility(Integer dayOfWeek,String dniVolunteer){
        jdbcTemplate.update("DELETE FROM disponibility WHERE dayOfWeek=? AND dnivolunteer=?",dayOfWeek,dniVolunteer);
    }

    /**
     * get a disponibility
     * @param dayOfWeek, dniVolunteer
     * @return Disponibility
     */
    public Disponibility getDisponibility(Integer dayOfWeek,String dniVolunteer){
        try{
            return  jdbcTemplate.queryForObject("SELECT * FROM disponibility WHERE dnivolunteer=? AND dayofweek=?",
                                    new DisponibilityRowMapper(),dniVolunteer,dayOfWeek);
        }catch(EmptyResultDataAccessException e) {
         return null;
        }
    }
    /**
     * get a disponibility
     * @param dniVolunteer
     * @return Disponibility
     */
    public List<Disponibility> getDisponibilitysAccepted(String dniVolunteer){
        try{
            return  jdbcTemplate.query("SELECT * FROM disponibility WHERE dnivolunteer=? AND state='A';",
                    new DisponibilityRowMapper(),dniVolunteer);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * get a disponibility
     * @param dniVolunteer
     * @return Disponibility
     */
    public List<String>     getDirectionsAccepted(String dniVolunteer){
        try{
            return  jdbcTemplate.query("SELECT postaddress FROM person WHERE dni IN (SELECT dnielderlypeople FROM disponibility WHERE dnivolunteer=? AND state='A')",
                    new DirectionRowMapper(),dniVolunteer);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }



    /**
     * get a disponibility
     * @param dniVolunteer
     * @return Disponibility
     */
    public List<Disponibility> getDisponibilitys(String dniVolunteer){
        try{
            return  jdbcTemplate.query("SELECT * FROM disponibility WHERE dnivolunteer=?",
                    new DisponibilityRowMapper(),dniVolunteer);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * get a disponibility with the dnielderlypeople
     * @param dniElderlyPeople
     * @return Disponibility
     */
    public List<Disponibility> getDisponibilitiesElderly(String dniElderlyPeople){
        try{
            return  jdbcTemplate.query("SELECT * FROM disponibility WHERE dnielderlypeople=?;",
                    new DisponibilityRowMapper(),dniElderlyPeople);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * get a free disponibilities
     * @param
     * @return Disponibility
     */
    public List<Disponibility> getDisponibilitiesLibres(){
        try{
            return  jdbcTemplate.query("SELECT * FROM disponibility WHERE dnielderlypeople IS NULL;",
                    new DisponibilityRowMapper());
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Get de pending disponibilities
     * @param
     * @return Disponibility
     */
    public List<Disponibility> getDisponibilitiesPendents(){
        try{
            return  jdbcTemplate.query("SELECT * FROM disponibility WHERE state='P' AND dniElderlyPeople IS NOT NULL;",
                    new DisponibilityRowMapper());
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }








}
