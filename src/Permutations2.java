import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Permutations2 {

    static List<List<Integer>> result = new ArrayList<>();

    public static List<List<Integer>> permuteUnique(int[] nums) {
        storeUnique(Arrays.stream(nums).boxed().collect(Collectors.toList()), new ArrayList<>());
        return result;
    }

    private static void storeUnique(List<Integer> nums, List<Integer> curList) {
        if (nums.size() == 0) {
            result.add(List.copyOf(curList));
            return;
        }

        Set<Integer> uniqueInts = getUnique(nums);
        for (Integer i : uniqueInts) {
            nums.remove(i);
            curList.add(i);
            storeUnique(nums, curList);
            nums.add(i);
            curList.remove(curList.size() - 1);
        }
    }

    private static Set<Integer> getUnique(List<Integer> nums) {
        return new HashSet<>(nums);
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println(permuteUnique(nums));
    }
}
