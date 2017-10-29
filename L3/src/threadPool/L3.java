package threadPool;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//import java.util.Scanner;

public class L3 {

	public static void main(String[] args) {

		runSimulation();
//		runOneTest();

	}

	@SuppressWarnings("unused")
	private static void runOneTest() {
		int M, K;
		Scanner s = new Scanner(System.in);
		System.out.print("M = ");
		M = Integer.parseInt(s.nextLine());
		System.out.print("K = ");
		K = Integer.parseInt(s.nextLine());
		Matrice A = new Matrice(M);
		Matrice B = new Matrice(M);
		Matrice C = new Matrice(M);

		A.generateMatrice();
		B.generateMatrice();
		C.generateZeroMatrice();

		runTest(M, K, A, B, C);
		s.close();
	}

	@SuppressWarnings("unused")
	private static void runSimulation() {
		int M = 600;
		Matrice A = new Matrice(M);
		Matrice B = new Matrice(M);
		Matrice C = new Matrice(M);

		A.generateMatrice();
		B.generateMatrice();
		C.generateZeroMatrice();

		int K = 1;
		runTest(M, K, A, B, C);
		K = 2;
		runTest(M, K, A, B, C);
		K = 4;
		runTest(M, K, A, B, C);
		K = 8;
		runTest(M, K, A, B, C);
		K = 10;
		runTest(M, K, A, B, C);
		K = 20;
		runTest(M, K, A, B, C);
		K = 40;
		runTest(M, K, A, B, C);
	}

	private static void runTest(int M, int K, Matrice A, Matrice B, Matrice C) {
		int div = M / K;
		int mod = M % K;

		ExecutorService executor = Executors.newFixedThreadPool(K);
		int iterator = 0;

		long startTime = System.currentTimeMillis();
		for (int i = 0; i < K; i++) {
			int start = iterator;
			iterator += div;

			if (mod <= 0)
				iterator--;
			else
				mod--;

			int end = iterator;
			iterator++;
			Worker w = new Worker(start, end, M, A, B, C);
			executor.submit(w);
		}

		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();

		C.generateZeroMatrice();

		long elaspedTime = endTime - startTime;
		System.out.println(M + " size with \t" + K + " threads: " + elaspedTime);
	}

}
