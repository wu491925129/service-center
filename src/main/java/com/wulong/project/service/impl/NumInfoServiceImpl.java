package com.wulong.project.service.impl;

import com.google.common.base.Strings;
import com.wulong.project.Application;
import com.wulong.project.dao.NumInfoMapper;
import com.wulong.project.model.NumInfo;
import com.wulong.project.service.NumInfoService;
import com.wulong.project.core.AbstractService;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.apache.commons.lang3.StringUtils.isEmpty;


/**
 * Created by CodeGenerator on 2019/03/15.
 */
@Service
@Transactional
public class NumInfoServiceImpl extends AbstractService<NumInfo> implements NumInfoService {

    @Resource
    private NumInfoMapper numInfoMapper;

    /**
     * 取号
     * @param id        序号id
     * @param numName   序号描述
     * @param length    序号长度
     * @return
     */
    public String getNum(String id,String numName,int length) {
        String serialnumber = "";
        // 加前缀
        if (!Strings.isNullOrEmpty(id)) {
            // id作为前缀
            serialnumber += id;
        }
        int number = 0;
        NumInfo numInfo = numInfoMapper.selectByPrimaryKey(id);
        if (numInfo == null) {
            number = 1;
            numInfo = new NumInfo();
            numInfo.setId(id);
            numInfo.setCurrentNum(number);
            numInfo.setNumName(numName);
            numInfo.setNumLength(length);
            numInfoMapper.insert(numInfo);
        } else {
            number = numInfo.getCurrentNum();
            number++;
            numInfo.setNumName(numName);
            numInfo.setCurrentNum(number);
            numInfo.setNumLength(length);
            numInfoMapper.updateByPrimaryKey(numInfo);
        }

        int dnum = length - numbits(number);
        if (dnum > 0) {
            serialnumber += dup("0",dnum);
        }
        serialnumber += number;
        return serialnumber;
    }

    /**
     * 获取号码位数
     * @param number
     * @return
     */
    private int numbits(long number){
        int bit=1;
        while(number >= 10){
            bit++;
            number = number / 10;
        }
        return bit;
    }

    private String dup(CharSequence cs, int num) {
        if (!isEmpty(cs) && num > 0) {
            StringBuilder sb = new StringBuilder(cs.length() * num);

            for(int i = 0; i < num; ++i) {
                sb.append(cs);
            }

            return sb.toString();
        } else {
            return "";
        }
    }
}
