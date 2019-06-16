package com.llgzone.market.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

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
  @Transactional
  public List<Goods> getGoods(HashMap<String, Object> params) {
    List<Goods> goodsUser = goodsMapper.selectByParams(params);
    for (Goods goods : goodsUser) {
      goods.setLabels(new ArrayList<Labels>());
      int gid = goods.getGid();
      GlKey gl = new GlKey();
      gl.setGid(gid);
      if (params.containsKey("lid")) {
        gl.setLid((Integer)params.get("lid"));
      }
      goods.setLabels(labelsMapper.selectByGl(gl));
    }
    return goodsUser;
  }

  public List<Labels> getLabels() {
    return labelsMapper.selectAll();
  }

  public Labels getLabelByName(String labelName) {
    return labelsMapper.selectByName(labelName);
  }

  @Transactional
  public void addGoods(GoodsInfo gi) throws Exception {
    List<Integer> lids = gi.getLid();
    Goods goods = new Goods(gi.getGid(), gi.getUid(), 
    gi.getName(), gi.getDescription(), gi.getImgUrl(), gi.getPay(), gi.getDiscount());
    goodsMapper.insertSelective(goods);
    System.out.println(goods.getGid());
    Integer gid = goods.getGid();
    for (Integer lid : lids) {
      Labels l = labelsMapper.selectByPrimaryKey(lid);
      if (l == null) {
        throw new Exception("没有这个标签");
      }
      glMapper.insert(new GlKey(gid, lid)); 
    }


  }

  public void addLabel(List<String> labelName) {
    labelsMapper.insertLabel(labelName);
  }
}