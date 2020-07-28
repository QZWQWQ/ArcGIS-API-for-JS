package com.njwpdi.modules.bd.dao;

import com.njwpdi.modules.bd.entity.BdEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ${comments}
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-18 16:33:08
 */
@Mapper
@Component
public interface BdDao extends BaseMapper<BdEntity> {

    List<BdEntity> getByPrjId(BdEntity bdEntity);

    int deleteByPrjId(String[]  prjIds);
}
