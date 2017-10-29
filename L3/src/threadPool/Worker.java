package threadPool;

public class Worker implements Runnable{

	int start, end, M;
	Matrice A,B,C;

	Worker (int start, int end, int M, Matrice A, Matrice B, Matrice C) {
		this.start = start;
		this.end = end;
		this.M = M;
		this.A = A;
		this.B = B;
		this.C = C;
	}

	public void run() {
//		System.out.println("Start=" + start + " / end=" + end);

		for (int i = start; i <= end; i++) { // aRow
			for (int j = 0; j < M; j++) { // bColumn
				for (int k = 0; k < M; k++) { // aColumn
					C.get()[i][j] += A.get()[i][k] * B.get()[k][j];
				}
			}
		}
	}

}
