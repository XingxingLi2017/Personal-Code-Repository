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
  public ListNode merge(ListNode one, ListNode two) {
    if(one == null)
      return two;
    if(two == null)
      return one;
    ListNode p1 = one, p2 = two;
    ListNode head = null, tail = null;
    ListNode temp = null;
    while(p1 != null && p2 != null){
      // cut out the node
      if(p1.value <= p2.value){
        temp = p1;
        p1 = p1.next;
      }else{
        temp = p2;
        p2 = p2.next;
      }
      // add splited-out node into new list
      temp.next = null;
      if(head == null){
        head = temp;
        tail = temp;
      }
      else{
        tail.next = temp;
        tail = tail.next;
      }
    }
    // merge the rest part
    if(p1 == null)
      tail.next = p2;
    if(p2 == null)
      tail.next = p1;
    return head;
  }
}
