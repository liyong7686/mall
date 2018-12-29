package com.liyong.dao;

import java.util.List;
import java.util.Map;

import com.liyong.model.Goods;

public interface GoodsMapper {

	public Goods findById(String id) ;
 
	public List<Goods> list(Map<String, Object> map); 
	public Integer getTotal(Map<String, Object> map) ;
	
	public Integer add(Goods goods) ;
	public Integer update(Goods goods) ;
}
