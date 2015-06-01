package grace.pku.apm;

import com.newer.t4.demo.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DemoActivity extends Activity {

	/** Called when the activity is first created. */
	private ImageView image;// ���״̬ͼƬ
	private TextView textCD;// ��س��״̬
	private TextView textRL;// ���ʣ������
	private TextView textZT;// ���״̬
	private TextView textDY;// ��ص�ѹmV
	private TextView textWD;// ����¶�
	private TextView textLX;// �������
	private BroadcastReceiver myBroadcastReciver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			// ���ʣ������
			int level = (int) (intent
					.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
					/ (float) intent.getIntExtra(BatteryManager.EXTRA_SCALE,
							100) * 100);
			textRL.setText(level + "%");

			// ��ص�ǰʹ��״̬
			image.setImageResource(intent.getIntExtra(
					BatteryManager.EXTRA_ICON_SMALL, 0));
			switch (intent.getIntExtra(BatteryManager.EXTRA_STATUS, 1)) {
			case BatteryManager.BATTERY_STATUS_CHARGING:
				if (intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 1) == BatteryManager.BATTERY_PLUGGED_AC)
					textCD.setText("ʹ�ó���������");
				else
					textCD.setText("ʹ��USB�����");
				break;
			case BatteryManager.BATTERY_STATUS_DISCHARGING:
				textCD.setText("�ŵ���");
				break;
			case BatteryManager.BATTERY_STATUS_FULL:
				textCD.setText("�ѳ���");
				break;
			case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
				textCD.setText("δ����");
				break;
			}
			// ���״̬
			switch (intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 1)) {
			case BatteryManager.BATTERY_HEALTH_DEAD:
				textZT.setText("������𻵣�");
				textZT.setTextColor(Color.RED);
				break;
			case BatteryManager.BATTERY_HEALTH_GOOD:
				textZT.setText("����");
				break;
			case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
				textZT.setText("��ѹ����");
				break;
			case BatteryManager.BATTERY_HEALTH_OVERHEAT:
				textZT.setText("�¶ȹ���");
				break;
			case BatteryManager.BATTERY_HEALTH_UNKNOWN:
				textZT.setText("δ֪");
				break;
			case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
				textZT.setText("δ֪����");
				break;
			}
			// ��ص�ѹ
			textDY.setText(intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 1)+"mV");
			// ����¶�
			textWD.setText((intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 1)/10.0)+"��");
			// �������
			textLX.setText(intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY));

		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		textCD = (TextView) findViewById(R.id.textCD);
		textRL = (TextView) findViewById(R.id.textRL);
		textZT = (TextView) findViewById(R.id.textZT);
		textDY = (TextView) findViewById(R.id.textDY);
		textWD = (TextView) findViewById(R.id.textWD);
		textLX = (TextView) findViewById(R.id.textLX);
		image = (ImageView) findViewById(R.id.imageView1);
		registerReceiver(myBroadcastReciver, new IntentFilter(
				Intent.ACTION_BATTERY_CHANGED));
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(myBroadcastReciver);
	}
}