package com.jy.controller.task;

import com.jy.model.task.Task;
import com.jy.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;


    @RequestMapping("task/toList")
    String toTaskListPage() {
        return "task/list";
    }

    @RequestMapping("task/list")
    @ResponseBody
    Map<String, Object> selectTaskAllList(Task task) {
        Map<String, Object> map = taskService.selectTaskAllList(task);
        return map;
    }

    @RequestMapping("task/toAdd")
    String toAddPage() {
        return "task/add";
    }

    @RequestMapping("task/insert")
    @ResponseBody
    String insertTask(Task task) {
        taskService.insertTask(task);
        return "{}";
    }

    @RequestMapping("task/updateTask")
    @ResponseBody
    String updateTask(Task task) {
        taskService.updateTask(task);
        return "{}";
    }

    @RequestMapping("task/deleteTask")
    @ResponseBody
    String deleteTask(Task task) {
        taskService.deleteTask(task);
        return "{}";
    }

}
