#!/usr/bin/env python3

import sys, os

os.system('clear')
myPath = sys.path
if (len(sys.argv)==2):
#    n = eval(sys.argv[1])
    n = int(sys.argv[1])
    myPath = sys.path[n]            # sys.path[0]   ====== current directory
#    myNewPath = sys.path.append(n) # We can append some new address in the list "sys.path"    
    print(myPath)
else:
    print (myPath)
