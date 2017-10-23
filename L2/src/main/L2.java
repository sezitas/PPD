package main;

import inventory.IFactory;
import inventory.Inventory;
import inventory.InventoryChecker;
import inventory.ManualFactory;


public class L2 {
		
	public static void main(String[] args) {
		runSimulation();
	}
		
	private static void runSimulation () {
		int M = 60000;
		Inventory inventory;
		inventory = createInventory(M);
		
		int K = 1;
		runTest(inventory, M, K);
		K = 2;
		runTest(inventory, M, K);
		K = 4;
		runTest(inventory, M, K);
		K = 8;
		runTest(inventory, M, K);
		K = 10;
		runTest(inventory, M, K);
		K = 20;
		runTest(inventory, M, K);
		K = 40;
		runTest(inventory, M, K);
	}
	
	private static void runTest (Inventory inventory, int M, int K) {

		int div=M/K;
		int mod=M%K;

		Thread workerThreads[] = new Thread[K];
		int iterator = 0;
		
		InventoryChecker iv = new InventoryChecker(inventory);
		Thread checkerThread = new Thread(iv);
		checkerThread.start();
		System.out.print("[ ");
		long startTime = System.currentTimeMillis();
		for (int i=0; i<K; i++) {
			int start = iterator;
			iterator += div;

			if(mod <= 0)
				iterator--;
			else
				mod--;

			int end = iterator;
			iterator++;
			
			Worker w = new Worker(inventory, start, end);
			workerThreads[i] = new Thread(w);
			workerThreads[i].start();
		}

		for (int i=0; i<K; i++) {
			try {
				workerThreads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.print("] ");
		long elaspedTime = endTime - startTime;
		System.out.println(M + " size with \t" + K + " threads in : " + elaspedTime + " ms");
		checkerThread.interrupt();
		inventory.runCheck();
		inventory.reset();
	}

	private static Inventory createInventory(int M) {
		IFactory fact = new ManualFactory();
		Inventory inventory = new Inventory();
		fact.generateProducts(inventory);
		fact.generateBills(inventory, M);
//		System.out.println(inventory);
		return inventory;
	}
	
}
