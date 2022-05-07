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
import com.ruoyi.neuqer.domain.bo.NeuqReturnRecordBo;
import com.ruoyi.neuqer.domain.vo.NeuqReturnRecordVo;
import com.ruoyi.neuqer.service.INeuqOrderItemService;
import com.ruoyi.neuqer.service.INeuqReturnRecordService;
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
@RequestMapping("/neuqer/returnRecord")
public class NeuqReturnRecordController extends BaseController {

    private final INeuqReturnRecordService iNeuqReturnRecordService;
    private final INeuqOrderItemService iNeuqOrderItemService;

    /**
     * 查询领取记录列表
     */
    @ApiOperation("查询领取记录列表")
    @SaCheckPermission("neuqer:returnRecord:list")
    @GetMapping("/list")
    public TableDataInfo<NeuqReturnRecordVo> list(@Validated(QueryGroup.class) NeuqReturnRecordBo bo, PageQuery pageQuery) {
        return iNeuqReturnRecordService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出领取记录列表
     */
    @ApiOperation("导出领取记录列表")
    @SaCheckPermission("neuqer:returnRecord:export")
    @Log(title = "领取记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated NeuqReturnRecordBo bo, HttpServletResponse response) {
        List<NeuqReturnRecordVo> list = iNeuqReturnRecordService.queryList(bo);
        ExcelUtil.exportExcel(list, "领取记录", NeuqReturnRecordVo.class, response);
    }

    /**
     * 获取领取记录详细信息
     */
    @ApiOperation("获取领取记录详细信息")
    @SaCheckPermission("neuqer:returnRecord:query")
    @GetMapping("/{id}")
    public R<NeuqReturnRecordVo> getInfo(@ApiParam("主键")
                                                  @NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return R.ok(iNeuqReturnRecordService.queryById(id));
    }

    /**
     * 新增领取记录
     */
    @ApiOperation("新增领取记录")
    @SaCheckPermission("neuqer:returnRecord:add")
    @Log(title = "领取记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody NeuqReturnRecordBo bo) {
        try{
            NeuqOrderItem item = iNeuqOrderItemService.selectById(bo.getItemId());
            if(ObjectUtil.isNotNull(item)){
                    item.setTakeNumber(item.getReturnNumber() + bo.getReturnNumber());
                    iNeuqOrderItemService.update(item);
                    return toAjax(iNeuqReturnRecordService.insertByBo(bo) ? 1 : 0);
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
    @SaCheckPermission("neuqer:returnRecord:edit")
    @Log(title = "领取记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody NeuqReturnRecordBo bo) {
        return toAjax(iNeuqReturnRecordService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除领取记录
     */
    @ApiOperation("删除领取记录")
    @SaCheckPermission("neuqer:returnRecord:remove")
    @Log(title = "领取记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@ApiParam("主键串")
                                       @NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iNeuqReturnRecordService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
