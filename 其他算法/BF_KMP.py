'''
字符串匹配算法
'''
def index_BF(string, match):
    # 定义两个字符串都从0开始
    i,j = 0,0
    while i < len(string) and j < len(match):
        # 相等就移动到下一个
        if string[i] == match[j]:
            i += 1
            j += 1
        # 不相等就让需要匹配的字符串回退到上次位置+1
        else:
            i = i-j+1
            j = 0
    # 如果j与match长度相等，说明已经完全匹配了
    if j == len(match):
        return i - len(match)
    else:
        return -1

# 计算匹配字符串的重复字符
def backspace(match,back):
    i,j=1,0
    while(i < len(match)):
        if match[i] == match[j]:
            # 只有当back[i]等于0才赋值，防止回退的值被覆盖
            if back[i] == 0:
                back[i] = j
            i += 1
            j += 1
        else:
            # 只有当back[i]等于0才赋值，防止回退的值被覆盖
            if back[i] == 0:
                back[i] = j
            if j == 0:
                i += 1
            else:
                j = 0
    #return back
def index_KMP(matchString, match):
    # 定义两个字符串都从0开始
    i,j = 0,0
    while i < len(matchString) and j < len(match):
        # 相等就继续前进，否则回退
        if j == 0 or matchString[i] == match[j]:
            i += 1
            j += 1
        else:
            j = back[j]
    if j == len(match):
        return i - len(match)
    else:
        return -1
    
if __name__=="__main__":
    pos = index_BF('aaaaaba', 'ba')
    print(pos)
    matchString = "acabaabaabcacaabc"
    match = "abaabcac"
    back = [0]*len(match)
    backspace(match,back)
    print(index_KMP(matchString,match))