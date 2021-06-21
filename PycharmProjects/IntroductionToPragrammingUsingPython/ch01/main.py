# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.

import turtle, tkinter


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')
    turtle.showturtle()
    turtle.write("Welcome to Python")
    turtle.forward(100)
    turtle.right(90)
    turtle.color("red")
    turtle.forward(50)
    turtle.right(90)
    turtle.color("green")
    turtle.forward(100)
    turtle.right(90)
    turtle.forward(80)
    turtle.penup()
    turtle.goto(50, -50)
    turtle.pendown()
    turtle.color("red")
    turtle.circle(50)



# See PyCharm help at https://www.jetbrains.com/help/pycharm/
