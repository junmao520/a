package edu.feicu.puzzle.util;

import android.util.Log;
import edu.feicui.puzzle.BuildConfig;

public class LogWrapper {
	public final static boolean DEBUG = true && BuildConfig.DEBUG;

	public static void v(String tag, String msg) {
		if (DEBUG) {
			int i=1;
			Log.v(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if (DEBUG) {
			Log.i(tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (DEBUG) {
			Log.w(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (DEBUG) {
			Log.e(tag, msg);
		}
	}
}
