package mvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yj.record.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.m.Record;

/**
 * Created by yj on 2018/6/15.
 */

public class HistoryRecordListAdapter extends RecyclerView.Adapter<HistoryRecordListAdapter.ViewHolder>{
    private List<Record> data;

    @Inject
    public HistoryRecordListAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_record, parent, false));
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvStartDate.setText(data.get(position).getStartDate());
        holder.tvTime.setText(data.get(position).getTime());
        holder.tvTaskType.setText(data.get(position).getTaskType());
    }



    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(List<Record> data) {
        this.data = data;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_start_date)
        TextView tvStartDate;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_task_type)
        TextView tvTaskType;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
