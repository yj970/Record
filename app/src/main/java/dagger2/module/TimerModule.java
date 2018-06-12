package dagger2.module;

import java.util.Timer;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yj on 2018/6/12.
 */

@Module
public class TimerModule {

    @Provides
    public Timer provideTimer() {
        return new Timer();
    }
}
