package com.thread.callable;

public class CallableRequest {
	private String field1;
	private String field2;
	private String field3;
	
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	public static CallableRequest of(String f1, String f2, String f3) {
		CallableRequest req = new CallableRequest();
		req.setField1(f1);
		req.setField2(f2);
		req.setField3(f3);
		
		return req;
	}
}
