package dagger2.module;

import android.os.Handler;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yj on 2018/6/12.
 */

@Module
public class HandlerModule {

    @Provides
    public Handler provideHandler() {
        return new Handler();
    }
}
