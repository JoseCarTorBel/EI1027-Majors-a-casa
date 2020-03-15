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
            return jdbcTemplate.queryForObject("SELECT * FROM volunteer WHERE dni=?",
                                                new VolunteerRowMapper(), dni);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Pimero se añade a Person y luego a Volunteer.
     * @param volunteer
     */
    public void addVolunteer(Volunteer volunteer){
        jdbcTemplate.update("INSERT INTO person VALUES(?,?,?,?,?,?,?,?,?)",
                                volunteer.getName(),volunteer.getSecondName(),volunteer.getDni(),volunteer.getPhone(),volunteer.getDateOfBirth(),
                                volunteer.getPostAddress(), volunteer.getEmail(), volunteer.getUsername(),volunteer.getPasswd());
        jdbcTemplate.update("INSERT INTO volunteer VALUES(?,?,?)",
                                volunteer.getDni(), volunteer.getEndDate(),volunteer.getState());

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
     * update a volunteer
     * @param dni
     */
    public void updatePasswd(String dni, String password){
        jdbcTemplate.update("UPDATE volunteer SET passwd=? WHERE dni=?",password,dni);
    }


    /**
     * update a volunteer
     * @param dni, newState
     */
    public void updateState(String dni, String newState) {
        jdbcTemplate.update("UPDATE volunteer SET state=? WHERE dni=?",newState,dni);
    }

    /**
     * update a volunteer
     * @param volunteer
     */
    public void updateVolunteer(Volunteer volunteer) {
        jdbcTemplate.update("UPDATE volunteer SET name=?, secondname=?, phone=?, dateofbrith=?, postaddress=?, state=?, email=?, username=?, passwd=? WHERE dni=?",
                volunteer.getName(),volunteer.getSecondName(),volunteer.getPhone(),volunteer.getDateOfBirth(),
                volunteer.getPostAddress(),volunteer.getState(),volunteer.getEmail(),volunteer.getUsername(),volunteer.getPasswd(),volunteer.getDni());
    }

}
