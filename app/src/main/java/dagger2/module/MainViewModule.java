package dagger2.module;

import dagger.Module;
import dagger.Provides;
import mvp.Contract;

/**
 * Created by yj on 2018/6/12.
 */

@Module
public class MainViewModule {
    Contract.MainView mainView;

    public MainViewModule(Contract.MainView mainView) {
        this.mainView = mainView;
    }

    @Provides
    public Contract.MainView provideMainView() {
        return mainView;
    }
}
