package com.njwpdi.modules.survey.controller;

import com.njwpdi.common.utils.PageUtils;
import com.njwpdi.common.utils.Result;
import com.njwpdi.common.validator.ValidatorUtils;
import com.njwpdi.common.validator.group.AddGroup;
import com.njwpdi.modules.bd.service.BdService;
import com.njwpdi.modules.survey.entity.SurveyEntity;
import com.njwpdi.modules.survey.service.SurveyService;
import com.njwpdi.modules.zk.service.ZkService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/survey")
@Api("查询")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @Autowired
    private ZkService zkService;

    @Autowired
    private BdService bdService;

    @GetMapping("/list")
    public Result list(@RequestParam Map<String,Object> params){
        PageUtils page = surveyService.queryPage(params);

        return Result.ok().put("page",page);
    }

    @GetMapping("/info/{prjId}")
    public Result info(@PathVariable("prjId") String prjId) {
        SurveyEntity prj = surveyService.getById(prjId);

        return Result.ok().put("prj", prj);
    }

    @PostMapping("/add")
    public Result add(@RequestBody SurveyEntity prj) {
        ValidatorUtils.validateEntity(prj, AddGroup.class);

        surveyService.addPrj(prj);

        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody SurveyEntity prj) {
        ValidatorUtils.validateEntity(prj, AddGroup.class);

        surveyService.updatePrj(prj);

        return Result.ok();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody String[] prjIds) {

        surveyService.deleteBatch(prjIds);
        zkService.deleteByPrjId(prjIds);
        bdService.deleteByPrjId(prjIds);
        return Result.ok();
    }

    @GetMapping("/zk")
    public Result zk(@RequestBody String[] prjIds) {

        surveyService.deleteBatch(prjIds);

        return Result.ok();
    }

}
