class Solution {
    public void wiggleSort(int[] nums) {
        if(nums == null || nums.length == 0)
            return ;
        Arrays.sort(nums);
        int mid_number = nums[(nums.length-1)/2];
        int mid  = (nums.length-1)/2;
        // odd = top, even = bottom
        for(int i  = 1, j = nums.length-1-((nums.length-1)%2 == 1?1:0); i <= mid;i+=2,j-=2)
        {
            swap(nums,i,j);
        }
        // put the bottom midians to the front to avoid top=bottom=median
        for(int i = 0,j= 0 ; i < nums.length ; i+=2)
        {
            if(nums[i] == mid_number)
            {
                swap(nums,j,i);
                j+=2;
            }
        }
        // put the top median to the back,
        for(int i = 1,j= nums.length-1-((nums.length-1)%2 == 1?0:1) ; i < j ; i+=2)
        {
            if(nums[i] == mid_number)
            {
                swap(nums,j,i);
                j-=2;
            }
        }
    }
    private void swap(int[] arr, int i , int j)
    {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
