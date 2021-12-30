package challenges;

public class IntersectionOfTwoLinkedListsChallenge {
    public static void main(String[] args) {
        ListNode headA = new ListNode(4);

        ListNode one = new ListNode(1);
        ListNode eight = new ListNode(8);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);

        headA.next = one;
        one.next = eight;
        eight.next = four;
        four.next = five;


        ListNode headB= new ListNode(5);
        ListNode six = new ListNode(6);
        ListNode one_1 = new ListNode(1);
        headB.next = six;
        six.next = one_1;
        one_1.next = eight;


        ListNode tempA = headA;
        ListNode tempB = headB;
        int lenA = 0;
        int lenB = 0;

        while(tempA != null) {
            lenA++;
            tempA = tempA.next;
        }

        while(tempB != null) {
            lenB++;
            tempB = tempB.next;
        }

        ListNode largeList = null;
        ListNode smallList = null;
        int diff = 0;
        if(lenA > lenB) {
            largeList = headA;
            smallList = headB;
            diff = lenA-lenB;
        } else {
            largeList = headB;
            smallList = headA;
            diff = lenB-lenA;
        }


        for(int i = 0; i < diff; i++) {
            largeList = largeList.next;
        }

        while(largeList != null && smallList != null) {
            if(largeList == smallList) {
                System.out.println(largeList.val);
                break;
            }else {
                largeList = largeList.next;
                smallList = smallList.next;
            }

        }
    }
}
