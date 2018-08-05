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
  public ListNode reorder(ListNode head) {
    if(head == null || head.next == null)
      return head;
    ListNode newHead = new ListNode(-1);
    newHead.next = head;
    ListNode list2 = new ListNode(-1);
    ListNode temp=null, curr=newHead;
    int len = 0;
    // count length
    while(curr.next != null){
      curr = curr.next;
      len++;
    }
    //split mid and insert reversly
    int mid = len/2;
    int count=0;
    curr = newHead;
    while(curr.next != null){
      count++;
      if(count > mid){
        // split out the node
        temp = curr.next;
        curr.next = temp.next;
        //insert reversly
        temp.next = list2.next;
        list2.next = temp;
      }else
        curr = curr.next;
    }

    // merge two lists
    ListNode res = null, tail=null;
    boolean flag = true;
    while(list2.next != null && newHead.next != null){
      // cut out node
      if(flag){
        temp = newHead.next;
        newHead.next = temp.next;
        temp.next = null;
        flag=false;
      }else{
        temp=list2.next;
        list2.next = temp.next;
        temp.next = null;
        flag = true;
      }
      // insert
      if(res == null){
        res = temp;
        tail = temp;
      }else{
        tail.next = temp;
        tail = tail.next;
      }
    }
    if(newHead.next == null)
      tail.next = list2.next;
    if(list2.next == null)
      tail.next = newHead.next;
    return res;
  }
}
