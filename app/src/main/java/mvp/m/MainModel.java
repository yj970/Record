package mvp.m;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

/**
 * Created by yj on 2018/6/12.
 */

public class MainModel {

    private String nowDate;

    @Inject
    public MainModel() {
    }

    public String getNowDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
