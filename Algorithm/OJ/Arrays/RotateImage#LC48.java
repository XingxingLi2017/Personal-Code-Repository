class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return ;
        int rows = matrix.length;
        int cols = matrix[0].length;

        // turn upside-down => make the diagnals correct
        for (int i = 0 ; i <= (rows - 1)/ 2 ; i++) {
            int[] temp = matrix[rows - 1 - i];
            matrix[rows - 1 - i] = matrix[i];
            matrix[i] = temp;
        }

        // flip along diagnals
        for (int i = 0 ; i < rows ; i++) {
            for (int j = i ; j < cols ; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
