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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;


public class UpFinishPicActivity extends Activity implements OnClickListener {

	private ImageView img;
	private Button upFinishPic_pre;
	private ImageButton upFinishPic_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(1);
		setContentView(R.layout.activity_up_finish_pic);
		img = (ImageView)findViewById(R.id.upFinish_food);
		upFinishPic_pre = (Button)findViewById(R.id.upFinishPic_pre);
		upFinishPic_back = (ImageButton)findViewById(R.id.upFinishPic_back);
		img.setOnClickListener(this);
		upFinishPic_pre.setOnClickListener(this);
		upFinishPic_back.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.up_finish_pic, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.upFinish_food:
			Intent intent = new Intent(Intent.ACTION_PICK,       
	                 android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);  
	        startActivityForResult(intent, 1);
			break;
		case R.id.upFinishPic_pre:
			Intent in = new Intent(getApplicationContext(),PreRecipeActivity.class);
			startActivity(in);
			break;
		case R.id.upFinishPic_back:
			Intent in2 = new Intent(getApplicationContext(),FragmentActivity.class);
			startActivity(in2);
			break;
		}
	}
	
	@Override  
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (resultCode == RESULT_OK) {  
            Uri uri = data.getData();  
            Log.e("uri", uri.toString());  
            ContentResolver cr = this.getContentResolver();  
            try {  
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));  
                /* ��Bitmap�趨��ImageView */  
                img.setImageBitmap(bitmap);  
            } catch (FileNotFoundException e) {  
                Log.e("Exception", e.getMessage(),e);  
            }  
        }else{
        	img.setImageBitmap(null); 
        }
        super.onActivityResult(requestCode, resultCode, data);  
    }  

}
