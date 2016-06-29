package com.chw.tetrisgame.ui;

import java.util.Timer;
import java.util.TimerTask;

import com.chw.tetrisgame.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends Activity{
	public static int VIEW_WELCOME=0;
	public static int VIEW_MAIN=1;
	
	/**
	 * 主界面
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		Timer timer=new Timer();
		timer.schedule(new MyTask(), 3000); 
//		Intent it=new Intent(MainActivity.this,TetrisActivity.class);
//		startActivity(it);
//		finish();
//		handler.sendEmptyMessage(0);
//		handler.post(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				try {
//					
//			
//			
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			
//		}
//			}
//		});
		
		
	}
	class MyTask extends TimerTask {  
		  
	    @Override  
	    public void run() {  
	    	Intent it=new Intent(MainActivity.this,TetrisActivity.class);
			startActivity(it);
			finish();
	  
	    } 
	}
}
