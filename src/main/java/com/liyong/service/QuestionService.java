package com.liyong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liyong.dao.QuestionMapper;
import com.liyong.model.Question;
import com.liyong.until.SessionUtil;

@Service
public class QuestionService {

	@Autowired
    private QuestionMapper questionMapper;

    public void createQuestion(Question question) {
        question.setGmtModified(System.currentTimeMillis());
        question.setGmtCreate(question.getGmtModified());
        question.setUserId(SessionUtil.getUser().getId());
        questionMapper.insertSelective(question);
    }

    public List<Question> list(Integer page, Integer size) {
    	Question question = new Question();
        return questionMapper.selectList(question);
    }
}
