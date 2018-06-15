package mvp.v;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class CountTimeActivity extends BaseActivity implements Contract.CountTimeView {
    @Inject
    CountTimePresenterImpl presenter;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_count_time)
    TextView tvCountTime;
    @BindView(R.id.tv_complete)
    TextView tvComplete;

    public static void startCountTimeActivity(Activity activity, String nowDate, String TaskType) {
        Intent intent = new Intent(activity, CountTimeActivity.class);
        intent.putExtra(Constant.TaskType.TASK_TYPE, TaskType);
        intent.putExtra(Constant.STATE_DATE, nowDate);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_time);

        DaggerCountTimeComponent.builder().countTimeViewModule(new CountTimeViewModule(this)).build().inject(this);

        String taskType = getIntent().getStringExtra(Constant.TaskType.TASK_TYPE);
        String nowDate = getIntent().getStringExtra(Constant.STATE_DATE);
        presenter.init(taskType, nowDate);
    }

    @OnClick({R.id.tv_complete})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_complete:
                presenter.complete();
                break;
        }
    }


    @Override
    public void init(String taskType, String nowDate) {
        tvStartTime.setText(nowDate);
        toolbar.setTitle(taskType);
    }

    @Override
    public void setTime(String time) {
        tvCountTime.setText(time);
    }

    @Override
    public void gotoComplete(String nowDate, String type, String time) {
        HistoryRecordListActivity.startHistoryRecordListActivity(this);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.recovery();
    }
}
