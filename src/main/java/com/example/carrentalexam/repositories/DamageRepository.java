package com.example.carrentalexam.repositories;

import com.example.carrentalexam.models.CustomerRepairCosts;
import com.example.carrentalexam.models.Damage;
import com.example.carrentalexam.models.EmployeeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DamageRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createDamageReport(int rentalContractId, String descriptionOfDamage, double repairCosts, String status) {
        String query = "insert into damages(rental_contract_id, description_of_damage, repair_costs, damage_status)" +
                "values(?, ?, ?, ?);";
        jdbcTemplate.update(query, rentalContractId, descriptionOfDamage, repairCosts, status);
    }

    public List<Damage> getDamagesByContractId(int rentalContractId) {
        String query = "select * from damages where rental_contract_id = ?;";
        RowMapper rowMapper = new BeanPropertyRowMapper(Damage.class);
        return jdbcTemplate.query(query, rowMapper, rentalContractId);
    }

    public void changeDamageFromReportedToProcessed(int damageId) {
        String query = "update damages set damage_status = 'PROCESSED' where damage_id = ?;";
        jdbcTemplate.update(query, damageId);
    }

    public double getTotalRepairCosts() {
        String query = "select sum(repair_costs) from damages;";
        Double result = jdbcTemplate.queryForObject(query, Double.class);
        return result != null ? result : 0.0;
    }

    public List<CustomerRepairCosts> getAllCustomerRepairCosts() {
        String query = "select \n" +
                "    c.name, SUM(d.repair_costs) as total_repair_costs \n" +
                "from \n " +
                "    customers c\n" +
                "join \n" +
                "    rental_contracts rc on c.customer_id = rc.customer_id\n" +
                "join \n" +
                "    damages d on rc.rental_contract_id = d.rental_contract_id\n" +
                "group by c.name;\n;";
        RowMapper<CustomerRepairCosts> rowMapper = new BeanPropertyRowMapper<>(CustomerRepairCosts.class);
        return jdbcTemplate.query(query, rowMapper);
    }

}
