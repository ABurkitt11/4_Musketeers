
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
        self.weight = None
        self.bias = None

    def train_set(self, dataSet, yval):

        #initial parameters
        self.weight = np.zeros(22)
        self.bias = 0

        y_ = np.where(yval > 0, 1, 0)


        #learn weights
        for _ in range(self.max_epochs):
            for index, row in dataSet.iterrows():
                linear_output = np.dot(row, self.weight) + self.bias
                y_predicted = unit_step_func(linear_output)


                update = self.learning_rate * (y_[index] - y_predicted)
                self.weight += update * row
                self.bias += update


    def predict(self, file_dict):
        linear_output = np.dot(file_dict, self.weight) + self.bias
        y_predicted = output_unit_step_func(linear_output)
        return y_predicted




#trains perceptron using "MushroomData_8000.txt", then takes an input of the same format minus the edibility and predicts it for the mushroom(s)
if __name__ == '__main__':
    y_val, file_dict = readdata("MushroomData_8000.txt",True)
    perceptron = Perceptron(0.1, 2)
    perceptron.train_set(file_dict, y_val)
    test_file = input("Enter test data file name ")
    test_data = readdata(test_file, False)
    print(perceptron.predict(test_data))