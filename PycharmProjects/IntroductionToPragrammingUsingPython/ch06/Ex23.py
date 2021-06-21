def convertMillis(millis):
    seconds = millis // 1000
    millis %= 1000
    minutes = seconds // 60
    seconds %= 60
    hours = minutes // 60
    minutes %= 60
    return str(hours) + ": " + str(minutes) + ": " + str(seconds)

print(convertMillis(555550000))