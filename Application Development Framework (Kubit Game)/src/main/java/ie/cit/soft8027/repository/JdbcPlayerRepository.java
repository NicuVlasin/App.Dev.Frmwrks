package ie.cit.soft8027.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ie.cit.soft8027.domain.Player;

@Repository
public class JdbcPlayerRepository implements PlayerRepository
{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPlayerRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Player get(int id) {
        String sql = "SELECT * FROM player WHERE id = ?";
        Player player = jdbcTemplate.queryForObject(sql, new Object[] { id }, new PlayerRowMapper());

        return player;
    }

    @Override
    public Player findByFullName(String fullName) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM player WHERE fullName = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { fullName }, new PlayerRowMapper());
    }

    @Override
    public void save(Player player)
    {
        if (player.getId() != 0) {
            update(player);
        } else {
            add(player);
        }
    }

    private void update(Player player)
    {
        String sql = "UPDATE player SET fullName = ?, password = ?, balance = ? WHERE id = ?";
        jdbcTemplate.update(sql, player.getId(), player.getFullName(),
                player.getPassword(),player.getBalance());

    }


    private void add(Player player) {
        String sql = "INSERT INTO player (id, fullName, password, balance) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                new Object[] {player.getId(), player.getFullName(), player.getPassword(), player.getBalance()} );
    }


    @Override
    public void remove(Player player) {
        String sql = "DELETE player WHERE id = ?";
        jdbcTemplate.update(sql, new Object[] { player.getId() } );

    }

    @Override
    public List<Player> findAll() {
        String sql = "SELECT * FROM player";
        return jdbcTemplate.query(sql, new PlayerRowMapper());

    }

}