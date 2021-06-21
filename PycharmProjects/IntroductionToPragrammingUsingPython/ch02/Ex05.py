subtotal, rate = eval(input("Enter the subtotal and a gratuity rate: "))
rate /= 100
gratuity = rate * subtotal
print("The gratuity is", gratuity, "and the total is", subtotal + gratuity)
