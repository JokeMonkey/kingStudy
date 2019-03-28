package com.jcy.king;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test001 {
	public static void main(String[] args) {
	    SimpleDateFormat sfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(1548604800000L);
		String dateStr = sfDate.format(date);
		System.out.println(dateStr);
	}
}
