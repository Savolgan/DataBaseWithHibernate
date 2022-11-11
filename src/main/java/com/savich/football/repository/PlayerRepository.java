package com.savich.football.repository;


import com.savich.football.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    @Query(value = "SELECT *  FROM player p where p.id_club =:id_club", nativeQuery = true)
    List<Player> findPlayersBiIdClub(@Param("id_club") int id_club);
    List<Player> findByAge(int age);

    @Query("SELECT p FROM Player p where p.age < :playerAge  ")
    List<Player> findPlayerWithAgeLessThan(@Param("playerAge") int playerAge);


}
