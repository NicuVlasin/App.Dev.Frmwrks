package ie.cit.soft8027.repository;

import java.util.List;
import ie.cit.soft8027.domain.Player;

public interface PlayerRepository
{

    public Player get(int id);

    public Player findByFullName(String fullName);

    public void save(Player player);

    public void remove(Player player);

    public List<Player> findAll();
}