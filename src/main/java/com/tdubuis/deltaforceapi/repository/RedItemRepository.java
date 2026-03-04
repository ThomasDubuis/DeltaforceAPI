package com.tdubuis.deltaforceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdubuis.deltaforceapi.entity.RedItem;

@Repository
public interface RedItemRepository extends JpaRepository<RedItem, Long>
{
}
