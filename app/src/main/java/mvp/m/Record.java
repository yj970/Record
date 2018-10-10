package mvp.m;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * Created by yj on 2018/6/15.
 */

public class Record extends LitePalSupport implements Serializable{
    private String startDate;
    private String time;
    private String taskType;
    private String msg;// 心得

    public Record(String startDate, String time, String taskType) {
        this.startDate = startDate;
        this.time = time;
        this.taskType = taskType;
    }

    public Record() {
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
