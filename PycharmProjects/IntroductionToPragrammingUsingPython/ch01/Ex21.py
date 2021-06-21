import turtle


# 画出圆圈
turtle.circle(100)

# 时针
turtle.penup()
turtle.goto(0, 100)
turtle.pendown()
turtle.color("red")
turtle.left(180)
turtle.forward(40)

# 分针
turtle.penup()
turtle.goto(0, 100)
turtle.pendown()
turtle.color("blue")
turtle.right(180)
turtle.forward(65)

# 秒针
turtle.penup()
turtle.goto(0, 100)
turtle.pendown()
turtle.color("brown")
turtle.left(90)
turtle.forward(80)

turtle.color("black")


# 标记数字
turtle.penup()
turtle.goto(0, 10)
turtle.pendown()
turtle.write("6")

turtle.penup()
turtle.goto(90, 100)
turtle.pendown()
turtle.write("3")

turtle.penup()
turtle.goto(0, 190)
turtle.pendown()
turtle.write("0")


turtle.penup()
turtle.goto(-90, 100)
turtle.pendown()
turtle.write("9")

# 显示时间
turtle.penup()
turtle.goto(-18, -20)
turtle.pendown()
turtle.write("9:15:00")

turtle.done()
