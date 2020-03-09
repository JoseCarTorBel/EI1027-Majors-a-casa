package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Line;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class LineDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }


    /**
     * add a line
     * @param line, codInvoice, codRequest
     */
    public void addLine(Line line, String codInvoice, String codRequest){
        jdbcTemplate.update("INSERT INTO line VALUES (?,?,?,?)",
                codInvoice, codRequest, line.getConcept(), line.getPrice()
        );
    }

    /**
     * update a line
     * @param line, codInvoice, codRequest
     */
    public void updateLine(Line line, String codInvoice, String codRequest){
        jdbcTemplate.update("UPDATE line SET concept=?, priceservice=? WHERE codinvoice=? AND codrequest=?",
                line.getConcept(), line.getPrice(),codInvoice, codRequest
        );
    }

    /**
     * remove a line
     * @param codInvoice, codRequest
     */
    public void removeLine(String codInvoice, String codRequest){
        jdbcTemplate.update("DELETE FROM line WHERE codrequest=? AND codinvoice=? ",codRequest,codInvoice);
    }


    /**
     * get a line
     * @param codInvoice, codRequest
     * @return Line
     */
    public Line getLine(String codInvoice, String codRequest){
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM line WHERE codinvoice=? AND codrequest=?",
                    new LineRowMapper(),codInvoice, codRequest);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
