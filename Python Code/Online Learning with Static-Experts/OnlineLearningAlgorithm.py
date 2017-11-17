import numpy as np
import matplotlib.pyplot as plt

class OnlineLearning:
    def __init__(self, labels, data, b = 1):
        self.labels = labels
        self.b = b
        self.data = data

    def staticExpert(self):
        # initialization
        # n = row, d = column
        n, d = self.data.shape
        y_hat = np.zeros(n)
        pt = np.ones(d) * (1./d)
        averageLoss = np.zeros(n)
        totalLoss = np.zeros(n)
        # receive data
        for i in range(n):
            # prediction
            y_hat[i] = np.sum(self.data[i] * pt)
            # get the loss (xi-y)^2
            loss = (self.data[i]-self.labels[i])**2
            # update pt
            pt = pt * np.exp(-self.b * loss)
            # normalize new pt
            pt = pt / np.sum(pt)
            totalLoss[i] = (y_hat[i]-self.labels[i])**2
            averageLoss[i] = np.sum(totalLoss)/(i+1.)
        return averageLoss


# extract 7th column as labels and reshape the raw data
# the features is actually the prediction of jth experts, ith row is the ith prediction of experts
data = np.loadtxt(open("cloud.csv",'rt'), delimiter=',')
labels = data[:, 6]
data = np.delete(data, 6, axis=1)

Online = OnlineLearning(labels, data)
loss1 = Online.staticExpert()
Online.b = 0.000001
loss2 = Online.staticExpert()
Online.b = 120
loss3 = Online.staticExpert()
plt.plot(loss1)
plt.plot(loss2)
plt.plot(loss3)
plt.legend(['b = 1', 'b = 0.000001', 'b=120'], loc='upper left')
plt.xlabel('Iteration i')
plt.ylabel('Loss')
plt.show()


