package mvp.v;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yj.record.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger2.component.DaggerCountTimeComponent;
import dagger2.module.CountTimeViewModule;
import mvp.Constant;
import mvp.Contract;
import mvp.p.CountTimePresenterImpl;

/**
 * Created by yj on 2018/6/12.
 */

public class CountTimeActivity extends AppCompatActivity implements Contract.CountTimeView {
    @Inject
    CountTimePresenterImpl presenter;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_count_time)
    TextView tvCountTime;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.tv_type)
    TextView tvType;

    public static void startCountTimeActivity(Activity activity, String nowDate, String TaskType) {
        Intent intent = new Intent(activity, CountTimeActivity.class);
        intent.putExtra(Constant.TaskType.Task_TYPE, TaskType);
        intent.putExtra(Constant.NOW_DATE, nowDate);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_time);
        ButterKnife.bind(this);

        DaggerCountTimeComponent.builder().countTimeViewModule(new CountTimeViewModule(this)).build().inject(this);

        String taskType = getIntent().getStringExtra(Constant.TaskType.Task_TYPE);
        String nowDate = getIntent().getStringExtra(Constant.NOW_DATE);
        presenter.init(taskType, nowDate);
    }

    @OnClick({R.id.tv_complete})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_complete:
                presenter.stopCount();
                break;
        }
    }


    @Override
    public void init(String taskType, String nowDate) {
        tvStartTime.setText(nowDate);
        tvType.setText(taskType);
    }

    @Override
    public void setTime(String time) {
        tvCountTime.setText(time);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.recovery();
    }
}
