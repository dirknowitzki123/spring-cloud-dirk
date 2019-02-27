package com.dirk.base.controller.user;


import org.springframework.boot.autoconfigure.web.HttpMessageConverters;

import java.util.ArrayList;

public class EndlessLoopTest {
	public static void main(String[] args) {
		ArrayList list=new ArrayList();
		int i = 0;
		while(true){
			i++;
			list.add(new Object());
			System.out.println(i);
		}
	}
}

