package com.jy.mapper.task;

import com.jy.model.task.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {
    @Select(" <script>" +
            " select t_id taskID," +
            " t_key taskKey," +
            " t_desc taskDesc," +
            " t_cron taskCron," +
            " t_flag taskFlag," +
            " t_class taskClass," +
            " t_method taskMethod" +
            " from t_tasks" +
            " <where>" +
            " 1 = 1" +
            " and t_status = 1" +
            " <if test='taskFlag > -1'>" +
            " and t_flag = #{taskFlag}" +
            " </if>" +
            " <if test='taskID > 0'>" +
            " and t_id = #{taskID}" +
            " </if>" +
            " </where>" +
            " </script>")
    List<Task> selectTaskList(Task task);

    @Insert(" <script>" +
            " insert into t_tasks" +
            " (t_key," +
            " t_desc," +
            " t_cron," +
            " <if test='taskFlag > -1'>" +
            " t_flag," +
            " </if>" +
            " t_class," +
            " t_method)" +
            " values" +
            " (#{taskKey}," +
            " #{taskDesc}," +
            " #{taskCron}," +
            " <if test='taskFlag > -1'>" +
            " #{taskFlag}," +
            " </if>" +
            " #{taskClass}," +
            " #{taskMethod})" +
            " </script>")
    void insertTask(Task task);

    @Update(" update t_tasks" +
            " set t_flag = #{taskFlag}" +
            " where t_id = #{taskID}")
    void updateTask(Task task);


    @Update(" update t_tasks" +
            " set t_status = 2" +
            " where t_id = #{taskID}")
    void deleteTask(Task task);
}
