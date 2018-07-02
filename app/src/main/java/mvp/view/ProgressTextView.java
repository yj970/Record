package mvp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.yj.record.R;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by yj on 2018/6/16.
 */

public class ProgressTextView extends TextView {
    // 控件宽度
    int width = -1;
    // 控件高度
    int height = -1;
    // 缩放率
    float scale = 0.8f;
    // 是否按下
    boolean isPress;
    // 中心圆画笔
    Paint circlePaint;
    // 外圈弧画笔
    Paint arcPaint;
    // 任务计时器
    Timer timer;
    // 回滚任务计时器
    Timer rollBACKTimer;
    // 是否处于回滚状态
    boolean isRollBack;
    // 出发点角度, 决定的只是从哪个位置划画弧线
    final int leaveAngle = -90;
    // 最初的角度
    final int startAngle = 0;
    // 外圈角度,  当angle == completeAngle时，任务完成。
    int angle = startAngle;
    // 每次计时递增的角度
    final int addAngle = 3;
    // 完成计时的任务角度
    final int completeAngle = (360+addAngle);
    // 字体大小
    float textSize = -1;

    Handler handler;
    // 计时任务完成监听
    ITimerTaskCompleteListener iTimerTaskCompleteImpl;


    public ProgressTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ProgressTextView(Context context) {
        super(context);
        init();
    }

    public ProgressTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {


        setClickable(true);

        textSize = getTextSize();

        circlePaint = new Paint();
        circlePaint.setColor(getResources().getColor(R.color.orange));
        circlePaint.setStrokeWidth(1);
        circlePaint.setAntiAlias(true);

        arcPaint = new Paint();
        arcPaint.setColor(getResources().getColor(R.color.orange));
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(2);
        arcPaint.setAntiAlias(true);
        arcPaint.setStrokeCap(Paint.Cap.BUTT);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    invalidate();
                } else if (msg.what == 1) {
                    invalidate();
                    if (iTimerTaskCompleteImpl != null) {
                        iTimerTaskCompleteImpl.onTimerTaskComplete();
                    }
                }
            }
        };

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isRollBack) {
            return super.onTouchEvent(event);
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            isRollBack = false;
            // 按下
            isPress = true;
            // 初始化角度
            angle = startAngle;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    // 每次递增角度
                    angle += addAngle;
                    Log.d("MyTAG", "angle="+angle);
                    // 发送事件，刷新
                    handler.sendEmptyMessage(0);
                    // 如果计时完成
                    if (angle >= completeAngle) {
                        timer.cancel();
                        handler.sendEmptyMessage(1);
                    }
                }
            }, 0, 10);// 10毫秒刷新一次

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            isRollBack = true;
            isPress = false;
            // 取消计时任务
            timer.cancel();
            invalidate();
            rollBACKTimer = new Timer();
            rollBACKTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    angle-=addAngle;
                    handler.sendEmptyMessage(0);
                    if (angle <+ startAngle) {
                        isRollBack = false;
                        rollBACKTimer.cancel();
                    }
                }
            }, 0, 10);
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float radius =  getWidth()/2*0.9f;
        if (isPress || isRollBack) {
            radius = radius*scale;
            // 从View的左上角开始算
            // left、top、right、bottom
            RectF rectF = new RectF(5 , 5 , width-5 , height-5);
            canvas.drawArc(rectF, leaveAngle, angle, false, arcPaint);
            setTextSize(textSize*scale);
        } else {
            setTextSize(textSize);
        }
        canvas.drawCircle(width/2, height/2, radius, circlePaint);
        super.onDraw(canvas);// 这玩意绘制Text内容, 要放在最后，不然text会被画的圆挡住
    }


    public interface ITimerTaskCompleteListener {
        void onTimerTaskComplete();
    }

    public void setTimerTaskCompleteImpl(ITimerTaskCompleteListener listener) {
        this.iTimerTaskCompleteImpl = listener;
    }
}