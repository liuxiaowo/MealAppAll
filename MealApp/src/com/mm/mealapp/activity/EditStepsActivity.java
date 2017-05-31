package com.mm.mealapp.activity;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class EditStepsActivity extends Activity implements OnClickListener {

	private ImageButton back,editSteps_del;
	private ImageView editSteps_img;
	private TextView editSteps_confirm,editSteps_num;
	private EditText editSteps_edit;
	public static int num =0;
	public static Uri uri;
	public static Drawable drawable;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(1);
		setContentView(R.layout.activity_edit_steps);
		back = (ImageButton)findViewById(R.id.editSteps_back);
		editSteps_img = (ImageView)findViewById(R.id.editSteps_img);
		editSteps_del = (ImageButton)findViewById(R.id.editSteps_del);
		editSteps_confirm = (TextView)findViewById(R.id.editSteps_confirm);
		editSteps_edit = (EditText)findViewById(R.id.editSteps_edit);
		editSteps_num = (TextView)findViewById(R.id.editSteps_num);
		editSteps_num.setText(""+(num+1));
		back.setOnClickListener(this);
		editSteps_img.setOnClickListener(this);
		editSteps_del.setOnClickListener(this);
		editSteps_confirm.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_steps, menu);
		return true;
	}
	 @Override  
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	        if (resultCode == RESULT_OK) {  
	            uri = data.getData();  
	            Log.e("uri", uri.toString());  
	            ContentResolver cr = this.getContentResolver();  
	            try {  
	                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));  
	                /* ��Bitmap�趨��ImageView */  
	                editSteps_img.setImageBitmap(bitmap);  
	            } catch (FileNotFoundException e) {  
	                Log.e("Exception", e.getMessage(),e);  
	            }  
	        }else{
	        	editSteps_img.setImageBitmap(null); 
	        }
	        super.onActivityResult(requestCode, resultCode, data);  
	    }  
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.editSteps_back:
			Intent in = new Intent(getApplicationContext(),ProductionStepsActivity.class);
			startActivity(in);
			break;
		case R.id.editSteps_img:
			Intent intent = new Intent(Intent.ACTION_PICK,       
	                 android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);  
	        startActivityForResult(intent, 1);
			break;
		case R.id.editSteps_del:
			finish();
			break;
		case R.id.editSteps_confirm:
			num++;
			drawable = editSteps_img.getDrawable();
			Intent in3 = new Intent(getApplicationContext(),ProductionStepsActivity.class);
			in3.putExtra("word", editSteps_edit.getText().toString());
			startActivity(in3);
			break;
		}
	}

}
