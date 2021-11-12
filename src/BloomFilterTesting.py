import csv
# import matplotlib

with open('../BloomFilterValues.csv') as file:
    data = csv.DictReader(file)
    for row in data:
        print(row['k'], row['b'], row['m'], row['value'])
    
