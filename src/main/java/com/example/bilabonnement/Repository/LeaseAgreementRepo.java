package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.LeaseAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LeaseAgreementRepo
{
    @Autowired
    JdbcTemplate template;

    //Sumaya - Henter alle lejeaftaler
    public List<LeaseAgreement> fetchAll()
    {
        String sql = "select * from lease_agreement";
        RowMapper<LeaseAgreement> rowmapper = new BeanPropertyRowMapper<>(LeaseAgreement.class);
        return template.query(sql, rowmapper);
    }

    //Sumaya - opretter en ny lejeaftale
    public void addLeaseAgreement(LeaseAgreement leaseAgreement)
    {
        String sql = "INSERT INTO lease_agreement (lease_id, start_date, end_date, start_mileage, end_mileage, monthly_price, total_price, lease_type, status, car_registration_number, user_id, customer_id, location_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Indsætter værdier i databasen vha. JdbcTemplate
        template.update(sql,
                leaseAgreement.getLeaseId(),
                leaseAgreement.getStartDate(),
                leaseAgreement.getEndDate(),
                leaseAgreement.getStartMileage(),
                leaseAgreement.getEndMileage(),
                leaseAgreement.getMonthlyPrice(),
                leaseAgreement.getTotalPrice(),
                leaseAgreement.getLeaseType(),
                leaseAgreement.getStatus(),
                leaseAgreement.getCarRegistrationNumber(),
                leaseAgreement.getUserId(),
                leaseAgreement.getCustomerId(),
                leaseAgreement.getLocationId()
        );
    }

    //Isabella: til dashboard - se hvor mange biler der er udlejet
    public int countActiveLeases()
    {
        String sql = "SELECT COUNT(*) FROM lease_agreement WHERE status = 'Active'";
        return template.queryForObject(sql, Integer.class);
    }



    // Sumaya - sletter en lejeaftale fra databasen baseret på lease_id
    public boolean deleteLeaseAgreement(int id) {
        String sql = "DELETE FROM lease_agreement WHERE lease_id = ?";

        // Sumaya - Udfører en DELETE i databasen og returnerer true, hvis det lykkes at slette en lejeaftale.
        // Hvis der ikke findes nogen lejeaftale med det givne ID, bliver der ikke slettet noget – og metoden returnerer false.
        return template.update(sql, id) > 0;
    }


    // Sumaya - Opdaterer en eksisterende lejeaftale i databasen baseret på lease_id (primærnøglen)
    public void updateLeaseAgreement(int id, LeaseAgreement leaseAgreement) {
        String sql = "UPDATE lease_agreement SET start_date = ?, end_date = ?, start_mileage = ?, end_mileage = ?, monthly_price = ?, total_price = ?, lease_type = ?, status = ?, car_registration_number = ?, user_id = ?, customer_id = ?, location_id = ? WHERE lease_id = ?";

        // Indsætter værdier i databasen vha. JdbcTemplate
        template.update(sql,
                leaseAgreement.getStartDate(),
                leaseAgreement.getEndDate(),
                leaseAgreement.getStartMileage(),
                leaseAgreement.getEndMileage(),
                leaseAgreement.getMonthlyPrice(),
                leaseAgreement.getTotalPrice(),
                leaseAgreement.getLeaseType(),
                leaseAgreement.getStatus(),
                leaseAgreement.getCarRegistrationNumber(),
                leaseAgreement.getUserId(),
                leaseAgreement.getCustomerId(),
                leaseAgreement.getLocationId(),
                id); // lease_id bruges til at finde den lejeaftale der skal opdateres
    }


    public LeaseAgreement findActiveLeaseByRegistrationNumber(String regNumber) {
        String sql = "SELECT * FROM lease_agreement WHERE car_registration_number = ? AND status IN ('Active', 'Completed')";
        List<LeaseAgreement> result = template.query(sql, new LeaseAgreementRowMapper(), regNumber);
        return result.stream().findFirst().orElse(null);
    }


    // Intern klasse til mapping af LeaseAgreement
    private static class LeaseAgreementRowMapper implements RowMapper<LeaseAgreement> {
        @Override
        public LeaseAgreement mapRow(ResultSet rs, int rowNum) throws SQLException {
            LeaseAgreement lease = new LeaseAgreement();
            lease.setLeaseId(rs.getInt("lease_id"));
            lease.setStartDate(rs.getDate("start_date"));
            lease.setEndDate(rs.getDate("end_date"));
            lease.setStartMileage(rs.getInt("start_mileage"));
            lease.setEndMileage(rs.getInt("end_mileage"));
            lease.setMonthlyPrice(rs.getDouble("monthly_price"));
            lease.setTotalPrice(rs.getDouble("total_price"));
            lease.setLeaseType(rs.getString("lease_type"));
            lease.setStatus(rs.getString("status"));
            lease.setCarRegistrationNumber(rs.getString("car_registration_number"));
            lease.setUserId(rs.getInt("user_id"));
            lease.setCustomerId(rs.getInt("customer_id"));
            lease.setLocationId(rs.getInt("location_id"));
            return lease;
        }
    }

    // Sumaya - Finder en lejeaftale i databasen baseret på dens lease_id
    public LeaseAgreement findById(int id) {

        // Hent alle felter fra lease_agreement, hvor lease_id matcher den angivne id
        String sql = "SELECT * FROM lease_agreement WHERE lease_id = ?";
        RowMapper<LeaseAgreement> rowMapper = new BeanPropertyRowMapper<>(LeaseAgreement.class);

        // Udfører og returnerer den fundne lejeaftale som et LeaseAgreement-objekt
        return template.queryForObject(sql, rowMapper, id);
    }


    //Isbaella - til Dashboard
    public double sumTotalPriceOfLeasedCars()
    {
        String sql = "SELECT SUM(total_price) FROM lease_agreement WHERE status = 'active'";
        Double result = template.queryForObject(sql, Double.class); //Isabella - Jdbc sætning der eksekverer SQL'en , Double.class fortæller Spring at det er decimaltal som værdi
        if (result != null)
        {
         return result;
        } else
        {
        return 0.0;
        }
    }
}

