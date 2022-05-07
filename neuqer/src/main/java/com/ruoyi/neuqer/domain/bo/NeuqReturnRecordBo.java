package com.ruoyi.neuqer.domain.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 领取记录业务对象 neuq_return_record
 *
 * @author yxy
 * @date 2022-05-07
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("领取记录业务对象")
public class NeuqReturnRecordBo extends BaseEntity {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 订单明细id
     */
    @ApiModelProperty(value = "订单明细id", required = true)
    @NotNull(message = "订单明细id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long itemId;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号", required = true)
    @NotBlank(message = "订单编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orderSn;

    /**
     * 产品名称
     */
    @ApiModelProperty(value = "产品名称", required = true)
    @NotBlank(message = "产品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productName;

    /**
     * 产品分类
     */
    @ApiModelProperty(value = "产品分类", required = true)
    @NotBlank(message = "产品分类不能为空", groups = { AddGroup.class, EditGroup.class })
    private String className;

    /**
     * 购物选项
     */
    @ApiModelProperty(value = "购物选项", required = true)
    @NotBlank(message = "购物选项不能为空", groups = { AddGroup.class, EditGroup.class })
    private String shoppingOptions;

    /**
     * 领取数量
     */
    @ApiModelProperty(value = "领取数量", required = true)
    @NotNull(message = "领取数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer returnNumber;

    /**
     * 领取人id
     */
    @ApiModelProperty(value = "领取人id", required = true)
    private String receiverId;


}
