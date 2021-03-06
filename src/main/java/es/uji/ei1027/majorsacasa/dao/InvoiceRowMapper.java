package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Invoice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoiceRowMapper implements RowMapper<Invoice> {

    @Override
    public Invoice mapRow(ResultSet rs, int i) throws SQLException {

        Invoice invoice = new Invoice();

        invoice.setCodInvoice(rs.getString("codinvoice"));
        invoice.setDniElderlyPeople(rs.getString("dnielderlypeople"));
        invoice.setPrice(rs.getFloat("price"));
        Date date=rs.getDate("date");
        invoice.setDate(date != null ? date.toLocalDate() : null);
        return invoice;

    }
}
