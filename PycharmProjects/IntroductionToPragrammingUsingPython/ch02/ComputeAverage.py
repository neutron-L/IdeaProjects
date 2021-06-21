'''
radius = 1
area = 3.14 * radius
print(id(radius), id(area))

radius = 2
area = 3.14 * radius
print(id(radius), id(area))

radius = 1
area = 3.14 * radius
print(id(radius), id(area))

r = 1
a = 3.14 * r
print(id(r), id(a))

'''


number1 = eval(input("Enter the first number:"))
number2 = eval(input("Enter the second number:"))
number3 = eval(input("Enter the third number:"))

average = (number1 + number2 + number3) / 3

print("The average of", number1, number2, number3, "is", average)
sum = 1 + 2 + 3 + \
    4 + 5
print(sum)

radius = eval(input("enter a radius:"))
print(radius)
