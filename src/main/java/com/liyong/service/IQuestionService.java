package com.liyong.service;

import java.util.List;

import com.liyong.model.Question;

public interface IQuestionService {
	public void createQuestion(Question question);

    public List<Question> list(Integer page, Integer size);
}
