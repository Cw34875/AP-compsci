public class TwoDMatrix1{
    String[][] matrix;
    
    public TwoDMatrix1(int square){
        matrix = new String[square][square];
        System.out.println(this);
        this.fillMatrixXs();
        System.out.println(this);
        //this.fillLowerDiagonalOs();
        this.diagOnes();
        System.out.println(this);
    }
    
    public void fillMatrixXs(){
     for (int row=0; row<matrix.length; row++) {
            for (int col=0; col<matrix[row].length; col++) {
                matrix[row][col] = "X";
            }
        }
    }
    
    public void fillLowerDiagonalOs(){
        for (int row=0; row<matrix.length; row++) {
            for (int col=0; col<matrix[row].length; col++) {
                 if( row > col ) //row - col > 0
                    matrix[row][col] = "O";
            }
        }
    }
    
     public void diagOnes(){
         for (int row=0; row<matrix.length; row++) {
             for (int col=0; col<matrix[row].length; col++) {
                 //notice that row and col 
                 //are related to the matrix length
                 if(row + col == matrix.length - 1)
                    matrix[row][col] = "1";
             }
         }
     }
//     
//     public int[][] rotate(){
//        int[][] temp = new int[matrix.length][matrix.length]; 
//        int[][] nums = new int[matrix.length][matrix.length];
//        for (int row=0; row<matrix.length; row++) {
//             for (int col=0; col<matrix[row].length; col++) {
//                 nums[row][col] = row + col; 
//             }
//        }
//        
//        for (int row=0; row<nums.length; row++) {
//             for (int col=0; col<nums[row].length; col++) {
//                 temp[row][col] = nums[ ____][ _____]; 
//             }
//        }
//        return temp;
//     }
     
    public boolean onSameDiag(int row1, int col1, int row2, int col2){
//            if(row1+ col1 == row2 + col2)
//                 return true;
//            else if(row1 - col1 == row2 - col2)
//                 return true;
//            
//            double slope = (row1 - row2) / (col1 - col2);
//            if(slope == 1 || slope == -1)
//                 return true;
                
          return Math.abs(col1 - col2)  ==  Math.abs(row1 - row2);
    }
    
    public String toString(){
        String temp = "";
        for (int row=0; row<matrix.length; row++) {
            for (int col=0; col<matrix[row].length; col++) {
                temp += matrix[row][col] + " ";
            }
            temp += "\r\n";
        }
        return temp;
    }
}

