package com.savich.football.service;

import com.savich.football.model.Club;
import com.savich.football.model.Player;
import com.savich.football.repository.PlayerRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElseThrow(() -> new RuntimeException("No player with this id"));
    }

    public HttpStatus createPlayer(Player player) {
        playerRepository.save(player);
        return HttpStatus.CREATED;
    }

    public void updatePlayerById(Long id, @NotNull Player player) {
        Optional<Player> playerToFindOptional = playerRepository.findById(id);

        Player playerToUpdate = playerToFindOptional.get();
        playerToUpdate.setName(player.getName());
        playerToUpdate.setAge(player.getAge());
        playerRepository.save(playerToUpdate);
    }

    public HttpStatus deletePlayerById(Long id) {
        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
            return HttpStatus.OK;
        }

        return HttpStatus.NOT_FOUND;
    }

    public List<Player> getPlayersByIdClub(int id_club) {

        return playerRepository.findPlayersBiIdClub(id_club);
    }

    public List<Player> getPlayersByAge(int age) {

        return playerRepository.findByAge(age);
    }

    public List<Player> findPlayerWithAge(int age) {

        return playerRepository.findPlayerWithAgeLessThan(age);
    }
}
