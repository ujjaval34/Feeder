package com.feederfox.ujaval.politician.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.widget.Toast;

public class Utilities {

	private static Toast vToastMsg 			= null;
	private static ProgressDialog vRingProgressDialog	= null;
	private static Activity vAppContext 		= null;
	
	/*
	 * set the application context
	 */
	public static void setApplicationContext(Activity pAppContext) {
		vAppContext = pAppContext;
	}
	
	/*
	 * returns the application context
	 */
	public static Activity getApplicationContext() {
		return vAppContext;
	}
	
	/*
	 * shows the progress dialog
	 */
	public static void showProgressDialog(final String pHeadingToBeDisplayed, final String pBodyToBeDisplayed, final boolean pIsCancelable) {
		
		// if progress dialog is not initiliazed 
		if(vRingProgressDialog == null) {
			
			// create and show the progress dialog
			vRingProgressDialog = ProgressDialog.show(vAppContext, pHeadingToBeDisplayed, pBodyToBeDisplayed, true, pIsCancelable);
		}
		else {
			
			// show the progress dialog
			vRingProgressDialog.show();
		}
	}
	
	/*
	 * hides the progress dialog
	 */
	public static void hideProgressDialog() {
		
		// if progress dialog is initiliazed
		if(vRingProgressDialog!=null) {	
			
			// hide the progress dialog
			vRingProgressDialog.dismiss();
			
			// set the reference to null
			vRingProgressDialog = null;
		}
	}
	
	/*
	 * shows the toast message in middle of the screen. 
	 */
	public static void showToast(String pMsg, boolean pLongDuration) {
		
		if(pLongDuration) {
			
			// make the toast
			vToastMsg = Toast.makeText(vAppContext, pMsg, Toast.LENGTH_LONG);
		}
		else {
			
			// make the toast
			vToastMsg = Toast.makeText(vAppContext, pMsg, Toast.LENGTH_SHORT);
		}
		
		// set it the to the center of the screen
		vToastMsg.setGravity(Gravity.BOTTOM, 0, 100);
		
		// show the toast
		vToastMsg.show();
	}
	public static boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager
				= (ConnectivityManager) vAppContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
}
