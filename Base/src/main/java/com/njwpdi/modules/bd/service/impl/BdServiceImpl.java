package com.njwpdi.modules.bd.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.njwpdi.common.utils.PageUtils;
import com.njwpdi.common.utils.Query;

import com.njwpdi.modules.bd.dao.BdDao;
import com.njwpdi.modules.bd.entity.BdEntity;
import com.njwpdi.modules.bd.service.BdService;


@Service("bdService")
public class BdServiceImpl extends ServiceImpl<BdDao, BdEntity> implements BdService {
    @Autowired
    private BdDao bdDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("name");
        IPage<BdEntity> page = this.page(
                new Query<BdEntity>().getPage(params),
                new QueryWrapper<BdEntity>()
                        .like(StringUtils.isNotBlank(name),"bd_name",name)
                        .or()
                        .like(StringUtils.isNotBlank(name),"bd_type",name)
                        .or()
                        .like(StringUtils.isNotBlank(name),"prj_name",name)
        );

        return new PageUtils(page);
    }

    @Override
    public List<BdEntity> getByPrjId(BdEntity bdEntity){
        return bdDao.getByPrjId(bdEntity);
    }

    @Override
    public int deleteByPrjId(String[] prjIds){
        return bdDao.deleteByPrjId(prjIds);
    }

}
