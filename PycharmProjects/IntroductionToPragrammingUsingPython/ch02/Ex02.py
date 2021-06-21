radius, length = eval(input("Enter the radius and length of a cylinder: "))
PI = 3.14159
area = radius * radius * PI
volume = area * length
print("The area is", area)
print("The volume is", int(volume*10)/10)
