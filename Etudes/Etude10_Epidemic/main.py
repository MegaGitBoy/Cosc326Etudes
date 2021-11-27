
import sys
immuneOrSick = "IS"
def solve(universe):
    #String to compare old universe to current. If no changes then simulation done
    universeString = ""
    newUniverseString = ""

    #Copy universe into string
    for i in range(len(universe)):
        universeString += (universe[i])
    #Run evolutions of world until finished
    while True:
        #For each non-sick non immume check if they become sick
        for i in range(len(universe)):
            for j in range(len(universe[0])):
                sickNeighbour = 0
                if universe[i][j] == ".":
                    #Check not at top of world
                    if i != 0:
                        if universe[i - 1][j] == "S":
                               sickNeighbour += 1
                    #Check not at bottem of world
                    if i != (len(universe) - 1):
                        if universe[i + 1][j] == "S":
                               sickNeighbour += 1
                    #Check if not at left side of world
                    if j != 0:
                       if universe[i][j - 1] == "S":
                           sickNeighbour += 1
                    #Check if not at right side of world
                    if j != len(universe[0]) - 1:
                       if universe[i][j + 1] == "S":
                           sickNeighbour += 1
                #If neighbours of sick greater than 2 then become sick
                if sickNeighbour > 1:
                    universe[i] = universe[i][:j] + "S" + universe[i][j + 1:]


        #Store new universe in string
        for i in range(len(universe)):
            newUniverseString += (universe[i])


        #Check if universes are the same (finished simulation)
        if universeString == newUniverseString:

            for i in range(len(universe)):
                print(universe[i])
            print("")
            return
        #If not finished simulations. Set universe to current and reset new universe
        universeString = newUniverseString
        newUniverseString = ""






universe = []
for row in sys.stdin:
    #Check if line isnt a blank or the end of the file
    if row != "\n" and row[-1] == "\n":
        row = row.replace("\n", "")
        universe.append(row)
    #If row is end of file
    elif row[-1] != "\n":
        universe.append(row)
        solve(universe)
        universe = []
    #Else newline reached ane universe read in
    else:
        solve(universe)
        universe = []







