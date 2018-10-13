
# coding: utf-8

# # Benchmarking Accounting Data for SMB

# In[1]:

import xlrd
import csv

def csv_from_excel():

    wb = xlrd.open_workbook('0001.xlsx')
    #sh = wb.sheet_by_index(0)
    sh = wb.sheet_by_name('Balance Sheet')
    your_csv_file = open('0001.csv','w')
    wr = csv.writer(your_csv_file, quoting=csv.QUOTE_ALL)
    
    for rownum in range(sh.nrows):
        wr.writerow(sh.row_values(rownum))

    your_csv_file.close()

   
    

