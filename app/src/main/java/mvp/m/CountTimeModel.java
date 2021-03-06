package mvp.m;

import javax.inject.Inject;

import utils.TaskUtil;

/**
 * Created by yj on 2018/6/12.
 */

public class CountTimeModel {
    private long time = 0;
    private String startDate;
    private String taskType;


    @Inject
    public CountTimeModel() {

    }

    public void resetTime() {
        time = 0;
    }

    public String updateTime() {
        time++;
        return secondToTime(time);
    }

    public String getTime() {
        return secondToTime(time);
    }

    /**
     * 将秒数转换为日时分秒，
     * @param second
     * @return
     */
    private String secondToTime(long second){
        long days = second / 86400;            //转换天数
        second = second % 86400;            //剩余秒数
        long hours = second / 3600;            //转换小时
        second = second % 3600;                //剩余秒数
        long minutes = second /60;            //转换分钟
        second = second % 60;                //剩余秒数

        String sDay = String.valueOf(days).length() > 1 ? String.valueOf(days) : "0"+String.valueOf(days);
        String sHours = String.valueOf(hours).length() > 1 ? String.valueOf(hours) : "0"+String.valueOf(hours);
        String sMin = String.valueOf(minutes).length() > 1 ? String.valueOf(minutes) : "0"+String.valueOf(minutes);
        String sSecond = String.valueOf(second).length() > 1 ? String.valueOf(second) : "0"+String.valueOf(second);

        if(days>0){
            return sDay + ":" + sHours + ":" + sMin + ":" + sSecond;
        }else{
            return sHours + ":" + sMin + ":" + sSecond;
        }
    }


    public String getShowType() {
       return TaskUtil.getType(taskType);
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    // 存储这次任务
    public void saveRecord() {
        Record record = new Record(startDate, getTime(), TaskUtil.getType(taskType));
        record.save();
    }
}
