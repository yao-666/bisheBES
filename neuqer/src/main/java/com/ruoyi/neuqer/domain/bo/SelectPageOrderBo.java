package com.ruoyi.neuqer.domain.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author yxy
 * @version 1.0
 * @date 2022/5/6 0:39
 * @apiNote
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("订单列表查询对象")
public class SelectPageOrderBo extends BaseEntity {

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号", required = true)
    @NotBlank(message = "订单编号不能为空", groups = {AddGroup.class, EditGroup.class})
    @Pattern(regexp = "^[0-9]*$", message = "订单编号必须为数字")
    private String orderSn;

    /**
     * 订单状态：0=待发货；1=已发货；2=已完成；3=申请退款；4=已退款；5=拒绝退款；
     */
    @ApiModelProperty(value = "订单状态：0=待发货；1=已发货；2=已完成；3=申请退款；4=已退款；5=拒绝退款；", required = true)
    @NotBlank(message = "订单状态不能为空", groups = {AddGroup.class, EditGroup.class})
    private String orderStatus;


    /**
     * 订单类型：0=普通订单，1=拼团订单，2=秒杀订单
     */
    @ApiModelProperty(value = "订单类型：0=普通订单，1=拼团订单，2=秒杀订单", required = true)
    @NotBlank(message = "订单类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private String orderType;

    /**
     * 配送方式：0=自提；1=同校送货
     */
    @ApiModelProperty(value = "配送方式：0=自提；1=同校送货", required = true)
    @NotBlank(message = "配送方式不能为空", groups = {AddGroup.class, EditGroup.class})
    private String shippingMethod;

    /**
     * 收货人姓名
     */
    @ApiModelProperty(value = "收货人姓名", required = true)
    @NotBlank(message = "收货人姓名不能为空", groups = {AddGroup.class, EditGroup.class})
    private String receiverName;

    /**
     * 收货人电话
     */
    @ApiModelProperty(value = "收货人电话", required = true)
    @Pattern(regexp = "\\d{11}", message = "收货人电话必须为数字，并且长度为11")
    private String receiverPhone;

    /**
     * 收货人地址
     */
    @ApiModelProperty(value = "地址", required = true)
    private String receiverDetailAddress;

    /**
     * 收货人地址
     */
    @ApiModelProperty(value = "地址", required = true)
    private String pickUpAddress;

    /**
     *  产品名称
     */
    @ApiModelProperty(value = "产品名称", required = true)
    private String productName;

    /**
     * 单次数量
     */
    @ApiModelProperty(value = "单次数量", required = true)
    private Integer singleNumber;

    /**
     * 总数量
     */
    @ApiModelProperty(value = "总数量", required = true)
    private Integer totalAmount;
}
