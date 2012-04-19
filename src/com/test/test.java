package com.test;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class test extends Activity {
	ProgressBar bar; 

    Handler handler=new Handler() {
    	@Override
    	public void handleMessage(Message msg) {
    		bar.incrementProgressBy(5);
    	}
    };
    
    boolean isRunning = false;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bar = (ProgressBar)findViewById(R.id.process);
        //setContentView(R.layout.main);
    }
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		bar.setProgress(0);
		
		Thread background = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					for(int i=0; i<10 && isRunning;i++) {
						Thread.sleep(1000);
						handler.sendMessage(handler.obtainMessage());
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		
		isRunning = true;
		background.start();
		
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		isRunning = false;
	}
    

}