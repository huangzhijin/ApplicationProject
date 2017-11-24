package com.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

/**
 * Created by pc on 2017/11/23.
 */

public class MyView extends View {
    private Paint paint,paint2;
    private Context context;
    int R=400,r=200,distance=400,rectF_with=400,rectF_height=100,deg=35,top=500;


    public MyView(Context context) {
        super(context);
        this.context=context;
        initPaint();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint(){
        paint=new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);

        paint2=new Paint();
        paint2.setColor(Color.GREEN);
        paint2.setAntiAlias(true);
        paint2.setStrokeWidth(2);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        paint2.setStrokeJoin(Paint.Join.ROUND);
        paint2.setStyle(Paint.Style.FILL);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获取屏幕的宽高
        DisplayMetrics displayMetrics=new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screem_height=displayMetrics.heightPixels;
        int screem_width=displayMetrics.widthPixels;

        //如果有save（）和restore（），那么平移、缩放、旋转等操作只对save（）和restore（）作用域之间的代码有效。
        //画布平移屏幕中点，然后旋转
//        canvas.translate(screem_width/2, screem_height/2);
        canvas.translate(screem_width/2, screem_height/4);
        canvas.save();
        canvas.rotate(-36);
        canvas.drawColor(Color.WHITE);

        Path path=new Path();

       double degrees= Math.toDegrees(Math.PI);
        Log.i("GLDN_LOG","degrees==="+degrees);
        double  radians= Math.toRadians(degrees);
        Log.i("GLDN_LOG","radians==="+radians);
        //外圆环
        canvas.drawCircle(0,0,R,paint);
       //内圆环
        canvas.drawCircle(0,0,r,paint);
        for(int i=0;i<5;i++){
             //外圆环
              //算弧度
             double radian= ((18+72*i)/180.0)*Math.PI;
              //由弧度转化为角度
             double  degree= Math.toDegrees(radian);
//            int x= (int)(Math.cos(Math.toDegrees(degree))*R) ;
//            int y= (int)( Math.sin(Math.toDegrees(degree))*R);
            int x= (int)(Math.cos(radian)*R) ;
            int y= (int)( Math.sin(radian)*R);
            //内圆环
            //算弧度
            double radian2= ((54+72*i)/180.0)*Math.PI;
            //由弧度转化为角度
            double  degree2= Math.toDegrees(radian2);
//            int x2= (int)(Math.cos(Math.toDegrees(degree2))*r) ;
//            int y2= (int)( Math.sin(Math.toDegrees(degree2))*r);
            int x2= (int)(Math.cos(radian2)*r) ;
            int y2= (int)( Math.sin(radian2)*r);
             Log.i("GLDN_LOG","x==="+x+";y=="+y);

             if(i==0){
                 path.moveTo(x,y);
                 path.lineTo(x2,y2);
             }else{
                 path.lineTo(x,y);
                 path.lineTo(x2,y2);
             }
         }
        path.close();
        canvas.drawPath(path,paint);
        canvas.restore();
        //画细线
        canvas.drawLine(0,R,0,screem_height/2+R,paint);

        //绘制右边扇形
        drawRightArc(canvas,deg);

        //绘制左边扇形
        drawLeftArc(canvas,deg);

        new Thread(new MyThread()).start();
    }

    private void drawRightArc(Canvas canvas,int deg){
        canvas.save();
        canvas.translate((float) Math.sin(-deg/180.0*Math.PI)*(R+distance+rectF_height/2),(float) Math.cos(-deg/180.0*Math.PI)*(R+distance+rectF_height/2));
        canvas.rotate(-deg);
        RectF rectF=new RectF(0,R+distance,rectF_with,R+distance+rectF_height);
        canvas.drawArc(rectF,0,180,false,paint2);
//        canvas.drawRect(rectF,paint);
        canvas.restore();
    }

    private void drawLeftArc(Canvas canvas,int deg){
        canvas.save();
        canvas.translate((float) Math.sin(deg/180.0*Math.PI)*(R+distance-rectF_height/2),(float) Math.cos(deg/180.0*Math.PI)*(R+distance-rectF_height/2));
        canvas.rotate(deg);
        RectF rectF2=new RectF(-rectF_with,R+distance-rectF_height,0,R+distance);
        canvas.drawArc(rectF2,0,180,false,paint2);
//        canvas.drawRect(rectF2,paint);
        canvas.restore();
    }

   private class  MyThread implements Runnable{
       @Override
       public void run() {
          if(deg>=35&&deg<60){
              deg=deg+10;
          }else{
              deg=35;
          }
           try {
               Thread.sleep(3000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           Log.i("GLDN_LOG","deg==="+deg);
           postInvalidate();
       }
   }






    private void drawTrag(){
//        int width=200,height=200;
 //       path.moveTo(screem_width/2,screem_height/2);
//        path.lineTo(screem_width/2+width,screem_height/2);
//        path.lineTo(screem_width/2+width/2,screem_height/2+height);
//        path.addCircle(screem_width/2,screem_height/2,width, Path.Direction.CW);
//        canvas.drawTextOnPath("这是三角形这是三角形这是三角形这是三角形",path,0,20,paint);
//        path.close();
//        canvas.drawPath(path,paint);

//        所以外顶点坐标 x:  Math.cos( (18+72*i)/180*Math.PI) * R
//
//        y:  Math.sin((18+72*i)/180*Math.PI) * R
//
//        所以内顶点坐标 x:  Math.cos( (54+72*i)/180*Math.PI) * r
//
//        y:  Math.sin((54+72*i)/180*Math.PI) * r
//        Math.toDegrees(angrad);     //弧度转换成角度，返回：angdeg / 180d * PI
//                                       //角度转换成弧度，返回：angrad * 180d / PI
//        Math.toRadians(angdeg);
    }



}
