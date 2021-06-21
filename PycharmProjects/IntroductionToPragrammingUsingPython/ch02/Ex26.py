import turtle

x, y, radius = eval(input("Enter the coordinate and radius of circle: "))

turtle.penup()
turtle.goto(x, y-radius)
turtle.pendown()
turtle.circle(radius)
turtle.penup()
turtle.goto(x, y)
turtle.pendown()
turtle.write(radius * radius * 3.14159)

turtle.done()
