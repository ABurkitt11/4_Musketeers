

from read import readdata
import numpy as np

def unit_step_func(x):
    return np.where(x > 0, 1, 0)

class Perceptron:
    def __init__(self, learning_rate = 0.1, max_epochs = 2):
        self.learning_rate = learning_rate
        self.max_epochs = max_epochs
        self.weight = None
        self.bias = None
        self.activation_func = unit_step_func

    def train_set(self, dataSet, yval):

        #initial parameters
        self.weight = np.zeros(22)
        self.bias = 0

        y_ = np.where(yval > 0, 1, 0)


        #learn weights
        for _ in range(self.max_epochs):
            for index, row in dataSet.iterrows():
                linear_output = np.dot(row, self.weight) + self.bias
                y_predicted = self.activation_func(linear_output)


                update = self.learning_rate * (y_[index] - y_predicted)
                self.weight += update * row
                self.bias += update


    def predict(self, file_dict):
        linear_output = np.dot(file_dict, self.weight) + self.bias
        y_predicted = self.activation_func(linear_output)
        return y_predicted





if __name__ == '__main__':
    y_val, file_dict = readdata("MushroomData_8000.txt",True)
    perceptron = Perceptron(0.1, 2)
    perceptron.train_set(file_dict, y_val)
    test_data = readdata("MushroomData_Unknwon_100.txt", False)
    print(perceptron.predict(file_dict.head(20)))