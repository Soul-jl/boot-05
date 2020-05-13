package com.jy.quartz;

import com.jy.model.task.Task;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class QuartzManager {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ApplicationContext applicationContext;


    /**
     * @Description: 添加定时任务
     * @Param:
     * @return: void
     * @Date: 2018/8/17
     */
    public void addJob(Task task){
        //corn表达式校验
        if(CronExpression.isValidExpression(task.getTaskCron())){
            try {
                // 任务名，任务组，任务执行类
                JobDetail jobDetail= JobBuilder.newJob(MailJob.class).withIdentity(task.getTaskKey())
                        .withDescription(task.getTaskDesc()).build();
                // 传参 可以不要
                jobDetail.getJobDataMap().put("myTask", task);
                jobDetail.getJobDataMap().put("applicationContext", applicationContext);
                // 触发器
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                // 触发器名,触发器组
                triggerBuilder.withIdentity(task.getTaskKey() + "：触发器");
                triggerBuilder.startNow();
                // 触发器时间设定
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(task.getTaskCron()));
                // 创建Trigger对象
                CronTrigger trigger = (CronTrigger) triggerBuilder.build();
                // 调度容器设置JobDetail和Trigger
                scheduler.scheduleJob(jobDetail, trigger);
                // 启动
                if (!scheduler.isShutdown()) {
                    scheduler.start();
                }
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
            System.out.println(task.getTaskKey() + "：开始定时任务");
        } else {
            System.out.println(task.getTaskKey() + "：corn表达式不合法");
        }
    }

    /**
     * @Description: 删除定时任务
     * @Param: [jobName:任务名称]
     * @return: void
     * @Date: 2018/8/17
     */
    public void removeJob(Task task) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(task.getTaskKey() + "：触发器");
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(JobKey.jobKey(task.getTaskKey()));// 删除任务
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
