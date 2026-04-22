package com.tdubuis.deltaforceapi.repository;

import com.tdubuis.deltaforceapi.entity.ItemFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ItemFoundRepository extends JpaRepository<ItemFound, UUID>
{
	@Query("""
        SELECT r
        FROM ItemFound r
        WHERE r.player.id = COALESCE(:playerId, r.player.id)
        AND r.item.id = COALESCE(:redId, r.item.id)
        AND r.foundAt >= COALESCE(:fromDate, r.foundAt)
        AND r.foundAt <= COALESCE(:toDate, r.foundAt)
        AND (:squadId IS NULL OR r.squad.id = :squadId)
        """)
	List<ItemFound> findAllItemFoundWithFilters(@Param("playerId") UUID playerId, @Param("redId") UUID redId, @Param("squadId") UUID squadId, @Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate);
}
