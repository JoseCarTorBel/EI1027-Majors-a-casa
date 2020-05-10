package es.uji.ei1027.majorsacasa.dao;


import es.uji.ei1027.majorsacasa.model.Volunteer;
import org.apache.catalina.valves.JDBCAccessLogValve;
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


    /**
     * Obtiene la lista de todos los voluntarios de la tabla volunteer
     * @return List<Volunteer>
     */
    public List<Volunteer> getVolunteerList() {
        try{

            return jdbcTemplate.query("SELECT * FROM volunteer JOIN person ON volunteer.dni=person.dni ",new VolunteerRowMapper());
        } catch (
        EmptyResultDataAccessException e) {
            return new ArrayList<Volunteer>();
        }
    }


    /**
     * Obtiene el voluntario cuya clave es dni
     * @param dni
     * @return Volunteer
     */
    public Volunteer getVolunteer(String dni) {
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM volunteer JOIN person ON volunteer.dni=person.dni WHERE person.dni=?",
                                                new VolunteerRowMapper(), dni);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }


    /**
     * Obtiene un listado de voluntarios, los cuales estan pendientes de aprovacion por el cas
     * @return List<Volunteer>
     */
    public List<Volunteer> getVolunteersPendent() {
        try{
            return jdbcTemplate.query("SELECT * FROM volunteer JOIN person ON volunteer.dni=person.dni WHERE volunteer.state='P'",
                    new VolunteerRowMapper());
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Añade un voluntario a la BBDD, incluye los inserts a la tabla person y volunteer
     * @param volunteer
     */
    public void addVolunteer(Volunteer volunteer){
        jdbcTemplate.update("INSERT INTO person VALUES(?,?,?,?,?,?,?,?,?)",
                                volunteer.getName(),volunteer.getSecondName(),volunteer.getDni(),volunteer.getPhone(),volunteer.getDateOfBirth(),
                                volunteer.getPostAddress(), volunteer.getEmail(), volunteer.getUsername(),volunteer.getPasswd());
        jdbcTemplate.update("INSERT INTO volunteer VALUES(?,?,?)",
                                volunteer.getDni(), null,"P"); // ESTOS ATRIBUTOS SON NULL YA QUE ES EL CAS QUIEN MANEJA ESTOS DOS DATOS
        // sera en el update cuando se modifiquen

    }

    /**
     * Borrar volunteer, primero se borra de hobies y disponibility, luego de voluntario, luego de personas.
     * @param dni
     */
    public void removeVolunteer(String dni){
        jdbcTemplate.update("DELETE FROM hobbies WHERE dni=?",dni);
        jdbcTemplate.update("DELETE FROM disponibility WHERE dnivolunteer=?",dni);
        jdbcTemplate.update("DELETE FROM volunteer WHERE dni=?",dni);
        jdbcTemplate.update("DELETE FROM person WHERE dni=?",dni);
    }


    /**
     * Acepta la peticion de registro de un voluntario
     * @param dniVolunteer
     */
    public void acceptVolunteer(String dniVolunteer){
        updateState(dniVolunteer,"A");
    }


    /**
     * Deniega la peticion de registro de un voluntario
     * *
     * @param dniVolunteer
     */
    public void denyVolunteer(String dniVolunteer){
        updateState(dniVolunteer,"R");

    }


    /**
     * Actualiza el estado de un voluntario
     * @param dni, newState
     */
    public void updateState(String dni, String newState) {
        System.out.println(dni+"  "+newState);
        jdbcTemplate.update("UPDATE volunteer SET state=? WHERE dni=?",newState,dni);
    }

    /**
     * Actualiza tod o el voluntario
     * IMPORTANTE NO CAMBIAR DNI (si no problema BBDD)
     * @param volunteer
     */
    public void updateVolunteer(Volunteer volunteer) {
        System.out.println(volunteer.toString());
        jdbcTemplate.update("UPDATE person SET name=?, secondname=?, phone=?, dateofbirth=?, postaddress=?, email=?, username=?, passwd=? WHERE dni=?",
                volunteer.getName(),volunteer.getSecondName(),volunteer.getPhone(),volunteer.getDateOfBirth(),
                volunteer.getPostAddress(),volunteer.getEmail(),volunteer.getUsername(),volunteer.getPasswd(),volunteer.getDni());

        jdbcTemplate.update("UPDATE volunteer SET enddate=?, state=? WHERE dni=?",
                volunteer.getEndDate(),volunteer.getState(),volunteer.getDni());

    }


}
