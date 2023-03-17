
import numpy as np
import pandas as pd

from category import category




# def countCategories(file_dict):
    # attributePos = {}
    # temp = None
    # for row in range(len(file_dict)):
    #     for col in range(len(file_dict[row])):
    #         if attributePos.get(file_dict[row][col], col) is None:
    #             attributePos[(file_dict[row][col], col)] = category(0,0)
    #
    #         if file_dict[row][0] == 'e':
    #             # attributePos[(file_dict[row][col], col)].increaseTotal()
    #             pass
    #         else:
    #             temp = attributePos[(file_dict[row][col], col)]
    #
    # print("test")
    # print(attributePos)






def readdata(file_path):

    with open(file_path, "r") as file:
        file_contents = file.readlines()

    # file_dict = {}
    # file_ans = {}

    # for i, line in enumerate(file_contents):
    #     attributes = line.strip().split(',')
    #     file_dict[i] = attributes

    clm = [
        'class', 'cap-shape', 'cap-surface', 'cap-color', 'bruises?', 'odor',
        'gill-attachment', 'gill-spacing', 'gill-size', 'gill-color', 'stalk-shape',
        'stalk-root', 'stalk-surface-above-ring', 'stalk-surface-below-ring',
        'stalk-color-above-ring', 'stalk-color-below-ring', 'veil-type',
        'veil-color', 'ring-number', 'ring-type', 'spore-print-color', 'population',
        'habitat'
    ]

    myTable = pd.read_csv(file_path ,header=None, names=clm)

    print(myTable.iterrows())
    dictionary = {(4 ,'t'): 2, }

    myTable["class"] = myTable["class"].replace("p" ,1)
    myTable["class"] = myTable["class"].replace("e", 0)

    myTable["habitat"] = myTable["habitat"].replace("d", 0)
    myTable["habitat"] = myTable["habitat"].replace("g", 0)
    myTable["habitat"] = myTable["habitat"].replace("l", 1)
    myTable["habitat"] = myTable["habitat"].replace("m", 0)
    myTable["habitat"] = myTable["habitat"].replace("p", 1)
    myTable["habitat"] = myTable["habitat"].replace("u", 1)
    myTable["habitat"] = myTable["habitat"].replace("w", 0)

    myTable["population"] = myTable["population"].replace("y", 0)
    myTable["population"] = myTable["population"].replace("v", 1)
    myTable["population"] = myTable["population"].replace("s", 0)
    myTable["population"] = myTable["population"].replace("n", 0)
    myTable["population"] = myTable["population"].replace("c", 0)
    myTable["population"] = myTable["population"].replace("a", 0)


    myTable["spore-print-color"] = myTable["spore-print-color"].replace("k", 0)
    myTable["spore-print-color"] = myTable["spore-print-color"].replace("n", 0)
    myTable["spore-print-color"] = myTable["spore-print-color"].replace("b", 0)
    myTable["spore-print-color"] = myTable["spore-print-color"].replace("h", 1)
    myTable["spore-print-color"] = myTable["spore-print-color"].replace("r", 1)
    myTable["spore-print-color"] = myTable["spore-print-color"].replace("o", 0)
    myTable["spore-print-color"] = myTable["spore-print-color"].replace("u", 0)
    myTable["spore-print-color"] = myTable["spore-print-color"].replace("w", 1)
    myTable["spore-print-color"] = myTable["spore-print-color"].replace("y", 0)

    myTable["ring-number"] = myTable["ring-number"].replace("n", 1)
    myTable["ring-number"] = myTable["ring-number"].replace("o", 1)
    myTable["ring-number"] = myTable["ring-number"].replace("t", 0)

    myTable["veil-color"] = myTable["veil-color"].replace("n", 0)
    myTable["veil-color"] = myTable["veil-color"].replace("o", 0)
    myTable["veil-color"] = myTable["veil-color"].replace("w", 0)
    myTable["veil-color"] = myTable["veil-color"].replace("y", 1)

    myTable["veil-type"] = myTable["veil-type"].replace("u", 0)
    myTable["veil-type"] = myTable["veil-type"].replace("p", 0)

    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("b", 1)
    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("c", 1)
    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("e", 0)
    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("g", 0)
    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("n", 1)
    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("o", 0)
    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("p", 1)
    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("w", 0)
    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("y", 1)

    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("b", 1)
    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("c", 1)
    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("e", 0)
    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("g", 0)
    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("n", 1)
    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("o", 0)
    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("p", 1)
    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("w", 0)
    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("y", 1)


    print(myTable)



    print(myTable.value_counts(subset=['veil-type', 'class'], sort=False).reset_index())


    return 1




# for index, row in myTable.iterrows():
    #     print(row)

    # for index, row in myTable.iterrows():
    #     for i in enumerate(row):
    #             print(i)


    # print(myTable)

    # countCategories(file_dict)

    # print(myTable[['class', 'cap-shape']].value_counts())
