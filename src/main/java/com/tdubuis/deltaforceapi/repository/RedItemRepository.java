package com.tdubuis.deltaforceapi.repository;

import com.tdubuis.deltaforceapi.entity.redItem.RedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RedItemRepository extends JpaRepository<RedItem, UUID>
{}
