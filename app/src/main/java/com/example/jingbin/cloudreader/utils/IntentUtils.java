package com.example.jingbin.cloudreader.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;


import com.example.jingbin.cloudreader.R;

import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author: Biao
 * @创建时间: 2015-8-21 下午5:10:07
 * @描述信息: 意图工具类
 * @svn提交者: $Author$
 * @提交时间: $Date: 2015-11-21 14:56:11 +0800 (Sat, 21 Nov 2015) $
 * @当前版本: $Rev: 39 $
 */
@SuppressWarnings("deprecation")
public class IntentUtils {
	public static File cameraFile;

	/**
	 * 切换界面并带有参数
	 * 
	 * @param activity
	 * @param cls
	 * @param name
	 */
	public static void changeActivity(Activity activity, Class<?> cls,
									  BasicNameValuePair... name) {
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		for (int i = 0; i < name.length; i++) {
			intent.putExtra(name[i].getName(), name[i].getValue());
		}
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_right_in,
				R.anim.activity_left_out);
	}

	/**
	 * 切换界面并带有参数
	 * 
	 * @param activity
	 * @param cls
	 * @param bundle
	 */
	public static void changeActivity(Activity activity, Class<?> cls,
									  Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		intent.putExtras(bundle);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_right_in,
				R.anim.activity_left_out);
	}

	/**
	 * 从底部切换界面并带有参数
	 * 
	 * @param activity
	 * @param cls
	 * @param bundle
	 */
	public static void changeActivityFromBottom(Activity activity,
												Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		intent.putExtras(bundle);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_bottom_in,
				R.anim.activity_fade_out);
	}

	/**
	 * 淡入切换界面并带有参数
	 * 
	 * @param activity
	 * @param cls
	 * @param bundle
	 */
	public static void changeActivityByFade(Activity activity, Class<?> cls,
											Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		intent.putExtras(bundle);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_fade_in,
				R.anim.activity_bottom_out);
	}

	/**
	 * 切换界面并通过集合来传递参数
	 * 
	 * @param activity
	 * @param cls
	 * @param
	 */
	public static void changeActivity(Activity activity, Class<?> cls,
									  List<BasicNameValuePair> values) {
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		for (int i = 0; i < values.size(); i++) {
			intent.putExtra(values.get(i).getName(), values.get(i).getValue());
		}
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_right_in,
				R.anim.activity_left_out);
	}

	/**
	 * 切换界面
	 * 
	 * @param activity
	 * @param cls
	 */
	public static void changeActivity(Activity activity, Class<?> cls) {
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_right_in,
				R.anim.activity_left_out);
	}

	/**
	 * 淡入切换
	 * 
	 * @param activity
	 * @param cls
	 */
	public static void changeActivityByFade(Activity activity, Class<?> cls) {
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_fade_in,
				R.anim.activity_bottom_out);
	}

	/**
	 * 从底部切换界面
	 * 
	 * @param activity
	 * @param cls
	 */
	public static void changeActivityFromBottom(Activity activity, Class<?> cls) {
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.activity_bottom_in,
				R.anim.activity_fade_out);
	}


}
