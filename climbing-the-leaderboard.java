import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'climbingLeaderboard' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY ranked
     *  2. INTEGER_ARRAY player
     */

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        List<Integer> player_ranks = new ArrayList<Integer>();
        List<Integer> tmp_lst = ranked;
        int temp = tmp_lst.get(0);
        Set<Integer> hashSet = new LinkedHashSet(tmp_lst);
        List<Integer> removedDuplicates = new ArrayList(hashSet);
        System.out.println(removedDuplicates.toString());
        
        for (int j = 0; j < player.size();j++){
            //adds player to a sorted list on ranked
            //retrieves index and adds into to player
            //rankings list
            removedDuplicates.add(player.get(j));
            Collections.sort(removedDuplicates);
            Collections.reverse(removedDuplicates);
            int idx = removedDuplicates.indexOf(player.get(j));
            player_ranks.add(idx+1);
            
            removedDuplicates.remove(idx);
        } 
        return player_ranks;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int rankedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ranked = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int playerCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> player = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.climbingLeaderboard(ranked, player);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
