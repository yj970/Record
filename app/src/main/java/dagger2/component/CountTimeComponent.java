package dagger2.component;

import dagger.Component;
import dagger2.module.CountTimeViewModule;
import dagger2.module.HandlerModule;
import dagger2.module.TimerModule;
import mvp.v.CountTimeActivity;

/**
 * Created by yj on 2018/6/12.
 */

@Component(modules = {CountTimeViewModule.class, TimerModule.class, HandlerModule.class})
public interface CountTimeComponent {
    void inject(CountTimeActivity countTimeActivity);
}
