package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Contract;
import es.uji.ei1027.majorsacasa.model.ServiceType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContractRowMapper implements RowMapper<Contract> {

    @Override
    public Contract mapRow(ResultSet rs, int i) throws SQLException {

        Contract contract = new Contract();
        contract.setService((ServiceType) rs.getObject("servicetype")); //TODO esto no se si esta bien asi
        Date date=rs.getDate("initialtime");
        contract.setInitialDate(date != null ? date.toLocalDate() : null);
        date=rs.getDate("finaltime");
        contract.setFinalDate(date != null ? date.toLocalDate() : null);
        contract.setPrice(rs.getFloat("price"));
        return contract;

    }
}
