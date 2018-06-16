package mvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
    ILongClickListener longClickListenerImpl;


    @Inject
    public HistoryRecordListAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_record, parent, false));
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvStartDate.setText(data.get(position).getStartDate());
        holder.tvTime.setText(data.get(position).getTime());
        holder.tvTaskType.setText(data.get(position).getTaskType());
        holder.line.setVisibility(position == (data.size()-1) ? View.GONE : View.VISIBLE);
        holder.item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClickListenerImpl != null) {
                    longClickListenerImpl.onLongClickListener(data.get(position), position);
                }
                return true;
            }
        });
    }



    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void setData(List<Record> data) {
        this.data = data;
    }

    public List<Record> getData() {
        return data;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_start_date)
        TextView tvStartDate;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_task_type)
        TextView tvTaskType;
        @BindView(R.id.line)
        View line;
        @BindView(R.id.rl_parent)
        RelativeLayout item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public boolean addRecord(Record record) {
        if (data == null) {
            return false;
        } else {
          return  data.add(record);
        }
    }

    public boolean removeRecord(Record record) {
        if (data == null) {
            return false;
        } else {
           return data.remove(record);
        }
    }


    public interface ILongClickListener {
        void onLongClickListener(Record record, int position);
    }

    public void setLongClickListenerImpl(ILongClickListener longClickListenerImpl) {
        this.longClickListenerImpl = longClickListenerImpl;
    }
}
