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
  public boolean hasCycle(ListNode head) {
    if(head==null || head.next == null)
      return false;
    // using hash set to store visited node
    HashSet<ListNode> hash = new HashSet<>();
    ListNode curr = head;
    while(curr != null){
      // use hash to store the reference
      // if reference is same, hash set will treat it as same items
      if(!hash.contains(curr)){
        hash.add(curr);
        curr = curr.next;
      }else{
        return true;
      }
    }
    return false;
  }
}
