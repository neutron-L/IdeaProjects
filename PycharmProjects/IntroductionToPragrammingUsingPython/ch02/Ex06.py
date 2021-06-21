num = eval(input("Enter an integer between 0 and 1000: "))

a = num % 10
num //= 10

b = num % 10
num //= 10

c = num % 10
num //= 10

print("The sum of the digits is", a + b + c)
