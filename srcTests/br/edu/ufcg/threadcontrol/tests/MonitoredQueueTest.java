package br.edu.ufcg.threadcontrol.tests;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

import org.junit.Test;

import junit.framework.TestCase;
import junit.framework.TestResult;
import br.edu.ufcg.threadcontrol.MonitoredQueue;
import br.edu.ufcg.threadcontrol.MonitoredQueue.Operation;
import br.edu.ufcg.threadcontrol.ThreadConfiguration;
import br.edu.ufcg.threadcontrol.ThreadControl;
import br.edu.ufcg.threadcontrol.ThreadState;
import br.edu.ufcg.threadcontrol.tests.auxiliarythreads.MonitorableThread;
import junit.framework.TestCase;



public class MonitoredQueueTest extends TestCase {//implements UncaughtExceptionHandler {
	private List<Throwable> failures;
	
	
	/*@Override
	public void uncaughtException(Thread arg0, Throwable arg1) {
		System.out.println("A failure!!! " + arg1);
		this.failures.add(arg1);
		
	}*/
	
	public void testMonitoredQueueTest(){
		BlockingQueue <String> fila =  new LinkedBlockingQueue<String>();
		MonitoredQueue mQ = new MonitoredQueue(fila);
		Thread t1 = new Thread();
		Thread t2 = new Thread();
		Thread t3 = new Thread();
		Thread t4 = new Thread();
		Thread t5 = new Thread();
	
				 
		 mQ.addThreadBlockingQueue(t1,Operation.put );
		 mQ.addThreadBlockingQueue(t2,Operation.put );
		 mQ.addThreadBlockingQueue(t3,Operation.take);
		 mQ.addThreadBlockingQueue(t4,Operation.take);
		 mQ.addThreadBlockingQueue(t5,Operation.take);
		 
		 
		 assertTrue(mQ.getNumberOfMonitoringThreads(Operation.put)==2);
		 assertTrue(mQ.getNumberOfMonitoringThreads(Operation.take)==3);
		 
		 mQ.removeThreadBlockingQueue(t1,Operation.put);
		 mQ.removeThreadBlockingQueue(t3,Operation.take);
		 
		 assertTrue(mQ.getNumberOfMonitoringThreads(Operation.put)==1);
		 assertTrue(mQ.getNumberOfMonitoringThreads(Operation.take)==2);
		 
		 assertEquals(mQ.getThreadStateForQueueSize(t1),ThreadState.UNKNOWN);
		 assertEquals(mQ.getThreadStateForQueueSize(t4),ThreadState.WAITING);
		 assertEquals(mQ.getThreadStateForQueueSize(t5),ThreadState.WAITING);
		 
		 assertTrue(mQ.getMonitoringThreads(Operation.put).contains(t2));
		 assertTrue(mQ.getMonitoringThreads(Operation.take).contains(t4));
		 assertTrue(mQ.getMonitoringThreads(Operation.take).contains(t5));
		 
		 
		
		//assertNotNull(mQ.getNumberOfMonitoringThreads(oper.take));
		
	}
	
	
	
	
	
}
