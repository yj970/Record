package mvp.m;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by yj on 2018/6/15.
 */

public class HistoryRecordListModel {
    private List<Record> historyRecordData;

    @Inject
    public HistoryRecordListModel() {
        // test
//        historyRecordData = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//        historyRecordData.add(new Record("2018年6月"+i+"日", "00:30:35", "锻炼"));
//        }
    }

    public List<Record> getHistoryRecordData() {
        historyRecordData = LitePal.findAll(Record.class);
        return historyRecordData;
    }

    public void deleteRecord(Record record) {
        record.delete();
    }
}
