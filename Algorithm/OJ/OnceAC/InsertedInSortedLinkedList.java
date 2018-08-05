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
  public ListNode insert(ListNode head, int value) {
    if(head == null){
      return new ListNode(value);
    }
    // new a head node
    ListNode newHead = new ListNode(-1);
    newHead.next = head;
    ListNode curr = newHead;
    // node waiting to be inserted
    ListNode temp = new ListNode(value);
    // use curr.next to end loop
    while(curr.next != null){
      if(curr.value <= value && curr.next.value >= value){
        temp.next = curr.next;
        curr.next = temp;
        return newHead.next;
      }
      curr = curr.next;
    }
    // attach to the end
    if(curr.value <= temp.value)
      curr.next = temp;
    return newHead.next;
  }
}
