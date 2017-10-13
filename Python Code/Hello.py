import sys
import random
import re
import os

print("hello world!")
'''
hello wolrd. i'm begining to use python
'''
s1="hello world.\ni'm begining to use python."
print(s1)

# var has to started with letter, can have _
var_233 = 3
print("var_233 = ",var_233,'\t')
'''
data type in python:
    numbers, strings, lists, tuples, dictionaries(maps)
'''

# arithmetic operators: //, **
var=5.2
print("var =",var)
print("exponential calculations =",var**2)
print("normal division / =",var/2)
print("floor division // =",var//2)
print("string in python is connected by comma","rather than plus +")

'''
String: recognized by '' , "", ''''''
----------------------------------------------------------
'''

# put special sign in the strings
quote = "\" put the backslash behind the quote \""
print(quote)
# multi-line strings and strings joint
multi_line_quote = '''this is the multiline
strings in python'''
new_line = quote+multi_line_quote
print(new_line)
# standard format
print("%s XING %s XING %s \nLI" %("standard format output",quote, multi_line_quote))
# eliminate the newline sign
print('use end=\"\" to eliminate newline', end="")
print(' There is no new line')

# print something mutiple times
print("hello * 5\n" * 5)

s2 = "This is a test string"
print(s2[1:6])
# difference: when don't find the target, index() raise exception
# find() return -1
print(s2.find("is"))
print(s2.index("t"))

longString = "i'll catch you   if you fall - the floor"
print("Use [:] in string ", longString[0:4])
# output: floor
print("[-x:] in string: ", longString[-5:])
# output: I'll catch you if you fall - the
print("[:-x] in string", longString[:-5])
print("%.5f is a decimal number, %c is a character" % (1.5, "3"))
print(longString.capitalize())
print("isalpha():", longString.isalpha())
print("isalnum():", longString.isalnum())
print("replace():", longString.replace("floor", "ground"))
longString = "      "+longString
print("after white spacing:", longString)
print("strip()", longString.strip())
splitList = longString.split()
print("split():", splitList)
print("re.split():", re.split(" +", longString))

'''
-------------------------------------------------------------
'''

'''
List Operation: recognized by []
'''
grocery_list=['juice', 'tomatoes' , "potatoes"]
print(grocery_list)
print(grocery_list[2])
grocery_list[2] = 34
print(grocery_list)
#access range of list
print(grocery_list[0:2])

other = ['wash car', 'pick up kids', 'cash check']
#lists can contain lists
toDoList=[other, grocery_list]
print(toDoList)
print(toDoList[0][1])

# functions of list
list2=[]
i=1
list2.append("items "+str(i))
print(list2)
list2.insert(4, 77)
print(list2)
list2.insert(1,88)
print(list2)
list2.insert(44, 99)
print(list2)
# remove items by index and by object
list2.pop(1)
print(list2)
list2.remove('items 1')
print(list2)
list2.append("hello")
print(list2)
del list2[list2.__len__()-1]
print(list2)
list2.sort()
list2.reverse()
print(list2)
print('len(list) = ',len(list2))
list2.append(90)
list2.append(435)
print('max(list2) = ',max(list2))

'''
Tuples: recognized by ()
-------------------------------------------------------------
'''
piTuple = (3,1,4,5,9)
# change tuple to list
new_list=list(piTuple)
new_tuple=tuple(new_list)
print('len(new_tuple) = ',len(new_tuple) , "min(new_tuple) = ", min(new_tuple))

'''
Dictionary: recognized by {}
'''
# constructor, access, assignment, delete entry
dic1 = {1: 3, 2: 4, 'Fiddler': "Issac Bowin"}
print(dic1)
print(dic1[1])
print(dic1['Fiddler'])
del dic1[2]
print(dic1)
dic1[1] = 7
print(len(dic1))
print(dic1.keys())
print(dic1.values())

'''
-------------------------------------------------------------
'''

'''
Control flow, recognized by :
-------------------------------------------------------------
'''
# condition
flag = 3
if flag > 1:
    print("flag if execution")
elif flag == 2:
    print("flag elif execution")
else:
    print('flag else execution block')
# logical operators: and, or , not
if (flag >= 1) and (flag <= 18):
    print('flag >= 1 and flag <= 18')
elif flag == 2 or flag == 5:
    print("flag==2 or flag == 5")
elif not(flag == 3):
    print("not(flag == 3)")

# loop
for x in range(0, 10):
    print(x, ' ', end='')
print('')

list3 = [112,324,5,45,65,34,2,5]

for y in list3:
    print(y, " ")
    if y == 5:
        break
for i in range(0, 5):
    for j in range(0, i):
        print("-", end="")
    for k in range(i, 5):
        print("* ", end="")
    print("")

randomNumber = random.randrange(0, 16)
while(randomNumber != 15):
    print(randomNumber)
    randomNumber = random.randrange(0, 16)
    print(randomNumber)

'''
-------------------------------------------------------------
'''

'''
Function, recognized by def name(parameters):
define before using
-------------------------------------------------------------
'''
def addNumber(fNum, lNum):
    sumNum = fNum+lNum
    return

print(addNumber(1,4))

def addNumber2(a,b,c):
    sum = a+b+c
    return sum

print(addNumber2(1,2,4))

'''
-------------------------------------------------------------
'''

# get input from console
# use sys class
# name = sys.stdin.readline()
# print('input name = ', name)

'''
sys module: file I\O
recognized by open(), mode = wb or r+
--------------------------------------------------------------
'''
testFile = open('MyTestFile.txt', 'wb')
print("testFile.mode", testFile.mode)
print('testFile.name', testFile.name)
testFile.write(bytes("I have a dream\nSome day, my children will live in the country.", 'UTF-8'))
testFile.close()
testFile = open('MyTestFile.txt', 'r+')
content = testFile.read()
print("read content = :", content)
testFile.close()
# delete file using OS module
# we have to close file before we delete them
os.remove('MyTestFile.txt')

'''
Objects parts:
recognized by class name :
'''

class Animal:

    # constructors in python, identified by __init__
    # access modifier, var->public, _var->protected, __var->private
    # __name__->the front __ get invalid and __name__ is a normal name usually defined by system
    def __init__(self, name, height, weight, sound):
        self._name = name
        self._height = height
        self._weight = weight
        self._sound = sound

    def setName(self, name):
        self._name = name

    def getName(self):
        return self._name

    def setWeight(self, weight):
        self._weight = weight

    def getWeight(self):
        return self._weight

    def setHeight(self, height):
        self._height = height

    def getHeight(self):
        return self._height

    def setSound(self, sound):
        self._sound = sound

    def getSound(self):
        return self._sound

    def getType(self):
        print("Animal")

    def toString(self):
        # return "%s is %d cm tall and %d kilogram and says %d" %(self.__name, self.__height, self.__weight, self.__sound)
        return "{} is {} cm tall and {} kilograms and say {}" .format(self._name,
                                                                      self._height,
                                                                      self._weight,
                                                                      self._sound)


cat = Animal("cat", 3, 4, "meow")
print("cat.toString:", cat.toString())


# inheritance, recognized by class Name() -> can't have space between Name()
class Dog(Animal):

    def __init__(self, name, height, weight, sound, owner):
        self.owner = owner
        super(Dog, self).__init__(name, height, weight, sound)

    # parameter = X -> have default value
    def multipleSounds(self, howMany = 2):
        if howMany is None:
            print(self.getSound())
        else:
            print(self.getSound() * howMany)

    def getType(self):
        print("Dog")

    def toString(self):
        return "{} is {} cm tall and {} kilograms and say {}, His owner is {}".format(self._name,
                                                                                      self._height,
                                                                                      self._weight,
                                                                                      self._sound,
                                                                                      self.owner)


spot = Dog("Spot", 53, 27, "Ruff", "XING")
print("Dog.toString", spot.toString())
print("spot.owner", spot.owner)

class Polymorphism:
    def test(self,var):
        temp = var.toString()
        print(temp)

p = Polymorphism()
p.test(cat)
p.test(spot)

