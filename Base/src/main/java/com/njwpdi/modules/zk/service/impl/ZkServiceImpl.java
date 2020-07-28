package com.njwpdi.modules.zk.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.njwpdi.common.utils.PageUtils;
import com.njwpdi.common.utils.Query;

import com.njwpdi.modules.zk.dao.ZkDao;
import com.njwpdi.modules.zk.entity.ZkEntity;
import com.njwpdi.modules.zk.service.ZkService;
import org.springframework.transaction.annotation.Transactional;


@Service("zkService")
public class ZkServiceImpl extends ServiceImpl<ZkDao, ZkEntity> implements ZkService {
    @Autowired
    private ZkDao zkDao;
    private ZkEntity zkEntity;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("name");
        IPage<ZkEntity> page = this.page(
                new Query<ZkEntity>().getPage(params),
                new QueryWrapper<ZkEntity>()
                        .like(StringUtils.isNotBlank(name),"zk_elv",name)
                        .or()
                        .like(StringUtils.isNotBlank(name),"zk_dep",name)
                        .or()
                        .like(StringUtils.isNotBlank(name),"zk_num",name)
                        .or()
                        .like(StringUtils.isNotBlank(name),"prj_name",name)
                        .or()
                        .like(StringUtils.isNotBlank(name),"zk_dsp",name)
        );

        return new PageUtils(page);
    }

    @Override
    public  List<ZkEntity> getByPrjId(ZkEntity zkEntity){
        return zkDao.getByPrjId(zkEntity);
    }

    @Override
    public int deleteByPrjId(String[] prjIds){
        return zkDao.deleteByPrjId(prjIds);
    }

    @Override
    public int saveExcel(List<ZkEntity> zkEntityList) {
        return zkDao.saveExcel(zkEntityList);
    }

}
