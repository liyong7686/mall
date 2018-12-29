package com.liyong.service;

import com.liyong.model.Config;

public interface IConfigService {

	public Integer update(Config config);
	public Config findById(Integer id);
}
