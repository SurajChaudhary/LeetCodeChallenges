package challenges;

public class LongestCommonPrefixChallenge {
    /**
     * Write a function to find the longest common prefix string amongst an array of strings.
     *
     * If there is no common prefix, return an empty string "".
     *
     *
     *
     * Example 1:
     *
     * Input: strs = ["flower","flow","flight"]
     * Output: "fl"
     * Example 2:
     *
     * Input: strs = ["dog","racecar","car"]
     * Output: ""
     * Explanation: There is no common prefix among the input strings.
     *
     *
     * Constraints:
     *
     * 1 <= strs.length <= 200
     * 0 <= strs[i].length <= 200
     * strs[i] consists of only lower-case English letters.
     */
    public static void main(String[] args) {
        String[] words = {"flower","flow","flight"};
        String output= "fl";
        String result = getLongestCommonPrefix(words);
        System.out.println("Longest prefix "
                + (output.equalsIgnoreCase(result)
                ? "is found : [" + result + "]."
                : "could not be found."));
    }

    private static String getLongestCommonPrefix(String[] words) {
        if(words == null || words.length == 0) {
            return "";
        }
        if(words.length < 2) {
            return words[0];
        }
        String prefix = words[0];
        for(int i=1; i < words.length; i++) {
            while(!words[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length()-1);
            }
        }
        return prefix;
    }
}
