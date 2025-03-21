struct Dice {
    var right: Int = 0
    var left: Int = 0
    var up: Int = 0
    var down: Int = 0
    var front: Int = 0
    var rear: Int = 0
    private mutating func toLeft() {
        let nDown = left
        let nLeft = up
        let nUp = right
        let nRight = down
        down = nDown
        left = nLeft
        up = nUp
        right = nRight
    }
    private mutating func toRight() {
        let nDown = right
        let nLeft = down
        let nUp = left
        let nRight = up
        down = nDown
        left = nLeft
        up = nUp
        right = nRight
    }
    private mutating func toFront() {
        let nDown = front
        let nfront = up
        let nUp = rear
        let nRear = down
        down = nDown
        front = nfront
        up = nUp
        rear = nRear
    }
    private mutating func toRear() {
        let nDown = rear
        let nfront = down
        let nUp = front
        let nRear = up
        down = nDown
        front = nfront
        up = nUp
        rear = nRear
    }
    mutating func rollDice(_ dir: String.SubSequence) {
        switch dir {
        case "1":
            toRight()
        case "2":
            toLeft()
        case "3":
            toRear()
        default:
            toFront()
        }
    }
}



let nmxyk = readLine()!.split { $0 == " " }.map { Int(String($0))! },
    n = nmxyk[0],
    m = nmxyk[1],
    x = nmxyk[2],
    y = nmxyk[3],
    k = nmxyk[4]

func isMovable(_ y: Int, _ x: Int) -> Bool {
    return (0..<n).contains(y) && (0..<m).contains(x) ? true : false
}
var mapInfo = (0..<n).map { _ in readLine()!.split { $0 == " " }.map { Int(String($0))! } }
let dx: [String.SubSequence : Int] = [
          "1": 1,
          "2": -1,
          "3": 0,
          "4": 0 ]
let dy: [String.SubSequence : Int] = [
          "1": 0,
          "2": 0,
          "3": -1,
          "4": 1 ]


var curY = x
var curX = y
var answer = ""
var dice = Dice()
readLine()!.split{ $0 == " "}.forEach { c in
    let nx = curX + dx[c]!
    let ny = curY + dy[c]!
    guard isMovable(ny, nx) else { return }
    curX = nx
    curY = ny
    dice.rollDice(c)
    if mapInfo[ny][nx] == 0 {
        mapInfo[ny][nx] = dice.down
    } else {
        dice.down = mapInfo[ny][nx]
        mapInfo[ny][nx] = 0
    }
    answer.write("\(dice.up)\n")
}
print(answer)