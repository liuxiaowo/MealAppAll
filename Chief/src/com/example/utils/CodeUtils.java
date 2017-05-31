package com.example.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by Jackie on 2015/12/1.
 * ����ͼƬ��֤��Ĺ�����
 */
public class CodeUtils {
    private static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    private static CodeUtils mCodeUtils;
    private int mPaddingLeft, mPaddingTop;
    private StringBuilder mBuilder = new StringBuilder();
    private Random mRandom = new Random();

    //Default Settings
    private static final int DEFAULT_CODE_LENGTH = 4;//��֤��ĳ���  ������4λ
    private static final int DEFAULT_FONT_SIZE = 60;//�����С
    private static final int DEFAULT_LINE_NUMBER = 3;//������������
    private static final int BASE_PADDING_LEFT = 20; //��߾�
    private static final int RANGE_PADDING_LEFT = 35;//��߾෶Χֵ
    private static final int BASE_PADDING_TOP = 42;//�ϱ߾�
    private static final int RANGE_PADDING_TOP = 15;//�ϱ߾෶Χֵ
    private static final int DEFAULT_WIDTH = 200;//Ĭ�Ͽ��.ͼƬ���ܿ�
    private static final int DEFAULT_HEIGHT = 100;//Ĭ�ϸ߶�.ͼƬ���ܸ�
    private static final int DEFAULT_COLOR = 0xDF;//Ĭ�ϱ�����ɫֵ

    public static CodeUtils getInstance() {
        if(mCodeUtils == null) {
            mCodeUtils = new CodeUtils();
        }
        return mCodeUtils;
    }

    //������֤��ͼƬ
    public Bitmap createBitmap() {
        mPaddingLeft = 0; //ÿ��������֤��ͼƬʱ��ʼ��
        mPaddingTop = 0;

        Bitmap bitmap = Bitmap.createBitmap(DEFAULT_WIDTH, DEFAULT_HEIGHT, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        //���ɵ���֤��
        String code = createCode();

        canvas.drawColor(Color.rgb(DEFAULT_COLOR, DEFAULT_COLOR, DEFAULT_COLOR));
        Paint paint = new Paint();
        paint.setTextSize(DEFAULT_FONT_SIZE);

        for (int i = 0; i < code.length(); i++) {
            randomTextStyle(paint);
            randomPadding();
            canvas.drawText(code.charAt(i) + "" , mPaddingLeft, mPaddingTop, paint);
        }

        //������
        for (int i = 0; i < DEFAULT_LINE_NUMBER; i++) {
            drawLine(canvas, paint);
        }

        canvas.save(Canvas.ALL_SAVE_FLAG);//����
        canvas.restore();
        return bitmap;
    }

    //������֤��
    public String createCode() {
        mBuilder.delete(0, mBuilder.length()); //ʹ��֮ǰ�����������

        for (int i = 0; i < DEFAULT_CODE_LENGTH; i++) {
            mBuilder.append(CHARS[mRandom.nextInt(CHARS.length)]);
        }

        return mBuilder.toString();
    }

    //���ɸ�����
    private void drawLine(Canvas canvas, Paint paint) {
        int color = randomColor();
        int startX = mRandom.nextInt(DEFAULT_WIDTH);
        int startY = mRandom.nextInt(DEFAULT_HEIGHT);
        int stopX = mRandom.nextInt(DEFAULT_WIDTH);
        int stopY = mRandom.nextInt(DEFAULT_HEIGHT);
        paint.setStrokeWidth(1);
        paint.setColor(color);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    //�����ɫ
    private int randomColor() {
        mBuilder.delete(0, mBuilder.length()); //ʹ��֮ǰ�����������

        String haxString;
        for (int i = 0; i < 3; i++) {
            haxString = Integer.toHexString(mRandom.nextInt(0xFF));
            if (haxString.length() == 1) {
                haxString = "0" + haxString;
            }

            mBuilder.append(haxString);
        }

        return Color.parseColor("#" + mBuilder.toString());
    }

    //����ı���ʽ
    private void randomTextStyle(Paint paint) {
        int color = randomColor();
        paint.setColor(color);
        paint.setFakeBoldText(mRandom.nextBoolean());  //trueΪ���壬falseΪ�Ǵ���
        float skewX = mRandom.nextInt(11) / 10;
        skewX = mRandom.nextBoolean() ? skewX : -skewX;
        paint.setTextSkewX(skewX); //float���Ͳ�����������ʾ��б��������б
//        paint.setUnderlineText(true); //trueΪ�»��ߣ�falseΪ���»���
//        paint.setStrikeThruText(true); //trueΪɾ���ߣ�falseΪ��ɾ����
    }

    //������
    private void randomPadding() {
        mPaddingLeft += BASE_PADDING_LEFT + mRandom.nextInt(RANGE_PADDING_LEFT);
        mPaddingTop = BASE_PADDING_TOP + mRandom.nextInt(RANGE_PADDING_TOP);
    }
}
