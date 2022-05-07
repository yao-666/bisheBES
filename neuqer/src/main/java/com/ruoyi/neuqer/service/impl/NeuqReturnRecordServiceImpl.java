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
import com.ruoyi.neuqer.domain.bo.NeuqReturnRecordBo;
import com.ruoyi.neuqer.domain.vo.NeuqReturnRecordVo;
import com.ruoyi.neuqer.domain.NeuqReturnRecord;
import com.ruoyi.neuqer.mapper.NeuqReturnRecordMapper;
import com.ruoyi.neuqer.service.INeuqReturnRecordService;

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
public class NeuqReturnRecordServiceImpl implements INeuqReturnRecordService {

    private final NeuqReturnRecordMapper baseMapper;

    /**
     * 查询领取记录
     *
     * @param id 领取记录主键
     * @return 领取记录
     */
    @Override
    public NeuqReturnRecordVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询领取记录列表
     *
     * @param bo 领取记录
     * @return 领取记录
     */
    @Override
    public TableDataInfo<NeuqReturnRecordVo> queryPageList(NeuqReturnRecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<NeuqReturnRecord> lqw = buildQueryWrapper(bo);
        Page<NeuqReturnRecordVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询领取记录列表
     *
     * @param bo 领取记录
     * @return 领取记录
     */
    @Override
    public List<NeuqReturnRecordVo> queryList(NeuqReturnRecordBo bo) {
        LambdaQueryWrapper<NeuqReturnRecord> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<NeuqReturnRecord> buildQueryWrapper(NeuqReturnRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<NeuqReturnRecord> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getItemId() != null, NeuqReturnRecord::getItemId, bo.getItemId());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderSn()), NeuqReturnRecord::getOrderSn, bo.getOrderSn());
        lqw.like(StringUtils.isNotBlank(bo.getProductName()), NeuqReturnRecord::getProductName, bo.getProductName());
        lqw.like(StringUtils.isNotBlank(bo.getClassName()), NeuqReturnRecord::getClassName, bo.getClassName());
        lqw.eq(StringUtils.isNotBlank(bo.getShoppingOptions()), NeuqReturnRecord::getShoppingOptions, bo.getShoppingOptions());
        lqw.eq(bo.getReturnNumber() != null, NeuqReturnRecord::getReturnNumber, bo.getReturnNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getReceiverId()), NeuqReturnRecord::getReceiverId, bo.getReceiverId());
        return lqw;
    }

    /**
     * 新增领取记录
     *
     * @param bo 领取记录
     * @return 结果
     */
    @Override
    public Boolean insertByBo(NeuqReturnRecordBo bo) {
        NeuqReturnRecord add = BeanUtil.toBean(bo, NeuqReturnRecord.class);
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
    public Boolean updateByBo(NeuqReturnRecordBo bo) {
        NeuqReturnRecord update = BeanUtil.toBean(bo, NeuqReturnRecord.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(NeuqReturnRecord entity){
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
