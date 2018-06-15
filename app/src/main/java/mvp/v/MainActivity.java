package mvp.v;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yj.record.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger2.component.DaggerMainViewComponent;
import dagger2.module.MainViewModule;
import mvp.Constant;
import mvp.Contract;
import mvp.p.MainPresenterImpl;

public class MainActivity extends BaseActivity implements Contract.MainView {
    @BindView(R.id.ll_cook)
    LinearLayout llCook;
    @BindView(R.id.ll_train)
    LinearLayout llTrain;
    @BindView(R.id.ll_read)
    LinearLayout llRead;
    @BindView(R.id.ll_study)
    LinearLayout llStudy;
    @BindView(R.id.rl_bg)
    RelativeLayout rlBg;
    @Inject
    MainPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 利用 darrger2 实例化 presenter
        DaggerMainViewComponent.builder().mainViewModule(new MainViewModule(this)).build().inject(this);
        presenter.init();
    }


    @OnClick({R.id.ll_cook, R.id.ll_train, R.id.ll_read, R.id.ll_study})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_cook:
                presenter.startTask(Constant.TaskType.Task_COOK);
                break;
            case R.id.ll_train:
                presenter.startTask(Constant.TaskType.Task_TRAIN);
                break;
            case R.id.ll_read:
                presenter.startTask(Constant.TaskType.Task_READ);
                break;
            case R.id.ll_study:
                presenter.startTask(Constant.TaskType.Task_STUDY);
                break;
        }
}

    @Override
    public void startTask(String nowDate, String taskType) {
        CountTimeActivity.startCountTimeActivity(this, nowDate, taskType);
    }

    @Override
    public void initBg(Bitmap bgBitmap) {
        rlBg.setBackground(new BitmapDrawable(getResources(), bgBitmap));
    }
}
