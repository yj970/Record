package mvp;


import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * Created by yj on 2018/6/15.
 */

public class MyApp extends LitePalApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
