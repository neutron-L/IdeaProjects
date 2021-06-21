import turtle
import math

turtle.color("red")
turtle.penup()
turtle.goto(-39, 48)
turtle.right(math.atan(98/89) / math.pi * 180)
turtle.pendown()
turtle.forward(math.sqrt(math.pow(89, 2) + math.pow(-98, 2)))

turtle.done()