class Solution {
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> a_beau = kmpSearch(s, a);
        List<Integer> b_beau = kmpSearch(s, b);

        Collections.sort(b_beau); // Sort b_beau to use binary search

        List<Integer> beautiful = new ArrayList<>();
        for (int i : a_beau) {
            int left = Collections.binarySearch(b_beau, i - k);
            int right = Collections.binarySearch(b_beau, i + k + b.length());

            // Adjust indices for binarySearch result
            left = (left >= 0) ? left : -(left + 1);
            right = (right >= 0) ? right : -(right + 1);

            for (int j = left; j < right; j++) {
                if (Math.abs(b_beau.get(j) - i) <= k) {
                    beautiful.add(i);
                    break;
                }
            }
        }

        return beautiful;
    }

    private List<Integer> kmpSearch(String text, String pattern) {
        List<Integer> indices = new ArrayList<>();
        int[] pi = computePrefixFunction(pattern);

        int q = 0;
        for (int i = 0; i < text.length(); i++) {
            while (q > 0 && pattern.charAt(q) != text.charAt(i)) {
                q = pi[q - 1];
            }
            if (pattern.charAt(q) == text.charAt(i)) {
                q++;
            }
            if (q == pattern.length()) {
                indices.add(i - q + 1);
                q = pi[q - 1];
            }
        }

        return indices;
    }

    private int[] computePrefixFunction(String pattern) {
        int m = pattern.length();
        int[] pi = new int[m];
        int k = 0;
        for (int q = 1; q < m; q++) {
            while (k > 0 && pattern.charAt(k) != pattern.charAt(q)) {
                k = pi[k - 1];
            }
            if (pattern.charAt(k) == pattern.charAt(q)) {
                k++;
            }
            pi[q] = k;
        }
        return pi;
    }
}
