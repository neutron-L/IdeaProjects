import time
import random

correctCount = 0
count = 0
NUMBER_OF_QUESTIONS = 5

startTime = time.time()

while count < NUMBER_OF_QUESTIONS:
    num1 = random.randint(0, 9)
    num2 = random.randint(0, 9)

    if num1 < num2:
        num1, num2 = num2, num1

    answer = eval(input("What is "+str(num1) + " - " + str(num2) + "?"))
    if num1 - num2 == answer:
        print("You are correct!")
        correctCount += 1
    else:
        print("Your answer is wrong.\n", num1, '-', num2, 'is', num1-num2)
    count += 1

endTime = time.time()
testTime = int(endTime - startTime)
print("Correct count is", correctCount, "out of", NUMBER_OF_QUESTIONS, "\nTest time is", testTime, "s")
