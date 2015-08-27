public class TwoDProject
{
    int[][] nums;
    public TwoDProject(int rows, int cols)
    {
        nums = new int[rows][cols];
        this.fillArray();
        System.out.println(this);
        this.rotate();
        System.out.println(this);
        
    }
    
    private void fillArray(){
      int value = 1;
      for(int row = 0; row < nums.length; row++)
        for(int col = 0; col < nums[row].length; col++){
           nums[row][col] = value++;
        }
    }
    
    public void rotate(){
        int[][] rotated = new int[nums.length][nums.length];    
        for (int row=0; row<nums.length; row++) {
            for (int col=0; col<nums[row].length; col++) {
                int length = nums[row].length;
                rotated[row][col] = nums[length - 1 - col][row];
            }
        }
        nums = rotated;
    }
    
    public String toString(){
        String temp = "";
        for (int row=0; row<nums.length; row++) {
            for (int col=0; col<nums[row].length; col++) {
                if(nums[row][col] < 10)
                    temp += " ";
                
                 temp += nums[row][col] + " ";

                }
            temp += "\r\n";
        }
        return temp;
    }

}
