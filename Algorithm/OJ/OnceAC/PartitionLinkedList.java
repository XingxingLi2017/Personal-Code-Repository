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
  public ListNode partition(ListNode head, int target) {
    if(head == null || head.next == null)
      return head;
    ListNode newHead = new ListNode(-1);
    newHead.next = head;
    ListNode res = null, resTail=null;
    ListNode temp=null, curr=newHead;
    while(curr.next != null){
      if(curr.next.value < target){
        // cut out the node
        temp = curr.next;
        curr.next = curr.next.next;
        temp.next = null;
        // insert into res list
        if(res == null){
          res = temp;
          resTail=temp;
        }else{
          resTail.next = temp;
          resTail = resTail.next;
        }
      }else
        curr = curr.next;
    }
    if(res == null)
      return head;
    else
      resTail.next = newHead.next;
    return res;
  }
}
