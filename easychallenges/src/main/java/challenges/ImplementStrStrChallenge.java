package challenges;

public class ImplementStrStrChallenge {
    /**
     * Implement strStr().
     * <p>
     * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     * <p>
     * Clarification:
     * <p>
     * What should we return when needle is an empty string? This is a great question to ask during an interview.
     * <p>
     * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: haystack = "hello", needle = "ll"
     * Output: 2
     * Example 2:
     * <p>
     * Input: haystack = "aaaaa", needle = "bba"
     * Output: -1
     * Example 3:
     * <p>
     * Input: haystack = "", needle = ""
     * Output: 0
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 0 <= haystack.length, needle.length <= 5 * 104
     * haystack and needle consist of only lower-case English characters.
     */
    public static void main(String[] args) {
        //"mississippi"
        // "issipi"
        String haystack = "mississippi";
        String needle = "issipi";
        System.out.println("Needle was found at Index: [" + strStr(haystack, needle) + "] in haystack.");
    }

    public static int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        if (haystack == null || haystack.length() == 0) return -1;
        if (haystack.length() < needle.length()) return -1;

        for (int i = 0; i < haystack.length() - needle.length(); i++) {
            int j = 0;
            for (; j < needle.length(); j++) {
                if (haystack.charAt(i + j) == needle.charAt(j)) {
                    continue;
                } else {
                    break;
                }
            }
            if (j == needle.length())
                return i;

        }
        return -1;

    }
}
