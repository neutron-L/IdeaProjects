import math

latitude1, longitude1 = eval(input("Enter point 1 (latitude and longitude) in degrees: "))
latitude2, longitude2 = eval(input("Enter point 2 (latitude and longitude) in degrees: "))
R = 6371.01
distance = R * math.acos(
    math.sin(math.radians(longitude1)) * math.sin(math.radians(longitude2)) + math.cos(math.radians(longitude1)) *
    math.cos(math.radians(longitude2)) * math.cos(math.radians(latitude1) - math.radians(latitude2)))
x1 = math.cos(math.radians(longitude1))
y1 = math.sin(math.radians(latitude1))
x2 = math.cos(math.radians(longitude2))
y2 = math.sin(math.radians(latitude2))
distance = R * math.acos(1 - 0.5 * ((x1 - x2) ** 2 +
                                    (y1 - y2) ** 2))

print("The distance between the two points is", distance, "km")
print(2 * R * math.pi - distance)
