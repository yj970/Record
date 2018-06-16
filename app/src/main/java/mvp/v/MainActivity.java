package mvp.v;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yj.record.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.fab_add)
    FloatingActionButton fabAdd;
    @BindView(R.id.ll_action_parent)
    LinearLayout llActionParent;
    @BindView(R.id.ll_history)
    LinearLayout llHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 利用 darrger2 实例化 presenter
        DaggerMainViewComponent.builder().mainViewModule(new MainViewModule(this)).build().inject(this);
        presenter.init();


    }


    @OnClick({R.id.ll_cook, R.id.ll_train, R.id.ll_read, R.id.ll_study, R.id.fab_add, R.id.ll_history})
    void onViewClicked(View view) {
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
            case R.id.fab_add:
                clickFabAdd();
                break;
            case R.id.ll_history:
                HistoryRecordListActivity.startHistoryRecordListActivity(this);
                clickFabAdd();
                break;
        }
    }

    private void clickFabAdd() {
        boolean isShow = llActionParent.getVisibility() == View.VISIBLE ? true : false;
        if (isShow) {
            llActionParent.setVisibility(View.GONE);
            ObjectAnimator.ofFloat(fabAdd, "rotation", 135, 270).setDuration(300).start();
        } else {
            llActionParent.setVisibility(View.VISIBLE);
            ObjectAnimator.ofFloat(fabAdd, "rotation", 0, 135).setDuration(300).start();
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
