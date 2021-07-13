import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Chapter1 {
    public static void main(String[] args) {
    }

    public static boolean isUnique(String input) {
        HashSet<Character> seen = new HashSet<>(); // or use int[] l=new int[128];
        for (int i = 0; i < input.length(); i++) {
            seen.add(input.charAt(i));
        }
        return seen.size() == input.length();
    }

    public static boolean checkPerm(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        HashMap<Character, Integer> seenA = new HashMap<>(); // or use int[] l=new int[128];
        HashMap<Character, Integer> seenB = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            char aChar = a.charAt(i);
            char bChar = b.charAt(i);
            int prevA = seenA.getOrDefault(aChar, 0);
            int prevB = seenB.getOrDefault(bChar, 0);
            seenA.put(aChar, prevA + 1);
            seenB.put(bChar, prevB + 1);
        }
        for (char key : seenA.keySet()) {
            if (!seenA.get(key).equals(seenB.get(key))) {
                return false;
            }
        }
        return true;
    }

    public static String URLify(String input, int trueLength) {
        char[] charArr = input.toCharArray();
        // get new far right index
        int spaces = 0;
        for (int i = 0; i < trueLength; i++) {
            if (charArr[i] == ' ') {
                spaces++;
            }
        }
        int farIndex = trueLength - 1 + 2 * spaces;
        for (int i = trueLength - 1; i >= 0; i--) {
            if (charArr[i] == ' ') {
                charArr[farIndex] = '0';
                charArr[farIndex - 1] = '2';
                charArr[farIndex - 2] = '%';
                farIndex = farIndex - 3;
            } else {
                charArr[farIndex] = charArr[i];
                farIndex -= 1;
            }

        }
        return Arrays.toString(charArr);
    }

    public static boolean palPerm(String input) {
        char[] letters = input.toLowerCase().replaceAll("\\s", "").toCharArray();
        int[] seen = new int[128];
        for (char letter : letters) {
            seen[letter] += 1;
        }
        boolean seenOdd = false;
        for (int num : seen) {
            if (num % 2 == 1 && seenOdd) {
                return false;
            } else if (num % 2 == 1 && !seenOdd) {
                seenOdd = true;
            }
        }
        return true;
    }

    public static boolean oneAway(String a, String b) {
        if (a.equals(b)) {
            return true;
        }
        if (a.length() - b.length() > 1) {
            return false;
        }
        int diffs = 0;
        if (a.length() == b.length()) {
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    diffs += 1;
                }
                if (diffs > 1) {
                    return false;
                }
            }
            return true;
        }
        String shorter = a.length() > b.length() ? b : a;
        String longer = a.length() > b.length() ? a : b;
        int shortIndex = 0;
        int longIndex = 0;
        while (shortIndex < shorter.length()) {
            if (diffs > 1) {
                return false;
            }
            if (shorter.charAt(shortIndex) == longer.charAt(longIndex)) {
                shortIndex++;
                longIndex++;
            } else {
                longIndex++;
                diffs++;
            }
        }
        return true;
    }

    public static String stringComp(String input) {
        StringBuilder s = new StringBuilder();
        char curr = input.charAt(0);
        char next;
        int count = 1;
        for (int i = 1; i < input.length(); i++) {
            next = input.charAt(i);
            if (next == curr) {
                count += 1;
            } else {
                s.append(curr);
                s.append(count);
                curr = next;
                count = 1;
            }
        }
        s.append(curr);
        s.append(count);
        String compressed = s.toString();
        if (compressed.length() > input.length()) {
            return input;
        }
        return compressed;
    }

    public static void rotateMat(int[][] input) {
        int[][] rotated = new int[input.length][input.length];
        int length = input.length;
        int level = 0;
        while (level < length) {
            for (int i = level; i < length - 1 - level; i++) {
                //rotated[]
            }
        }
    }

    public static void zeroMat(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        boolean[] changedX = new boolean[width];
        boolean[] changedY = new boolean[height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (matrix[y][x] == 0 && (!changedX[x] && !changedY[y])) {
                    for (int i = 0; i < width; i++) {
                        matrix[y][i] = 0;
                    }
                    for (int j = 0; j < height; j++) {
                        matrix[j][x] = 0;
                    }
                    changedX[x] = true;
                    changedY[y] = true;
                }
            }
        }
    }
}
