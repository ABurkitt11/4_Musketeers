import numpy as np
import pandas as pd

pd.set_option('display.max_columns', None)

def readdata(file_path):




    with open(file_path, "r") as file:
        file_contents = file.readlines()

    file_dict = {}
    file_ans = {}



    for i, line in enumerate(file_contents):
        attributes = line.strip().split(',')
        file_dict[i] = attributes

    clm = [
        'class', 'cap-shape', 'cap-surface', 'cap-color', 'bruises?', 'odor',
        'gill-attachment', 'gill-spacing', 'gill-size', 'gill-color', 'stalk-shape',
        'stalk-root', 'stalk-surface-above-ring', 'stalk-surface-below-ring',
        'stalk-color-above-ring', 'stalk-color-below-ring', 'veil-type',
        'veil-color', 'ring-number', 'ring-type', 'spore-print-color', 'population',
        'habitat'
    ]

    myTable = pd.read_csv(file_path, header=None, names=clm)

    print(myTable.value_counts(subset = ['cap-shape', 'class'], sort = False).reset_index())

    for i in myTable['stalk-shape']:
        if(i == 'e'):
            i = 1
        else:
            i = 0

    for i in myTable['class']:
        if (i == 'e'):
            i = 0
        else:
            i = 1

    for i in myTable['cap-shape']:
        if (i == 'k'):
            i = 1
        elif (i == 'c'):
            i = 1
        else:
            i = 0
    

    return file_ans, file_dict