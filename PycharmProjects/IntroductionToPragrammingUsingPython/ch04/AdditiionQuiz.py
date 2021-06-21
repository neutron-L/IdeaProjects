import random

print(format(57.467678, "10.2f"))
print(format(57.56854, "10.2e"))
print(format(57, ">10b"))


number1 = random.randint(0, 9)
number2 = random.randint(0, 9)

answer = eval(input("What is " + str(number1) + " + " + str(number2) + "?"))
print(number1, "+", number2, "=", answer, "is", number1+number2 == answer)
