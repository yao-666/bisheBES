package com.ruoyi.neuqer.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 领取记录对象 neuq_return_record
 *
 * @author yxy
 * @date 2022-05-07
 */
@Data
@TableName("neuq_return_record")
public class NeuqReturnRecord extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 订单明细id
     */
    private Long itemId;
    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品分类
     */
    private String className;
    /**
     * 购物选项
     */
    private String shoppingOptions;
    /**
     * 领取数量
     */
    private Integer returnNumber;
    /**
     * 领取人id
     */
    private String receiverId;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

}
