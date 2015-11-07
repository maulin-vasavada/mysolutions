package mysolutions.dp;

public class LongestCommonSubsequence {

	public int lcs(String a, String b) {
		int maxRow = b.length()+1;
		int maxCol = a.length()+1;
		int[][] matrix = new int[maxRow][];
		initMatrix(matrix, maxRow, maxCol);
		
		int max=Integer.MIN_VALUE;
		for(int r=1;r<maxRow;r++) {
			for(int c=1;c<maxCol;c++) {
				if ( b.charAt(r-1) == a.charAt(c-1) ) {
					matrix[r][c] = 1 + matrix[r-1][c-1];
				} else {
					matrix[r][c] = Math.max(matrix[r-1][c], matrix[r][c-1]);
				}
				
				if ( matrix[r][c] > max ) {
					max = matrix[r][c];
				}
			}
		}
		
		return max;
	}
	
	private void initMatrix(int[][] matrix, int maxRow, int maxCol) {
		for(int r=0;r<maxRow;r++) {
			matrix[r] = new int[maxCol];
		}
	}
	
	static void testIterative() {
		String a= "abcdaf";
		String b= "acbcf";
		LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		System.out.println(lcs.lcs(a, b));
	}
	public static void main(String[] args) {
		testIterative();
	}
}
