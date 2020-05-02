package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContractDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
    }


    /**
     * add a contract
     * @param contract
     */
    public void addContract(Contract contract){


        jdbcTemplate.update("INSERT INTO contract VALUES (?,?,?,?,?,?,?,?,?)",
                makeKey(contract),contract.getCodContract(), contract.getCifcompany(), contract.getService(), contract.getInitialDate(), contract.getFinalDate(),
                contract.getPrice(),contract.getDaysOfWeek(),contract.getHour_initial(),contract.getHour_final());
    }

    /**
     * update a contract
     * @param contract
     */
    public void updateContract(Contract contract){
        jdbcTemplate.update("UPDATE contract SET service=?, initialtime=?, finaltime=?, price=? WHERE cifcompany=?",
                contract.getService(), contract.getInitialDate(), contract.getFinalDate(), contract.getPrice(), contract.getCifcompany()
                );
    }

    /**
     * Dar de baja a una empresa, es decir, cambiar la fecha de finalización.
     * @param codContract  Código contrato actual
     */
    public void unsubscribeContract(String codContract){
        jdbcTemplate.update("UPDATE contract SET finaltime=NOW() WHERE codcontract=?",codContract);
    }


    /**
     * get a contract
     * @param codContract
     * @return Contract
     */
    public Contract getContract(String codContract){
        try{
            return jdbcTemplate.queryForObject("SELECT * FROM contract WHERE cifcompany=?",
                    new ContractRowMapper() ,codContract);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    /**
     * Listado de contratos vigentes en comparación con hoy.
     * @return List<Contract>
     */
    public List<Contract> getListContractVigente(){
        try{
            return jdbcTemplate.query(  "SELECT * " +
                                            "FROM contract " +
                                            "WHERE initialtime<= NOW() AND finaltime>=NOW();",
                                        new ContractRowMapper());

        }catch (EmptyResultDataAccessException e){
            return  new ArrayList<Contract>();
        }

    }

    /**
     * Listado de contrados NO vigentes comparados con hoy.
     * @return List<Contract>
     */
    public List<Contract> getListContractPasados(){
        try{
            return jdbcTemplate.query(  "SELECT * " +
                                            "FROM contract " +
                                            "WHERE finaltime<=NOW();",
                                        new ContractRowMapper());

        }catch (EmptyResultDataAccessException e){
            return  new ArrayList<Contract>();
        }
    }

    /**
     *  Contratos por servicio.
     * @param servicio  int
     * @return  List<Contract>
     */
    public List<Contract> getListContractVigente(int servicio){
        try{
            return jdbcTemplate.query(  "SELECT * " +
                            "FROM contract " +
                            "WHERE initialtime<= NOW() AND finaltime>=NOW() " +
                            "AND service=?;",
                    new ContractRowMapper(),servicio);

        }catch (EmptyResultDataAccessException e){
            return  new ArrayList<Contract>();
        }
    }

    /**
     * Listado de contrados NO vigentes comparados con hoy según tipo de servicio.
     * @return List<Contract>
     */
    public List<Contract> getListContractPasados(int servicio){
        try{
            return jdbcTemplate.query(  "SELECT * " +
                            "FROM contract " +
                            "WHERE finaltime<=NOW()" +
                            "AND service=?;",
                    new ContractRowMapper(),servicio);

        }catch (EmptyResultDataAccessException e){
            return  new ArrayList<Contract>();
        }
    }


    private String makeKey(Contract contract) {
        String[] f = contract.getInitialDate().toString().split("-");
        int serv = contract.getService().getPosition();

        return serv+""+f[0]+""+f[1]+""+f[2]+""+contract.getCifcompany();

    }
}
