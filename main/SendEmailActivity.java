package com.example.gawat;
 
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
 
public class SendEmailActivity extends Activity {
 
	LinearLayout buttonSend;
	EditText textTo;
	EditText textSubject;
	EditText textMessage;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_email);
 
		buttonSend = (LinearLayout) findViewById(R.id.button_register);
		textTo = (EditText) findViewById(R.id.etEmailRS);
		
 
		buttonSend.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
 
			  String to = textTo.getText().toString();
			  String message = randomCode();
// 
//			  Intent email = new Intent(Intent.ACTION_SEND);
//			  email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
//			  //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
//			  //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
//			  email.putExtra(Intent.EXTRA_TEXT, message);
//
//			  //need this to prompts email client only
//			  email.setType("message/rfc822");
			  
			 // startActivity(Intent.createChooser(email, "Choose an Email client :"));
			  
			  Uri uri = Uri.parse("mailto:bmyanti.krb@gmail.com");
		        Intent myActivity2 = new Intent(Intent.ACTION_SENDTO, uri);                                   
		                    myActivity2.putExtra(Intent.EXTRA_SUBJECT,
		                "Customer comments/questions");
		        startActivity(myActivity2);
			  
			}
		});
	}
	
	public String randomCode()
	{
		Random r = new Random();
		int i1 = r.nextInt(80 - 65) + 65;
		String a = Integer.toString(i1);
		return a;
	}
}