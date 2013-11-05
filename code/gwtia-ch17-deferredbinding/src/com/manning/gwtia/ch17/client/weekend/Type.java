package com.manning.gwtia.ch17.client.weekend;

public class Type{
	String val = "Unset val";
	String getVal(){return val;}
	Type(String val){
		this.val = val;
	}
	Type(){
		this.val="Unset val";
	}
}
