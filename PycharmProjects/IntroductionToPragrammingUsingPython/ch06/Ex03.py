def reverse(number):
    strn = str(number)
    rstrn = ''
    for i in range(0, len(strn)):
        rstrn += strn[len(strn)-1-i]
    return int(rstrn)


def isPalindrome(number):
    return number == reverse(number)


print(isPalindrome(1234321))
