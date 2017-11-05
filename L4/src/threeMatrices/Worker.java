package threeMatrices;

import java.util.concurrent.ExecutorService;

public class Worker implements Runnable {

	private ExecutorService exec;
	private int start, end, M;
	private Matrix A, B, R1, D, R2;

	Worker(ExecutorService exec, int start, int end, int M, Matrix A, Matrix B, Matrix R1, Matrix D, Matrix R2) {
		this.exec = exec;
		this.start = start;
		this.end = end;
		this.M = M;
		this.A = A;
		this.B = B;
		this.R1 = R1;
		this.D = D;
	}

	public void run() {
		for (int i = start; i <= end; i++) { // aRow
			for (int j = 0; j < M; j++) { // bColumn
				for (int k = 0; k < M; k++) { // aColumn
					R1.get()[i][j] += A.get()[i][k] * B.get()[k][j];
				}
			}
			Worker2 w2 = new Worker2(i, M, R1, D, R2);
			exec.submit(w2);
		}
	}

}
