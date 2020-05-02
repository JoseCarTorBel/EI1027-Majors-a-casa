package es.uji.ei1027.majorsacasa.dao;


import es.uji.ei1027.majorsacasa.model.ElderlyPeople;
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

    /**
     * Obtiene la lista de todos los ancianos de la tabla elderlypeple
     * @return List<ElderlyPeople>
     */
    public List<ElderlyPeople> getElderlyPeopleList() {
        try{
            return jdbcTemplate.query("SELECT * FROM elderlypeople JOIN person ON elderlypeople.dni=person.dni",new ElderlyPeopleRowMapper());
        } catch (
                EmptyResultDataAccessException e) {
            return new ArrayList<ElderlyPeople>();
        }
    }


    /**
     *  Obtiene el elderlypeople cuya clave es dni
     * @param dni
     * @return ElderlyPeople
     */
    public ElderlyPeople getElderlyPeople(String dni) {
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM elderlypeople JOIN person ON elderlypeople.dni=person.dni WHERE elderlypeople.dni=? ",
                    new ElderlyPeopleRowMapper(), dni);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     *  add elderlyPeople
     * @param elderlyPeople
     */
    public void addElderlyPeople(ElderlyPeople elderlyPeople){
        jdbcTemplate.update("INSERT INTO person VALUES(?,?,?,?,?,?,?,?,?)",
                elderlyPeople.getDni(),elderlyPeople.getSecondName(),elderlyPeople.getDni(),elderlyPeople.getPhone(),elderlyPeople.getDateOfBirth(),
                elderlyPeople.getPostAddress(),elderlyPeople.getEmail(),elderlyPeople.getUsername(),elderlyPeople.getPasswd());

        jdbcTemplate.update("INSERT INTO elderlypeople VALUES(?,?,?,?)",
               elderlyPeople.getDni(),"P",elderlyPeople.getJustification(),elderlyPeople.getDniSocialWorker());

    }


    /**
     *  remove elderlyPeople
     * @param dni
     */
    public void removeElderyPeople(String dni){
        jdbcTemplate.update("DELETE FROM elderlypeople WHERE dni=?",dni);
    }


    /**
     *  update passwd
     * @param dni, password
     */
    public void updatePasswd(String dni, String password){
        jdbcTemplate.update("UPDATE person SET passwd=? WHERE dni=?",password,dni);
    }


    /**
     *  update elderlyPeople
     * @param elderlypeople
     */
    public void updateElderlyPeople(ElderlyPeople elderlypeople) {
        jdbcTemplate.update("UPDATE person SET name=?, secondname=?, phone=?, dateofbirth=?, postaddress=?, email=?, username=?, passwd=? WHERE dni=?",
                elderlypeople.getName(),elderlypeople.getSecondName(),elderlypeople.getPhone(),elderlypeople.getDateOfBirth(),
                elderlypeople.getPostAddress(),elderlypeople.getEmail(),elderlypeople.getUsername(),elderlypeople.getPasswd(),elderlypeople.getDni());
        jdbcTemplate.update("UPDATE elderlypeople SET  state=?, justification=?, dnisocialworker=? WHERE dni=?",
                elderlypeople.getState(), elderlypeople.getJustification(),elderlypeople.getDniSocialWorker(),elderlypeople.getDni());
    }

    /**
     * Obtiene la lista de todos los ancianos de la tabla elderlypeple con  ese estado
     * @param state
     * @return List<ElderlyPeople>
     */
    public List<ElderlyPeople> getElderlyPeopleSetState(char state) {
        try{
            return jdbcTemplate.query("SELECT * FROM elderlypeople JOIN person ON elderlypeople.dni=person.dni where state=?",new ElderlyPeopleRowMapper(),state);
        } catch (
                EmptyResultDataAccessException e) {
            return new ArrayList<ElderlyPeople>();
        }
    }

}
