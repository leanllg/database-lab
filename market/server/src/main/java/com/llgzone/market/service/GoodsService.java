package com.llgzone.market.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.HashMap;

import com.llgzone.market.dao.GoodsMapper;
import com.llgzone.market.dao.LabelsMapper;
import com.llgzone.market.dto.GoodsInfo;
import com.llgzone.market.dao.GlMapper;
import com.llgzone.market.model.Goods;
import com.llgzone.market.model.Labels;
import com.llgzone.market.model.GlKey;


@Service
public class GoodsService {
  @Autowired GoodsMapper goodsMapper;
  @Autowired LabelsMapper labelsMapper;
  @Autowired GlMapper glMapper;


  // 获得商品信息包括标签
  public List<Goods> getGoods(HashMap<String, Object> params) {
    return goodsMapper.selectByParams(params);
  }

  public List<Labels> getLabels() {
    return labelsMapper.selectAll();
  }

  public Labels getLabelByName(String labelName) {
    return labelsMapper.selectByName(labelName);
  }

  @Transactional
  public void addGoods(GoodsInfo gi) throws Exception {
    Integer lid = gi.getLid();
    Integer gid = gi.getGid();

    Labels l = labelsMapper.selectByPrimaryKey(lid);
    if (l == null) {
      throw new Exception("没有这个标签");
    }
    Goods goods = new Goods(gi.getGid(), gi.getUid(), 
    gi.getName(), gi.getDescription(), gi.getImgUrl(), gi.getPay(), gi.getDiscount());
    goodsMapper.insertSelective(goods);
    glMapper.insert(new GlKey(gid, lid)); 
  }

  public void addLabel(String labelName) {
    labelsMapper.insertLabel(labelName);
  }
}