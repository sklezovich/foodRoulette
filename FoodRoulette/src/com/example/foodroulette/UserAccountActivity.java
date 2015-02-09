package com.example.foodroulette;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class UserAccountActivity extends ActionBarActivity implements OnClickListener {
	
	private DatePicker Birthday;
	private TextView displayDate;
	private Button changeDate;
	private Button createAccount;
	
	private int year;
	private int month;
	private int day;
	
	static final int DATE_DIALOG_ID = 999;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_account);
		

        changeDate = (Button) findViewById(R.id.ChangeDateButton);
        createAccount = (Button) findViewById(R.id.CreateAccountButton);


        changeDate.setOnClickListener((OnClickListener) this);
        createAccount.setOnClickListener((OnClickListener) this);
        
		setCurrentDateOnView();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_account, menu);
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
	
	public void setCurrentDateOnView() {
		 
		displayDate = (TextView) findViewById(R.id.displayDateText);
		Birthday = (DatePicker) findViewById(R.id.BirthdayDatePicker);
 
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
 
		// set current date into textview
		displayDate.setText(new StringBuilder()
			// Month is 0 based, just add 1
			.append(month + 1).append("-").append(day).append("-")
			.append(year).append(" "));
 
		// set current date into datepicker
		Birthday.init(year, month, day, null);
	}
	

	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.ChangeDateButton:
				showDialog(DATE_DIALOG_ID);
			break;
			case R.id.CreateAccountButton:
				Intent intent = new Intent (this, PreferencesActivity.class);
					startActivity(intent);
					break;		
		}
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
		   // set date picker as current date
		   return new DatePickerDialog(this, datePickerListener, 
                         year, month,day);
		}
		return null;
	}
 
	private DatePickerDialog.OnDateSetListener datePickerListener 
                = new DatePickerDialog.OnDateSetListener() {
 
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
 
			// set selected date into textview
			displayDate.setText(new StringBuilder().append(month + 1)
			   .append("-").append(day).append("-").append(year)
			   .append(" "));
 
			// set selected date into datepicker also
			Birthday.init(year, month, day, null);
 
		}
	};
	
}
 

