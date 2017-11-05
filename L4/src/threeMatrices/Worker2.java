package threeMatrices;

public class Worker2 implements Runnable {

	private int index, M;
	private Matrix A, B, C;

	Worker2(int index, int M, Matrix A, Matrix B, Matrix C) {
		this.index = index;
		this.M = M;
		this.A = A;
		this.B = B;
		this.C = C;
	}

	public void run() {
		for (int j = 0; j < M; j++) { // bColumn
			for (int k = 0; k < M; k++) { // aColumn
				C.get()[index][j] += A.get()[index][k] * B.get()[k][j];
			}
		}
	}

}
