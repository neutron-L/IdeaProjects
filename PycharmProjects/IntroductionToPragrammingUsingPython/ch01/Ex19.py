import turtle
import math


p1 = (40, -69.28)
p2 = (-40, -69.28)
p3 = (-80, -9.8)
p4 = (-40, 69)
p5 = (40, 69)
p6 = (80, 0)

turtle.penup()
turtle.goto(p1[0], p1[1])
turtle.pendown()

turtle.left(math.atan((p6[1] - p1[1])/(p6[0]-p1[0])))
turtle.forward(100)

turtle.left(math.atanh((p2[1] - p1[1]) / (p2[0] - p1[0])) / math.pi * 180)
turtle.forward(100)

turtle.done()




