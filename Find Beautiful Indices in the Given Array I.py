class Solution:
    def beautifulIndices(self, s: str, a: str, b: str, k: int) -> List[int]:
        n = len(s)
        a_indices = [i for i in range(n - len(a) + 1) if s[i:i + len(a)] == a]
        b_indices = [j for j in range(n - len(b) + 1) if s[j:j + len(b)] == b]

        beautiful = set()
        for i in a_indices:
            idx = bisect.bisect_left(b_indices, i)
            if idx > 0 and abs(b_indices[idx - 1] - i) <= k:
                beautiful.add(i)
            if idx < len(b_indices) and abs(b_indices[idx] - i) <= k:
                beautiful.add(i)

        return sorted(beautiful)


        
