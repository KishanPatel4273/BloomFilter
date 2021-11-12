import csv
import matplotlib

with open('../BloomFilterValues.csv') as file:
    data = csv.DictReader(file)
    d = {}
    for row in data:
        k, b, m, value = row['k'], row['b'], row['m'], row['value']
        if k in d:
            if b in d[k]:
                if m in d[k][b]:
                    d[k][b][m] = value
                else:
                    d[k][b] = {m: value}
            else:
                d[k] = {b: {m: value}}
        else:
            d = {k: {b: {m: value}}}

    defaultK, defaultB, defaultM = 16, 32, 50

    kVals = [i for i in range(8, 16 + 1)]:
    for k in kVals:
        # draw graph with k, defaultB, defaultM

    bVals = [i for i in range(16, 128 + 1, 8)]:
    for b in bVals:
        # draw graph with k, defaultB, defaultM

    mVals = [i for i in range(10, 100 + 1, 5)]:
    for m in mVals:
        # draw graph with k, defaultB, defaultM
