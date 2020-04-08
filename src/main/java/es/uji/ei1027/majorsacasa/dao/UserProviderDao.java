package es.uji.ei1027.majorsacasa.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import es.uji.ei1027.majorsacasa.model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import es.uji.ei1027.majorsacasa.model.UserDetails;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserProviderDao implements UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }




    // Se guardara una clave username, y una lista de UserDetails
    public HashMap<String,UserDetails> getUsersList() {


        HashMap<String,UserDetails> users = new HashMap<String,UserDetails>();

        // SocialWorker
        try {

            List<UserDetails> userDetails= jdbcTemplate.query("SELECT username,passwd,socialworker.dni FROM socialworker;", new UserDetailsRowMapper());
            for(UserDetails user:userDetails) {
                user.setRol("SocialWorker");
                users.put(user.getUsername(),user);
            }
        } catch (
                EmptyResultDataAccessException e) {}

        // Voluntarios
        try {

            List<UserDetails> userDetails= jdbcTemplate.query("SELECT username, passwd,person.dni FROM volunteer JOIN person on person.dni=volunteer.dni;", new UserDetailsRowMapper());
            for(UserDetails user:userDetails){
                user.setRol("Volunteer");
                users.put(user.getUsername(),user);
            }
        } catch (
                EmptyResultDataAccessException e) {}

        // ElderlyPeoples
        try {

            List<UserDetails> userDetails= jdbcTemplate.query("SELECT username, passwd,person.dni FROM elderlypeople JOIN person on person.dni=elderlypeople.dni;", new UserDetailsRowMapper());
            for(UserDetails user:userDetails){
                user.setRol("Elderly");
                users.put(user.getUsername(),user);
            }
        } catch (
                EmptyResultDataAccessException e) {}

        // Company
        try {

            List<UserDetails> userDetails= jdbcTemplate.query("SELECT username, passwd, cif AS \"dni\" FROM company;", new UserDetailsRowMapper());
            for(UserDetails user:userDetails){
                user.setRol("Company");
                users.put(user.getUsername(),user);
            }
        } catch (
                EmptyResultDataAccessException e) {}

        // CAS
        try {

            List<UserDetails> userDetails= jdbcTemplate.query("SELECT username, passwd,rol FROM casusers;", new CasUserRowMapper());
            for(UserDetails user:userDetails){
                users.put(user.getUsername(),user);
            }
        } catch (
                EmptyResultDataAccessException e) {}

        return users;







    }


    @Override
    public UserDetails loadUserByUsername(String username, String password) {
        HashMap<String,UserDetails> users = getUsersList();
       if (users.containsKey(username)){
           if(users.get(username).getPassword().equals(password)){
               return users.get(username);
           }
            return null;
       }else{
           return null;
       }

    }

    @Override
    public Collection<UserDetails> listAllUsers() {
        return getUsersList().values();
    }
}