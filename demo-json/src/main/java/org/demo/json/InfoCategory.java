package org.demo.json;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 信息类目
 * </p>
 *
 * @author xiebk
 * @since 2022-07-19
 */
@Getter
@Setter
@Accessors(chain = true)
public class InfoCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long categoryId;

    /**
     * 类目名称
     */
    private String categoryName;

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
