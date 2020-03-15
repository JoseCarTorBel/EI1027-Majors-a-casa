package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Contract;
import es.uji.ei1027.majorsacasa.model.ServiceType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContractRowMapper implements RowMapper<Contract> {

    @Override
    public Contract mapRow(ResultSet rs, int i) throws SQLException {

        Contract contract = new Contract();
        contract.setService((ServiceType) rs.getObject("servicetype")); //TODO esto no se si esta bien asi
        contract.setInitialDate(rs.getDate("initialtime").toLocalDate());
        contract.setFinalDate(rs.getDate("finaltime").toLocalDate());
        contract.setPrice(rs.getFloat("price"));
        return contract;

    }
}
