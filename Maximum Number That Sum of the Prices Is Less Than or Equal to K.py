class Solution:
    def findMaximumNumber(self, k: int, x: int) -> int:
        def bit_contribution(bit_pos, n):
            if bit_pos > n.bit_length():
                return 0
            cycle_length = 1 << bit_pos
            complete_cycles = n // cycle_length
            remaining_numbers = n % cycle_length

            total_set_bits = complete_cycles * (cycle_length // 2)
            if remaining_numbers >= cycle_length // 2:
                total_set_bits += remaining_numbers - cycle_length // 2 + 1

            return total_set_bits

        low, high = 1, 10**18
        while low < high:
            mid = (low + high + 1) // 2
            total_price = sum(bit_contribution(i * x, mid) for i in range(1, (mid.bit_length() // x) + 1))

            if total_price <= k:
                low = mid
            else:
                high = mid - 1

        return low

        
