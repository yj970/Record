package mvp.v;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.yj.record.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yj on 2018/6/15.
 */

public class BaseActivity extends AppCompatActivity{
    // @Nullable代表对象可以为null。若没有这个注解，在找不到R.id.toolbar时就会崩溃。
    @Nullable
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    public void setContentView(int layout) {
        super.setContentView(layout);
        ButterKnife.bind(this);
        bindToolbar(toolbar);
        setToolbarNavigationIconClickListener(toolbar);
    }

    public void bindToolbar(Toolbar toolbar) {}

    // 导航按钮点击事件
    public void setToolbarNavigationIconClickListener(Toolbar toolbar) {
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }
}
