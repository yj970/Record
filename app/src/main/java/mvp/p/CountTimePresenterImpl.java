package mvp.p;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;


import mvp.Contract;
import mvp.m.CountTimeModel;

/**
 * Created by yj on 2018/6/12.
 */

public class CountTimePresenterImpl implements Contract.CountTimePresenter {
    Contract.CountTimeView view;
    CountTimeModel model;
    Timer timer;
    Handler handler;

    @Inject
    public CountTimePresenterImpl(Contract.CountTimeView view, CountTimeModel model, Timer timer, Handler handler) {
        this.view = view;
        this.model = model;
        this.timer = timer;
        this.handler = handler;
    }

    @Override
    public void init(String taskType, String nowDate) {
        // 存储数据
        model.setStartDate(nowDate);
        model.setTaskType(taskType);

        String type = model.getShowType();
        view.init(type, nowDate);
        // 启动定时器
        model.resetTime();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                final String time = model.updateTime();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.setTime(time);
                    }
                });
            }
        }, 0, 1000);

    }

    @Override
    public void stopCount() {
        timer.cancel();
    }

    /**
     * 回收资源
     */
    @Override
    public void recovery() {
        timer.cancel();
        timer = null;
    }

    @Override
    public void complete() {
        timer.cancel();
        model.saveRecord();
        view.gotoComplete(model.getStartDate(), model.getTaskType(), model.getTime());
    }

}
