class Solution {
    /*
    use KMP algorithm to get overlay array
    then get the duplicate components
    */
    public boolean repeatedSubstringPattern(String s) 
    {   
        int[] overlay_array = kmp(s);
        int length = s.length();
        int overlay= overlay_array[s.length()-1] + 1;   //length of prefix which is [length - copy.length]
        if(overlay > 0 && length%(length - overlay) == 0)
            return true;
        else
            return false;

    }
    
    //KMP string matching algorithm O(n)time complexity
    //return the proper prefix and suffix overlay array of pattern string s
    public static int[] kmp(String s)
    {
        int[] overlay = new int[s.length()];    //overlay[i] record s[i] can overlay the prefix of string s[0, overlay[i]]
        overlay[0] = -1;                        //overlay[i] = -1 means s[i] doesn't have any overlay
        for(int i = 1 ; i < s.length() ; i++)
        {
            int index = overlay[i-1];
            while(index >= 0 && (s.charAt(index+1) != s.charAt(i)) )    //find the longest proper prefix index
                index = overlay[index];         //prevent the situation that the long prefix doesn't overlay 
                                                //but there is a smaller one in the long prefix that can overlay suffix
            if(s.charAt(index+1) == s.charAt(i))
                overlay[i] = index+1;
            else
                overlay[i] = -1;
        }
        return overlay;
    }
    
    public static boolean match(String str , String pattern)
    {
        if(pattern.length() ==0)
            return str.length() == 0;
        
        int index_str = 0 , index_pattern = 0;
        int[] overlay = kmp(pattern);       //kmp algorithm get the overlay array
        while(index_str < str.length() && index_pattern < pattern.length())
        {
            if(str.charAt(index_str) == pattern.charAt(index_pattern))
            {
                index_pattern++;
                index_str++;
            }
            else    //mismatch
            {
                if(index_pattern == 0)  //now we are at the beginning of pattern
                {
                    index_str++;
                }
                else
                {
                    index_pattern = overlay[index_pattern-1]+1; //use the overlay, we don't need to go back to the head
                }                                               //instead, we go back to the overlay
            }
        }
        if(index_pattern == pattern.length())
            return true;
        else
            return false;
    }
}