package mvp.v;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yj.record.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger2.component.DaggerMainViewComponent;
import dagger2.module.MainViewModule;
import mvp.Contract;
import mvp.Constant;
import mvp.p.MainPresenterImpl;

public class MainActivity extends AppCompatActivity implements Contract.MainView{
    @BindView(R.id.tv_cook)
    TextView tvCook;
    @BindView(R.id.tv_train)
    TextView tvTrain;
    @BindView(R.id.tv_read)
    TextView tvRead;
    @BindView(R.id.tv_study)
    TextView tvStudy;
    @Inject
    MainPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // 利用 darrger2 实例化 presenter
        DaggerMainViewComponent.builder().mainViewModule(new MainViewModule(this)).build().inject(this);
    }

    @OnClick({R.id.tv_cook, R.id.tv_train, R.id.tv_read, R.id.tv_study})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cook:
                presenter.startTask(Constant.TaskType.Task_COOK);
                break;
            case R.id.tv_train:
                presenter.startTask(Constant.TaskType.Task_TRAIN);
                break;
            case R.id.tv_read:
                presenter.startTask(Constant.TaskType.Task_READ);
                break;
            case R.id.tv_study:
                presenter.startTask(Constant.TaskType.Task_STUDY);
                break;
        }
    }

    @Override
    public void startTask(String nowDate, String taskType) {
        CountTimeActivity.startCountTimeActivity(this, nowDate, taskType);
    }
}
