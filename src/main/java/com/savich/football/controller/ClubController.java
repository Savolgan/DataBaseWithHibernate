package com.savich.football.controller;

import com.savich.football.model.Club;
import com.savich.football.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/clubs")
public class ClubController {
    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        ResponseEntity responseEntity = new ResponseEntity<>(clubService.getAllClubs(), HttpStatus.OK);
        System.out.println(responseEntity);
        return responseEntity;
    }

    @GetMapping("{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
        Club club = clubService.getClubById(id);
        if (club != null) {
            return new ResponseEntity<>(club, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }


    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        return new ResponseEntity<>(clubService.createClub(club));
    }

    @PutMapping("{id}")
    public void updateClubById(@PathVariable Long id, @RequestBody Club club) {
        clubService.updateClubById(id, club);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Club> deleteClubById(@PathVariable Long id) {
        return new ResponseEntity<>(clubService.deleteClubById(id));
    }

    @GetMapping("/name/{name}")
    public List<Club> getByName(@PathVariable(value = "name") String name) {
        return clubService.getClubByName(name);
    }

//    @PostMapping("/new-club")
//    public Club generateClub(){
//
//            return clubService.generateRandomClub();
//        }

}
