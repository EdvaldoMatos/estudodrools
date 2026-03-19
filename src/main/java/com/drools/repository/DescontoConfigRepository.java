package com.drools.repository;

import com.drools.entity.DescontoConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DescontoConfigRepository extends JpaRepository<DescontoConfigEntity, Long> {
    List<DescontoConfigEntity> findAllByOrderByPrioridadeDesc();
}
