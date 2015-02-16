package com.example.modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final TextView textView1 = (TextView) findViewById(R.id.layer1);
		final TextView textView2 = (TextView) findViewById(R.id.layer2);
		final TextView textView3 = (TextView) findViewById(R.id.layer3);
		final TextView textView5 = (TextView) findViewById(R.id.layer5);
		
		SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar1);
		
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub

				textView1.setBackgroundColor(makeColor(getResources().getColor(R.color.l1startcolor), getResources().getColor(R.color.l1endcolor), progress));
				
				textView2.setBackgroundColor(makeColor(getResources().getColor(R.color.l2startcolor), getResources().getColor(R.color.l2endcolor), progress));
				
				textView3.setBackgroundColor(makeColor(getResources().getColor(R.color.l3startcolor), getResources().getColor(R.color.l3endcolor), progress));
				
				textView5.setBackgroundColor(makeColor(getResources().getColor(R.color.l5startcolor), getResources().getColor(R.color.l5endcolor), progress));
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if (id == R.id.optionsMenu) {

			OptionDialog optionsDialog = new OptionDialog();
			optionsDialog.show(getFragmentManager(), null);
			
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public static class OptionDialog extends DialogFragment{
		
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState){
		//Build the dialog
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		//Get the layout inflater
		LayoutInflater inflater = getActivity().getLayoutInflater();
		
		//Inflate and set the layout for the dialog
		//Pass null as the parent view because its going in the dialog layout  
		builder.setView(inflater.inflate(R.layout.options_dialog, null));
		
		// User cannot dismiss dialog by hitting back button
		builder.setCancelable(false);
		
		//Add the buttons
		builder.setPositiveButton(R.string.positive_dialog, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
				startActivity(intent);
			}
		});
		
		builder.setNegativeButton(R.string.negative_dialog, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		
		return builder.create();
		}
		
	}
	
	public int makeColor(int startColor, int endColor, int progress){
		int red, green, blue;
		int deltaR, deltaG, deltaB;
		
		deltaR = Color.red(endColor)-Color.red(startColor);
		deltaG = Color.green(endColor)-Color.green(startColor);
		deltaB = Color.blue(endColor)-Color.blue(startColor);
		
		red = Color.red(startColor) + (deltaR * progress/100);
		green = Color.green(startColor) + (deltaG * progress/100);
		blue = Color.blue(startColor) + (deltaB * progress/100);
		
		return Color.rgb(red, green, blue);
	}

}
