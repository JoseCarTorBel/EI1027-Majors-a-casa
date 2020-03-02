package es.uji.ei1027.majorsacasa.dao;


import es.uji.ei1027.majorsacasa.model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VolunteerDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    //*Selects */
    public List<Volunteer> getVolunteerList() {
        try{
            return jdbcTemplate.query("SELECT * FROM volunteer",new VolunteerRowMapper());
        } catch (
        EmptyResultDataAccessException e) {
            return new ArrayList<Volunteer>();
        }
    }

    public Volunteer getVolunteer(String dni) {
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM volunteer WHERE dni=?",
                                                new VolunteerRowMapper(), dni);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void addVolunteer(Volunteer volunteer){
        jdbcTemplate.update("INSERT INTO volunteer VALUES(?,?,?,?,?,?,?,?,?)",
                            volunteer.getDni(),volunteer.getName(),volunteer.getSecondName(),volunteer.getPhone(),volunteer.getDateOfBirith(),
                            volunteer.getPostaddress(),volunteer.getState(),volunteer.getMail(),volunteer.getPassword());

    }

    public void removeVolunteer(String dni){
        jdbcTemplate.update("DELETE FROM volunteer WHERE dni=?",dni);
    }

    public void updatePasswd(String dni, String password){
        jdbcTemplate.update("UPDATE volunteer SET passwd=? WHERE dni=?",password,dni);
    }

    public void updateState(String dni, String newState) {
        jdbcTemplate.update("UPDATE volunteer SET state=? WHERE dni=?",newState,dni);
    }
}