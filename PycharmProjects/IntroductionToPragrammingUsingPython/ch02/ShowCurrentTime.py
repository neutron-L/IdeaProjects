import time

currentTime = time.time()
totalSeconds = int(currentTime)
currentSecond = totalSeconds % 60
totalMinutes = totalSeconds // 60
currentMinutes = totalMinutes % 60
totalHours = totalMinutes // 60
currentHour = totalHours % 24
print("Current time is", currentHour, ":", currentMinutes, ":", currentSecond, "GMT")
