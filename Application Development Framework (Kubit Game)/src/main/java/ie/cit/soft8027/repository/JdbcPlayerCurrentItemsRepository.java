package ie.cit.soft8027.repository;

import ie.cit.soft8027.domain.PlayerCurrentItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Repository
public class JdbcPlayerCurrentItemsRepository implements PlayerCurrentItemsRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPlayerCurrentItemsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PlayerCurrentItems get(int id) {
        String sql = "SELECT * FROM player_current_items WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new PlayerCurrentItemsRowMapper());
    }

    @Override
    public PlayerCurrentItems getPlayerItems(int playerId) {
        String sql = "SELECT * FROM player_current_items WHERE player_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{playerId}, new PlayerCurrentItemsRowMapper());
    }

    @Override
    public void save(PlayerCurrentItems currentItems) {
        if (currentItems.getId() != 0) {
            update(currentItems);
        } else {
            //all players should have a PlayerCurrentItems, no need to create one programmatically
            throw new NotImplementedException();
        }
    }

    private void update(PlayerCurrentItems currentItems) {
        String sql = "UPDATE player_current_items SET melee = ?, range_w = ?, armour = ?, shield=? WHERE id = ?";
        jdbcTemplate.update(sql, currentItems.getMelee(), currentItems.getRangeW(),
                currentItems.getArmour(), currentItems.getShield(), currentItems.getId());
    }


    @Override
    public void remove(PlayerCurrentItems equipment) {
        String sql = "DELETE player_current_items WHERE id = ?";
        jdbcTemplate.update(sql, new Object[]{equipment.getId()});
    }

    @Override
    public List<PlayerCurrentItems> findAll() {
        String sql = "SELECT * FROM player_current_items";
        return jdbcTemplate.query(sql, new PlayerCurrentItemsRowMapper());
    }
}