let n = Int(readLine()!)!
var room = [[Int]](repeating: [Int](repeating: 0, count: n), count: n)
var student = [Int]()
var friendList = [[Int]]()
(0..<n*n).forEach { _ in
    let row = readLine()!.split { $0 == " " }.map { Int(String($0))! }
    student.append(row.prefix(1).first!)
    friendList.append([Int](row.suffix(4)))
}

for i in student.indices {
    let goodPlaceList = condition1(student: student[i], friendList: friendList[i])
    if goodPlaceList.count == 1 {
        let yx = goodPlaceList.first!, x = yx[1], y = yx[0]
        room[y][x] = student[i]
        continue
    }
    let nicePlaceList = condition2(goodPlaceList)
    if nicePlaceList.count == 1 {
        let yx = nicePlaceList.first!, x = yx[1], y = yx[0]
        room[y][x] = student[i]
        continue
    }
    let bestPlaceList = condition3(nicePlaceList)
    if bestPlaceList.count == 2 {
        let yx = bestPlaceList, x = yx[1], y = yx[0]
        room[y][x] = student[i]
        continue
    }
    print("WTF?")
}
let answer = calcSatisfaction(room, studentList: student, friendList: friendList)
print(answer)

func calcSatisfaction(_ room: [[Int]], studentList: [Int], friendList: [[Int]]) -> Int {
    var stuToIndex = [Int](repeating: 0, count: (n*n)+1)

    for i in studentList.indices {
        let student = studentList[i]
        stuToIndex[student] = i
    }
    
    var satisfaction = 0
    let satisfactionGrade = [0, 1, 10, 100, 1000]
    for y in (0..<n) {
        for x in (0..<n) {
            var friendCount = 0
            let student = room[y][x]
            let index = stuToIndex[student]
            if y-1 >= 0 { friendCount += friendList[index].contains(room[y-1][x]) ? 1 : 0}
            if y+1 < n { friendCount += friendList[index].contains(room[y+1][x]) ? 1 : 0}
            if x-1 >= 0 { friendCount += friendList[index].contains(room[y][x-1]) ? 1 : 0}
            if x+1 < n { friendCount += friendList[index].contains(room[y][x+1]) ? 1 : 0}
            satisfaction += satisfactionGrade[friendCount]
        }
    }
    return satisfaction
}

func condition1(student: Int, friendList: [Int]) -> [[Int]]{
    var valuedRoom = [[Int]](repeating: [Int](repeating: 0, count: n), count: n)
    var maximum = Int.min
    var maximumPlacList = [[Int]]()
    for y in (0..<n) {
        for x in (0..<n) {
            guard room[y][x] == 0 else { continue }
            if y-1 >= 0 { valuedRoom[y][x] += friendList.contains(room[y-1][x]) ? 1 : 0}
            if y+1 < n { valuedRoom[y][x] += friendList.contains(room[y+1][x]) ? 1 : 0}
            if x-1 >= 0 { valuedRoom[y][x] += friendList.contains(room[y][x-1]) ? 1 : 0}
            if x+1 < n { valuedRoom[y][x] += friendList.contains(room[y][x+1]) ? 1 : 0}
            if valuedRoom[y][x] > maximum {
                maximum = valuedRoom[y][x]
                maximumPlacList = [[y,x]]
                continue
            }
            if valuedRoom[y][x] == maximum {
                maximumPlacList.append([y,x])
            }
        }
    }
    return maximumPlacList
}

func condition2(_ placePos: [[Int]]) -> [[Int]]{
    var valuedRoom = [[Int]](repeating: [Int](repeating: 0, count: n), count: n)
    var maximum = Int.min
    var maximumPlacList = [[Int]]()
    for yx in placePos {
        let x = yx[1], y = yx[0]
        if y-1 >= 0 { valuedRoom[y][x] += (room[y-1][x] == 0) ? 1 : 0}
        if y+1 < n { valuedRoom[y][x] += (room[y+1][x] == 0) ? 1 : 0}
        if x-1 >= 0 { valuedRoom[y][x] += (room[y][x-1] == 0) ? 1 : 0}
        if x+1 < n { valuedRoom[y][x] += (room[y][x+1] == 0) ? 1 : 0}
        if valuedRoom[y][x] > maximum {
            maximum = valuedRoom[y][x]
            maximumPlacList = [[y,x]]
            continue
        }
        if valuedRoom[y][x] == maximum {
            maximumPlacList.append([y,x])
        }
    }
    return maximumPlacList
}

func condition3(_ placePos: [[Int]]) -> [Int] {
    var min = Int.max
    var minimumPlaceList = [[Int]]()
    for i in placePos.indices {
        let x = placePos[i][1], y = placePos[i][0]
        if y < min {
            min = y
            minimumPlaceList = [[y, x]]
            continue
        }
        if y == min {
            minimumPlaceList.append([y, x])
        }
    }
    guard minimumPlaceList.count > 1 else { return minimumPlaceList.first! }
    
    return minimumPlaceList.sorted { $0[1] < $1[1] }.first!
}