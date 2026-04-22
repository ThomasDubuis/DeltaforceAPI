package com.tdubuis.deltaforceapi.repository;

import com.tdubuis.deltaforceapi.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID>
{
	Optional<Player> findPlayerByEmail(String email);
}