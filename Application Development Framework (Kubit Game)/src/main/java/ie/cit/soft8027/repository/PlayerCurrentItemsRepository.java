package ie.cit.soft8027.repository;

import ie.cit.soft8027.domain.PlayerCurrentItems;

import java.util.List;


public interface PlayerCurrentItemsRepository {
    public PlayerCurrentItems get(int id);

    public PlayerCurrentItems getPlayerItems(int playerId);

    public void save(PlayerCurrentItems equipment);

    public void remove(PlayerCurrentItems equipment);

    public List<PlayerCurrentItems> findAll();
}