package org.demo.json;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 信息发布列表
 * </p>
 *
 * @author xiebk
 * @since 2022-07-19
 */
@Getter
@Setter
@Accessors(chain = true)
public class InfoPublish implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long publishId;

    /**
     * 类目id
     */
    private Long categoryId;

    /**
     * 标题
     */
    private String title;

    /**
     * 公示开始时间
     */
    private LocalDateTime startTime;

    /**
     * 公式结束时间
     */
    private LocalDateTime endTime;

    /**
     * 内容
     */
    private String noticeInfo;

    /**
     * 落款
     */
    private String inscribed;

    /**
     * 图片地址
     */
    private String picUrls;

    /**
     * 文件地址
     */
    private String fileUrls;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String delFlag;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
