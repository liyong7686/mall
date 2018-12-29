package com.liyong.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liyong.error.CommonErrorCode;
import com.liyong.model.Question;
import com.liyong.model.ResultDTO;
import com.liyong.service.IQuestionService;

//微信小程序接口
@RestController
@RequestMapping("/api")
public class QuestionController {
	
    private final Logger log = LoggerFactory.getLogger(LoginController.class);
    
	@Resource
    private IQuestionService questionService;

    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public ResultDTO post(@RequestBody Question question) {
        try {
            questionService.createQuestion(question);
            return ResultDTO.ok(null);
        } catch (Exception e) {
            log.error("QuestionController post error, question : {}", question, e);
            return ResultDTO.fail(CommonErrorCode.UNKOWN_ERROR);
        }
    }

    @RequestMapping(value = "/question/list", method = RequestMethod.GET)
    public ResultDTO list(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size) {
        try {
            List<Question> questions = questionService.list(page, size);
            return ResultDTO.ok(questions);
        } catch (Exception e) {
            log.error("QuestionController post error", e);
            return ResultDTO.fail(CommonErrorCode.UNKOWN_ERROR);
        }
    }
}
