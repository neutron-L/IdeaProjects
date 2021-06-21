num = eval(input("Enter the number of lines: "))
for i in range(num):
    blanks = num - i
    while blanks > 0:
        print(' ', end='')
        blanks -= 1
    for j in range(i, 0, -1):
        print(j, end='')
    for k in range(2, i+1):
        print(k, end='')
    print()
