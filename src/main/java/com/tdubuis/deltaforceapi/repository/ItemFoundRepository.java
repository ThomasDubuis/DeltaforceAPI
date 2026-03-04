package com.tdubuis.deltaforceapi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tdubuis.deltaforceapi.entity.ItemFound;

@Repository
public interface ItemFoundRepository extends JpaRepository<ItemFound, Long>
{
	@Query("""
        SELECT r
        FROM ItemFound r
        WHERE r.player.id = COALESCE(:playerId, r.player.id)
        AND r.item.id = COALESCE(:redId, r.item.id)
        AND r.foundAt >= COALESCE(:fromDate, r.foundAt)
        AND r.foundAt <= COALESCE(:toDate, r.foundAt)
        """)
	List<ItemFound> findAllItemFoundWithFilters(@Param("playerId") Long playerId, @Param("redId") Long redId, @Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate);
}
