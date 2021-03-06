import numpy as np
import matplotlib.pyplot as plt

'''
Expectation-Maximization Algorithm:
Usage:
	In mixture model, there are multiple processes to produce the data points.
	We want to figure out which process produces the data point that we are interested in.
	If we know the parameters of distributions on the processes, we can simply get the probabilities 
	of the data point in each process. Then we can pick up the most likely process as the classification of the data point
	But now we don't know about the underlying distribution, so we need to learn the parameters based on the training data set

Steps: (example distribution: Spherical Gaussian Function)
	initialize the parameters of each distribution (there are k distributions)
	while iteration < max:
		// Estimate posterior probability
		for i in range(n):
			R[i,k] = [weight[k]*Function[k](X[i])]/sum(R[i,0->k])	// Beyes' Rule, P(Y=k|x) = [P(x|Y=k)P(Y=k)]/ P(x)
																// P(x) = sum(P(x|Y=j)P(Y=j)), P(x|Y=k) = F(x)
		log_likelihood=sum(sum(R[i,0->k]))
		N[k]=sum(R[0->n,k])

		// Maximization, equations comes from partial derivative: dG(x)/du, dG/ds
		for k in range(k)
			u[k]=1/N[k] * sum(i=0->n,X[i]*R[i,k])
			wight[k] = N[k]/n
			sigma[k]= 1/(N[k]*colum) * sum(i=0->n, R[i,k]* ||X[i]-u[k]||^2)
			
'''


class GaussianEM:
    def __init__(self, k = 3, eps = 0.0000001, sig = 1):
        self.k = k
        self.eps = eps
        self.sig = sig

    def fit_EM(self, X, max_iters = 10100):
        n, d = X.shape
        # initailazation
        mu = X[np.random.choice(n, self.k, False), :]
        # mu = np.ones((self.k, d))
        # mu[self.k-1] = X[np.random.choice(n, 1, False), :]
        Sigma = [self.sig] * self.k
        w = [1./self.k] * self.k
        R = np.zeros((n, self.k))
        log_likelihoods = []

        # the spherical gaussian function, use variance rather than covariance matrices
        G = lambda mu, sig: (2 * np.pi * sig)**(-d/2.) * np.exp((-1./(2 * sig)) * np.sum((X-mu)**2, axis=1))

        while len(log_likelihoods) < max_iters:
            # Estimating the Posterior Probability
            for k in range(self.k):
                R[:, k] = w[k] * G(mu[k], Sigma[k])   # numerator of the Beyes' rule

            log_likelihood = np.sum(np.log(np.sum(R, axis=1)))  # log-likelihood should increase iteratively
            log_likelihoods.append(log_likelihood)

            R = (R.T / np.sum(R, axis=1)).T         # full responsibility of the Beyes' rule
            N_j = np.sum(R, axis=0)

            # Maximizing the parameters of Gaussians
            for k in range(self.k):
                mu[k] = 1./N_j[k] * np.sum(R[:, k] * X.T, axis=1).T
                w[k] = 1./n * N_j[k]
                t = np.sum((X-mu[k])**2, axis=1)
                Sigma[k] = np.sum(R[:, k] * t)/(d*N_j[k])

            if len(log_likelihoods) < 4:
                continue
            if np.abs(log_likelihood - log_likelihoods[-3]) < self.eps:
                break
        print('log-likelihoods = ', log_likelihoods)
        print('mu = ', mu)
        print("mixing proportion = ", w)
        print('sigma = ', Sigma)
        plt.plot(log_likelihoods)
        plt.xlabel("Iteration")
        plt.ylabel('log likelihood')
        plt.show()
        return (w, mu, Sigma)

filename = 'irisData.csv'
data = np.loadtxt(open(filename, 'rt'), delimiter=',')
print('data.shape=', data.shape)
gem = GaussianEM()
mixing, mu, sig = gem.fit_EM(data)

# sort points with labels
labels = np.loadtxt(open("irisLabels.csv", 'rt'))
labels = np.array([labels])
data_labels = np.concatenate((labels.T, data), axis=1)
data_labels = np.sort(data_labels, axis=0)
data_labels = data_labels[:, 1:]
# print(data_labels)
mixing, mu, sig = gem.fit_EM(data_labels)













