package com.wulong.project.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wulong.project.core.Result;
import com.wulong.project.core.ResultGenerator;
import com.wulong.project.db.DataBaseFactory;
import com.wulong.project.model.DbInfo;
import com.wulong.project.service.DbInfoService;
import com.wulong.project.service.impl.NumInfoServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

/**
* Created by CodeGenerator on 2019/03/14.
*/
@RestController
@CrossOrigin
@RequestMapping("/db/info")
@Transactional(rollbackFor = Exception.class)
public class DbInfoController {
    @Resource
    private DbInfoService dbInfoService;

    @Resource
    private NumInfoServiceImpl numInfoService;

    /**
     * 新增/编辑 数据源
     * @param dbInfo
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody DbInfo dbInfo) {
        if (Strings.isNullOrEmpty(dbInfo.getDbName()) ||
            Strings.isNullOrEmpty(dbInfo.getDbAddress())||
            Strings.isNullOrEmpty(dbInfo.getUserName())||
            Strings.isNullOrEmpty(dbInfo.getPassword())||
            Strings.isNullOrEmpty(dbInfo.getDbType())) {
            return ResultGenerator.genFailResult("数据填写不全！");
        }
        if (Strings.isNullOrEmpty(dbInfo.getId())) {
            // 新增
            dbInfo.setId(numInfoService.getNum("DB", "数据库ID", 5));
            dbInfo.setStatus("1");
            dbInfo.setOpTime(new Date());
            dbInfoService.save(dbInfo);
            return ResultGenerator.genSuccessResult();
        } else {
            // 编辑
            DbInfo info = dbInfoService.findById(dbInfo.getId());
            dbInfo.setOpTime(new Date());
            if ("******".equals(dbInfo.getPassword())) {
                // 默认密码不更改
                dbInfo.setPassword(info.getPassword());
            }
            dbInfoService.update(dbInfo);
            return ResultGenerator.genSuccessResult();
        }

    }

    /**
     * 测试连接
     * @param info
     * @return
     */
    @PostMapping("/test")
    public Result testDb(@RequestBody DbInfo info) {
        DbInfo dbInfo = dbInfoService.findById(info.getId());
        boolean status = DataBaseFactory.getInstance().testConnection(dbInfo);
        if (status) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("连接失败！");
        }
    }

    /**
     * 删除数据源
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Result delete(@RequestParam String id) {
        if (!Strings.isNullOrEmpty(id)) {
            DbInfo info = new DbInfo();
            info.setId(id);
            info.setStatus("0");
            dbInfoService.updateByPrimaryKeySelective(info);
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("删除失败!");
    }

    /**
     * 获取数据源字典
     * @return
     */
    @GetMapping("/dic")
    public Result dic(){
        PageHelper.startPage(0, 0);
        Condition condition = new Condition(DbInfo.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("status","1");
        List<DbInfo> list = dbInfoService.findByCondition(condition);
        List<Map<String,Object>> result = new ArrayList<>();
        list.forEach(item->{
            Map<String,Object> map = new HashMap<>();
            map.put("label",item.getDbName());
            map.put("value",item.getId());
            result.add(map);
        });
        PageInfo pageInfo = new PageInfo(result);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition = new Condition(DbInfo.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("status","1");
        List<DbInfo> list = dbInfoService.findByCondition(condition);
        list.forEach(item->{
            item.setPassword("******");
        });
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
