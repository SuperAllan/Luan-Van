package vn.com.luanvan.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class tesst {

	public static void main(String[] args) {
		DecimalFormat formatter = new DecimalFormat("#,###.#####");
		int a = (int) (21769 * 1.4 * 160.34217 * 20);
		
		System.out.println(formatter.format(a));
	}

}
