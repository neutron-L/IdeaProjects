import turtle

radius = eval(input("Enter the radius of circle: "))

turtle.circle(radius)

turtle.penup()
turtle.goto(radius*2, 0)
turtle.pendown()
turtle.circle(radius)

turtle.penup()
turtle.goto(radius*2, radius*2)
turtle.pendown()
turtle.circle(radius)

turtle.penup()
turtle.goto(0, radius*2)
turtle.pendown()
turtle.circle(radius)

turtle.done()
