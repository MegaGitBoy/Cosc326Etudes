#NAME:Leo Venn    ID:3430125
import sys


#Dictionaries to map name with a number and vice versa for later lookup
nameDict = {}
numberDict = {}

#List of current round
roundList = []

#Nubmer associated with voter
voterNo = 0
#number associated with key for voter in dictionary
keyPairNo = 0

#Read in vote and create appropriate dictionary entries in dictionaries
def processVote(name):
    try:
        numberRep = nameDict[name]
        roundList[voterNo].append(numberRep)
    except:
        global keyPairNo
        nameDict[name] = keyPairNo
        numberDict[keyPairNo] = name
        roundList[voterNo].append(keyPairNo)
        keyPairNo += 1

endOfFile = False

for row in sys.stdin:
    # Process input to see if valid/transform input into valid input if possible
    if endOfFile:
        break
    if row == '':
        break

    justSpaces = row
    justSpaces = justSpaces.replace(" ", "").replace("\t", "")
    if row != "\n" and justSpaces != "\n":
        roundList.append([])
        name = ""
        for i in range(0, len(row)):
            if i == len(row) - 1:
                if row[i] != "\n":
                    if row[i] != "\t" and row[i] != " ":
                        name += row[i]
                    if name != "":
                        processVote(name)
                    name = ""
                    endOfFile = True;
                else:
                    if name != "":
                        processVote(name)
                        name = ""
            elif row[i] != " " and row[i] != "\t":
                name += row[i]
            else:
                if name != "":
                    processVote(name)
                    name = ""
        voterNo += 1

#Initialize list of points for each candidate
pointsList = [0] * len(nameDict)
#Create list to store prev rounds points for tie breaking
prevRounds = []
#Check if round is over
done = False

#For every candidate
for r in range(0, len(nameDict)):

    # For every vote voter
    for n in range(0, len(roundList)):
        # Add point to candidate list
        pointsList[roundList[n][0]] += 1

    # Find voter with least points
    minn = max(pointsList)

    for c in pointsList:
        if c >= 0 and c < minn:
            minn = c

    # Indicies of lowest vote candidates
    indices = [j for j, x in enumerate(pointsList) if x == minn]

    # If more that one lowest vote candidate. Backtrack to previous round to tie break
    if len(indices) > 1:
        if r == 0:
            done = True
        else:
            # Variables to calculate tie breaks
            tempScoreList = []
            roundCheck = len(prevRounds) - 1
            tempIndices = []
            validTempIndices = []

            # While there are still multiple lowest votes, backtrack further until only one candidate
            while len(indices) != 1:
                if roundCheck == -1:

                    done = True
                    break

                for i in range(0, len(indices)):
                    tempScoreList.append(prevRounds[roundCheck][indices[i]])


                if len(tempScoreList) == 2 and tempScoreList[0] != tempScoreList[1]:
                    if tempScoreList[0] > tempScoreList[1]:
                        indices = [indices[1]]
                        break

                    elif tempScoreList[0] < tempScoreList[1]:
                        indices = [indices[0]]
                        break
                else:
                    tempIndices = [j for j, x in enumerate(tempScoreList) if x == min(tempScoreList)]

                    for j in range(0, len(tempIndices)):
                        temp = tempIndices[j]
                        validTempIndices.append(indices[temp])

                    if roundCheck == 0 and len(validTempIndices) > 1:

                        done = True
                        break
                # Reset values for next backtrack
                indices = validTempIndices.copy()
                roundCheck -= 1
                tempScoreList.clear()
                tempIndices.clear()
                validTempIndices.clear()



    #Remove that candidate from the round list
    for j in range(len(roundList) - 1, -1, -1):
        for k in range(len(roundList[j]) - 1, -1, -1):

            if roundList[j][k] == indices[0]:


                del roundList[j][k]
                if len(roundList[j]) == 0:
                    roundList.remove(roundList[j])
                k -= 1
    #Add final points list to prev rounds
    prevRounds.append(pointsList.copy())
    # Create temporary variables to use for printing final output
    tempList = []
    tempWord = []
    print("Round", r + 1)

    # Process temporary list and sort appropriately(alphabetically and points wise)
    for i in range(0, len(pointsList)):
        if pointsList[i] != -1:
            tempList.append([pointsList[i],numberDict[i]])
            tempList.sort()
            tempList.reverse()
            tempWord.append(numberDict[i])

    maxWord = len(max(tempWord, key = len))
    tempList.sort(key=lambda x: x[1], reverse=True)
    tempList.sort(key=lambda x: x[0])
    tempList.reverse()

    # Print final result
    for i in range(0, len(tempList)):

        str = ""
        buffer = maxWord - len(tempList[i][1])
        for j in range(0, buffer):
            str += " "
        print(tempList[i][1],str, "",tempList[i][0])
    summ = 0
    for i in range(0, len(pointsList)):
        if pointsList[i] != -1:
            summ += pointsList[i]

    #if more than one candidate has half votes. declare them winnder
    if (max(pointsList) > (summ - max(pointsList))):
        winner = pointsList.index(max(pointsList))
        print("Winner:", numberDict[winner])
        exit(0)

    if done:
        print("Unbreakable Tie")
        exit(0)

    #if theres one person left. Declare them winnder
    if(len(tempList) == 1):
        winner = pointsList.index(max(pointsList))
        print("Winner:", numberDict[winner])
        exit(0)
    else:
        print("Eliminated:", numberDict[indices[0]])

    #set eliminated candidates to value -1 so they are ignored in points lists and not displayed
    print("")
    pointsList[indices[0]] = -1
    for i in range(0, len(pointsList) ):
        if pointsList[i] != -1:
            pointsList[i] = 0







