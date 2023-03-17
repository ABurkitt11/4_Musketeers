
def readdata(file_path):
    with open(file_path, "r") as file:
        file_contents = file.readlines()

    file_dict = {}
    file_ans = {}

    for i, line in enumerate(file_contents):
        values = line.strip().split(',')
        file_ans = values[0]
        attributes = values[1:]
        file_dict[i] = attributes

    print(file_dict)
    print()

    def data():
        for index, row in range(file_dict):
             return file_ans, file_dict
        


