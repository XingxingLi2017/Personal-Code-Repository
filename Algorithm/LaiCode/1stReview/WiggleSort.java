/*
    1. get median of the arr by quick select
    2. swap arr[top] and arr[bottom] which top<=mid, bottom>mid
            top=odd, bottom=even
    3. put arr[bottom] == mid_number to the bottoms in the front part
    4. put arr[top] == mid_number to the tops in the back part

*/
class Solution {
    public void wiggleSort(int[] nums) {
        if(nums==null || nums.length<2)
            return;
        Arrays.sort(nums);
        int mid = (nums.length-1)/2;
        int mid_number=nums[mid];
        //  nums.length-1-((nums.length-1)%2 == 0?0:1) => ensure the current position is top(odd)
        for(int i = 1 , j=nums.length-1-((nums.length-1)%2 == 0?0:1); i <=mid ; i+=2,j-=2)
            swap(nums,i,j);

        // fast pointer is i, slow pointer is j
        // to avoid duplicate swap, i and j should move in the same direction
        // e.g. i=2, j=4, swap(2,4), then , i=4, j=2, swap(4,2)
        for(int i = 0, j = 0; i< nums.length ; i+=2)
            if(nums[i] == mid_number){
                swap(nums,i,j);
                j+=2;
            }
        for(int i =nums.length-1-((nums.length-1)%2 == 0?1:0),j=i ; i>=0 ; i-=2)
            if(nums[i] == mid_number){
                swap(nums,i,j);
                j-=2;
            }
    }

    private void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
