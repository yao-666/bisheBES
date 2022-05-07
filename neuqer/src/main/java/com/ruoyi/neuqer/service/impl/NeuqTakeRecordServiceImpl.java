package com.ruoyi.neuqer.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.neuqer.domain.bo.NeuqTakeRecordBo;
import com.ruoyi.neuqer.domain.vo.NeuqTakeRecordVo;
import com.ruoyi.neuqer.domain.NeuqTakeRecord;
import com.ruoyi.neuqer.mapper.NeuqTakeRecordMapper;
import com.ruoyi.neuqer.service.INeuqTakeRecordService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 领取记录Service业务层处理
 *
 * @author yxy
 * @date 2022-05-07
 */
@RequiredArgsConstructor
@Service
public class NeuqTakeRecordServiceImpl implements INeuqTakeRecordService {

    private final NeuqTakeRecordMapper baseMapper;

    /**
     * 查询领取记录
     *
     * @param id 领取记录主键
     * @return 领取记录
     */
    @Override
    public NeuqTakeRecordVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询领取记录列表
     *
     * @param bo 领取记录
     * @return 领取记录
     */
    @Override
    public TableDataInfo<NeuqTakeRecordVo> queryPageList(NeuqTakeRecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<NeuqTakeRecord> lqw = buildQueryWrapper(bo);
        Page<NeuqTakeRecordVo> result = baseMapper.selectVoPage(pageQuery.build(),lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询领取记录列表
     *
     * @param bo 领取记录
     * @return 领取记录
     */
    @Override
    public List<NeuqTakeRecordVo> queryList(NeuqTakeRecordBo bo) {
        LambdaQueryWrapper<NeuqTakeRecord> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<NeuqTakeRecord> buildQueryWrapper(NeuqTakeRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<NeuqTakeRecord> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getItemId() != null, NeuqTakeRecord::getItemId, bo.getItemId());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderSn()), NeuqTakeRecord::getOrderSn, bo.getOrderSn());
        lqw.like(StringUtils.isNotBlank(bo.getProductName()), NeuqTakeRecord::getProductName, bo.getProductName());
        lqw.like(StringUtils.isNotBlank(bo.getClassName()), NeuqTakeRecord::getClassName, bo.getClassName());
        lqw.eq(StringUtils.isNotBlank(bo.getShoppingOptions()), NeuqTakeRecord::getShoppingOptions, bo.getShoppingOptions());
        lqw.eq(bo.getTakeNumber() != null, NeuqTakeRecord::getTakeNumber, bo.getTakeNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getReceiverId()), NeuqTakeRecord::getReceiverId, bo.getReceiverId());
        return lqw;
    }

    /**
     * 新增领取记录
     *
     * @param bo 领取记录
     * @return 结果
     */
    @Override
    public Boolean insertByBo(NeuqTakeRecordBo bo) {
        NeuqTakeRecord add = BeanUtil.toBean(bo, NeuqTakeRecord.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改领取记录
     *
     * @param bo 领取记录
     * @return 结果
     */
    @Override
    public Boolean updateByBo(NeuqTakeRecordBo bo) {
        NeuqTakeRecord update = BeanUtil.toBean(bo, NeuqTakeRecord.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(NeuqTakeRecord entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除领取记录
     *
     * @param ids 需要删除的领取记录主键
     * @return 结果
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
