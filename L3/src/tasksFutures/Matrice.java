package tasksFutures;

import java.util.Random;

public class Matrice {
	int M;
	Integer[][] A;

	Matrice(int M) {
		this.M = M;
		A = new Integer[M][M];
	}

	public void generateMatrice() {
		Random rn = new Random();
		for(int i=0; i<M; i++) {
			for(int j=0; j<M; j++) {
				A[i][j]=rn.nextInt(10);
			}
		}
	}

	public void generateZeroMatrice() {
		for(int i=0; i<M; i++) {
			for(int j=0; j<M; j++) {
				A[i][j]=0;
			}
		}
	}

	public Integer[][] get() {
		return A;
	}

	public void printMatrice(){
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.println();
		}
	}
}
