import json

if __name__ == '__main__':
    with open('file.json') as f:
        data = json.load(f)

    print(data)
    print(data[0]['province'])
