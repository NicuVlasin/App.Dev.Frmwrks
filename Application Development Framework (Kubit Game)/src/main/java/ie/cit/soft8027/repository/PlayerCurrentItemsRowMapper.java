package ie.cit.soft8027.repository;

import ie.cit.soft8027.domain.PlayerCurrentItems;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PlayerCurrentItemsRowMapper implements RowMapper<PlayerCurrentItems> {
    @Override
    public PlayerCurrentItems mapRow(ResultSet rs, int index) throws SQLException {
        PlayerCurrentItems currentItems = new PlayerCurrentItems();

        currentItems.setId(rs.getInt("id"));
        currentItems.setPlayerId(rs.getInt("player_id"));

        currentItems.setArmour(rs.getInt("armour"));
        currentItems.setMelee(rs.getInt("melee"));
        currentItems.setRangeW(rs.getInt("range_w"));
        currentItems.setShield(rs.getInt("shield"));

        return currentItems;
    }
}
