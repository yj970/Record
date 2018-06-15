package dagger2.component;

import dagger.Component;
import dagger2.module.HistoryRecordListModule;
import mvp.v.HistoryRecordListActivity;

/**
 * Created by yj on 2018/6/15.
 */

@Component(modules = HistoryRecordListModule.class)
public interface HistoryRecordListComponent {
    void inject(HistoryRecordListActivity historyRecordListActivity);
}
