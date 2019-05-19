class Solution:
    def divide(self, dividend: int, divisor: int) -> int:
        if ((divisor < 0 and dividend > 0) or (divisor > 0 and dividend < 0)) and (dividend % divisor != 0):
            if dividend//divisor+1 > 2147483647:
                return 2147483647
            return dividend//divisor+1
        if dividend//divisor+1 > 2147483647:
            return 2147483647
        return dividend//divisor
        