package com.mm.mealapp.activity;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.miebo.utils.BaseActivity;


 @SuppressLint("NewApi")
public class FragmentActivity extends BaseActivity {

	private final String TAG = "FragmentActivity";

    private RadioGroup mRadioGroup;
    private FragmentTwo mSpeeddialFragmentTwo;
    private FragmentOne mSpeeddialFragmentOne;
    private FragmentThree mSpeeddialFragmentThree;
    RadioButton radio0,radio1,radio2;

    FragmentTransaction transaction;
    

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.activity_fragment);
        init_date();
        setupWidgets();
    }

    private void init_date(){
        transaction = getFragmentManager()
                .beginTransaction();
        if (null == mSpeeddialFragmentOne) {
            mSpeeddialFragmentOne = new FragmentOne();
        }
        transaction.add(R.id.fragment_container,
                mSpeeddialFragmentOne);
        // Commit the transaction
        transaction.commit();
    }

    private void setupWidgets() {

        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radio0 = (RadioButton)findViewById(R.id.radio0);
        radio1 = (RadioButton)findViewById(R.id.radio1);
        radio2 = (RadioButton)findViewById(R.id.radio2);
        mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub

                switch (checkedId) {
                case R.id.radio0:
                	radio0.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.index_red,0, 0);
                	radio0.setTextColor(Color.parseColor("#ff5a60"));
                	radio1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.order_gray,0, 0);
                	radio1.setTextColor(Color.parseColor("#989494"));
                	radio2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.mine_gray,0, 0);
                	radio2.setTextColor(Color.parseColor("#989494"));
                    Log.v(TAG, "setupWidgets():radio0 clicked");
                    if (null == mSpeeddialFragmentOne) {
                        mSpeeddialFragmentOne = new FragmentOne();
                    }
                    transaction = getFragmentManager()
                            .beginTransaction();
                    transaction.replace(R.id.fragment_container,
                            mSpeeddialFragmentOne);             
                    // Commit the transaction
                    transaction.commit();
                    break;
                case R.id.radio1:
                	radio0.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.index_gray,0, 0);
                	radio0.setTextColor(Color.parseColor("#989494"));
                	radio1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.order_red,0, 0);
                	radio1.setTextColor(Color.parseColor("#ff5a60"));
                	radio2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.mine_gray,0, 0);
                	radio2.setTextColor(Color.parseColor("#989494"));
                    Log.v(TAG, "setupWidgets():radio1 clicked");
                    if (null == mSpeeddialFragmentTwo) {
                        mSpeeddialFragmentTwo = new FragmentTwo();
                    }
                    transaction = getFragmentManager()
                            .beginTransaction();
                    transaction.replace(R.id.fragment_container,
                            mSpeeddialFragmentTwo);                 
                    // Commit the transaction
                    transaction.commit();
                    break;
                case R.id.radio2:
                	radio0.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.index_gray,0, 0);
                	radio0.setTextColor(Color.parseColor("#989494"));
                	radio1.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.order_gray,0, 0);
                	radio1.setTextColor(Color.parseColor("#989494"));
                	radio2.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.mine_red,0, 0);
                	radio2.setTextColor(Color.parseColor("#ff5a60"));
                    Log.v(TAG, "setupWidgets():radio2 clicked");

                    if (null == mSpeeddialFragmentThree) {
                        mSpeeddialFragmentThree = new FragmentThree();
                    }
                    transaction = getFragmentManager()
                            .beginTransaction();
                    transaction.replace(R.id.fragment_container,
                            mSpeeddialFragmentThree);                   
                    // Commit the transaction
                    transaction.commit();
                    break;

                default:
                    break;
                }
            }
        });
    }

	@Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        // dataEncapsulation.closeDataBase_speedDial();
    }

    
    
}

