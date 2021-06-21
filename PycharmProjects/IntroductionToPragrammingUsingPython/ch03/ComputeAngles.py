import math

x1, y1, x2, y2, x3,y3= eval(input("Enter three points: "))

a = math.sqrt((x2 - x3)**2 + (y2-y3)**2)
b = math.sqrt((x1 - x3)**2 + (y1-y3)**2)
c = math.sqrt((x2 - x1)**2 + (y2-y1)**2)

A = math.degrees(math.acos((b**2 + c**2 - a**2) / (2*b*c)))
B = math.degrees(math.acos((a**2 + c**2 - b**2) / (2*a*c)))
C = math.degrees(math.acos((a**2 + b**2 - c**2) / (2*a*b)))

print("The three angles are", round(A , 2), round(B,2), round(C,2))



