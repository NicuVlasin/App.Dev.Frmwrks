package ie.cit.soft8027.repository;

import ie.cit.soft8027.domain.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Repository
public class JdbcEquipmentRepository implements EquipmentRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcEquipmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Equipment get(int id) {
        try {
            String sql = "SELECT * FROM equipment WHERE id = ?";
            Equipment equipment = jdbcTemplate.queryForObject(sql, new Object[]{id}, new EquipmentRowMapper());
            return equipment;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("");
            return null;
        }
    }

    @Override
    public Equipment findByTypeAndLevel(String type, int level) {
        try {
            String sql = "SELECT * FROM equipment WHERE type = ? AND level= ?";
            Equipment equipment = jdbcTemplate.queryForObject(sql, new Object[]{type, level}, new EquipmentRowMapper());
            return equipment;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("");
            return null;
        }
    }

    @Override
    public void save(Equipment equipment) {
        //you cannot programmatically add new equipment into the system
        throw new NotImplementedException();
    }


    @Override
    public void remove(Equipment equipment) {
        String sql = "DELETE equipment WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{equipment.getId()});
    }

    @Override
    public List<Equipment> findAll() {
        String sql = "SELECT * FROM equipment";
        return jdbcTemplate.query(sql, new EquipmentRowMapper());
    }
}