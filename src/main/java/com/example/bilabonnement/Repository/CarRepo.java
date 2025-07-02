package com.example.bilabonnement.Repository;

import com.example.bilabonnement.DTO.CarWithModelDTO;
import com.example.bilabonnement.Model.Car;
import com.example.bilabonnement.Model.Car.CarStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CarRepo {

    @Autowired
    JdbcTemplate template;


    // Isabella - Intern klasse der håndterer mapping fra ResultSet → Car-objekt med enum
    private static class CarRowMapper implements RowMapper<Car> {
        @Override
        public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
            Car car = new Car();
            car.setRegistrationNumber(rs.getString("registration_number"));
            car.setVinNumber(rs.getString("vin_number"));
            car.setStatus(CarStatus.valueOf(rs.getString("status")));
            car.setColor(rs.getString("color"));
            car.setPurchasePrice(rs.getDouble("purchase_price"));
            car.setRegistrationFee(rs.getDouble("registration_fee"));
            car.setMileage(rs.getInt("mileage"));
            car.setLocation(rs.getString("location"));
            car.setModelId(rs.getInt("model_id"));
            return car;
        }
    }


    // Henter alle biler
    public List<Car> fetchAll() {
        String sql = "SELECT * FROM car";
        return template.query(sql, new CarRowMapper()); //static klassen brugt her
    }


    //Isabella - Hent alle biler med model (DTO)
    public List<CarWithModelDTO> fetchAllCarsWithModel() {
        String sql = """
        SELECT 
            c.registration_number, c.vin_number, c.status, c.color, c.purchase_price,
            c.registration_fee, c.mileage, c.location, c.model_id,
            m.brand, m.model_name, m.equipment_level, m.co2_emission, m.monthly_price
        FROM car c
        JOIN car_model m ON c.model_id = m.model_id
        """;

        RowMapper<CarWithModelDTO> rowMapper = new BeanPropertyRowMapper<>(CarWithModelDTO.class); //Vi kan bruge BeanPropertyRowMapper her da vi bruger String status i DTO
        return template.query(sql, rowMapper);
    }


    // Isabella - Tilføjer ny bil
    public void addCar(Car car) {
        String sql = "INSERT INTO car (registration_number, vin_number, status, color, purchase_price, registration_fee, mileage, location, model_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        template.update(sql,
                car.getRegistrationNumber(),
                car.getVinNumber(),
                car.getStatus().name(),  // enum → string
                car.getColor(),
                car.getPurchasePrice(),
                car.getRegistrationFee(),
                car.getMileage(),
                car.getLocation(),
                car.getModelId());
    }


    // Isabella - Find bil ud fra registration_number (primær nøgle)
    public Car findCarByRegistration(String regNumber) {
        String sql = "SELECT * FROM car WHERE registration_number = ?";
        return template.queryForObject(sql, new CarRowMapper(), regNumber);
    }




    //Isabella - Opdater bil
    public void updateCar(String regNumber, Car car) {
        String sql = "UPDATE car SET vin_number = ?, status = ?, color = ?, purchase_price = ?, registration_fee = ?, mileage = ?, location = ?, model_id = ? " +
                "WHERE registration_number = ?";

        template.update(sql,
                car.getVinNumber(),
                car.getStatus().name(),
                car.getColor(),
                car.getPurchasePrice(),
                car.getRegistrationFee(),
                car.getMileage(),
                car.getLocation(),
                car.getModelId(),
                regNumber);
    }



    //------------------------------------------------------------------------------------------------------------------

    //Isabella - Se status på biler i dashboard
    public Map<String, Integer> getCarCountByStatus() {
        String sql = "SELECT status, COUNT(*) as count FROM car GROUP BY status";

        return template.query(sql, rs ->
        {
            Map<String, Integer> result = new HashMap<>();  //Vi opretter en tom HashMap hvor data fra databasen kommer ind, status er nøglen og værdien er antallet
            while (rs.next())
            {
                String status = rs.getString("status");
                int count = rs.getInt("count");
                result.put(status, count);
            }
            return result;
        });
    }


    //Isabella - Hvilke biler er udlejet, ledig (Til Dashboard)- Isabella
    public Map<String, Map<String, Integer>> getStatusCountsGroupedByModel() {
        String sql = "SELECT m.model_name, c.status, COUNT(*) AS count " +
                "FROM car c " +
                "JOIN car_model m ON c.model_id = m.model_id " +
                "GROUP BY m.model_name, c.status " +
                "ORDER BY m.model_name, c.status";

        Map<String, Map<String, Integer>> result = new HashMap<>();

        template.query(sql, rs -> {
            while (rs.next()) {
                String modelName = rs.getString("model_name");
                String status = rs.getString("status");
                int count = rs.getInt("count");

                result.computeIfAbsent(modelName, k -> new HashMap<>()).put(status, count);
            }
            return null;
        });

        return result;
    }


    /*Isabella - find pris pr. måned----------------------------------------------------------------------------------*/

    public double findMonthlyPriceByRegistration(String regNumber) {
        String sql = """
        SELECT cm.monthly_price
        FROM car c
        JOIN car_model cm ON c.model_id = cm.model_id
        WHERE c.registration_number = ?
        """;
        return template.queryForObject(sql, Double.class, regNumber);
    }

/*--------------------------------------------------------------------------------------------------------------------*/

}
