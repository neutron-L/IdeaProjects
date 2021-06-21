def format(number, width):
    if len(str(number)) >= width:
        return str(number)
    else:
        return (width - len(str(number))) * '0' + str(number)


print(format(34, 8))

