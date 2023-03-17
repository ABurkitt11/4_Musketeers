from read import readdata
import numpy as np

def unit_step_func(x):
    return np.where(x > 0, 1, 0)

class Perceptron:
    def init(self, learning_rate = 0.1, max_epochs = 8):
        self.learning_rate = learning_rate
        self.max_epochs = max_epochs
        self.weight = None
        self.bias = None
        self.activation_func = unit_step_func

    def train_set(self, file_dict, yval):
        n_samples, n_features = file_dict.shape

        #initial parameters
        self.weight = np.zeros(n_features)
        self.bias = 0

        y_ = np.where(yval > 0, 1, 0)


        #learn weights
        for _ in range(self.max_epochs):
            for index, x_i in enumerate(file_dict):
                linear_output = np.dot(x_i, self.weight) + self.bias
                y_predicted = self.activation_func(linear_output)

                #perceptron update rule
                update = self.learning_rate * (y_[index] - y_predicted)
                self.weight += update * x_i
                self.bias += update


    def predict(self, file_dict):
        linear_output = np.dot(file_dict, self.weight) + self.bias
        y_predicted = self.activation_func(linear_output)
        return y_predicted





if __name__ == '__main__':
    y_val, file_dict = readdata("MushroomData_8000.txt")
    #perceptron = Perceptron(0.1, 8)
