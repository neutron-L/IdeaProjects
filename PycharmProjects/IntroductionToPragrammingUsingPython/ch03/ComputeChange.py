amount = eval(input("Enter an amount, for example, 11.56: "))
remainingAmount = int(amount * 100)

numberOfOneDollars = remainingAmount // 100
remainingAmount %= 100

numberOfQuarters = remainingAmount // 25
remainingAmount %= 25

numberOfDimes = remainingAmount // 10
remainingAmount %= 10

numberOfNickels = remainingAmount // 5
remainingAmount %= 5

numberOfPennies= remainingAmount

print("Your amount", amount, "consists of\n",
      "\t", numberOfOneDollars, "dollars\n",
      "\t", numberOfQuarters, "quarters\n",
      "\t", numberOfDimes, "dimes\n",
      "\t", numberOfNickels, "nickels\n",
      "\t", numberOfPennies, "pennies\n")


