package com.llgzone.market.dao;

import com.llgzone.market.model.Labels;
import java.util.List;

public interface LabelsMapper {
    int deleteByPrimaryKey(Integer lid);

    int insert(Labels record);

    int insertSelective(Labels record);

    int insertLabel(String lname);

    Labels selectByPrimaryKey(Integer lid);

    List<Labels> selectAll();

    Labels selectByName(String lname);

    int updateByPrimaryKeySelective(Labels record);

    int updateByPrimaryKey(Labels record);
}