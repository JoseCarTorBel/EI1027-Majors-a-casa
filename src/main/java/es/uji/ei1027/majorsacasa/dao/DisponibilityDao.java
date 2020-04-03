package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Company;
import es.uji.ei1027.majorsacasa.model.Disponibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

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
        jdbcTemplate.update("INSERT INTO disponibility VALUES (?,?,?,?,?,?)",
                disponibility.getDniVolunteer(),disponibility.getDniElderlyPeople(),disponibility.getDayOfWeek(),disponibility.getInitialTime(),disponibility.getFinalTime(),disponibility.isOpen()
                );
    }

    /**
     * update a Disponibility
     * @param disponibility
     */
    public void updateDisponibility(Disponibility disponibility){
        jdbcTemplate.update("UPDATE disponibility SET dayofweek=?, initialtime=?, finaltime=?, open=? WHERE dnivolunteer=? AND dnielderlypeople=?",
                disponibility.getDayOfWeek(),disponibility.getInitialTime(),disponibility.getFinalTime(),disponibility.isOpen(),disponibility.getDniVolunteer(),disponibility.getDniElderlyPeople()
        );
    }

    /**
     * remove a Disponibility
     * @param dniElderlyPeople, dniVolunteer
     */
    public void removeDisponibility(String dniElderlyPeople,String dniVolunteer){
        jdbcTemplate.update("DELETE FROM disponibility WHERE dnielderlypeople=?, dnivolunteer=?",dniElderlyPeople,dniVolunteer);
    }

    /**
     * get a disponibility
     * @param dniElderlyPeople, dniVolunteer
     * @return Disponibility
     */
    public Disponibility getDisponibility(String dniElderlyPeople,String dniVolunteer){
        try{
            return  jdbcTemplate.queryForObject("SELECT * FROM disponibility WHERE dnielderlypeople=?, dnivolunteer=?",
                                    new DisponibilityRowMapper(),dniElderlyPeople,dniVolunteer);
        }catch(EmptyResultDataAccessException e) {
         return null;
        }
    }
    /**
     * get a disponibility
     * @param dniVolunteer
     * @return Disponibility
     */
    public Disponibility getDisponibility(String dniVolunteer){
        try{
            return  jdbcTemplate.queryForObject("SELECT * FROM disponibility WHERE dnivolunteer=?;",
                    new DisponibilityRowMapper(),dniVolunteer);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }




}
