import math

class Circle:
    def __init__(self, radius=1):
        self.__radius = radius

    def getRadius(self):
        return self.__radius

    def getPerimeter(self):
        return 2 * self.__radius * math.pi

    def getArea(self):
        return self.__radius * self.__radius * math.pi


if __name__ == '__main__':
    c = Circle(4)
    # print(c.__radius)
    print(c.getRadius())
    print(c.getArea())
