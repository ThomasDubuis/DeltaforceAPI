package com.tdubuis.deltaforceapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdubuis.deltaforceapi.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>
{
	List<Player> findByRotationGreaterThan(int oldRotation);

	List<Player> findAllByOrderByRotationAsc();
}