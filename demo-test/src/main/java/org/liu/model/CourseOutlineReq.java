package org.liu.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CourseOutlineReq {
    /**
     * 课程大纲名称
     */
    private String name;
    /**
     * 介绍
     */
    private String remark;
    /**
     * 章节URL，音频课是视频地址，图文课是内容地址，直播课是录像存放地址
     */
    private String sectionUrl;
    /**
     * 开始直播时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private Date startBroadcastingTime;
    /**
     * 结束直播时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private Date endBroadcastingTime;
    /**
     * 章节号
     */
    private Integer sectionNumber;
    /**
     * 课程大纲父id（为0则是根大纲）
     */
    private Long parentId;
    /**
     * 课程大纲学习顺序
     */
    private Integer learningOrder;
    /**
     * 是否试学（0-否，1-是）
     */
    private Integer isTrial;
    /**
     * 课程大纲的节列表
     */
    private List<CourseOutlineReq> paragraphList;
}
