package com.dev.bbs.exceptions;

public class ManualException extends RuntimeException{

	public ManualException(String string)
	{
		System.err.println(string);
	}
}
