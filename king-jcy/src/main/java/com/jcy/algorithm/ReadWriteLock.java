package com.jcy.algorithm;

public class ReadWriteLock {
	
	private int read = 0;
	private int write = 0;
	
	public synchronized void readLock() throws InterruptedException {
		while(write > 0){
			wait();
		}
		
		read++;
	}
	
	
	public synchronized void readUnLock() {
		read--;
		notifyAll();
	}
	
	
	public synchronized void writeLock() throws InterruptedException {
		
		while(read > 0 || write > 0) {
			wait();
		}
		
		write++;
	}
	
	
	public synchronized void writeUnlock() {
		write--;
		notifyAll();
	}
	
}
