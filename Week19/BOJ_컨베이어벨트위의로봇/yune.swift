struct Piece {
    init(duration: Int, hasRobot: Bool = false) {
        self.duration = duration
        self.hasRobot = hasRobot
    }
    var duration: Int
    var hasRobot: Bool
}

let nk = readLine()!.split { $0 == " " }.map { Int(String($0))! },
    n = nk[0],
    k = nk[1]
var conveyerBelt = readLine()!.split { $0 == " " }.map { Int(String($0))! }.map { Piece(duration: $0) }
func rotateConveyer() {
    conveyerBelt.insert(conveyerBelt.removeLast(), at: 0)
}
var answer = 0
var zeroBlankCount = 0

func countZeroBlank() {
    var count = 0
    conveyerBelt.forEach { blank in
        if blank.duration == 0 {
            count += 1
        }
    }
    zeroBlankCount = count
}
while zeroBlankCount < k {
    answer += 1
    rotateConveyer()
    if conveyerBelt[n-1].hasRobot {
        conveyerBelt[n-1].hasRobot = false
    }
    for i in stride(from: n-2, through: 0, by: -1) {
        if conveyerBelt[i].hasRobot && conveyerBelt[i+1].duration >= 1 && !conveyerBelt[i+1].hasRobot {
            conveyerBelt[i+1].duration -= 1
            conveyerBelt[i].hasRobot = false
            if i == n-2 {
                continue
            }
            conveyerBelt[i+1].hasRobot = true
        }
    }
    if conveyerBelt[0].hasRobot == false && conveyerBelt[0].duration > 0 {
        conveyerBelt[0].hasRobot = true
        conveyerBelt[0].duration -= 1
    }
    countZeroBlank()
}
print(answer)