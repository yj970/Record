package dagger2.component;

import dagger.Component;
import dagger2.module.MainViewModule;
import mvp.v.MainActivity;

/**
 * Created by yj on 2018/6/12.
 */

@Component(modules = MainViewModule.class)
public interface MainViewComponent {
    void inject(MainActivity mainActivity);
}
