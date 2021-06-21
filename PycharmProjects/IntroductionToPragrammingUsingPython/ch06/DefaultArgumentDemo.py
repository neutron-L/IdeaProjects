def printArea(width=1, height=2):
    area = height * width
    print("width:", width, '\thight:', height, '\tarea:', area)

def func(x, y=4):
    print(x, y)


printArea()
printArea(4, 2.5)
printArea(height=4, width=3)
printArea(height=3)
printArea(width=4)
print(ord('a'))
print(chr(66))