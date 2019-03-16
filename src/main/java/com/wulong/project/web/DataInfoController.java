package com.wulong.project.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wulong.project.core.Result;
import com.wulong.project.core.ResultGenerator;
import com.wulong.project.db.SqlEexecuter;
import com.wulong.project.model.DataInfo;
import com.wulong.project.model.DbInfo;
import com.wulong.project.service.DataInfoService;
import com.wulong.project.service.DbInfoService;
import com.wulong.project.service.impl.NumInfoServiceImpl;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2019/03/14.
*/
@CrossOrigin
@RestController
@RequestMapping("/data/info")
public class DataInfoController {
    @Resource
    private DataInfoService dataInfoService;

    @Resource
    private DbInfoService dbInfoService;

    @Resource
    private NumInfoServiceImpl numInfoService;

    @PostMapping("/add")
    public Result add(@RequestBody DataInfo dataInfo) {
        if (Strings.isNullOrEmpty(dataInfo.getDbId()) ||
            Strings.isNullOrEmpty(dataInfo.getDataName())||
            Strings.isNullOrEmpty(dataInfo.getDataSql())) {
            return ResultGenerator.genFailResult("数据填写不全！");
        }
        if (Strings.isNullOrEmpty(dataInfo.getDataId())) {
            // 新增
            dataInfo.setDataId(numInfoService.getNum("DT", "数据项ID", 5));
            dataInfo.setStatus("1");
            dataInfo.setUpdateTime(new Date());
            dataInfoService.save(dataInfo);
            return ResultGenerator.genSuccessResult();
        } else {
            // 编辑
            dataInfo.setUpdateTime(new Date());
            dataInfoService.update(dataInfo);
            return ResultGenerator.genSuccessResult();
        }
    }

    @PostMapping("/getData")
    public Result getData(@RequestBody DataInfo info) {
        DbInfo dbInfo = dbInfoService.findById(info.getDbId());
        List<Map<String,Object>> list = SqlEexecuter.getInstance().executeQuery(info.getDataSql(),dbInfo);
        return ResultGenerator.genSuccessResult(list);
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        if (!Strings.isNullOrEmpty(id)) {
            DataInfo info = new DataInfo();
            info.setDataId(id);
            info.setStatus("0");
            dataInfoService.updateByPrimaryKeySelective(info);
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("删除失败!");
    }


    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition = new Condition(DataInfo.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("status","1");
        List<DataInfo> list = dataInfoService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
