package com.tdubuis.deltaforceapi.repository;

import com.tdubuis.deltaforceapi.entity.redInCache.RedInCache;
import com.tdubuis.deltaforceapi.entity.redInCache.RedInCacheId;
import com.tdubuis.deltaforceapi.entity.redInCache.RedLvlInCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RedInCacheRepository extends JpaRepository<RedInCache, RedInCacheId>
{
	@Query("""
        SELECT r
        FROM RedInCache r
        WHERE (:playerId IS NULL OR r.player.id = :playerId)
        AND (:redId IS NULL OR r.redItem.id = :redId)
        AND (:lvl IS NULL OR r.lvl = :lvl)
        """)
	List<RedInCache> findAllRedInCacheWithFilters(@Param("playerId") UUID playerId, @Param("redId") UUID redId, @Param("lvl") RedLvlInCache lvl);
}
