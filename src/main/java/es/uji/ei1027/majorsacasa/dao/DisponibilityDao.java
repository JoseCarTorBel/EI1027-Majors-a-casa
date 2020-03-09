package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Company;
import es.uji.ei1027.majorsacasa.model.Disponibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class DisponibilityDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }


    /**
     * add a Disponibility
     * @param disponibility, dniElderlyPeople, dniVolunteer
     */
    public void addDisponibility(Disponibility disponibility,String dniElderlyPeople, String dniVolunteer){
        jdbcTemplate.update("INSERT INTO disponibility VALUES (?,?,?,?,?,?)",
                dniVolunteer,dniElderlyPeople,disponibility.getDayOfWeek(),disponibility.getInitialTime(),disponibility.getFinalTime(),disponibility.isOpen()
                );
    }

    /**
     * update a Disponibility
     * @param disponibility, dniElderlyPeople, dniVolunteer
     */
    public void updateDisponibility(Disponibility disponibility,String dniElderlyPeople, String dniVolunteer){
        jdbcTemplate.update("UPDATE disponibility SET dayofweek=?, initialtime=?, finaltime=?, open=? WHERE dnivolunteer=? AND dnielderlypeople=?",
                disponibility.getDayOfWeek(),disponibility.getInitialTime(),disponibility.getFinalTime(),disponibility.isOpen(),dniVolunteer,dniElderlyPeople
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



}
