package mvp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.yj.record.R;

/**
 * Created by yj on 2018/6/16.
 */

public class ProgressTextView extends TextView {
    int width = -1;
    int height = -1;


    float scale = 0.9f;// 缩放率

    boolean isPress;

    Paint paint;


    int m1;
    int l1;
    int d1;

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
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.red));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            isPress = true;

            // 缩小UI为原来的90%
            getLayoutParams().width = (int) (width*scale);
            getLayoutParams().height = (int) (height*scale);
            requestLayout();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            isPress = false;
            // 还原UI
            getLayoutParams().width =  width;
            getLayoutParams().height = height;
            requestLayout();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("MyTAG", "measure..."+(++m1)+"");
        // 获得原始的长宽
        if (width == -1 && getWidth() != 0) {
            width = getWidth();
            Log.d("MyTAG", "measure..."+width);
        }
        if (height == -1 && getHeight() != 0) {
            height = getHeight();
            Log.d("MyTAG", "measure..."+height);
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("MyTAG", "layout..."+(++l1)+"");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("MyTAG", "draw..."+(++d1)+"");
        if (isPress) {
            // 绘制外层圆
            float radius =  getWidth()/2;
//            canvas.drawCircle(width*scale/2, height*scale/2, radius, paint);
            Log.d("MyTAG", getLeft()+".........tranx");
            Log.d("MyTAG", getTop()+".........trany");
            Log.d("MyTAG", getRight()+".........tranr");
            Log.d("MyTAG", getBottom()+".........tranb");
            // 从View的左上角开始算
            // left、top、right、bottom
            RectF rectF = new RectF(0-10 , 0-10 , getWidth()+10 , getHeight()+10);
            canvas.drawArc(rectF, 0, 180, false, paint);
        }

    }
}
