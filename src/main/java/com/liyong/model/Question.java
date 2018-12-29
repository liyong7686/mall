package com.liyong.model;

import lombok.Data;

@Data
public class Question {

	private String id;
    private Integer userId;
    private String title;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private Long gmtCreate;
    private Long gmtModified;
    private Byte status;
    private String content;

}
