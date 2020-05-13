package com.jy.model.task;


import com.jy.common.PageUtil;

public class Task extends PageUtil {

    private int taskID;

    private String taskKey;

    private String taskDesc;

    private String taskCron;

    private int taskFlag = - 1;

    private String taskClass;

    private String taskMethod;

    /** 添加业务字段 */
    private String btn;

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }

    public String getTaskClass() {
        return taskClass;
    }

    public void setTaskClass(String taskClass) {
        this.taskClass = taskClass;
    }

    public String getTaskMethod() {
        return taskMethod;
    }

    public void setTaskMethod(String taskMethod) {
        this.taskMethod = taskMethod;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskCron() {
        return taskCron;
    }

    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron;
    }

    public int getTaskFlag() {
        return taskFlag;
    }

    public void setTaskFlag(int taskFlag) {
        this.taskFlag = taskFlag;
    }
}
