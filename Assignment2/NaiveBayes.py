#!/usr/bin/env python
# coding: utf-8

# In[66]:


import numpy as np
import pandas as pd
import math
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder
from sklearn import metrics
from matplotlib import pyplot as plt


# In[67]:


#This defines a new class called NewModel and a method called train that takes two parameters:
#input_data (the training data) and labels (the corresponding labels for each data point). 
#The method initializes the class variables input_data, labels, classes, and parameters.
class NaiveBayes():
    def train(self, input_data, labels):
        self.input_data, self.labels = input_data, labels
        self.classes = np.unique(labels)
        self.parameters = []
        #This loops through each unique class in the training data and gets the subset of 
        #training data corresponding to that class. It then appends an empty list to the parameters 
        #list for that class.
        for i, c in enumerate(self.classes):
            data_for_c = input_data[np.where(labels == c)]
            self.parameters.append([])
            #This loops through each feature column in the subset of training data for the
            #current class and calculates the mean and variance for that feature. 
            #It then appends a dictionary containing the mean and variance to the parameters list for that class.
            for col in data_for_c.T:
                parameters = {"mean_val": col.mean(), "variance": col.var()}
                self.parameters[i].append(parameters)

    #This is a helper method that calculates the likelihood of a feature value x given the mean and 
    #variance of that feature for a given class.
    def _calculate_likelihood(self, mean_val, variance, x):
        eps = 1e-4 
        coeff = 1.0 / math.sqrt(2.0 * math.pi * variance + eps)
        exponent = math.exp(-(math.pow(x - mean_val, 2) / (2 * variance + eps)))
        return coeff * exponent

    #This is another helper method that calculates the prior probability of a given class c in the training data.
    def _calculate_prior(self, c):
        freq = np.mean(self.labels == c)
        return freq

    #This is a method that classifies a single sample based on the Naive Bayes model. 
    #It loops through each class in the training data and calculates the posterior probability 
    #of the sample belonging to that class. It does this by calculating the likelihood of each feature
    #value in the sample given the mean and variance of that feature for the current class,
    #and multiplying these likelihoods together with the prior probability of the class. 
    #It then appends the posterior probability to a list of posteriors.
    #Finally, it returns the class with the highest posterior probability.
    def _classify_sample(self, sample):
        posteriors = []
        for i, c in enumerate(self.classes):
            posterior = self._calculate_prior(c)
            for feature_value, params in zip(sample, self.parameters[i]):
                likelihood = self._calculate_likelihood(params["mean_val"], params["variance"], feature_value)
                posterior *= likelihood
            posteriors.append(posterior)
        return self.classes[np.argmax(posteriors)]

    #This method takes a set of input data and classifies each sample using the _classify_sample method. 
    #It returns a list of predicted classes for each input sample.
    def predict(self, input_data):
        predictions = [self._classify_sample(sample) for sample in input_data]
        return predictions


# In[68]:


clm = [
    'class', 'cap-shape', 'cap-surface', 'cap-color', 'bruises?', 'odor',
    'gill-attachment', 'gill-spacing', 'gill-size', 'gill-color', 'stalk-shape',
    'stalk-root', 'stalk-surface-above-ring', 'stalk-surface-below-ring',
    'stalk-color-above-ring', 'stalk-color-below-ring', 'veil-type',
    'veil-color', 'ring-number', 'ring-type', 'spore-print-color', 'population',
    'habitat'
]


# In[69]:


data = pd.read_csv("MushroomData_8000.txt", header=None, names=clm)
data.shape


# In[70]:


# data.head()


# In[71]:


# data.info()


# In[72]:


categorical = [var for var in data.columns if data[var].dtype == 'O']

# print('There are {} categorical variables\n'.format(len(categorical)))

# print('The categorical variables are :\n\n', categorical)


# In[73]:


data[categorical].head()


# In[74]:


data[categorical].isnull().sum()


# In[75]:


# for var in categorical:

#     print(data[var].value_counts())


# In[76]:


# for var in categorical:

#     print(data[var].value_counts() / float(len(data)))


# In[77]:


le = LabelEncoder()
le2 = LabelEncoder()
y = data['class']
X = data.drop(['class'], axis=1)
y = le.fit_transform(y)
X = X.apply(le2.fit_transform)


# In[78]:


X_train, X_test, y_train, y_test = train_test_split(X,
                                                    y,
                                                    test_size=0.3,
                                                    random_state=42)


# In[79]:


X_test


# In[80]:


y_test


# In[81]:


nb =NaiveBayes()
nb.train(X_train.values,y_train)


# In[82]:


unknown_data = pd.read_csv('MushroomData_Unknwon_100.txt', names=clm[1:])
unknown_data = unknown_data.apply(le2.fit_transform)


# In[83]:


y_pred = nb.predict(unknown_data.values)
# print(y_pred)


# In[84]:


prediction=le.inverse_transform(y_pred)
prediction


# In[85]:


y_pred = nb.predict(X_test.values)
acc = metrics.accuracy_score(y_test, y_pred)
print("Accuray of the provided Algorithm:", acc)

# In[86]:



# In[88]:


np.savetxt('predictionResultNB.txt', prediction, fmt='%c')
print("predictionResultNB.txt has been outputted")

# In[ ]:




