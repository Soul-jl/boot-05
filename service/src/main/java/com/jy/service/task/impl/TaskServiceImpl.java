package com.jy.service.task.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jy.mapper.task.TaskMapper;
import com.jy.model.task.Task;
import com.jy.quartz.QuartzManager;
import com.jy.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private QuartzManager quartzManager;

    @Override
    public List<Task> selectTaskList(Task task) {
        List<Task> list = taskMapper.selectTaskList(task);
        return list;
    }

    @Override
    public Map<String, Object> selectTaskAllList(Task task) {
        Map<String, Object> map = new HashMap<>();
        Page<Object> page = PageHelper.startPage(task.getPage(), task.getLimit());
        List<Task> list = selectTaskList(task);
        long total = page.getTotal();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", list);
        map.put("count", total);
        return map;
    }

    @Override
    public void insertTask(Task task) {
        if (1 == task.getTaskFlag()) {
            //立即执行，把任务添加到定时器触发器
            quartzManager.addJob(task);
        }
        //把信息保存到数据库中
        taskMapper.insertTask(task);
    }

    @Override
    public void updateTask(Task task) {
        if(task.getBtn().equals("stop")) {
            task.setTaskFlag(0);
        }
        if(task.getBtn().equals("start")) {
            task.setTaskFlag(1);
        }
        updateTaskDetail(task);
    }

    private void updateTaskDetail(Task task) {
        //修改数据库
        taskMapper.updateTask(task);
        //把任务信息从数据库查询出来
        task.setTaskFlag(-1);
        List<Task> list = taskMapper.selectTaskList(task);
        if (null != list && 1 == list.size()) {
            if(task.getBtn().equals("stop")) {
                quartzManager.removeJob(list.get(0));
            } else {
                quartzManager.addJob(list.get(0));
            }
        }
    }

    @Override
    public void deleteTask(Task task) {
        if(1 == task.getTaskFlag()) {
            task.setBtn("stop");
            //查询并停止这个任务
            updateTaskDetail(task);
        }
        //删除数据库中的任务信息
        taskMapper.deleteTask(task);
    }
}
