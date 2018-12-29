package com.liyong.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.liyong.model.ResultDTO;
import com.liyong.until.ResponseUtil;

@Controller
@RequestMapping("/upload")
public class UploadController {

    public static final String ROOT = "src/main/resources/static/images/goods/";
	
	@RequestMapping("/image")
	public String manage(HttpServletRequest request, @RequestParam("file") MultipartFile file,HttpServletResponse response) throws Exception {
		ResultDTO result = new ResultDTO();
		
		System.out.println("上传图片。。。。。。。。");
		
		System.out.println("---" + file.getOriginalFilename());
		
		Path path = Paths.get(ROOT, file.getOriginalFilename());
		
		System.out.println("path:");
		System.out.println(path.toString());
		
        File tempFile = new File(path.toString());
        if (!tempFile.exists()) {
            Files.copy(file.getInputStream(), path);
        }
        LinkedHashMap<String, Object> metaMap = new LinkedHashMap<String, Object>();
        String id = null;
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 1000;
        id = String.valueOf(rannum);
        metaMap.put("contentType", "jpg");
        metaMap.put("_id", id);
      //  MongoUtil.uploadFile(tempFile, id, metaMap);

		
		Gson gson = new Gson();
		result.setStatus(1);
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
}
