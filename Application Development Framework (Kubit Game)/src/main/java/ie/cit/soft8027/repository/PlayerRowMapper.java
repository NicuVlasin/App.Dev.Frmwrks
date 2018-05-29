package ie.cit.soft8027.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import ie.cit.soft8027.domain.Player;



public class PlayerRowMapper implements RowMapper<Player>
{

    @Override
    public Player mapRow(ResultSet rs, int index) throws SQLException
    {
        Player player = new Player();

        player.setId(rs.getInt("id"));
        player.setFullName(rs.getString("fullName"));
        player.setPassword(rs.getString("password"));
        player.setBalance(rs.getInt("balance"));

        return player;
    }
}