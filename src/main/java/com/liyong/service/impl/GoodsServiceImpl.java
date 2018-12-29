package com.liyong.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyong.dao.GoodsMapper;
import com.liyong.model.Goods;
import com.liyong.service.IGoodsService;
import com.liyong.until.CreateTablePrimaryId;



@Service
public class GoodsServiceImpl implements IGoodsService{
	
	@Autowired
	private GoodsMapper goodsMapper;

	public Goods findById(String id) {
		return goodsMapper.findById(id);
	}
 
	public List<Goods> list(Map<String, Object> map) {
		return goodsMapper.list(map);
	}

	public Integer getTotal(Map<String, Object> map) {
		return goodsMapper.getTotal(map);
	}
	
	public Integer addOrupdate(Goods goods) {
		if(goods.getId() != null && !goods.getId().equals("")){
			System.out.println("-----" + goods.getId());
			return goodsMapper.update(goods);
		}else{
			System.out.println("----add------------" );
			goods.setId(CreateTablePrimaryId.tableId("t_goods"));
			return goodsMapper.add(goods);
		}		
	}
	
	public Integer delete(String id){
		Goods goods = goodsMapper.findById(id);
		goods.setActivity(0);
		return goodsMapper.update(goods);
	}
	

	 
}
