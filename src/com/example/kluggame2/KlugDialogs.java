package com.example.kluggame2;



import com.example.catchbotanimated.R;

import android.app.Activity;
import android.app.ProgressDialog;

public class KlugDialogs {
	ProgressDialog pDialog;
	public KlugDialogs(Activity act)
	{
		pDialog=new ProgressDialog(act);
		pDialog.setContentView(R.layout.alert_layer);
	}

}
