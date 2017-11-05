package threeMatrices;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Matrix {
	int M;
	Integer[][] A;
	List<Lock> locks = null;
	List<Condition> ready = null;

	Matrix(int M) {
		this.M = M;
		locks = new ArrayList<>(M);
		ready = new ArrayList<>(M);
		for (int i = 0; i < M; i++) {
			locks.add(i, new ReentrantLock());
			ready.add(i, locks.get(i).newCondition());
		}
		A = new Integer[M][M];
	}

	public void generateMatrice() {
		Random rn = new Random();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				A[i][j] = rn.nextInt(10);
			}
		}
	}

	public void generateZeroMatrice() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				A[i][j] = 0;
			}
		}
	}

	public Integer[][] get() {
		return A;
	}

	public void printMatrice() {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.println();
		}
	}
}
