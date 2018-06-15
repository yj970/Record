package mvp.m;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.yj.record.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.inject.Inject;

import utils.RenderScriptUtil;

/**
 * Created by yj on 2018/6/12.
 */

public class MainModel {

    @Inject
    public MainModel() {
    }

    public String getNowDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public Bitmap getBgBitmap(Context context) {
        Bitmap sourceBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.bg_1);
        int random = new Random().nextInt(5)+20;// [20, 24]
        Bitmap bitmap = RenderScriptUtil.getRSBitmap(context, sourceBitmap, random);
        return bitmap;
    }
}
