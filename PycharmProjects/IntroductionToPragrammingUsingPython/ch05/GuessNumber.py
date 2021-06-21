import random

num = random.randint(0, 100)
print("Guess a magic number between 0 and 100")
guess = -1
while guess != num:
    guess = eval(input("Enter your guess"))
    if guess == num:
        print("Yes, the number is", num)
    elif guess > num:
        print("Your guess is too high")
    else:
        print("Your guess is too low")
