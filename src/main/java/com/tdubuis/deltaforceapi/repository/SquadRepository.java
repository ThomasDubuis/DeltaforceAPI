package com.tdubuis.deltaforceapi.repository;

import com.tdubuis.deltaforceapi.entity.Squad.Squad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SquadRepository extends JpaRepository<Squad, UUID>
{

	@Query("""
		SELECT s FROM Squad s
		WHERE s.player1.id = :playerId
		OR s.player2.id = :playerId
		OR s.player3.id = :playerId
		""")
	List<Squad> findAllByPlayerId(@Param("playerId") UUID playerId);

	Optional<Squad> findSquadById(UUID id);

	Optional<Squad> findSquadByJoiningKey(UUID joiningKey);
}
