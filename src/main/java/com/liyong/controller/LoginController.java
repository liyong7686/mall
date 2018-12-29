package com.liyong.controller;

import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.liyong.adapter.WechatAdapter;
import com.liyong.error.CommonErrorCode;
import com.liyong.error.ErrorCodeException;
import com.liyong.model.LoginDTO;
import com.liyong.model.ResultDTO;
import com.liyong.model.SessionDTO;
import com.liyong.model.TokenDTO;
import com.liyong.model.User;
import com.liyong.service.UserService;
import com.liyong.until.DigestUtil;

//微信小程序接口
@RestController
@RequestMapping("/api")
public class LoginController {

    private final Logger log = LoggerFactory.getLogger(LoginController.class);

    // Spring 自动注入 wechatAdapter，因 WechatAdapter 类上面有 @Service 注解
    @Resource
    private WechatAdapter wechatAdapter;
	@Resource
	private UserService  userService;
    
	@RequestMapping("/login")
    public ResultDTO login(@RequestBody LoginDTO loginDTO) {
        try {
            log.info("login request : {}", loginDTO);
            
            // 使用 code 调用微信 API 获取 session_key 和 openid
            SessionDTO sessionDTO = wechatAdapter.jscode2session(loginDTO.getCode());
            log.info("login get session : {}", sessionDTO);

            // 检验传递过来的使用户信息是否合法
            DigestUtil.checkDigest(loginDTO.getRawData(), sessionDTO.getSessionKey(), loginDTO.getSignature());
            String token = UUID.randomUUID().toString();

            User user = JSON.parseObject(loginDTO.getRawData(), User.class);
            user.setToken(token);
            user.setOpenId(sessionDTO.getOpenid());
            userService.saveOrUpdate(user);
            //生成token，用于自定义登录态，这里的存储逻辑比较复杂，放到下一讲
            TokenDTO data = new TokenDTO();
            data.setToken(token);
            return ResultDTO.ok(data);
        } catch (ErrorCodeException e) {
            log.error("login error, info : {}", loginDTO, e.getMessage());
            return ResultDTO.fail(e);
        } catch (Exception e) {
            log.error("login error, info : {}", loginDTO, e);
            return ResultDTO.fail(CommonErrorCode.UNKOWN_ERROR);
        }
    }
	
	
	
}
