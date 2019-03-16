package com.wulong.project.service.impl;

import com.wulong.project.dao.DbInfoMapper;
import com.wulong.project.model.DbInfo;
import com.wulong.project.service.DbInfoService;
import com.wulong.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/03/14.
 */
@Service
@Transactional
public class DbInfoServiceImpl extends AbstractService<DbInfo> implements DbInfoService {
    @Resource
    private DbInfoMapper dbInfoMapper;

}
