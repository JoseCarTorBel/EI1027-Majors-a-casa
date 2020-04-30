package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class RequestDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }


    /**
     * add a request
     * @param request
     */
    public void addRequest(Request request){
        jdbcTemplate.update("INSERT INTO request VALUES (?,?,?,?,?,?,?,?,?)",
                request.getCodRequest(),request.getState(),request.getService(),request.getInitialDate(),request.getAprovedDate(),request.isRejected(),request.getEndDate(),request.getDniElderlyPeople(), request.getCifCompany()
                );
    }

    /**
     * update a request
     * @param request
     */
    public void updateRequest(Request request){
        jdbcTemplate.update("UPDATE request SET state=?, servicetype=?, requestdate=?, approveddate=?, rejected=?, enddate=?, dnielderlypeople=?, cifcompany=? WHERE codrequest=?",
                request.getState(),request.getService(),request.getInitialDate(),request.getAprovedDate(),request.isRejected(),request.getEndDate(),request.getDniElderlyPeople(),request.getCifCompany(), request.getCodRequest()
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

    /**
     * get a request
     * @param dniElderlyPeople
     * @return Disponibility
     */
    public List<Request> getRequestsElderly(String dniElderlyPeople){
        try{
            return jdbcTemplate.query("SELECT * FROM request WHERE dnielderlypeople=?",
                    new RequestRowMapper(),dniElderlyPeople);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }



}
