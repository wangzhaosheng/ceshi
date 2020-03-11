package com.example;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import ga.mdm.PolicyManager;

public class ApnActivity extends AppCompatActivity {

    //部标api
//    PolicyManager mPolicyManager = PolicyManager.getInstance();
    private EditText et_iccid;
    private EditText et_apn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apn);
        et_iccid=findViewById(R.id.et_iccid);
        et_apn=findViewById(R.id.et_apn);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public void click78(View view) {
        List<SubscriptionInfo> list = SubscriptionManager.from(this).getActiveSubscriptionInfoList();

        for (SubscriptionInfo info : list) {
            Log.d("Q_M", "ICCID-->" + info.getIccId());//898600810117F0001559

        }

        for (int i = 0; i < list.size(); i++) {
            String iccId = list.get(0).getIccId();
            et_iccid.setText(iccId);
        }

    }


    public void click79(View view) {
        List<String> apnString = queryApn();
        String aa="";
        for (String s : apnString) {
            aa=aa+s+"----\r\n";
        }
        et_apn.setText(aa);
    }

    public List<String> queryApn() {
        List<String> listapn =new ArrayList<String>();
        //部标api
        PolicyManager mPolicyManager = PolicyManager.getInstance();
        try {
            List<Integer> idList = mPolicyManager.getApnList();
            if (idList != null) {
                for (int i = 0; i < idList.size(); i++) {
                    int apnId = idList.get(i);
                    String apnInfo = mPolicyManager.getApnInfo(apnId);
                    listapn.add(apnInfo);
//                    Log.e(TAG, "apnId: " + apnId + ", " + apnInfo);
                }
            } else {
//                Log.e(TAG, "idList is null");
            }
        } catch (Exception e) {
//            Log.e(TAG, "idList Exception: " + e);
            e.printStackTrace();
        }
        return listapn;
    }
}
