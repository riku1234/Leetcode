import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpecialEquivalentString {
    private static int numSpecialEquivGroups(String[] words) {
        HashMap<List<List<Integer>>, List<String>> group = new HashMap<>();
        for (String word : words) {
            List<List<Integer>> key = key(word);
            if (!group.containsKey(key)) {
                //System.out.println("Doesn't contain key : " + key);
                group.put(key, new ArrayList<>());
            }
            group.get(key).add(word);
        }

        return group.keySet().size();
    }

    private static List<List<Integer>> key(String word) {
        List<Integer> odd = new ArrayList<>(26);
        List<Integer> even = new ArrayList<>(26);
        for (int i=0; i< 26; i++) {
            odd.add(i, 0);
            even.add(i, 0);
        }
        for (int i=0; i<word.length(); i++) {
            if (i%2 == 0) {
                int index = word.charAt(i) - 'a';
                int data = even.get(index);
                even.remove(index);
                even.add(index, data + 1);
            } else {
                int index = word.charAt(i) - 'a';
                int data = odd.get(index);
                odd.remove(index);
                odd.add(index, data + 1);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(odd); result.add(even);
        return result;
    }

    public static void main(String[] args) {
        String[] words = {"abc","acb","bac","bca","cab","cba"};
        System.out.println(numSpecialEquivGroups(words));
    }
}
