package mvp.p;

import java.util.List;

import javax.inject.Inject;

import mvp.Contract;
import mvp.m.HistoryRecordListModel;
import mvp.m.Record;

/**
 * Created by yj on 2018/6/15.
 */

public class HistoryRecordListPresenterImpl implements Contract.HistoryRecordListPresenter {
    Contract.HistoryRecordListView view;
    HistoryRecordListModel model;

    @Inject
    public HistoryRecordListPresenterImpl(Contract.HistoryRecordListView view, HistoryRecordListModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void init() {
        List<Record> list = model.getHistoryRecordData();
        view.setHistoryRecordList(list);
    }

    @Override
    public void deleteRecord(Record record, int position) {
        // 数据库删除
        model.deleteRecord(record);
        // UI删除
        view.deleteRecord(record, position);
    }

}
