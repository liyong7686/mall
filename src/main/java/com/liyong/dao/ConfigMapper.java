package com.liyong.dao;

import com.liyong.model.Config;

public interface ConfigMapper {

	public Integer update(Config config);
	
	public Config findById(Integer id);
}
