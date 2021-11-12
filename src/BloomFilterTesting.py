import csv
import matplotlib.pyplot as plt

with open('../BloomFilterValues.csv') as file:
    data = csv.DictReader(file)
    d = {}
    for row in data:
        k, b, m, value = row['k'], row['b'], row['m'], row['value']
        if not k in d:
            d[k] = {}
        if not b in d[k]:
            d[k][b] = {}
        d[k][b][m] = value

    defaultK, defaultB, defaultM = str(16), str(32), str(50)

    kVals = [str(i) for i in range(8, 16 + 1)]
    kYVals = [d[k][defaultB][defaultM] for k in kVals]
    plt.plot(kVals, kYVals, 'ro')
    plt.show()

    bVals = [str(i) for i in range(16, 128 + 1, 8)]
    bYVals = [d[defaultK][b][defaultM] for b in bVals]
    plt.plot(bVals, bYVals, 'ro')
    plt.show()

    mVals = [str(i) for i in range(10, 100 + 1, 5)]
    mYVals = [d[defaultK][defaultB][m] for m in mVals]
    plt.plot(mVals, mYVals, 'ro')
    plt.show()
