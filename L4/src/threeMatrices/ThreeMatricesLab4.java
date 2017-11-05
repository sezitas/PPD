package threeMatrices;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//import java.util.Scanner;

public class ThreeMatricesLab4 {

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

		Matrix A = new Matrix(M);
		Matrix B = new Matrix(M);
		Matrix D = new Matrix(M);
		Matrix R1 = new Matrix(M);
		Matrix R2 = new Matrix(M);
		
		A.generateMatrice();
		B.generateMatrice();
		D.generateMatrice();
		R1.generateMatrice();
		R2.generateMatrice();

		runTest(M, K, A, B, R1, D, R2);
		s.close();
	}

	@SuppressWarnings("unused")
	private static void runSimulation() {
		System.out.print("Initializing matrices... ");
		int M = 700;
		Matrix A = new Matrix(M);
		Matrix B = new Matrix(M);
		Matrix D = new Matrix(M);
		Matrix R1 = new Matrix(M);
		Matrix R2 = new Matrix(M);

		A.generateMatrice();
		B.generateMatrice();
		D.generateMatrice();
		R1.generateMatrice();
		R2.generateMatrice();
		
		System.out.println("Done.\nStarting Simulation:");

		int K = 1;
		runTest(M, K, A, B, R1, D, R2);
		K = 2;
		runTest(M, K, A, B, R1, D, R2);
		K = 4;
		runTest(M, K, A, B, R1, D, R2);
		K = 8;
		runTest(M, K, A, B, R1, D, R2);
		K = 10;
		runTest(M, K, A, B, R1, D, R2);
		K = 20;
		runTest(M, K, A, B, R1, D, R2);
		K = 40;
		runTest(M, K, A, B, R1, D, R2);
		System.out.println("Simulation Complete.");
	}

	private static void runTest(int M, int K, Matrix A, Matrix B, Matrix R1, Matrix D, Matrix R2) {
		int div = M / K;
		int mod = M % K;

		ExecutorService executor1 = Executors.newFixedThreadPool(K);
		ExecutorService executor2 = Executors.newFixedThreadPool(K);
		int iterator = 0;

		long startTime = System.currentTimeMillis();
		for (int i = 0; i < M; i++) {
		
		}
		for (int i = 0; i < K; i++) {
			int start = iterator;
			iterator += div;

			if (mod <= 0)
				iterator--;
			else
				mod--;

			int end = iterator;
			iterator++;
			Worker w1 = new Worker(executor2, start, end, M, A, B, R1, D, R2);
			executor1.submit(w1);
		}

		executor1.shutdown();
		try {
			executor1.awaitTermination(30, TimeUnit.SECONDS);
			executor2.shutdown();
			executor2.awaitTermination(30, TimeUnit.SECONDS);
//			System.out.println("wait... over");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();

		R1.generateZeroMatrice();

		long elaspedTime = endTime - startTime;
		System.out.println(M + " size with \t" + K + " threads: " + elaspedTime);
	}

}
