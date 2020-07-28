package com.njwpdi.modules.bd.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.njwpdi.common.utils.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.njwpdi.modules.bd.entity.BdEntity;
import com.njwpdi.modules.bd.service.BdService;
import com.njwpdi.common.utils.PageUtils;



/**
 * ${comments}
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-18 16:33:08
 */
@RestController
@RequestMapping("bd/bd")
public class BdController {
    @Autowired
    private BdService bdService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = bdService.queryPage(params);

        return Result.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{bdId}")
    public Result info(@PathVariable("bdId") String bdId){
		BdEntity bd = bdService.getById(bdId);

        return Result.ok().put("bd", bd);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody BdEntity bd){
		bdService.save(bd);

        return Result.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody BdEntity bd){
		bdService.updateById(bd);

        return Result.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody String[] bdIds){
		bdService.removeByIds(Arrays.asList(bdIds));

        return Result.ok();
    }

    /**
     * 页面跳转
     */
    @GetMapping("/infoByPrjId/{prjId}")
    public Result prjInfo(@PathVariable("prjId") String prjId){
        BdEntity bdEntity = new BdEntity();
        bdEntity.setPrjId(prjId);
        List<BdEntity> bd = bdService.getByPrjId(bdEntity);
        return Result.ok().put("bd", bd);
    }

}
