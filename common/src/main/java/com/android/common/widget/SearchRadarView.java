package com.android.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


import com.android.common.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 扫描雷达自定义view
 */
public class SearchRadarView extends View {

    private static final String TAG = "SearchView";
    private int widthSize;
    private int heightSize;
    private Paint mPaint,circlePaint;
    RectF rectF;
    private final Context mContext;
    private final static int SIZE_CRICLE = 30;
    private int location=0;
    private int boWenNum = 4;
    private int distance;
    private float movePosition = 0;
    TimerTask myTask;
    Timer timer;
    int radis;
    SweepGradient mSweepGradient;
    Matrix matrix;

    public int getCircleNum() {
        return boWenNum;
    }

    public void setCircleNum(int boWenNum) {
        this.boWenNum = boWenNum;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public SearchRadarView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public SearchRadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public SearchRadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public SearchRadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView() {
        mPaint = new Paint();
        circlePaint = new Paint();
        circlePaint.setAlpha(100);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAlpha(100);
        matrix = new Matrix();
        mSweepGradient = new SweepGradient(radis,
                radis, //以圆弧中心作为扫描渲染的中心以便实现需要的效果
                new int[] {mContext.getColor(R.color.color_search) ,mContext.getColor(R.color.color_search) },new float[]{0f,0.1f}
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG,"onDraw");
        canvas.save();
        matrix.setRotate(location, radis, radis);
        mSweepGradient.setLocalMatrix(matrix);
        mPaint.setShader(mSweepGradient);
        canvas.drawArc(rectF,location,SIZE_CRICLE,true,mPaint);
        for (int i = 0; i < boWenNum+1; i++) {
            if (distance*i+movePosition< radis){
                float alpha = 100.0f*(radis - distance*i - movePosition)/radis;
                if (alpha>100) {
                    alpha =100;
                }
                circlePaint.setAlpha((int)alpha);
                canvas.drawCircle(radis,radis,distance*i+movePosition,circlePaint);
            }
        }
//        circlePaint.setAlpha(20);
//        canvas.drawCircle(radis,radis,radis,circlePaint);
//        canvas.restore();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthSize = MeasureSpec.getSize(widthMeasureSpec);
        heightSize = MeasureSpec.getSize(heightMeasureSpec);
        radis = Math.min(heightSize, widthSize)/2;
        rectF = new RectF(0,0, widthSize,heightSize);
        Log.d(TAG,"heightSize ="+heightSize+",widthSize = " + widthSize);
//        RadialGradient rg = new RadialGradient(radis,radis,radis,mContext.getColor(R.color.purple_200),mContext.getColor(R.color.white),Shader.TileMode.MIRROR);
//        circlePaint.setShader(rg);
        distance = radis/boWenNum;

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void startView(){
        Log.d(TAG,"startView()");
        if (myTask != null) {
            myTask.cancel();
            myTask = null;
        }
//把渐变设置到笔刷
        myTask = new TimerTask() {
            @Override
            public void run() {
                location = location+1;
                movePosition = movePosition+0.5f;
                if (movePosition > distance){
                    movePosition = 0;
                }
                postInvalidate();
            }
        };
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(myTask,0,10);
    }

    public void stopView(){
        Log.d(TAG,"stopView()");
        if (myTask != null) {
            myTask.cancel();
        }
    }

}
