/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class Solution {
  public ListNode middleNode(ListNode head) {
    if(head == null || head.next == null)
      return head;
    int len = 0;
    ListNode current = head;
    while(current != null){
      current = current.next;
      len++;
    }
    current = head;
    int mid = ((len%2==0)?len/2:len/2+1);
    int count=0;
    while(current != null){
      // go first and check
      count++;
      if(count == mid)
        return current;
      // then do the real action
      current=current.next;
    }
    return head;
  }
}
