package com.savich.football.controller;

import com.savich.football.model.Club;
import com.savich.football.model.Player;
import com.savich.football.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/players")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        return new ResponseEntity<>
                (playerService.getAllPlayers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
        if (player != null) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        return new ResponseEntity<>(playerService.createPlayer(player));
    }

    @PutMapping("{id}")
    public void updatePlayerById(@PathVariable Long id, @RequestBody Player player) {
        playerService.updatePlayerById(id, player);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Club> deletePlayerById(@PathVariable Long id) {
        return new ResponseEntity<>(playerService.deletePlayerById(id));
    }

    @GetMapping("/get-players/{id_club}")
    public List<Player> getPlayersByIdClub(@PathVariable(value = "id_club") int id_club)  {
        return playerService.getPlayersByIdClub(id_club);
    }

    @GetMapping("/get-age/{age}")
    public List<Player> findPlayersByAge(@PathVariable(value = "age") int age)  {
        return playerService.getPlayersByAge(age);
    }

    @GetMapping("/get-age-less")
    public List<Player> findPlayersAgeLess(int age)   {
        return playerService.findPlayerWithAge(age);
    }

}
