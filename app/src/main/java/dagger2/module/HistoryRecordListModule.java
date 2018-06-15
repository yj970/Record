package dagger2.module;

import dagger.Module;
import dagger.Provides;
import mvp.Contract;

/**
 * Created by yj on 2018/6/15.
 */
@Module
public class HistoryRecordListModule {
    Contract.HistoryRecordListView historyRecordListView;

    public HistoryRecordListModule(Contract.HistoryRecordListView historyRecordListView) {
        this.historyRecordListView = historyRecordListView;
    }

    @Provides
    public Contract.HistoryRecordListView provideHistoryRecordListView() {
        return historyRecordListView;
    }
}
