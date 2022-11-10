package com.savich.football.service;

import com.savich.football.model.Club;
import com.savich.football.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ClubService {
    private final ClubRepository clubRepository;
    private static final List<String> CLABNAMES = Arrays.asList("Nnnn", "Mmmmm", "Kkkkk", "Ppppppp", "Llllll");
    private final Random random = new Random();

    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }


    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Club getClubById(Long id) {
        return clubRepository.findById(id).orElseThrow(() -> new RuntimeException("No club with this id"));
    }

    public HttpStatus createClub(Club club) {
        clubRepository.save(club);
        return HttpStatus.CREATED;
    }

    public void updateClubById(Long id, Club club) {
        Optional<Club> clubToUpdateOptional = clubRepository.findById(id);
        Club clubToUpdate = clubToUpdateOptional.get();
        clubToUpdate.setName(club.getName());
        clubRepository.save(clubToUpdate);
    }

    public HttpStatus deleteClubById(Long id) {
        if (clubRepository.existsById(id)) {
            clubRepository.deleteById(id);
            return HttpStatus.OK;
        }

        return HttpStatus.NOT_FOUND;
    }

    public List<Club> getClubByName(String name) {

        return clubRepository.findByName(name);
    }

//    public Club generateRandomClub() {
//        Club club = new Club();
//        club.setName(CLABNAMES.get(random.nextInt(CLABNAMES.size())));
//        clubRepository.save(club);
//        return club;
//    }


}
