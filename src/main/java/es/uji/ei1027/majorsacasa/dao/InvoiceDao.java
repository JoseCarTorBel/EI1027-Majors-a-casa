package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class InvoiceDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }


    /**
     * add a invoice
     * @param invoice
     */
    public void addInvoice(Invoice invoice){
        jdbcTemplate.update("INSERT INTO invoice VALUES (?,?,?,?)",
                invoice.getCodInvoice(), invoice.getDniElderlyPeople(), invoice.getPrice(), invoice.getDate()
        );
    }

    /**
     * update a invoice
     * @param invoice
     */
    public void updateInvoice(Invoice invoice){
        jdbcTemplate.update("UPDATE invoice SET dnielderlypeople=?, price=?, date=? WHERE codinvoice=?",
                invoice.getDniElderlyPeople(), invoice.getPrice(), invoice.getDate(),invoice.getCodInvoice()
        );
    }

    /**
     * remove a invoice
     * @param codInvoice
     */
    public void removeInvoice(String codInvoice){
        jdbcTemplate.update("DELETE FROM invoice WHERE codinvoice=?",codInvoice);
    }


    /**
     * get a invoice
     * @param codInvoice
     * @return Invoice
     */
    public Invoice getInvoice(String codInvoice){
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM invoice WHERE codinvoice=?",
                    new InvoiceRowMapper(),codInvoice);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }


}
