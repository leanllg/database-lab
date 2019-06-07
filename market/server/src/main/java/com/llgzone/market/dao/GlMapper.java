package com.llgzone.market.dao;

import com.llgzone.market.model.GlKey;

public interface GlMapper {
    int deleteByPrimaryKey(GlKey key);

    int insert(GlKey record);

    int insertSelective(GlKey record);
}