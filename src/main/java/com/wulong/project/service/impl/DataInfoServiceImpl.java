package com.wulong.project.service.impl;

import com.wulong.project.dao.DataInfoMapper;
import com.wulong.project.model.DataInfo;
import com.wulong.project.service.DataInfoService;
import com.wulong.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/03/14.
 */
@Service
@Transactional
public class DataInfoServiceImpl extends AbstractService<DataInfo> implements DataInfoService {
    @Resource
    private DataInfoMapper dataInfoMapper;

}
