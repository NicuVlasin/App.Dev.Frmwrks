package ie.cit.soft8027.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ie.cit.soft8027.domain.Equipment;


public class EquipmentRowMapper  implements RowMapper<Equipment>
{
    @Override
    public Equipment mapRow(ResultSet rs, int index) throws SQLException
    {
        Equipment equipment = new Equipment();

        equipment.setId(rs.getInt("id"));
        equipment.setName(rs.getString("name"));
        equipment.setLevel(rs.getInt("level"));
        equipment.setDamage(rs.getInt("damage"));
        equipment.setProtection(rs.getInt("protection"));
        equipment.setPrice(rs.getInt("price"));
        equipment.setType(rs.getString("type"));

        return equipment;
    }
}
