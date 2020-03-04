package es.uji.ei1027.majorsacasa.dao;


import es.uji.ei1027.majorsacasa.model.ElderlyPeople;
import es.uji.ei1027.majorsacasa.model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ElderlyPeopleDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    // Obtiene la lista de todos los ancianos de la tabla elderlypeple
    public List<ElderlyPeople> getElderlyPeopleList() {
        try{
            return jdbcTemplate.query("SELECT * FROM elderlypeople",new ElderlyPeopleRowMapper());
        } catch (
                EmptyResultDataAccessException e) {
            return new ArrayList<ElderlyPeople>();
        }
    }

    // Obtiene el elderlypeople cuya clave es dni
    public ElderlyPeople getElderlyPeople(String dni) {
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM elderlypeople WHERE dni=?",
                    new ElderlyPeopleRowMapper(), dni);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void addElderlyPeople(ElderlyPeople elderlyPeople){
        jdbcTemplate.update("INSERT INTO elderlypeople VALUES(?,?,?,?,?,?,?,?,?,?,?)",
                elderlyPeople.getDni(),elderlyPeople.getName(),elderlyPeople.getSecondName(),elderlyPeople.getEmail(),elderlyPeople.getPhone(),
                elderlyPeople.getPostAddress(),elderlyPeople.getJustification(),elderlyPeople.getDateOfBirith(),
                elderlyPeople.getDniSocialWorker(),elderlyPeople.getUsername(),elderlyPeople.getPasswd());

    }

    public void removeElderyPeople(String dni){
        jdbcTemplate.update("DELETE FROM elderlypeople WHERE dni=?",dni);
    }

    public void updatePasswd(String dni, String password){
        jdbcTemplate.update("UPDATE elderlypeople SET passwd=? WHERE dni=?",password,dni);
    }

    public void updateElderlyPeople(ElderlyPeople elderlypeople) {
        jdbcTemplate.update("UPDATE elderlypeople SET name=?, secondname=?, phone=?, dateofbrith=?, postaddress=?, justification=?, email=?, username=?, passwd=?, dnisocialworker=? WHERE dni=?",
                elderlypeople.getName(),elderlypeople.getSecondName(),elderlypeople.getPhone(),elderlypeople.getDateOfBirith(),
                elderlypeople.getPostAddress(),elderlypeople.getJustification(),elderlypeople.getEmail(),elderlypeople.getUsername(),elderlypeople.getPasswd(),elderlypeople.getDniSocialWorker(),elderlypeople.getDni());
    }

}
