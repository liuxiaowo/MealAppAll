package com.mm.mealapp.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

public class MyEditText extends EditText{

 
    private Drawable dRight;  
    private Rect rBounds;  
  
    public MyEditText(Context paramContext) {  
        super(paramContext);  
        initEditText();  
    }  
  
    public MyEditText(Context paramContext, AttributeSet paramAttributeSet) {  
        super(paramContext, paramAttributeSet);  
        initEditText();  
    }  
  
    public MyEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {  
        super(paramContext, paramAttributeSet, paramInt);  
        initEditText();  
    }  
  
    // 锟斤拷始锟斤拷edittext 锟截硷拷  
    private void initEditText() {  
        setEditTextDrawable();  
        addTextChangedListener(new TextWatcher() { // 锟斤拷锟侥憋拷锟斤拷锟捷改憋拷锟斤拷屑锟斤拷锟� 
            @Override  
            public void afterTextChanged(Editable paramEditable) {  
            }  
  
            @Override  
            public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {  
            }  
  
            @Override  
            public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {  
                MyEditText.this.setEditTextDrawable();  
            }  
        });  
    }  
  
    // 锟斤拷锟斤拷图片锟斤拷锟斤拷示  
    public void setEditTextDrawable() {  
        if (getText().toString().length() == 0) {  
            setCompoundDrawables(null, null, null, null);  
        } else {  
            setCompoundDrawables(null, null, this.dRight, null);  
        }  
    }  
  
    @Override  
    protected void onDetachedFromWindow() {  
        super.onDetachedFromWindow();  
        this.dRight = null;  
        this.rBounds = null;  
  
    }  
  
    /** 
     * 锟斤拷哟锟斤拷锟斤拷录锟�锟斤拷锟街拷锟�锟斤拷锟斤拷 锟斤拷锟絜ditText锟斤拷效锟斤拷 
     */  
    @Override  
    public boolean onTouchEvent(MotionEvent paramMotionEvent) {  
        if ((this.dRight != null) && (paramMotionEvent.getAction() == 1)) {  
            this.rBounds = this.dRight.getBounds();  
            int i = (int) paramMotionEvent.getRawX();// 锟斤拷锟斤拷锟斤拷幕锟侥撅拷锟斤拷  
            // int i = (int) paramMotionEvent.getX();//锟斤拷锟斤拷呖锟侥撅拷锟斤拷  
            if (i > getRight() - 3 * this.rBounds.width()) {  
                setText("");  
                paramMotionEvent.setAction(MotionEvent.ACTION_CANCEL);  
            }  
        }  
        return super.onTouchEvent(paramMotionEvent);  
    }  
  
    /** 
     * 锟斤拷示锟揭诧拷X图片锟斤拷 
     *  
     * 锟斤拷锟斤拷锟斤拷锟斤拷 
     */  
    @Override  
    public void setCompoundDrawables(Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4) {  
        if (paramDrawable3 != null)  
            this.dRight = paramDrawable3;  
        super.setCompoundDrawables(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);  
    }  
}
