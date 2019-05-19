'''
题目描述：

在一个二维数组中，每一行都按照从左到右递增的顺序排序，
每一列都按照从上到下递增的顺序排序。请完成一个函数，
输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
'''
'''
有题目可以知道，在大概靠近中间的位置开始，
遇到target在两者大小之间时，回溯到上一级
'''

def Find(target, array):
    # 从左下角开始找，这样查找速度快
	# 当然右上角也一样
    row = len(array) - 1
    column = 0
    # 当行数大于0，当前列数小于总列数时循环条件成立
    while row >= 0 and column < len(array[0]):
        if(array[row][column] > target):
            row -= 1
        elif array[row][column] < target:
            column += 1
        else:
            return True
    return False

if __name__=='__main__':
    array=[[5,6],[7,8]]
    print(Find(5, array))