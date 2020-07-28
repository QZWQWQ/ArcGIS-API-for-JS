package com.njwpdi.modules.zk.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.njwpdi.common.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.njwpdi.modules.zk.entity.ZkEntity;
import com.njwpdi.modules.zk.service.ZkService;
import com.njwpdi.common.utils.PageUtils;


/**
 * ${comments}
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-16 10:23:51
 */
@RestController
@RequestMapping("/zk/zk")
public class ZkController {
    @Autowired
    private ZkService zkService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = zkService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{zkId}")
    public Result info(@PathVariable("zkId") String zkId){
		ZkEntity zk = zkService.getById(zkId);

        return Result.ok().put("zk", zk);

    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody ZkEntity zk){
		zkService.save(zk);

        return Result.ok();
    }
    /**
     * 保存
     */
    @RequestMapping("/saveExcel")
    public Result saveExcel(@RequestBody List<ZkEntity> zkEntityList){
//        zkService.saveExcel(zkEntityList);

        for (ZkEntity zkEntity : zkEntityList) {
            zkService.save(zkEntity);
        }
        return Result.ok();
    }
    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody ZkEntity zk){
		zkService.updateById(zk);

        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody String[] zkIds){
		zkService.removeByIds(Arrays.asList(zkIds));

        return Result.ok();
    }

    /**
     * 页面跳转
     */
    @GetMapping("/infoByPrjId/{prjId}")
    public Result prjInfo(@PathVariable("prjId") String prjId){
        ZkEntity zkEntity = new ZkEntity();
        zkEntity.setPrjId(prjId);
        List<ZkEntity> zk = zkService.getByPrjId(zkEntity);
        return Result.ok().put("zk", zk);
    }

}
