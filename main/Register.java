package com.example.gawat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Register extends Activity {
	LinearLayout buttonSend;
	EditText textTo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
//		
//		buttonSend = (LinearLayout) findViewById(R.id.button_register);
//		textTo = (EditText) findViewById(R.id.etEmailRS);
//		
//		buttonSend.setOnClickListener(new Button.OnClickListener(){
//
//	        	
//			   @Override
//			   public void onClick(View arg0) {
// 
//			  String to = textTo.getText().toString();
//			  String message = "1234567";
// 
//			  Intent email = new Intent(Intent.ACTION_SEND);
//			  email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
//			  //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
//			  //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
//			  email.putExtra(Intent.EXTRA_TEXT, message);
//
//			  //need this to prompts email client only
//			  email.setType("message/rfc822");
//			  
//			  startActivity(Intent.createChooser(email, "Choose an Email client :"));
//			  
//			}
//		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
