package com.thread.callable;

import java.util.concurrent.Callable;

public class CallableWorker implements Callable<CallableResult> {
	
	private CallableRequest req;
	
	public CallableWorker(CallableRequest req) {
		this.req = req;
	}
	
	public CallableResult call() {
		final CallableResult result = new CallableResult();
		result.setThreadName(Thread.currentThread().getName());
		
		result.setRes1(req.getField1());
		result.setRes2(req.getField2());
		result.setRes3(req.getField3());
		
		return result;
	}
}
