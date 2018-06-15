package utils;

import mvp.Constant;

/**
 * Created by yj on 2018/6/15.
 */

public class TaskUtil {

    // 显示到UI的字符串
    public static String getType(String taskType) {
        switch (taskType) {
            case Constant.TaskType.Task_COOK:
                return "烹饪";
            case Constant.TaskType.Task_TRAIN:
                return "锻炼";
            case Constant.TaskType.Task_READ:
                return "读书";
            case Constant.TaskType.Task_STUDY:
                return "学习";
            default:
                return "未知";
        }
    }
}
