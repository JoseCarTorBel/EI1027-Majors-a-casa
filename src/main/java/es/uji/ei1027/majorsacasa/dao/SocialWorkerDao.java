package es.uji.ei1027.majorsacasa.dao;


import es.uji.ei1027.majorsacasa.model.SocialWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class SocialWorkerDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }

    /**
     * Add social worker to BBDD
     * @param socialWorker
     */
    public void addSocialWorker(SocialWorker socialWorker){
        jdbcTemplate.update("INSERT INTO socialworker VALUES(?,?,?,?,?)",
                            socialWorker.getDni(),socialWorker.getName(),socialWorker.getPhone(),socialWorker.getUserName(),socialWorker.getPasswd());

    }

    /**
     * Remove a socialWorker
     * @param dni
     */
    public void removeSocialWorker(String dni){
        jdbcTemplate.update("DELETE FROM socialworker WHERE dni=?",dni);
    }

    /**
     * get a social worker
     * @param dni
     * @return SocialWorker
     */
    public SocialWorker getSocialWorker(String dni){
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM socialworker WHERE dni=?",
                    new SocialWorkerRowMapper(), dni);
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }



}
