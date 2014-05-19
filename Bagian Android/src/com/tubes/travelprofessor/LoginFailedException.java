package com.tubes.travelprofessor;

public class LoginFailedException extends Exception {
	@Override
	public String getMessage(){
		return "Login telah gagal.";
	}
}
