package com.ruoyi.neuqer.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 领取记录视图对象 neuq_take_record
 *
 * @author yxy
 * @date 2022-05-07
 */
@Data
@ApiModel("领取记录视图对象")
@ExcelIgnoreUnannotated
public class NeuqTakeRecordVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 订单明细id
     */
    @ExcelProperty(value = "订单明细id")
    @ApiModelProperty("订单明细id")
    private Long itemId;

    /**
     * 订单编号
     */
    @ExcelProperty(value = "订单编号")
    @ApiModelProperty("订单编号")
    private String orderSn;

    /**
     * 产品名称
     */
    @ExcelProperty(value = "产品名称")
    @ApiModelProperty("产品名称")
    private String productName;

    /**
     * 产品分类
     */
    @ExcelProperty(value = "产品分类")
    @ApiModelProperty("产品分类")
    private String className;

    /**
     * 购物选项
     */
    @ExcelProperty(value = "购物选项")
    @ApiModelProperty("购物选项")
    private String shoppingOptions;

    /**
     * 领取数量
     */
    @ExcelProperty(value = "领取数量")
    @ApiModelProperty("领取数量")
    private Integer takeNumber;

    /**
     * 领取人id
     */
    @ExcelProperty(value = "领取人id")
    @ApiModelProperty("领取人id")
    private String receiverId;


}
