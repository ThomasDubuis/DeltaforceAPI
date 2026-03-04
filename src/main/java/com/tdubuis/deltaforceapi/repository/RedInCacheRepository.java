package com.tdubuis.deltaforceapi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tdubuis.deltaforceapi.dto.RedItemInCacheWithLvlDTO;
import com.tdubuis.deltaforceapi.entity.RedInCache;
import com.tdubuis.deltaforceapi.entity.RedInCacheId;
import com.tdubuis.deltaforceapi.entity.RedLvlInCache;

@Repository
public interface RedInCacheRepository extends JpaRepository<RedInCache, RedInCacheId>
{
	@Query("""
        SELECT new com.tdubuis.deltaforceapi.dto.RedItemInCacheWithLvlDTO(r.player.id, r.redItem, r.lvl)
        FROM RedInCache r
        WHERE (:playerId IS NULL OR r.player.id = :playerId)
        AND (:redId IS NULL OR r.redItem.id = :redId)
        AND (:lvl IS NULL OR r.lvl = :lvl)
        """)
	List<RedItemInCacheWithLvlDTO> findAllRedInCacheWithFilters(@Param("playerId") Long playerId, @Param("redId") Long redId, @Param("lvl") RedLvlInCache lvl);
}
