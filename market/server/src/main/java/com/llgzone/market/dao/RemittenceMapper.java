package com.llgzone.market.dao;

import com.llgzone.market.model.Remittence;

public interface RemittenceMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Remittence record);

    int insertSelective(Remittence record);

    Remittence selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Remittence record);

    int updateByPrimaryKeyWithBLOBs(Remittence record);

    int updateByPrimaryKey(Remittence record);
}