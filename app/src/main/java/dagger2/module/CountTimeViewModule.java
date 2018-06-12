package dagger2.module;

import dagger.Module;
import dagger.Provides;
import mvp.Contract;

/**
 * Created by yj on 2018/6/12.
 */

@Module
public class CountTimeViewModule {
    Contract.CountTimeView countTimeView;

    public CountTimeViewModule(Contract.CountTimeView countTimeView) {
        this.countTimeView = countTimeView;
    }

    @Provides
    public Contract.CountTimeView provideCountTimeView() {
        return countTimeView;
    }
}
