package com.jy.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class StringTask {

    //@Scheduled(cron = "*/5 * * * * *")
    public void task1(){
        System.out.println("风陵渡口初相遇");
    }


}
