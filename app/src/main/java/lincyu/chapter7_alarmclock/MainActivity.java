package lincyu.chapter7_alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends ActionBarActivity {

	PendingIntent pendingintent;
	AlarmManager am;
	EditText text1;
	EditText text2;

	String hour;
	String min;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btn = (Button)findViewById(R.id.btn_start);
		btn.setOnClickListener(startlistener);
		btn = (Button)findViewById(R.id.btn_stop);
		btn.setOnClickListener(stoplistener);

		text1 = (EditText) findViewById(R.id.editText);
		text2 = (EditText) findViewById(R.id.editText2);


		Intent intent = new Intent();
		intent.setClass(MainActivity.this, AlarmReceiver.class);
		
		pendingintent = PendingIntent.getBroadcast(
				MainActivity.this, 1, intent, 0);

		am = (AlarmManager)getSystemService(ALARM_SERVICE);	
	}

	private OnClickListener startlistener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			startReport();
		}
	};

	private OnClickListener stoplistener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			stopReport();
		}
	};

	private void startReport() {

		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		hour = text1.getText().toString();
		min = text2.getText().toString();

//		int a = Integer.parseInt(hour)-Calendar.HOUR;
//		int b = Integer.parseInt(min)-Calendar.getInstance().MINUTE;
//		int d = ;
//		c.add(Calendar.HOUR, a);
		c.add(Calendar.MINUTE, 1);
//		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);


		
		long interval = 1000*60;
		am.setRepeating(AlarmManager.RTC_WAKEUP,
				c.getTimeInMillis(), interval, pendingintent);
		String z = "整點報時開始  " + hour + ":" + min;
		Toast.makeText(MainActivity.this, z,
				Toast.LENGTH_SHORT).show();
	}

	private void stopReport() {
		
		am.cancel(pendingintent);
		Toast.makeText(MainActivity.this, "停止整點報時",
				Toast.LENGTH_SHORT).show();
	}
}
