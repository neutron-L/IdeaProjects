# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.

from PrimeNumberFunction import isPrime
def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


def nPrintln(message, n):
    for i in range(n):
        print(message)


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')
    # 位置参数
    nPrintln('a', 3)
    # 关键字参数
    nPrintln(n=3, message='b')
    # nPrintln(3, message='c')
    print(isPrime(2))
    #     位置参数不能出现在关键字参数之后
    # nPrintln(message='d', 4)
# See PyCharm help at https://www.jetbrains.com/help/pycharm/

