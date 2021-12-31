package challenges;

public class ValidPalindromeChallenge {
    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        char[] c = s.toLowerCase().toCharArray();
        int i = 0;
        int j = c.length - 1;

        boolean isPalindrome = true;
        while (i < j) {
            if (!Character.isLetterOrDigit(c[i])) {
                i++;
            } else if (!Character.isLetterOrDigit(c[j])) {
                j--;
            } else if (c[i] != c[j]) {
                isPalindrome = false;
                break;
            } else {
                i++;
                j--;
            }


        }

        System.out.println("Is palindrome: " + isPalindrome);

    }
}
