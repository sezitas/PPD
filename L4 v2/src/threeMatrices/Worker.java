package threeMatrices;

public class Worker implements Runnable {

	private int start, end, M;
	private Matrix A, B, C;

	Worker(int start, int end, int M, Matrix A, Matrix B, Matrix C) {
		this.start = start;
		this.end = end;
		this.M = M;
		this.A = A;
		this.B = B;
		this.C = C;
	}

	public void run() {
//		System.out.println(Thread.currentThread().getName() + ":: stated mainWorker start:: " + start);
		for (int i = start; i <= end; i++) { // aRow
//			System.out.println(Thread.currentThread().getName() + ":: line:: " + i);
			C.locks.get(i).lock();
//			System.out.println(Thread.currentThread().getName() + "::C::gotLock C[" + i + "]");
			for (int j = 0; j < M; j++) { // bColumn
				for (int k = 0; k < M; k++) { // aColumn
					C.get()[i][j] += A.get()[i][k] * B.get()[k][j];
				}
			}
//			System.out.println(Thread.currentThread().getName() + ":: started thread Worker2:: " + i);
			C.ready.get(i).signal();
//			System.out.println(Thread.currentThread().getName() + "::C::signaled C[" + i + "]");
//			System.out.println(Thread.currentThread().getName() + "::C::releasingLock C[" + i + "]");
			C.locks.get(i).unlock();
//			System.out.println(Thread.currentThread().getName() + "::C::releasedLock C[" + i + "]");
		}
	}

}
