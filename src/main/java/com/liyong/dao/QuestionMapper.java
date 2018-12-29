package com.liyong.dao;

import java.util.List;

import com.liyong.model.Question;

public interface QuestionMapper {

	public int insertSelective(Question question);
	public List<Question> selectList(Question question);
	
}
