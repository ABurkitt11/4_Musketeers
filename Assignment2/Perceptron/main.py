
from read import readdata
import numpy as np

def unit_step_func(x):
    return np.where(x > 0, 1, 0)

def output_unit_step_func(x):
    return np.where(x > 0, 'p', 'e')

class Perceptron:
    def __init__(self, learning_rate = 0.1, max_epochs = 2):
        self.learning_rate = learning_rate
        self.max_epochs = max_epochs
        self.weights = None
        self.bias = None

    def train_set(self, dataSet, mushroomClasses):

        #initial parameters
        self.weights = np.zeros(22)
        self.bias = 0

        #2 nested loops go through the dataset and train the perceptron, one for every row in the dataset, and the other based on how many epochs are desired.
        for _ in range(self.max_epochs):
            for index, row in dataSet.iterrows():
                linear_output = np.dot(row, self.weights) + self.bias
                prediction = unit_step_func(linear_output)

                # the actual learning portion, these lines update our weights and bias based on the 'wrongness' of the prediction the perceptron made.
                update = self.learning_rate * (mushroomClasses[index] - prediction)
                self.weights += update * row
                self.bias += update


    def predict(self, dataSet):
        linear_output = np.dot(dataSet, self.weights) + self.bias
        prediction = output_unit_step_func(linear_output)
        return prediction




#trains perceptron using "MushroomData_8000.txt", then takes an input of the same format minus the edibility and predicts it for the mushroom(s)
if __name__ == '__main__':
    mushroomClasses, dataSet = readdata("MushroomData_8000.txt",True)
    perceptron = Perceptron(0.1, 2)
    perceptron.train_set(dataSet, mushroomClasses)
    test_file = input("Enter test data file name ")
    test_data = readdata(test_file, False)
    print(perceptron.predict(test_data))
