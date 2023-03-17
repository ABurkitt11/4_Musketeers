#!/usr/bin/env python
# coding: utf-8

# In[178]:


import numpy as np
from collections import Counter
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder
from sklearn import metrics
import matplotlib.pyplot as plt


# In[179]:


clm = [
    'class', 'cap-shape', 'cap-surface', 'cap-color', 'bruises?', 'odor',
    'gill-attachment', 'gill-spacing', 'gill-size', 'gill-color', 'stalk-shape',
    'stalk-root', 'stalk-surface-above-ring', 'stalk-surface-below-ring',
    'stalk-color-above-ring', 'stalk-color-below-ring', 'veil-type',
    'veil-color', 'ring-number', 'ring-type', 'spore-print-color', 'population',
    'habitat'
]


# In[180]:


class KNN:

    def __init__(self, K, P, dist_algo):
        self.K = K
        self.P = P
        self.dist_algo = dist_algo

    def fit(self, X, y):
        self.X_train = X
        self.y_train = y

    def predict(self, X_test):
        self.predictions = []

        if self.dist_algo == "minkowski":
            distances_fn = self.minkowski
        elif self.dist_algo == "euclidean":
            distances_fn = self.euclidean
        else:
            print(
                "Invalid Distance Algorithm, user from minkowski, euclidean! ")
            return []

        for i in range(len(X_test)):
            distances = distances_fn(self.X_train.values,
                                     X_test.iloc[i].values.reshape(1, -1))

            # Store distances in a dataframe
            distance_data = pd.DataFrame(data=distances, columns=['dist'])

            # Sort distances, and only consider the k closest points
            nearest_neighbours = distance_data.sort_values(by=['dist'],
                                                           axis=0)[:self.K]

            # Create counter object to track the labels of k closest neighbors
            counter = Counter(self.y_train[nearest_neighbours.index])

            # Get most common label from all the nearest neighbors
            prediction = counter.most_common()[0][0]

            # Append predicted label to output list
            self.predictions.append(prediction)

        return self.predictions

    # function of Minkowski distance algorithm
    def minkowski(self, x, y, P=1):

        distance = 0
        for a, b in zip(x, y):
            distance += abs(a - b)**P
        distance = distance**(1 / float(P))

        return distance

    # function of Euclidean distance algorithm

    def euclidean(self, x, y):
        distance = np.sqrt(sum(pow(a - b, 2) for a, b in zip(x, y)))

        return distance


# In[181]:


def score(self, X_test, y_test):
    predictions = self.predict(X_test)
    return (predictions == y_test).sum() / len(y_test)


# In[182]:


data = pd.read_csv("MushroomData_8000.txt", header=None, names=clm)
data


# In[183]:


le = LabelEncoder()
le2 = LabelEncoder()
y = data['class']
X = data.drop(['class'], axis=1)
y = le.fit_transform(y)
X = X.apply(le2.fit_transform)


# In[184]:


X_train, X_test, y_train, y_test = train_test_split(X,
                                                    y,
                                                    test_size=0.2,
                                                    random_state=42)


# In[185]:


X_test


# In[186]:


y_test


# In[187]:


knn = KNN(K=5, P=2, dist_algo="euclidean")


# In[188]:


knn.fit(X_train, y_train)


# In[189]:

#In order to determine the accuracy
pred = knn.predict(X_test)
acc = metrics.accuracy_score(y_test, pred)
print("Accuray of the provided Algorithm:", acc)


# In[190]:

# Read the unknown dataset
unknown_data = pd.read_csv('MushroomData_Unknwon_100.txt', names=clm[1:])
unknown_data = unknown_data.apply(le2.fit_transform)


# In[191]:


y_pred = knn.predict(unknown_data)
# print(y_pred)


# In[192]:


le.inverse_transform(y_pred)


# In[193]:

# In order to check the Accuracy at different K values, the following code is used in the Jupyter Notebook

'''
score = {}
score_list = []
for k in range(1, 20):
    classifier = KNN(K=k, P=5, dist_algo="euclidean")
    classifier.fit(X_train, y_train)
    pred = classifier.predict(X_test)
    acc = metrics.accuracy_score(y_test, pred)
    score[k] = acc
    score_list.append(acc)


# In[194]:


plt.plot(range(1, 20), score_list)
plt.xlabel("Value of K")
plt.ylabel("Accuracy ")
'''

# In[17]:


np.savetxt('predictionResultKNN.txt', le.inverse_transform(y_pred), fmt='%c')
print("predictionResultKNN.txt has been outputted")

# In[ ]:




