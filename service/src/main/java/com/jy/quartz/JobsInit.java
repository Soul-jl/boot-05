package com.jy.quartz;

import com.jy.model.task.Task;
import com.jy.service.task.TaskService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobsInit implements InitializingBean{

    @Autowired
    private TaskService taskService;

    @Autowired
    private QuartzManager quartzManager;


    @Override
    public void afterPropertiesSet() throws Exception {
        Thread.sleep(30000);
        //从数据库查询出所有的待执行定时任务
        Task pt = new Task();
        pt.setTaskFlag(1);
        List<Task> list = taskService.selectTaskList(pt);
        //遍历集合，把每个任务添加到调度器
        if(null != list){
            list.forEach(t ->{
                quartzManager.addJob(t);
            });
        }
    }
}
