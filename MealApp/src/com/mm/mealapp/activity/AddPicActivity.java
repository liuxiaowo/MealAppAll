package com.mm.mealapp.activity;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;



public class AddPicActivity extends Activity implements OnClickListener {

	private static final int REQUEST_CODE = 1;
	private ImageView addPic_imageview;
	private ImageButton back;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(1);
		setContentView(R.layout.activity_add_pic);
		addPic_imageview = (ImageView)findViewById(R.id.addPic_imageview);
		back = (ImageButton)findViewById(R.id.addPic_back);
		/*
		 * ����ϵͳѡ��ͼƬ���� ����1
		 */
		Intent intent = new Intent(Intent.ACTION_PICK,       
                 android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);  
        startActivityForResult(intent, REQUEST_CODE);
        addPic_imageview.setOnClickListener(this);
        back.setOnClickListener(this);
	}
	/*
	 * ����ϵͳѡ��ͼƬ���� ����2
	 */
	 @Override  
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	        if (resultCode == RESULT_OK) {  
	            Uri uri = data.getData();  
	            Log.e("uri", uri.toString());  
	            ContentResolver cr = this.getContentResolver();  
	            try {  
	                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));  
	                /* ��Bitmap�趨��ImageView */  
	                addPic_imageview.setImageBitmap(bitmap);  
	            } catch (FileNotFoundException e) {  
	                Log.e("Exception", e.getMessage(),e);  
	            }  
	        }else{
	        	addPic_imageview.setImageBitmap(null); 
	        }
	        super.onActivityResult(requestCode, resultCode, data);  
	    }  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_pic, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.addPic_imageview:
			Intent intent = new Intent(Intent.ACTION_PICK,       
	                 android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);  
	        startActivityForResult(intent, REQUEST_CODE);
			break;
		case R.id.addPic_back:
			Intent in = new Intent(getApplicationContext(),FragmentActivity.class);
			startActivity(in);
			finish();
			break;
		}
	}
	

}
