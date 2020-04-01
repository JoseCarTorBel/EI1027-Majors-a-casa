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
        contract.setCifcompany(rs.getString("cifcompany"));
        int service = rs.getInt("service");                      //Primero cojo el int de la BD, despues meto el enum en el modelo
        contract.setService(ServiceType.getOpcion(service));        //TODO cuando se implemente comprobar que va bien
        Date date=rs.getDate("initialtime");
        contract.setInitialDate(date != null ? date.toLocalDate() : null);
        date=rs.getDate("finaltime");
        contract.setFinalDate(date != null ? date.toLocalDate() : null);
        contract.setPrice(rs.getFloat("price"));
        return contract;

    }
}
