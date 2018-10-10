package mvp.v;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.yj.record.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import dagger2.component.DaggerHistoryRecordListComponent;
import dagger2.module.HistoryRecordListModule;
import mvp.Contract;
import mvp.adapter.HistoryRecordListAdapter;
import mvp.dialog.MaterialDialog;
import mvp.fragment.EditExperienceDialogFragment;
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
        setListener();
        presenter.init();
    }

    private void setListener() {
        adapter.setLongClickListenerImpl(new HistoryRecordListAdapter.ILongClickListener() {
            @Override
            public void onLongClickListener(Record record, int position) {
                showDeleteDialog(record, position);
            }
        });

        adapter.setEditClickListenerImpl(new HistoryRecordListAdapter.IEditClickListener() {
            @Override
            public void onEditClick(Record record) {
                showEditDialog(record);
            }
        });
    }

    // 显示心得dialog
    private void showEditDialog(Record record) {
        EditExperienceDialogFragment editNameDialog = EditExperienceDialogFragment.getInstance(record);
        editNameDialog.show(getSupportFragmentManager(), "EditExperienceDialogFragment");
    }

    private void initView() {
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        rvHistory.setAdapter(adapter);

        // item动画
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(500);
        defaultItemAnimator.setRemoveDuration(500);
        rvHistory.setItemAnimator(defaultItemAnimator);
    }

    private void showDeleteDialog(final Record record, final int position) {
       new MaterialDialog.Builder(this)
               .setMessage("确定删除这条记录吗?")
               .setPositiveClickListener(new MaterialDialog.IPositiveClickListener() {
                   @Override
                   public void onPositiveClickListener() {
                       presenter.deleteRecord(record, position);
                   }
               }).create().show();
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

    @Override
    public void deleteRecord(Record record, int position) {
        adapter.removeRecord(record);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, adapter.getData().size()-position);
    }

}
