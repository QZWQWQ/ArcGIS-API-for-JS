package com.njwpdi.modules.kance.controller;

import com.njwpdi.common.utils.PageUtils;
import com.njwpdi.common.utils.Result;
import com.njwpdi.common.validator.ValidatorUtils;
import com.njwpdi.common.validator.group.AddGroup;
import com.njwpdi.modules.kance.entity.KanceEntity;
import com.njwpdi.modules.kance.service.KanceService;
import com.njwpdi.modules.sys.entity.SysUserEntity;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kance")
@Api("查询")
public class KanceController {
    @Autowired
    private KanceService kanceService;

    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils page = kanceService.queryPage(params);

        return Result.ok().put("page", page);
    }

    @GetMapping("/info/{prjId}")
    public Result info(@PathVariable("prjId") String prjId) {
        KanceEntity prj = kanceService.getById(prjId);

        return Result.ok().put("prj", prj);
    }

    @PostMapping("/add")
    public Result add(@RequestBody KanceEntity prj) {
        ValidatorUtils.validateEntity(prj, AddGroup.class);

        kanceService.addPrj(prj);

        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody KanceEntity prj) {
        ValidatorUtils.validateEntity(prj, AddGroup.class);

        kanceService.updatePrj(prj);

        return Result.ok();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody String[] prjIds) {

        kanceService.deleteBatch(prjIds);

        return Result.ok();
    }


    //    @RequestMapping(value = "/list", method = RequestMethod.GET)
    //    public Result getKance(@RequestParam Map<String, Object> params) {
    //        List<KanceEntity> kanceEntityList = service.list(new QueryWrapper<KanceEntity>());
    //        return Result.ok().put("Kanceshuju", kanceEntityList);
    //    }


    //    @GetMapping("/queryByName")
    //    public Result queryByName(HttpServletRequest request){
    //        String prjName = request.getParameter("prjName");
    //        KanceEntity kanceEntity = new KanceEntity();
    //        kanceEntity.setPrjName(prjName);
    //
    //        kanceService.queryByName(kanceEntity);
    //        return Result.ok();
    //    }


    //    @ResponseBody
    //    @RequestMapping(value = "/add", method = RequestMethod.POST)
    //    public Result addKance(@RequestBody KanceEntity kanceEntity) {
    //        service.addKance(kanceEntity);
    //        List<KanceEntity> kanceEntityList = service.list(new QueryWrapper<KanceEntity>());
    //        return Result.ok().put("Kanceshuju", kanceEntityList);
    //    }
    //
    //    @ResponseBody
    //    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    //    public Result deleteKance(HttpServletRequest request) {
    //        String id = request.getParameter("id");
    //        service.deleteKanceById(id);
    //        List<KanceEntity> kanceEntityList = service.list(new QueryWrapper<KanceEntity>());
    //        return Result.ok().put("Kanceshuju", kanceEntityList);
    //    }
    //
    //    @ResponseBody
    //    @RequestMapping(value = "/update", method = RequestMethod.POST)
    //    public Result updateKance(@RequestBody KanceEntity kanceEntity) {
    //        service.updateKanceById(kanceEntity);
    //        List<KanceEntity> kanceEntityList = service.list(new QueryWrapper<KanceEntity>());
    //        return Result.ok().put("Kanceshuju", kanceEntityList);
    //    }


}
