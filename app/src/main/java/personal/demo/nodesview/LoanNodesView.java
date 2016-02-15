package personal.demo.nodesview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by user on 2016/1/22.
 * jcc
 */
public class LoanNodesView extends View{
    private DisplayMetrics metrics;
    private String[] nodes;
    private String[] times;
    private int currentNode;
    private Paint paint1=new Paint();
    private Paint paint2=new Paint();
    //数字画笔
    private TextPaint textPaint=new TextPaint();
    //文字画笔
    private TextPaint textPaint2=new TextPaint();
    //时间画笔
    private TextPaint textPaint3=new TextPaint();
    float width;
    float circle1;
    float circle2;

    float density;
    float screen_scaled_density;

    public LoanNodesView(Context context, AttributeSet attrs) {
        super(context, attrs);

        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        float screenWidth = metric.widthPixels;  // 屏幕宽度（像素）
        int screenHeight = metric.heightPixels;  // 屏幕高度（像素）
         density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）

        this.width=densityDpi;
        paint1.setColor(Color.parseColor("#33000088"));
        paint2.setColor(Color.parseColor("#ff228ce2"));
        textPaint2.setColor(Color.BLACK);
        textPaint2.setTextAlign(Paint.Align.CENTER);
        textPaint2.setTextSize(12*density);

        textPaint3.setColor(Color.BLACK);
        textPaint3.setTextAlign(Paint.Align.CENTER);
        textPaint3.setTextSize(9*density);
    }

    public LoanNodesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setNodes(String[] nodes,int currentNode){
        this.nodes=nodes;
        this.currentNode=currentNode;
        invalidate();
    }
    public void setTimes(String[] times){
        this.times=times;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int totalheight=getMeasuredHeight();
        int totalwidth=getWidth();
        width=totalwidth/6;
        circle1=width/4;
        circle2=width/6;

        if(nodes!=null) {
            for (int i = 0; i < nodes.length; i++) {
                //画圆圈
                if  (i < currentNode) {
                    drawNodes(canvas, i);
                } else {
                    drawGrayNodes(canvas, i);
                }
                //划线
                if (i < currentNode-1) {drawLine(canvas, i, 1);}
                else if(i==currentNode-1&&i<nodes.length-1){drawLine(canvas,i,2);}
                else if(i<nodes.length-1){drawLine(canvas,i,3);}
                //写字
                if(i<currentNode) {
                    drawText(canvas, i);
                }else{
                    drawGrayText(canvas,i);
                }
                //写时间
                if(times!=null) {
                    drawTime(canvas, i);
                }
            }
        }
    }

    public void drawTime(Canvas canvas,int i){
        if(i<times.length&&times[i]!=null&&times[i].contains(" ")) {
            canvas.drawText(times[i].split(" ")[0], width / 2 + i * width, width / 2 + 40*density, textPaint3);
            canvas.drawText(times[i].split(" ")[1],width / 2 + i * width, width / 2 + 48*density, textPaint3);

        }
    }

    private void drawGrayNodes(Canvas canvas,int i){
        paint2.setColor(Color.GRAY);
        RectF rectF2=new RectF(width/2-circle2+width*i,width/2-circle2,width/2+circle2+width*i,width/2+circle2);
        canvas.drawOval(rectF2, paint2);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(width/5);
        textPaint.setColor(Color.BLACK);
        textPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawText(i+1+"",width/2+width*i,width/2+4*density,textPaint);
    }
    private void drawNodes(Canvas canvas,int i){
        paint1.setColor(Color.parseColor("#33000088"));
        RectF rectF=new RectF(width/2-circle1+width*i,width/2-circle1,width/2+circle1+width*i,width/2+circle1);
        canvas.drawOval(rectF, paint1);
        paint2.setColor(Color.parseColor("#ff228ce2"));
        RectF rectF2=new RectF(width/2-circle2+width*i,width/2-circle2,width/2+circle2+width*i,width/2+circle2);
        canvas.drawOval(rectF2, paint2);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(10*density);
        textPaint.setColor(Color.WHITE);
        textPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawText(i + 1 + "", width / 2 + width * i, width / 2 + 4*density, textPaint);

    }
    //type 1 2 3为三种线： 短、左短右长 长
    private void drawLine(Canvas canvas,int i,int type){
        paint1.setStrokeWidth(3*density);
        switch (type){
            case 1:
                canvas.drawLine(width / 2 + circle1 + i * width, width / 2,
                        width / 2 - circle1 + (i + 1) * width, width / 2, paint1);
                break;
            case 2:
                canvas.drawLine(width / 2 + circle1 + i * width, width / 2,
                        width / 2 - circle2 + (i + 1) * width, width / 2, paint1);
                break;
            case 3:
                canvas.drawLine(width / 2 + circle2 + i * width, width / 2,
                        width / 2 - circle2 + (i + 1) * width, width / 2, paint1);
                break;
        }
    }

    private void drawText(Canvas canvas,int i){
        textPaint2.setColor(Color.parseColor("#ff228ce2"));
        canvas.drawText(nodes[i], width / 2 + i * width, width / 2 + 30*density, textPaint2);
    }
    private void drawGrayText(Canvas canvas,int i){
        textPaint2.setColor(Color.BLACK);
        canvas.drawText(nodes[i],width/2+i*width,width/2+30*density,textPaint2);
    }

}
