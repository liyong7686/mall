package com.liyong.service;

import java.util.List;
import java.util.Map;

import com.liyong.model.Goods;

public interface IGoodsService {

	public Goods findById(String id);
 
	public List<Goods> list(Map<String, Object> map);

	public Integer getTotal(Map<String, Object> map);
	
	public Integer addOrupdate(Goods goods);
	
	public Integer delete(String id);
	
}
