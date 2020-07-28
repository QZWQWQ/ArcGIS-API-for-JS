package com.njwpdi.modules.sys.service.impl;

import com.njwpdi.modules.sys.dao.SysAreaDao;
import com.njwpdi.modules.sys.entity.SysAreaEntity;
import com.njwpdi.modules.sys.service.SysAreaService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.njwpdi.common.utils.PageUtils;
import com.njwpdi.common.utils.Query;

/**
 * @author sunjing
 * @description
 * @date 2019-12-15 2:34 下午
 **/
@Service("sysAreaService")
public class SysAreaServiceImpl extends ServiceImpl<SysAreaDao, SysAreaEntity> implements SysAreaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysAreaEntity> page =
            this.page(new Query<SysAreaEntity>().getPage(params), new QueryWrapper<SysAreaEntity>());

        return new PageUtils(page);
    }

}