package com.thread.callable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
public class CallableResult {
	String threadName;
	
	String res1;
	String res2;
	String res3;
}
