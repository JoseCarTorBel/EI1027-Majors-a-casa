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

    public void addDisponibility(Disponibility disponibility,String dniElderlyPeople, String dniVolunteer){
        jdbcTemplate.update("INSERT INTO company VALUES (?,?,?,?,?,?)",
                dniVolunteer,dniElderlyPeople,disponibility.getDayOfWeek(),disponibility.getInitialTime(),disponibility.getFinalTime(),disponibility.isOpen()
                );
    }

    public void removeDisponibility(String dniElderlyPeople,String dniVolunteer){
        jdbcTemplate.update("DELETE FROM company WHERE dnielderlypeople=?, dnivolunteer=?",dniElderlyPeople,dniVolunteer);
    }

    public Disponibility getDisponibility(String dniElderlyPeople,String dniVolunteer){
        try{
            return  jdbcTemplate.queryForObject("SELECT * FROM company WHERE dnielderlypeople=?, dnivolunteer=?",
                                    new DisponibilityRowMapper(),dniElderlyPeople,dniVolunteer);
        }catch(EmptyResultDataAccessException e) {
         return null;
    }
    }



}
