

def isPrime(number):
    divisor = 2
    while divisor <= number / 2:
        if number % divisor == 0:
            return False
        divisor += 1
    return True

def printPrimeNumbers(numberOfPrimes):
    NUMBER_OF_PRIMES = 50
    NUMBER_OF_PRIMES_PER_LINE = 10
    count = 0
    number = 2

    while count < numberOfPrimes:
        if isPrime(number):
            count += 1
            print(number, end=' ')
            if count % NUMBER_OF_PRIMES_PER_LINE == 0:
                print()

        number += 1
if __name__ == "__main__":
    print("The first 50 prime numbers are")
    printPrimeNumbers(50)