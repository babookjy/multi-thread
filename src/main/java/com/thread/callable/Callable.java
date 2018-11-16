package com.thread.callable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Callable {
	
	private Lock lock = new ReentrantLock();
	private final static int THREAD_COUNT = 3;
	private ExecutorService executor;

	public static void main(String[] args) throws Exception {
		Callable callable = new Callable();
		List<CallableResult> resultList = callable.defaultExecute();
		
		resultList.stream().forEachOrdered(r -> {
			try {
				log.info(r.getThreadName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	private List<CallableResult> defaultExecute() throws Exception {
		final CallableRequest data = CallableRequest.of("test1", "test2", "test3");
		
		final List<CallableRequest> reqList =
				Arrays.asList(data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data
						, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data
						, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data
						, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data, data);
		
		final List<CallableResult> resultList = new ArrayList<CallableResult>();
		
		this.createThreadPool();
		
		for (CallableRequest r : reqList) {
			Future<CallableResult> future = executor.submit(new CallableWorker(r));
			resultList.add(future.get());
		}
		
		this.shutdownThreadPool();
		
		return resultList;
	}
	
	private void createThreadPool() {
		lock.lock();
		
		if (executor == null || executor.isShutdown()) {
			executor = Executors.newFixedThreadPool(THREAD_COUNT);
		}
		
		lock.unlock();
	}
	
	private void shutdownThreadPool() throws Exception {
		executor.shutdown();
		
		int cnt = 0;
		while (!executor.isTerminated()) {
			Thread.sleep(1000L);
			
			if (++cnt == 3) {
				executor.shutdownNow();
				break;
			}
		}
	}
}
