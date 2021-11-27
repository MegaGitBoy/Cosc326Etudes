#Leo Venn, ID:3430125
#Valentin Kiselev, ID: 5844920
import sys
#List of the best anagram for a word
solvedList = []
#Index of when the sorted dictionary changes word lengths
wordLenIndexChange = []
#The word lengths inside the dictionary from largest to smallest
wordLen = []

#Together the two above lists tell you where the dictionary changes word lengths listed and what the length of the words changed to is

#Recursive function that finds the best anagram for a word
def solve(dictionary, word):

    indexStart = 0
    #Start searching for potential anagrams with the start index in the dictionary being the word length
    for i in range(0, len(wordLenIndexChange)):
        if wordLen[i] == len(word):
            indexStart = wordLenIndexChange[i]
    #if length of word is less than whats in dictionary then there is no solution
    if len(word) < wordLen[-1]:
        return 0

    #Test whether a word in dicionary is contained within the charachters ('word') passed by the function
    #if word chosen leads to a non solution at recursion depth, move on to next potential word
    for j in range(indexStart, len(dictionary)):
        if len(dictionary[j]) <= len(word):
            lettersMatched = 0
            #alteredWord is the charachters left over when a word from the dictionary has been found to be
            #contained in the 'word' passed and is the left over charachters that need to be solved
            alteredWord = word
            for i in range(0, len(dictionary[j])):
                if dictionary[j][i] in alteredWord:
                    alteredWord = alteredWord[:alteredWord.find(dictionary[j][i])] + alteredWord[alteredWord.find(dictionary[j][i]) + 1:]
                    lettersMatched += 1
                else:
                    break
            #if all charachters in dictionary word have been mapped to 'word'
            if lettersMatched == len(dictionary[j]):
                if len(alteredWord) == 0:
                    solvedList.append(dictionary[j])
                    return 1
                #left over charachters passed recursively to be solved
                if (solve(dictionary, alteredWord) == 1):
                    solvedList.append(dictionary[j])
                    return 1

    return 0


#word to print which is inclusive of spaces
wordPrint = []
#word to use to solve which is exclusive of spaces
wordUse = []
#The dictionary
dictionary = []

#read words to find anagrams for
while True:

    row = sys.stdin.readline()




    if row=="\n":
        break
    row = row.strip()
    wordPrint.append(row)
    row = row.replace(" ", "")
    for i in row:
        if not i.isalpha() or i.isupper():
            print(i)
            print("Invlid Input: Use only lower case letter charachters")
            exit(0)

    wordUse.append(row)

#read dictionary words
while True:

    row = sys.stdin.readline()


    row = row.strip()
    if row=="":
        break
    row = row.replace(" ", "")
    dictionary.append(row)

#sort dictionary by length and alphabetically
dictionary.sort()
dictionary.sort(key = len, reverse = True)

#create wordlen and wordLenChangeIndex list from dictionary
currentLength = -1
index = 0
for c in dictionary:

    if len(c) != currentLength:
        wordLen.append(len(c))
        wordLenIndexChange.append(index)
        currentLength = len(c)
    index += 1


index = 0
#For every word, solve the anagram and print the words out
for i in range(0, len(wordUse)):
    solve(dictionary, wordUse[i])
    solvedList.reverse()
    print(wordPrint[index] + ": ",  end ='')
    index += 1
    for c in solvedList:
        print(c + " ", end ='')
    solvedList.clear()
    if i != len(wordUse) - 1:
        print("")

