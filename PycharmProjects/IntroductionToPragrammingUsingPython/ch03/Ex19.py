import turtle
import math

x1, y1, x2, y2 = eval(input("Enter the coordinates of point1 and point2 like x1, y1, x2, y2: "))

turtle.penup()
turtle.goto(x1, y1)
turtle.pendown()
turtle.write("("+ str(x1) + ", " + str(y1) + ")")

# degree = math.degrees(math.asin((y2-y1)/math.sqrt((x2-x1)**2 + (y2-y1)**2)))
# print(degree)
# turtle.left(degree)
# # turtle.left(45)
# turtle.forward(math.sqrt((x2-x1)**2 + (y2-y1)**2))
turtle.goto(x2, y2)
turtle.hideturtle()
turtle.write("(" +  str(x2) + ", "+ str(y2) + ")")

turtle.done()


