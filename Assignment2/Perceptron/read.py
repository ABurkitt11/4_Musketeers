
import numpy as np
import pandas as pd
pd.set_option('display.max_columns', None)
# from category import category

# file_dict = {}
    # file_ans = {}

    # for i, line in enumerate(file_contents):
    #     attributes = line.strip().split(',')
    #     file_dict[i] = attributes


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


def readdata(file_path, trainingData):
    with open(file_path, "r") as file:
        file_contents = file.readlines()

    clm = [
        'class', 'cap-shape', 'cap-surface', 'cap-color', 'bruises?', 'odor',
        'gill-attachment', 'gill-spacing', 'gill-size', 'gill-color', 'stalk-shape',
        'stalk-root', 'stalk-surface-above-ring', 'stalk-surface-below-ring',
        'stalk-color-above-ring', 'stalk-color-below-ring', 'veil-type',
        'veil-color', 'ring-number', 'ring-type', 'spore-print-color', 'population',
        'habitat'
    ]

    if trainingData is False:
        clm.pop(0)




    myTable = pd.read_csv(file_path, header=None, names=clm)

    if trainingData:
        myTable["class"] = myTable["class"].replace("p", 1.0)
        myTable["class"] = myTable["class"].replace("e", 0.0)

    myTable["habitat"] = myTable["habitat"].replace("d", 0.0)
    myTable["habitat"] = myTable["habitat"].replace("g", 0.0)
    myTable["habitat"] = myTable["habitat"].replace("l", 1.0)
    myTable["habitat"] = myTable["habitat"].replace("m", 0.0)
    myTable["habitat"] = myTable["habitat"].replace("p", 1.0)
    myTable["habitat"] = myTable["habitat"].replace("u", 1.0)
    myTable["habitat"] = myTable["habitat"].replace("w", 0.0)

    myTable["population"] = myTable["population"].replace("y", 0.0)
    myTable["population"] = myTable["population"].replace("v", 1.0)
    myTable["population"] = myTable["population"].replace("s", 0.0)
    myTable["population"] = myTable["population"].replace("n", 0.0)
    myTable["population"] = myTable["population"].replace("c", 0.0)
    myTable["population"] = myTable["population"].replace("a", 0.0)

    myTable["spore-print-color"] = myTable["spore-print-color"].replace("k", 0.0)
    myTable["spore-print-color"] = myTable["spore-print-color"].replace("n", 0.0)
    myTable["spore-print-color"] = myTable["spore-print-color"].replace("b", 0.0)
    myTable["spore-print-color"] = myTable["spore-print-color"].replace("h", 1.0)
    myTable["spore-print-color"] = myTable["spore-print-color"].replace("r", 1.0)
    myTable["spore-print-color"] = myTable["spore-print-color"].replace("o", 0.0)
    myTable["spore-print-color"] = myTable["spore-print-color"].replace("u", 0.0)
    myTable["spore-print-color"] = myTable["spore-print-color"].replace("w", 1.0)
    myTable["spore-print-color"] = myTable["spore-print-color"].replace("y", 0.0)

    myTable["ring-number"] = myTable["ring-number"].replace("n", 1.0)
    myTable["ring-number"] = myTable["ring-number"].replace("o", 1.0)
    myTable["ring-number"] = myTable["ring-number"].replace("t", 0.0)

    myTable["veil-color"] = myTable["veil-color"].replace("n", 0.0)
    myTable["veil-color"] = myTable["veil-color"].replace("o", 0.0)
    myTable["veil-color"] = myTable["veil-color"].replace("w", 0.0)
    myTable["veil-color"] = myTable["veil-color"].replace("y", 1.0)

    myTable["veil-type"] = myTable["veil-type"].replace("u", 0.0)
    myTable["veil-type"] = myTable["veil-type"].replace("p", 0.0)

    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("b", 1.0)
    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("c", 1.0)
    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("e", 0.0)
    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("g", 0.0)
    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("n", 1.0)
    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("o", 0.0)
    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("p", 1.0)
    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("w", 0.0)
    myTable["stalk-color-below-ring"] = myTable["stalk-color-below-ring"].replace("y", 1.0)

    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("b", 1.0)
    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("c", 1.0)
    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("e", 0.0)
    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("g", 0.0)
    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("n", 1.0)
    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("o", 0.0)
    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("p", 1.0)
    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("w", 0.0)
    myTable["stalk-color-above-ring"] = myTable["stalk-color-above-ring"].replace("y", 1.0)

    myTable["cap-shape"] = myTable["cap-shape"].replace("b", 0.0)
    myTable["cap-shape"] = myTable["cap-shape"].replace("c", 1.0)
    myTable["cap-shape"] = myTable["cap-shape"].replace("f", 0.0)
    myTable["cap-shape"] = myTable["cap-shape"].replace("k", 1.0)
    myTable["cap-shape"] = myTable["cap-shape"].replace("s", 0.0)
    myTable["cap-shape"] = myTable["cap-shape"].replace("x", 0.0)

    myTable["cap-surface"] = myTable["cap-surface"].replace("g", 1.0)
    myTable["cap-surface"] = myTable["cap-surface"].replace("y", 1.0)
    myTable["cap-surface"] = myTable["cap-surface"].replace("f", 0.0)
    myTable["cap-surface"] = myTable["cap-surface"].replace("s", 1.0)

    myTable["stalk-surface-below-ring"] = myTable["stalk-surface-below-ring"].replace("f", 0.0)
    myTable["stalk-surface-below-ring"] = myTable["stalk-surface-below-ring"].replace("k", 1.0)
    myTable["stalk-surface-below-ring"] = myTable["stalk-surface-below-ring"].replace("s", 0.0)
    myTable["stalk-surface-below-ring"] = myTable["stalk-surface-below-ring"].replace("y", 0.0)

    myTable["stalk-surface-above-ring"] = myTable["stalk-surface-above-ring"].replace("f", 0.0)
    myTable["stalk-surface-above-ring"] = myTable["stalk-surface-above-ring"].replace("k", 1.0)
    myTable["stalk-surface-above-ring"] = myTable["stalk-surface-above-ring"].replace("s", 0.0)
    myTable["stalk-surface-above-ring"] = myTable["stalk-surface-above-ring"].replace("y", 0.0)

    myTable["bruises?"] = myTable["bruises?"].replace("f", 1.0)
    myTable["bruises?"] = myTable["bruises?"].replace("t", 0.0)

    myTable["odor"] = myTable["odor"].replace("a", 0.0)
    myTable["odor"] = myTable["odor"].replace("c", 1.0)
    myTable["odor"] = myTable["odor"].replace("f", 1.0)
    myTable["odor"] = myTable["odor"].replace("l", 0.0)
    myTable["odor"] = myTable["odor"].replace("m", 1.0)
    myTable["odor"] = myTable["odor"].replace("n", 0.0)
    myTable["odor"] = myTable["odor"].replace("p", 1.0)
    myTable["odor"] = myTable["odor"].replace("s", 1.0)
    myTable["odor"] = myTable["odor"].replace("y", 1.0)

    myTable["ring-type"] = myTable["ring-type"].replace("p", 0.0)
    myTable["ring-type"] = myTable["ring-type"].replace("e", 1.0)
    myTable["ring-type"] = myTable["ring-type"].replace("l", 0.0)
    myTable["ring-type"] = myTable["ring-type"].replace("f", 0.0)
    myTable["ring-type"] = myTable["ring-type"].replace("n", 1.0)

    myTable["cap-color"] = myTable["cap-color"].replace("n", 0.0)
    myTable["cap-color"] = myTable["cap-color"].replace("b", 1.0)
    myTable["cap-color"] = myTable["cap-color"].replace("c", 0.0)
    myTable["cap-color"] = myTable["cap-color"].replace("e", 1.0)
    myTable["cap-color"] = myTable["cap-color"].replace("g", 0.0)
    myTable["cap-color"] = myTable["cap-color"].replace("p", 1.0)
    myTable["cap-color"] = myTable["cap-color"].replace("r", 0.0)
    myTable["cap-color"] = myTable["cap-color"].replace("u", 0.0)
    myTable["cap-color"] = myTable["cap-color"].replace("w", 0.0)
    myTable["cap-color"] = myTable["cap-color"].replace("y", 1.0)

    myTable["gill-attachment"] = myTable["gill-attachment"].replace("a", 0.0)
    myTable["gill-attachment"] = myTable["gill-attachment"].replace("f", 0.0)

    myTable["gill-spacing"] = myTable["gill-spacing"].replace("c", 1.0)
    myTable["gill-spacing"] = myTable["gill-spacing"].replace("w", 0.0)

    myTable["gill-size"] = myTable["gill-size"].replace("b", 0.0)
    myTable["gill-size"] = myTable["gill-size"].replace("n", 1.0)

    myTable["stalk-shape"] = myTable["stalk-shape"].replace("e", 1.0)
    myTable["stalk-shape"] = myTable["stalk-shape"].replace("t", 0.0)

    myTable["stalk-root"] = myTable["stalk-root"].replace("?", 1.0)
    myTable["stalk-root"] = myTable["stalk-root"].replace("b", 0.0)
    myTable["stalk-root"] = myTable["stalk-root"].replace("c", 0.0)
    myTable["stalk-root"] = myTable["stalk-root"].replace("e", 0.0)
    myTable["stalk-root"] = myTable["stalk-root"].replace("r", 0.0)

    myTable["gill-color"] = myTable["gill-color"].replace("b", 1.0)
    myTable["gill-color"] = myTable["gill-color"].replace("e", 0.0)
    myTable["gill-color"] = myTable["gill-color"].replace("g", 1.0)
    myTable["gill-color"] = myTable["gill-color"].replace("h", 1.0)
    myTable["gill-color"] = myTable["gill-color"].replace("k", 0.0)
    myTable["gill-color"] = myTable["gill-color"].replace("n", 0.0)
    myTable["gill-color"] = myTable["gill-color"].replace("o", 0.0)
    myTable["gill-color"] = myTable["gill-color"].replace("p", 0.0)
    myTable["gill-color"] = myTable["gill-color"].replace("r", 1.0)
    myTable["gill-color"] = myTable["gill-color"].replace("w", 0.0)
    myTable["gill-color"] = myTable["gill-color"].replace("y", 0.0)
    myTable["gill-color"] = myTable["gill-color"].replace("u", 0.0)


    print(myTable)

    if trainingData:
        temp = myTable['class']
        myTable = myTable.drop(['class'], axis=1)
        return temp, myTable
    else:
        return myTable