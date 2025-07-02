package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarModelRepo {

    @Autowired
    private JdbcTemplate template;

    // Hent alle modeller
    public List<CarModel> fetchAll() {
        String sql = "SELECT * FROM car_model";
        RowMapper<CarModel> rowMapper = new BeanPropertyRowMapper<>(CarModel.class);
        return template.query(sql, rowMapper);
    }

    /*/ Find én model ud fra ID
    public CarModel findById(int modelId) {
        String sql = "SELECT * FROM car_model WHERE model_id = ?";
        RowMapper<CarModel> rowMapper = new BeanPropertyRowMapper<>(CarModel.class);
        return template.queryForObject(sql, rowMapper, modelId);
    }

    // Tilføj ny model – med monthly_price
    public void addCarModel(CarModel model) {
        String sql = "INSERT INTO car_model (brand, model_name, equipment_level, co2_emission, monthly_price) VALUES (?, ?, ?, ?, ?)";
        template.update(sql,
                model.getBrand(),
                model.getModelName(),
                model.getEquipmentLevel(),
                model.getCo2Emission(),
                model.getMonthlyPrice());
    }

    // Opdater model – med monthly_price
    public void updateCarModel(CarModel model) {
        String sql = "UPDATE car_model SET brand = ?, model_name = ?, equipment_level = ?, co2_emission = ?, monthly_price = ? WHERE model_id = ?";
        template.update(sql,
                model.getBrand(),
                model.getModelName(),
                model.getEquipmentLevel(),
                model.getCo2Emission(),
                model.getMonthlyPrice(),
                model.getModelId());
    }

    // Slet model
    public boolean deleteById(int modelId) {
        String sql = "DELETE FROM car_model WHERE model_id = ?";
        return template.update(sql, modelId) > 0;
    }*/
}
