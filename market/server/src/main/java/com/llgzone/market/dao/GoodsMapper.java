package com.llgzone.market.dao;

import java.util.HashMap;
import java.util.List;
import com.llgzone.market.model.Goods;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer gid);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer gid);

    List<Goods> selectByParams(HashMap<String, Object> params);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}