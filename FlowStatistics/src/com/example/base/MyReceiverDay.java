package com.example.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.TrafficStats;

import com.example.flowstatistics.MainActivity;

/**
 * �˴�����ա�http://www.eoeandroid.com/thread-171911-1-1.html��
 * javaapk.com�ṩ����
 * @author yand
 * 
 */
public class MyReceiverDay extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		DataSupport minsert = new DataSupport(context);
		// ��ȡͨ��Mobile�����յ����ֽ�����������Android123��ʾ��Ҳ�����WiFi
		long g3_down_total = TrafficStats.getMobileRxBytes();
		// Mobile���͵����ֽ���
		long g3_up_total = TrafficStats.getMobileTxBytes();
		// ��ȡ�ܵĽ����ֽ���������Mobile��WiFi��
		long mrdown_total = TrafficStats.getTotalRxBytes();
		// �ܵķ����ֽ���������Mobile��WiFi��
		long mtup_total = TrafficStats.getTotalTxBytes();
		minsert.insertNow(g3_down_total, MainActivity.RXG, MainActivity.RX3G,
				MainActivity.NORMAL);
		minsert.insertNow(g3_up_total, MainActivity.TXG, MainActivity.TX3G,
				MainActivity.NORMAL);
		minsert.insertNow(mrdown_total, MainActivity.RX, MainActivity.RXT,
				MainActivity.NORMAL);
		minsert.insertNow(mtup_total, MainActivity.TX, MainActivity.TXT,
				MainActivity.NORMAL);
	}

}
