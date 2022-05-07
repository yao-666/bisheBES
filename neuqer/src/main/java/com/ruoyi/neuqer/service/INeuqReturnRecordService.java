package com.ruoyi.neuqer.service;

import com.ruoyi.neuqer.domain.NeuqReturnRecord;
import com.ruoyi.neuqer.domain.vo.NeuqReturnRecordVo;
import com.ruoyi.neuqer.domain.bo.NeuqReturnRecordBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 领取记录Service接口
 *
 * @author yxy
 * @date 2022-05-07
 */
public interface INeuqReturnRecordService {

    /**
     * 查询领取记录
     *
     * @param id 领取记录主键
     * @return 领取记录
     */
    NeuqReturnRecordVo queryById(Long id);

    /**
     * 查询领取记录列表
     *
     * @param neuqReturnRecord 领取记录
     * @return 领取记录集合
     */
    TableDataInfo<NeuqReturnRecordVo> queryPageList(NeuqReturnRecordBo bo, PageQuery pageQuery);

    /**
     * 查询领取记录列表
     *
     * @param neuqReturnRecord 领取记录
     * @return 领取记录集合
     */
    List<NeuqReturnRecordVo> queryList(NeuqReturnRecordBo bo);

    /**
     * 修改领取记录
     *
     * @param neuqReturnRecord 领取记录
     * @return 结果
     */
    Boolean insertByBo(NeuqReturnRecordBo bo);

    /**
     * 修改领取记录
     *
     * @param neuqReturnRecord 领取记录
     * @return 结果
     */
    Boolean updateByBo(NeuqReturnRecordBo bo);

    /**
     * 校验并批量删除领取记录信息
     *
     * @param ids 需要删除的领取记录主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
