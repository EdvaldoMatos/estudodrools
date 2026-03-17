package com.drools.mapper;

import com.drools.domain.DescontoConfig;
import com.drools.entity.DescontoConfigEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DescontoConfigMapper {

    DescontoConfig toDomain(DescontoConfigEntity entity);

    List<DescontoConfig> toDomainList(List<DescontoConfigEntity> entities);
}
