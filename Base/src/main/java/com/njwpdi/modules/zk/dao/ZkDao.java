package com.njwpdi.modules.zk.dao;

import com.njwpdi.modules.zk.entity.ZkEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ${comments}
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-16 10:23:51
 */
@Mapper
@Component
public interface ZkDao extends BaseMapper<ZkEntity> {

    List<ZkEntity> getByPrjId(ZkEntity zkEntity);

    int deleteByPrjId(String[]  prjIds);

    int saveExcel(List<ZkEntity> zkEntityList);
}
