class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int[][] diff = getDiffArray(board, skill);
        
        operate(diff);
        
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] + diff[r][c] > 0) answer++;
            }
        }
        
        return answer;
    }
    
    private int[][] getDiffArray(int[][] board, int[][] skill) {
        int[][] diff = new int[board.length + 1][board[0].length + 1];
        for (int[] arr : skill) {
            int type = arr[0];
            int r1 = arr[1];
            int c1 = arr[2];
            int r2 = arr[3];
            int c2 = arr[4];
            int degree = arr[5] * (type == 1 ? -1 : 1);
            
            diff[r1][c1] += degree;
            diff[r1][c2 + 1] -= degree;
            diff[r2 + 1][c1] -= degree;
            diff[r2 + 1][c2 + 1] += degree;
        }
        
        return diff;
    }
    
    private void operate(int[][] diff) {
        for (int r = 0; r < diff.length; r++) {
            for (int c = 1; c < diff[r].length; c++) {
                diff[r][c] += diff[r][c - 1];
            }
        }
        
        for (int c = 0; c < diff[0].length; c++) {
            for (int r = 1; r < diff.length; r++) {
                diff[r][c] += diff[r - 1][c];
            }
        }
    }
}