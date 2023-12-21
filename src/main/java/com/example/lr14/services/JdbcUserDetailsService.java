package com.example.lr14.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUserDetailsService implements UserDetailsService {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDetailsService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String query = "SELECT username, password, enabled FROM users WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{username}, (rs, rowNum) -> {
                String fetchedUsername = rs.getString("username");
                String password = rs.getString("password");
                boolean enabled = rs.getBoolean("enabled");
                return User.withUsername(fetchedUsername)
                        .password(password)
                        .disabled(!enabled)
                        .build();
            });
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

}
