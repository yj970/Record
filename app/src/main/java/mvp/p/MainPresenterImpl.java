package mvp.p;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import mvp.Contract;
import mvp.m.MainModel;

/**
 * Created by yj on 2018/6/12.
 */

public class MainPresenterImpl implements Contract.MainPresenter{
    private Contract.MainView view;
    private MainModel model;

    @Inject
    public MainPresenterImpl(Contract.MainView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void startTask(String taskType) {
        String nowDate = model.getNowDate();
        view.startTask(nowDate, taskType);
    }
}
