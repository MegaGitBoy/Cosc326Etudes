import sys

woofLetters = "pqrs"
leaderLetters = "CAKE"

def woof(row):
    #Amount of woofs currently active. Needed to test for leader woof.
    woofAmount = 0
    row = row.replace("\n", "")
    if len(row) == 0:
        print("not woof")
        return
    #Final charachter must be a woof
    if row[-1] in woofLetters:
        woofAmount += 1
        #Start from second last charachter and move backwards
        for i in range(len(row) - 2, -1, -1):
            if row[i] == 'N':
                  pass
            elif row[i] in "pqrs":
                  woofAmount += 1
            elif row[i] in leaderLetters and woofAmount >= 2:
                woofAmount -= 1
            else:
                print("not woof")
                return
        #Check if whole line is a woof
        if woofAmount == 1:
            print("woof")
        else:
            print("not woof")
        return
    else:
        print("not woof")

for row in sys.stdin:
    woof(row)




