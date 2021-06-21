minutesOfDay = 24 * 60
minutes = eval(input("Enter the number of minutes: "))
days = minutes // minutesOfDay
years = days//365
days %= 365
print(minutes, "minutes is approximately", years, "years and", days, "days")
