#!/usr/bin/env python3

# RegEx:
# https://docs.python.org/2/library/re.html
# 
# 29 Regular Expressions part-1 | Python Online Training | Python scripting | Learnbay
# https://www.youtube.com/watch?v=ZZI_94_JYS4
# 
# .           : Any character except new line
# \d          : Digits
# \D          : Not a digit
# \w          : Word character (i.e.: a-z, A-Z, 0-9)
# \W          : Not a word character
# \s          : White spaces (i.e.: space, tab, new line)
# \S          : Not a white space
# \b          : Word boundary
# \B          : Not a word boundary
# ^           : Beginning of a line
# $           : End of a line
# []          : Matches characters in the brackets
# [^ ]        : Matches any character not in the brackets
# [2-7]       : Range of numbers from 2 to 7
# [d-u]       : Range of characters, from d to u
# *           : Match 0 or more repetitions of the preceding RE. ab* will match ‘a’, ‘ab’, or ‘a’ followed by any number of ‘b’s.
# +           : Match 1 or more repetitions of the preceding RE. ab+ will match ‘a’ followed by any non-zero number of ‘b’s; it will not match just ‘a’.
# ?           : M atch 0 or 1 repetitions of the preceding RE. ab? will match either ‘a’ or ‘ab’.
# {}          : The number of occurrences(eg: Hel{4}o for finding Hellllo or Hel{2,4}o to find all occurrences with 2,3 or 4 'l')
# 
# Examples:
# 1. RegEx to match two type of given phone numbers,
# 123 - 456 - 1234
# 123-456-1234
# 123.456.1234
# RegEx = \d{3}\s?[-.]\d{3}\s?[-.]\d{4}
# 
# 2. RegEx to match two exact given phone numbers,
# 899-456-1234
# 799.456.1234
# RegEx = (8|7)99[-.]\d{3}[-.]\d{4}
# RegEx = (8|7)9{2}[-.]\d{3}[-.]\d{4}
# 
# 
# 30 Regular Expressions part-2 | Python Online Training | Python scripting | Learnbay
# https://www.youtube.com/watch?v=BuM6JCRzHno

import re

myStr = '''
123 - 456 - 1234
123-456-1234
290-123-6676
890.123.6676

899.123.6676
799.123.6678

898*123*8989
898#122#8989
898+898+9999

'''
# pattern = re.compile(r'(\d{3})[-.](\d{3})[-.](\d{4})')    # create 3 groups
pattern = re.compile(r'\d{3}[-.]\d{3}[-.]\d{4}')
matches = pattern.finditer(myStr)
""" for match in matches:
    #    print(match)
#    print(match.span())            # Start and End address of each match
#    print(match.group(3))
    start, end = match.span()
    matchList = pattern.findall(myStr) """
#    print(myStr[start:end])
#print(matchList)
mList1 = [i.group() for i in matches]
print(mList1)