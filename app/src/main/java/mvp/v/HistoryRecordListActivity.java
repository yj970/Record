package mvp.v;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.yj.record.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger2.component.DaggerHistoryRecordListComponent;
import dagger2.module.HistoryRecordListModule;
import mvp.Contract;
import mvp.adapter.HistoryRecordListAdapter;
import mvp.m.Record;
import mvp.p.HistoryRecordListPresenterImpl;

/**
 * Created by yj on 2018/6/15.
 */

public class HistoryRecordListActivity extends BaseActivity implements Contract.HistoryRecordListView {

    @BindView(R.id.rv_history)
    RecyclerView rvHistory;
    @Inject
    HistoryRecordListAdapter adapter;
    @Inject
    HistoryRecordListPresenterImpl presenter;


    public static void startHistoryRecordListActivity(Context context) {
        Intent intent = new Intent(context, HistoryRecordListActivity.class);
//        intent.putExtra(Constant.STATE_DATE, startDate);
//        intent.putExtra(Constant.TIME, time);
//        intent.putExtra(Constant.TaskType.TASK_TYPE, taskType);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_record_list);
        DaggerHistoryRecordListComponent.builder().historyRecordListModule(new HistoryRecordListModule(this)).build().inject(this);
        initView();
        presenter.init();
    }

    private void initView() {
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(adapter);
    }

    @Override
    public void bindToolbar(Toolbar toolbar) {
        toolbar.setTitle("历史记录");
    }

    @Override
    public void setHistoryRecordList(List<Record> list) {
        adapter.setData(list);
        adapter.notifyDataSetChanged();
    }
}
