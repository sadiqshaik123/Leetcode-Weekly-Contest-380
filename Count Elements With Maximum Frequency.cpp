class Solution {
public:
    int maxFrequencyElements(vector<int>& nums) {
        unordered_map<int, int> freqMap;
        for (int num : nums) {
            freqMap[num]++;
        }

        int maxFrequency = 0;
        int count = 0;
        for (const auto& entry : freqMap) {
            maxFrequency = max(maxFrequency, entry.second);
        }
        for (const auto& entry : freqMap) {
            if (entry.second == maxFrequency) {
                count += entry.second;
            }
        }

        return count;
    }
};
