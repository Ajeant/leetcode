'''
题目描述：
    给定一个double类型的浮点数base和int类型的整数exponent。
    求base的exponent次方。
'''  
'''
很容易想到动态规划，即从结果出发
比如需要计算2^10，
	1.计算2^5
	2.计算2^2 * 2
	3.计算2，即直接返回
'''
  
def power(base, exponent):
    # 任何数的0次方都得1，底数为0，指数不能为0
    if base==0.0 and exponent != 0:
        return 1
    # 任何数的0次方都得1
    if exponent == 0:
        return 1
    # 指数为1，则返回其本身
    if exponent == 1:
        return base
    # flag存储exponent的正负
    flag = False
    if exponent > 0:
        flag = True
    # 将指数拆分，如2^4本为4个2相乘，现改成2^(2^2)
    result = getPower(base, abs(exponent))
    if flag:
        return result
    else:
        return 1/result

def getPower(base, exponent):
    if exponent == 0:
        return 1
    if exponent == 1:
        return base
    result = getPower(base, (int)(exponent>>1))
    result *= result
    # 如果指数n为奇数，则要再乘一次底数base
    if (exponent & 1) == 1:
        result *= base
    return result
# =================================================
# 下面这是爆破法
def bfPower(base, exponent):
    # 任何数的0次方都得1，底数为0，指数不能为0
    if base==0.0 and exponent != 0:
        return 1
    # 任何数的0次方都得1
    if exponent == 0:
        return 1
    # 指数为1，则返回其本身
    if exponent == 1:
        return base
    # flag存储exponent的正负
    flag = False
    result = 1
    if exponent > 0:
        flag = True
    for i in range(abs(exponent)):
        result *= base
    if flag:
        return result
    else:
        return 1/result
    
if __name__=='__main__':
    print(power(2.0, -10))
    print(bfPower(2.0, -10))