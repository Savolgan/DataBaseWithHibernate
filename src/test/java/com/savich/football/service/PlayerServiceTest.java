package com.savich.football.service;

import com.savich.football.model.Club;
import com.savich.football.model.Player;
import com.savich.football.repository.ClubRepository;
import com.savich.football.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class PlayerServiceTest {
    private PlayerService playerService;
    private PlayerRepository playerRepository;

    @BeforeEach
    void init() {
        playerRepository = Mockito.mock(PlayerRepository.class);
        playerService = new PlayerService(playerRepository);
    }

    @Test
    void shouldReturnAllPlayersWhenCallGetAllPlayers() {
        List<Player> playerList = new ArrayList<Player>();
        when(playerRepository.findAll()).thenReturn(playerList);
        List<Player> resultList = playerService.getAllPlayers();
        assertThat(resultList).isEqualTo(playerList);
    }

    @Test
    void shouldReturnPlayerWhenCallGetPlayerById() {
        long id = 3;
        Player player = new Player();
        when(playerRepository.findById(id)).thenReturn(Optional.of(player));
        Player resultPlayer = playerService.getPlayerById(id);
        assertThat(resultPlayer).isEqualTo(player);
    }

    @Test
    void shouldReturnStatusCreatedWhenCallCreatePlayer() {
        Player player = new Player();
        player.setName("CreateNewName");
        player.setAge(35);
        player.setClub(new Club());

        HttpStatus result = playerService.createPlayer(player);
        assertThat(result).isEqualTo(HttpStatus.CREATED);
    }


    @Test
    void shouldUpdatePlayerWhenCallUpdatePlayerById() {
        long id = 3;
       Player player=new Player();
        player.setName("NewName");
        player.setAge(40);

        Optional<Player> playerToFind = playerRepository.findById(id);
        if (playerToFind.isPresent()) {
            Player playerToUpdate = playerToFind.get();
            playerToUpdate.setName(player.getName());
            playerService.updatePlayerById(3L, player);
            verify(playerRepository).save(playerToUpdate);
        }
    }
//    @Test
//    void shouldReturnStatusOkWhenCallUpdatePlayerById() {
//        long id = 3;
//        Player player = new Player();
//        player.setName("NewPlayer");
//        Optional<Player> playerToFind = playerRepository.findById(id);
//
//        if (playerToFind.isPresent()) {
//            Player playerToUpdate = playerToFind.get();
//            player.setName(player.getName());
//            HttpStatus result = playerService.updatePlayerById(id, player);
//            assertThat(result).isEqualTo(HttpStatus.OK);
//        }
//    }
//
//    @Test
//    void shouldReturnStatusCreatedWhenCallUpdatePlayerById() {
//        long id = 3;
//        Player player = new Player();
//        player.setName("NewPlayer");
//        Optional<Player> playerToFind = playerRepository.findById(id);
//
//        if (!playerToFind.isPresent()) {
//            HttpStatus result = playerService.updatePlayerById(id, player);
//            assertThat(result).isEqualTo(HttpStatus.CREATED);
//        }
//    }

    @Test
    void shouldReturnStatusOkWhenCallDeletePlayerById() {
        long id = 3;
        if (playerRepository.existsById(id)) {
            HttpStatus result = playerService.deletePlayerById(id);
            assertThat(result).isEqualTo(HttpStatus.OK);
        }
    }

    @Test
    void shouldReturnStatusNotFoundWhenCallDeletePlayerById() {
        long id = 3;
        if (!playerRepository.existsById(id)) {
            HttpStatus result = playerService.deletePlayerById(id);
            assertThat(result).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }

    @Test
    void shouldReturnPlayersWhenCallGetPlayersByIdClub() {
        int id_club = 1;
        List<Player> playerList = new ArrayList<Player>();
        when(playerRepository.findPlayersBiIdClub(id_club)).thenReturn(playerList);
        List<Player> resultList = playerService.getPlayersByIdClub(id_club);
        assertThat(resultList).isEqualTo(playerList);
    }

    @Test
    void shouldReturnPlayersWhenCallGetPlayersByAge() {
        int age=31;
        List<Player> playerList = new ArrayList<Player>();
        when(playerRepository.findByAge(age)).thenReturn(playerList);
        List<Player> resultList = playerService.getPlayersByAge(age);
        assertThat(resultList).isEqualTo(playerList);
    }

    @Test
    void shouldReturnPlayersWhenCallFindPlayerWithAge() {
        int age=31;
        List<Player> playerList = new ArrayList<Player>();
        when(playerRepository.findPlayerWithAgeLessThan(age)).thenReturn(playerList);
        List<Player> resultList = playerService.findPlayerWithAge(age);
        assertThat(resultList).isEqualTo(playerList);

    }
}