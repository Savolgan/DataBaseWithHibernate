package com.savich.football.service;

import com.savich.football.model.Club;
import com.savich.football.repository.ClubRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class ClubServiceTest {
    private ClubRepository clubRepository;
    private ClubService clubService;


    @BeforeEach
    void init() {
        clubRepository = Mockito.mock(ClubRepository.class);
        clubService = new ClubService(clubRepository);
    }

    @Test
    void shouldReturnClubWhenCallGetClubById() {
        long id = 3;
        Club club = new Club();
        when(clubRepository.findById(id)).thenReturn(Optional.of(club));
        Club resultClub = clubService.getClubById(id);
        assertThat(resultClub).isEqualTo(club);
    }

    @Test
    void shouldReturnAllClubsWhenCallGetAllClubs() {
        List<Club> clubList = new ArrayList<Club>();
        when(clubRepository.findAll()).thenReturn(clubList);
        List<Club> resultList = clubService.getAllClubs();
        assertThat(resultList).isEqualTo(clubList);
    }

    @Test
    void shouldReturnStatusCreatedWhenCallCreateClub() {
        Club club = new Club();
        club.setName("CreateNewName");
        HttpStatus result = clubService.createClub(club);
        assertThat(result).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void shouldUpdateClubWhenCallUpdateClubById() {
        long id = 3;
        Club club = new Club();
        club.setName("NewName");

        Optional<Club> clubToFind = clubRepository.findById(id);
        if (clubToFind.isPresent()) {
            Club clubToUpdate = clubToFind.get();
            clubToUpdate.setName(club.getName());
           clubService.updateClubById(3L, club);
            verify(clubRepository).save(clubToUpdate);
        }
    }


    @Test
    void shouldReturnStatusOkWhenCallDeleteClubById() {
        long id = 3;
        if (clubRepository.existsById(id)) {
            HttpStatus result = clubService.deleteClubById(id);
            assertThat(result).isEqualTo(HttpStatus.OK);
        }
    }

    @Test
    void shouldReturnStatusNotFoundWhenCallDeleteClubById() {
        long id = 3;
        if (!clubRepository.existsById(id)) {
            HttpStatus result = clubService.deleteClubById(id);
            assertThat(result).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }

    @Test
    void shouldReturnClubsWhenCallGetClubByName() {
        List<Club> clubList = new ArrayList<Club>();
        String name = "Neman";
        when(clubRepository.findByName(name)).thenReturn(clubList);
        List<Club> resultList = clubService.getAllClubs();
        assertThat(resultList).isEqualTo(clubList);
    }
}