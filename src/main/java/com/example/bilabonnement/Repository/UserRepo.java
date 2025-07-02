package com.example.bilabonnement.Repository;

import com.example.bilabonnement.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class UserRepo {

    @Autowired
    private JdbcTemplate template;


    // 1.1.1:  Login-metode i UserRepo, der returnerer komplet User ved korrekt login
    public User login(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND user_password = ?";
        try {
            return template.queryForObject(sql, (rs, rowNum) -> {
                User user = new User(); //vi opretter et tomt user objekt

                //vi udfylder objektet med data fra databasen
                user.setUserId(rs.getInt("user_id"));
                user.setName(rs.getString("username"));
                user.setPassword(rs.getString("user_password"));
                user.setUserRole(User.UserRole.valueOf(rs.getString("user_role")));
                user.setActive(rs.getBoolean("is_active"));
                user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate());

                return user; //det "færdige objekt bliver returneret"
            }, username, password);
        } catch (Exception e) {
            return null;
        }
    }

}






