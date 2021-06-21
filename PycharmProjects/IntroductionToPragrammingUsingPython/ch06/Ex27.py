
def isPrime(num):
    for i in range(2, num//2+1):
        if num % i == 0:
            return False
    return True


def findTwoPrimes():
    for i in range(2, 998):
        if isPrime(i) and isPrime(i+2):
            print("(" + str(i) + ", " + str(i+2)+")")


findTwoPrimes()