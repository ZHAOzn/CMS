package com.qdu.ClassManageSys;

import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		//定义系统输入
		Scanner scanner = new Scanner(System.in);
		//第一个字符串
		String s1 = scanner.nextLine();
		s1 = s1.replaceFirst(" ", "");	
		System.out.println(s1);
		int a = s1.lastIndexOf(" ");
		String s2 = s1.substring(0,a) + s1.substring(a+1);
		System.out.println(s2);
		scanner.close();
	}

}
