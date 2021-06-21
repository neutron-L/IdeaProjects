import turtle
import math


r = eval(input("Enter the length from the center to a vertex: "))
x = -r * math.cos(54/180*math.pi)
y = r * math.sin(54/180*math.pi)
turtle.left(36)
turtle.circle(r, steps=5)

turtle.penup()
turtle.goto(x, y)
turtle.pendown()
turtle.setheading(0)
len = r / math.sin(math.radians(54)) * math.sin(math.radians(72))
x2 = len * math.cos(math.radians(72))
y2 = len * math.sin(math.radians(72))
print(math.atan((y2-y)/(x2-x)))
turtle.left(math.degrees(math.atan((y2-y)/(x2-x))))
turtle.forward(r)
turtle.hideturtle()

turtle.penup()
turtle.goto((x+x2)/2, (y+y2)/2)
turtle.pendown()
turtle.write("r")

print("The area of the pentagon is", 5 * len ** 2 / (4 * math.tan(math.pi/5)))


turtle.done()

