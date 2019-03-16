package com.wulong.project.model;

import javax.persistence.*;

@Table(name = "num_info")
public class NumInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 取号描述
     */
    @Column(name = "num_name")
    private String numName;

    /**
     * 取号长度
     */
    @Column(name = "num_length")
    private Integer numLength;

    /**
     * 当前号码
     */
    @Column(name = "current_num")
    private Integer currentNum;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取取号描述
     *
     * @return num_name - 取号描述
     */
    public String getNumName() {
        return numName;
    }

    /**
     * 设置取号描述
     *
     * @param numName 取号描述
     */
    public void setNumName(String numName) {
        this.numName = numName;
    }

    /**
     * 获取取号长度
     *
     * @return num_length - 取号长度
     */
    public Integer getNumLength() {
        return numLength;
    }

    /**
     * 设置取号长度
     *
     * @param numLength 取号长度
     */
    public void setNumLength(Integer numLength) {
        this.numLength = numLength;
    }

    /**
     * 获取当前号码
     *
     * @return current_num - 当前号码
     */
    public Integer getCurrentNum() {
        return currentNum;
    }

    /**
     * 设置当前号码
     *
     * @param currentNum 当前号码
     */
    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }
}