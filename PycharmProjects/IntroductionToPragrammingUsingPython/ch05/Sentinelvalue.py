data = eval(input("Enter an integer (the input ends " + "if it is 0): "))

sum = 0
while data != 0:
    sum += data
    data = eval(input("Enter an integer (the input ends " + "if it is 0): "))

print("the sum is", sum)