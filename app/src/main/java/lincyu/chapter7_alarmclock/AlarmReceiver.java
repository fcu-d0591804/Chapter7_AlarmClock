package lincyu.chapter7_alarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min =  c.get(Calendar.MINUTE);
		
		Toast.makeText(context, /*"現在是 " + hour + " 點" + min*/"設置時間已到",
				Toast.LENGTH_LONG).show();
		System.out.print(min+"--------------------------");
	}
}
