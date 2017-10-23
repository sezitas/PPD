package main;

//import java.util.Scanner;

public class L1 {

	public static void main(String[] args) {

		runSimulation();
		
//		int M, K;
//		Scanner s = new Scanner(System.in);
//		M = Integer.parseInt(s.nextLine());
//		K = Integer.parseInt(s.nextLine());
//		runTest(M, K);
//		s.close();
		
	}
	
	private static void runSimulation () {
		int M = 200; 
		int K = 1;
		runTest(M, K);
		K = 2;
		runTest(M, K);
		K = 4;
		runTest(M, K);
		K = 8;
		runTest(M, K);
		K = 10;
		runTest(M, K);
		K = 20;
		runTest(M, K);
		K = 40;
		runTest(M, K);
	}
	
	private static void runTest (int M, int K) {
		
		Matrice A = new Matrice(M);
		Matrice B = new Matrice(M);
		Matrice C = new Matrice(M);
		
		A.generateMatrice();
		B.generateMatrice();
		C.generateZeroMatrice();
		
		int div=M/K;
		int mod=M%K;
				
		Thread t[] = new Thread[K];
		int iterator = 0;
		
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
			Worker w = new Worker(start, end, M, A, B, C);
			t[i] = new Thread(w);
			t[i].run();
		}
		
		for (int i=0; i<K; i++) {
			try {
				t[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		
		long elaspedTime = endTime - startTime;
		System.out.println(M + " size with \t" + K + " threads: " + elaspedTime);
	}

}
