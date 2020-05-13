package com.jy.quartz;

import com.jy.common.MyTask;
import com.jy.common.MailUtil;
import com.jy.model.task.Task;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MailJob extends QuartzJobBean {

    @Autowired
    private MyTask myTask;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
       // MailUtil.sendMail("1428280990@qq.com", "定时邮件，20秒一次", "狗蛋邮箱轰炸，轰到你怀疑人生");
        //Object scheduleJob = jobExecutionContext.getMergedJobDataMap().get("scheduleJob");
        System.out.println(jobExecutionContext.getJobDetail().getKey());
        //获取到任务定义的参数
        Task t = (Task) jobExecutionContext.getMergedJobDataMap().get("myTask");
        ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getMergedJobDataMap().get("applicationContext");
        try {
            //利用反射执行对应方法
            if (t.getTaskClass().contains("MyTask")) {
                Object obj = applicationContext.getBean("myTask");
                Method method = obj.getClass().getMethod(t.getTaskMethod());
                method.invoke(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println(jobExecutionContext.getJobDetail().getKey() + "发送一封邮件给项目组长");
    }
}
