package com.market.trade.model.projection;

import com.market.trade.model.BaseEntity;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "baseEntityProjection", types = BaseEntity.class)
public interface BaseEntityProjection {

    Long getId();
}
