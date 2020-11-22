#!/usr/bin/env python3
# -*- coding:utf-8 -*-

import os
os.system('clear')

#1.
#print('\n#1. Python way to do that\n')
#condition = False
#x=1 if condition else 0
#print('x = :',x)
#print('------------------------------------')

#2.
#print('\n#2. Python way to do that\n')
## underscore don't affect numbers
#num1 = 10_000_000
#num2 = 25_111_111

#3.
#total = num1 + num2
#print ("total : ", total)
#print ("total : ",f'{total :,}')
#print('------------------------------------')

#4.
#tickers = ["FB","APPL","GOOGL","MSN"]
## usual way
#for ticker in tickers:
#    print(ticker)
#
#print('\n#4. Python way to do that\n')
#for index, ticker in enumerate(tickers):
#    print(index,ticker)
#print('------------------------------------')

#5. usual way to choose the same index of two lists
#heroes = ['Spiderman','Superman','Deadpool','Batman']
#univers = ['DC','Ahvaz','Grenoble','Montpellier']
#for index , ticker in enumerate(tickers):
#    hero = heroes[index]
#    print(f'{ticker} is actually {hero}')
#print('\n#5. Python way to do that\n')
#for ticker, hero in zip(tickers, heroes):
#    print(f'{ticker} is actually {hero}')

#6.
#print('\n#6. To extend the Python way to do that\n')
#for ticker, hero, univer in zip(tickers, heroes, univers):
#    print(f'{ticker} is actually {hero} from {univer}')

#7.
#print('\n#7. Normal way of ZIP function is to send a tuple of all lists\n')
#for value in zip(tickers, heroes, univers):
#    print(value)
#print('------------------------------------')

#8.
#print('\n#8. The Python way to unpacking values\n')
#
#a, b = (1,2)
#print("a: ",a)
#print("b: ",b)

#9.
#print("\n#9. In case we don't need all values\n")
#c, _ = (3,4)
#print("c: ",c)

#10.
#print("\n#10. In case we don't need all values and we have more values than variables\n")
#d, e, *f = (3,4,5,6,7,8)
#print("d: ",d,"\ne: ",e,"\nf: ",f)

#11.
#print("\n#11. In case we don't need some values and we have more values than variables\n")
#d, e, *_ = (3,4,5,6,7,8)
#print("d: ",d,"\ne: ",e)
#
##12.
#print("\n#12. In case we don't need some values and we have more values than variables\n")
#d, e, _, *f = (3,4,5,6,7,8)
#print("d: ",d,"\ne: ",e,"\nf: ",f)
#
# #13.
# print("\n#13. In case we don't need some values and we have more values than variables\n")
# d, e, *_, f = (3,4,5,6,7,8)
# print("d: ",d,"\ne: ",e,"\nf: ",f)
# print('------------------------------------')

##14.
#print("\n#14. In two ways we can set an attribut, normal way and using setattr\n")
class Person():
    pass

person = Person()
# person.first = "Amir"
# person.last = "SHIRALI POUR"
# print ("person.first: ",person.first)
# print("person.last: ", person.last)

# first_key = 'first'
# first_value = 'Amir'
# setattr(person,'first','Corey')
# print ("person.first: ",person.first)

# #15.
# # print("\n#15.By using 'setattr', we can use variables to set attributs\n")
# setattr(person,first_key,first_value)
# print ("person.first: ",person.first)

# #16.
# # print("\n#16.By using 'getattr', we can get attribut's value\n")
# first = getattr(person,first_key)
# print(first)

# person_info = {'first':'Amir','last':'SHIRALI POUR'}
# for key, value in person_info.items():
#     setattr(person,key,value)
# print(person.first)
# print(person.last)

# # another way to show this information
# for key in person_info.keys():
#     print(getattr(person, key))
# print('------------------------------------')
#17. Using 'getpass function
# from getpass import getpass
# username = input('Username: ')
# password = getpass('Password: ')
# while (username !='amir' or password !='amir'):
#     print('Your username or password is entered incorrectly.')
#     username = input('Username: ')
#     password = getpass('Password: ')
# else:
#     print('Login successfully, Welcome Amir')
# print('------------------------------------')
#18. Using built-in 'help' function
# print("\n#18. Using built-in 'help' function\n")
# when we are in shell command line of Python, we can use 'help(funcrion name)' to have a documentation about that.
# but befor that, we need to import that module.

# Example:
# pythhon3
# import getpass
# help (getpass)

# To know just what attributs and methods available and you don't need
# all information about that attributs and methods! you can use built-in 'dir()' function like this:

# dir(getpass)
print('------------------------------------')
#19.
# # print("\n#19. Use 'os.system('clear')' or just use 'CTRL+L' to clear screen in python shell\n")

#20.
# print("\n#20. To have a definition about each functions we can use type the name of that funcion in the python shell without '()' parantheses.\n")

# like this:
#for example for 'today' method from datetime module, use this command:
# datetime.today


print('------------------------------------')
#21
# pour l'ajout d'un element dans un dictionnaire exsitant ,**dic[2] recupére
# tout les anciens elements du dic
dic[2] = {**dic[2], "class": "21254465"}
c = {**a, **b}
# c = { "a" : 1, "b" : 2, "c" : 3, "d" : 4 }
a.update(b)

print('------------------------------------')
print('------------------------------------')
print('------------------------------------')
print('------------------------------------')
print('------------------------------------')
print('------------------------------------')
print('------------------------------------')
print('------------------------------------')
print('------------------------------------')
print('------------------------------------')








print('------------------------------------')



