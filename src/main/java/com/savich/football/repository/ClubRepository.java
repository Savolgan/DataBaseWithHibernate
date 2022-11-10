package com.savich.football.repository;

import com.savich.football.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {

    List<Club> findByName(String str);

}
