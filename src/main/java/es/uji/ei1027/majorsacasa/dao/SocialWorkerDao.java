package es.uji.ei1027.majorsacasa.dao;


import es.uji.ei1027.majorsacasa.model.ElderlyPeople;
import es.uji.ei1027.majorsacasa.model.SocialWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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
     * Update social worker
     * @param socialWorker
     */
    public void updateSocialWorker(SocialWorker socialWorker){
        jdbcTemplate.update("UPDATE socialworker SET name=?, phone=?, username=?, passwd=? WHERE dni=?",
                socialWorker.getName(),socialWorker.getPhone(),socialWorker.getUserName(),socialWorker.getPasswd(),socialWorker.getDni());

    }

    /**
     * Obtiene la lista de todos los socialworker de la tabla socialworker
     * @return List<SocialWorker>
     */
    public List<SocialWorker> getSocialWorkerList() {
        try{
            return jdbcTemplate.query("SELECT * FROM socialworker",new SocialWorkerRowMapper());
        } catch (
                EmptyResultDataAccessException e) {
            return new ArrayList<SocialWorker>();
        }
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
