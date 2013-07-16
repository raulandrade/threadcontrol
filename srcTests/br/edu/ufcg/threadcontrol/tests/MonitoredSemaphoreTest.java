package br.edu.ufcg.threadcontrol.tests;


import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

import org.junit.Test;

import junit.framework.TestCase;
import junit.framework.TestResult;
import br.edu.ufcg.threadcontrol.MonitoredQueue;
import br.edu.ufcg.threadcontrol.MonitoredQueue.Operation;
import br.edu.ufcg.threadcontrol.MonitoredSemaphore;
import br.edu.ufcg.threadcontrol.ThreadConfiguration;
import br.edu.ufcg.threadcontrol.ThreadControl;
import br.edu.ufcg.threadcontrol.ThreadState;
import br.edu.ufcg.threadcontrol.tests.auxiliarythreads.MonitorableThread;
import junit.framework.TestCase;



public class MonitoredSemaphoreTest extends TestCase  {
	private List<Throwable> failures;
	

	public void testmonitoredSemaphore(){
		Semaphore sem = new Semaphore(0);
		MonitoredSemaphore mS = new MonitoredSemaphore(sem);
		
		Thread t1 = new Thread();
		Thread t2 = new Thread();
		Thread t3 = new Thread();
		Thread t4 = new Thread();
		Thread t5 = new Thread();
		
		assertTrue(mS.addThreadWithPermits(t1, 01));
		assertTrue(mS.addThreadWithPermits(t2, 02));
		assertTrue(mS.addThreadWithPermits(t3, 03));
		assertTrue(mS.addThreadWithPermits(t4, 04));
		assertTrue(mS.addThreadWithPermits(t5, 05));
		
		assertTrue(mS.getNumberOfMonitoringThreads()==5);
		
		mS.removeMonitoringThread(t2);
		mS.removeMonitoringThread(t3);
		
		assertTrue(mS.getNumberOfMonitoringThreads()==3);
		
		assertEquals(ThreadState.WAITING, mS.getThreadStateForAvailablePermits(t1));
		assertEquals(ThreadState.WAITING,mS.getThreadStateForAvailablePermits(t4));
		assertEquals(ThreadState.WAITING,mS.getThreadStateForAvailablePermits(t5));
		
		assertNotNull(mS.getMonitoringThreads());
		
		assertTrue(mS.getMonitoringThreads().contains(t1));
		assertTrue(mS.getMonitoringThreads().contains(t4));
		assertTrue(mS.getMonitoringThreads().contains(t5));
		
		mS.addAvailablePermits(10);
		assertEquals(ThreadState.POSSIBLY_NOTIFIED, mS.getThreadStateForAvailablePermits(t1));
		assertEquals(ThreadState.POSSIBLY_NOTIFIED,mS.getThreadStateForAvailablePermits(t4));
		assertEquals(ThreadState.POSSIBLY_NOTIFIED,mS.getThreadStateForAvailablePermits(t5));
		
		//assertTrue(mS.removeAvailablePermits(2)== sem.availablePermits()-2);
		
		//removeAvailablePermits, addAvailablePermits e resetAvailablePermits;
				 
		
		
	}
	
	
}
