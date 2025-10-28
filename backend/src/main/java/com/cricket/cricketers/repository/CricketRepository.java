package com.cricket.cricketers.repository;

import com.cricket.cricketers.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CricketRepository extends JpaRepository<Player,Long> {
}
