package com.example.bilabonnement.Repository;

import com.example.bilabonnement.DTO.DamageReportOverviewDTO;
import com.example.bilabonnement.Model.DamageReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DamageReportRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapper database-rækker til DamageReport-objekter
    private final RowMapper<DamageReport> rowMapper = new RowMapper<>() {
        @Override
        public DamageReport mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new DamageReport(
                    rs.getInt("report_id"),
                    rs.getObject("report_date", LocalDate.class),
                    rs.getString("description"),
                    rs.getDouble("total_price"),
                    rs.getInt("lease_id"),
                    rs.getString("inspector"),
                    rs.getBoolean("customer_present"),
                    rs.getString("status"),
                    rs.getString("registration_number")
            );

        }
    };

    // Henter alle skadesrapporter
    public List<DamageReport> findAll() {
        String sql = "SELECT * FROM damage_report";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Henter én skadesrapport baseret på ID
    public DamageReport findById(int id) {
        String sql = "SELECT * FROM damage_report WHERE report_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // Henter alle skadesrapporter for en bestemt bil
    public List<DamageReport> findByLeaseId(int leaseId) {
        String sql = "SELECT * FROM damage_report WHERE lease_id = ?";
        return jdbcTemplate.query(sql, rowMapper, leaseId);
    }

    // Gemmer en ny skadesrapport
    public void save(DamageReport report) {
        String sql = "INSERT INTO damage_report (" +
                "report_date, description, total_price, inspector, customer_present, status, lease_id, registration_number" +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                report.getDate(),
                report.getDescription(),
                report.getPrice(),
                report.getInspector(),
                report.isCustomerPresent(),
                report.getStatus(),
                report.getLeaseId(),
                report.getRegistrationNumber()

        );
    }


    // Opdaterer en eksisterende skadesrapport
    public void update(DamageReport report) {
        String sql = "UPDATE damage_report SET " +
                "report_date = ?, " +
                "description = ?, " +
                "total_price = ?, " +
                "inspector = ?, " +
                "customer_present = ?, " +
                "status = ?, " +
                "lease_id = ?, " +
                "registration_number = ?, " +
                "WHERE report_id = ?";

        jdbcTemplate.update(sql,
                report.getDate(),
                report.getDescription(),
                report.getPrice(),
                report.getInspector(),
                report.isCustomerPresent(),
                report.getStatus(),
                report.getLeaseId(),
                report.getRegistrationNumber(),
                report.getReportId()
        );
    }


    // Sletter en skadesrapport baseret på ID
    public void deleteById(int id) {
        String sql = "DELETE FROM damage_report WHERE report_id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Martin: Finder alle biler med status 'Skadet' og tilhørende skader
    public List<DamageReportOverviewDTO> findActiveDamageReports() {
        String sql = """
        SELECT c.registration_number,
               m.brand,
               m.model_name,
               d.description,
               d.total_price,
               d.report_date
        FROM car c
        JOIN lease_agreement la ON c.registration_number = la.car_registration_number
        JOIN damage_report d ON la.lease_id = d.lease_id
        JOIN car_model m ON c.model_id = m.model_id
        WHERE c.status = 'Damaged'
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            DamageReportOverviewDTO dto = new DamageReportOverviewDTO();
            dto.setRegistrationNumber(rs.getString("registration_number"));
            dto.setBrand(rs.getString("brand"));
            dto.setModelName(rs.getString("model_name"));
            dto.setDescription(rs.getString("description"));
            dto.setPrice(rs.getDouble("total_price"));
            dto.setReportDate(rs.getDate("report_date").toLocalDate());
            return dto;
        });
    }

}