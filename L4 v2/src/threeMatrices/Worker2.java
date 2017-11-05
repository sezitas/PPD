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
		try {
//			System.out.println(Thread.currentThread().getName() + "::A::gettingLock A[" + index + "]");
			A.locks.get(index).lock();
//			System.out.println(Thread.currentThread().getName() + "::A::gotLock A[" + index + "]");

//			System.out.println(Thread.currentThread().getName() + "::A::awaitingSignal A[" + index + "]");
			A.ready.get(index).await();
//			System.out.println(Thread.currentThread().getName() + "::A::gotSignal A[" + index + "]");

//			System.out.println(Thread.currentThread().getName() + "::A::releasingLock A[" + index + "]");
			A.locks.get(index).unlock();
//			System.out.println(Thread.currentThread().getName() + "::A::releasedLock A[" + index + "]");

//			System.out.println(Thread.currentThread().getName() + ":: Worker2:: " + index + " Start");
			for (int j = 0; j < M; j++) { // bColumn
				for (int k = 0; k < M; k++) { // aColumn
					C.get()[index][j] += A.get()[index][k] * B.get()[k][j];
				}
			}
			// System.out.println(Thread.currentThread().getName() + ":: Worker2:: " + index + " Done");

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			A.locks.get(index).unlock();
		}

	}

}
