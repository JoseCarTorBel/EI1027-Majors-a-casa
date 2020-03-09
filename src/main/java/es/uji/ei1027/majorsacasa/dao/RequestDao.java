package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class RequestDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }


    /**
     * add a request
     * @param request,codRequest
     */
    public void addRequest(Request request,String codrequest){
        jdbcTemplate.update("INSERT INTO request VALUES (?,?,?,?,?,?,?,?)",
                codrequest,request.getState(),request.getService(),request.getInitialDate(),request.getAprovedDate(),request.isRejected(),request.getEndDate(),request.getDniElderlyPeople()
                );
    }

    /**
     * update a request
     * @param request,codRequest
     */
    public void updateRequest(Request request,String codrequest){
        jdbcTemplate.update("UPDATE request SET state=?, servicetype=?, requestdate=?, approveddate=?, rejected=?, enddate=?, dnielderlypeople=? WHERE codrequest=?",
                request.getState(),request.getService(),request.getInitialDate(),request.getAprovedDate(),request.isRejected(),request.getEndDate(),request.getDniElderlyPeople(),codrequest
        );
    }

    /**
     * remove a request
     * @param codRequest
     */
    public void removeRequest(String codRequest){
        jdbcTemplate.update("DELETE FROM request WHERE codrequest=?",codRequest);
    }


    /**
     * get a request
     * @param codRequest
     * @return Request
     */
    public Request getRequest(String codRequest){
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM request WHERE codrequest=?",
                                    new RequestRowMapper(),codRequest);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }



}
