package com.ruoyi.neuqer.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.core.validate.QueryGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.neuqer.domain.NeuqOrderItem;
import com.ruoyi.neuqer.domain.bo.NeuqTakeRecordBo;
import com.ruoyi.neuqer.domain.vo.NeuqTakeRecordVo;
import com.ruoyi.neuqer.service.INeuqOrderItemService;
import com.ruoyi.neuqer.service.INeuqTakeRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 领取记录Controller
 *
 * @author yxy
 * @date 2022-05-07
 */
@Validated
@Api(value = "领取记录控制器", tags = {"领取记录管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/neuqer/takeRecord")
public class NeuqTakeRecordController extends BaseController {

    private final INeuqTakeRecordService iNeuqTakeRecordService;
    private final INeuqOrderItemService iNeuqOrderItemService;

    /**
     * 查询领取记录列表
     */
    @ApiOperation("查询领取记录列表")
    @SaCheckPermission("neuqer:takeRecord:list")
    @GetMapping("/list")
    public TableDataInfo<NeuqTakeRecordVo> list(@Validated(QueryGroup.class) NeuqTakeRecordBo bo, PageQuery pageQuery) {
        return iNeuqTakeRecordService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出领取记录列表
     */
    @ApiOperation("导出领取记录列表")
    @SaCheckPermission("neuqer:takeRecord:export")
    @Log(title = "领取记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated NeuqTakeRecordBo bo, HttpServletResponse response) {
        List<NeuqTakeRecordVo> list = iNeuqTakeRecordService.queryList(bo);
        ExcelUtil.exportExcel(list, "领取记录", NeuqTakeRecordVo.class, response);
    }

    /**
     * 获取领取记录详细信息
     */
    @ApiOperation("获取领取记录详细信息")
    @SaCheckPermission("neuqer:takeRecord:query")
    @GetMapping("/{id}")
    public R<NeuqTakeRecordVo> getInfo(@ApiParam("主键")
                                                  @NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return R.ok(iNeuqTakeRecordService.queryById(id));
    }

    /**
     * 新增领取记录
     */
    @ApiOperation("新增领取记录")
    @SaCheckPermission("neuqer:takeRecord:add")
    @Log(title = "领取记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody NeuqTakeRecordBo bo) {
        try{
            NeuqOrderItem item = iNeuqOrderItemService.selectById(bo.getItemId());
            if(ObjectUtil.isNotNull(item)){
                // 计算剩余未取数量
                Long num = item.getTotalQuantity() - item.getTakeNumber();
                if(bo.getTakeNumber() <= num){
                    item.setTakeNumber(item.getTakeNumber() + bo.getTakeNumber());
                    iNeuqOrderItemService.update(item);
                    return toAjax(iNeuqTakeRecordService.insertByBo(bo) ? 1 : 0);
                }else{
                    return R.fail("可取数量不足");
                }
            }else{
                return R.fail("未找到该订单明细");
            }
        }catch (Exception e){
            throw e;
        }

    }

    /**
     * 修改领取记录
     */
    @ApiOperation("修改领取记录")
    @SaCheckPermission("neuqer:takeRecord:edit")
    @Log(title = "领取记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody NeuqTakeRecordBo bo) {
        return toAjax(iNeuqTakeRecordService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除领取记录
     */
    @ApiOperation("删除领取记录")
    @SaCheckPermission("neuqer:takeRecord:remove")
    @Log(title = "领取记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@ApiParam("主键串")
                                       @NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iNeuqTakeRecordService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
