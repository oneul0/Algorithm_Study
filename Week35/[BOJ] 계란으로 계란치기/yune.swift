struct Egg: Hashable {
    var duration: Int
    let weight: Int
    var isBroken: Bool { duration <= 0 }
}

let n = Int(readLine()!)!
var eggs = (0..<n).map { _ in readLine()!.split { $0 == " " }.map { Int(String($0))! }}.map { Egg(duration: $0[0], weight: $0[1]) }
var isOnHand = [Bool](repeating: false, count: n)
var answer = 0

func hitEgg(_ current: Int) {
    
    let brokenEggCount = eggs.filter { $0.isBroken }.count
    if brokenEggCount > answer {
        answer = brokenEggCount
    }
    
    if current >= n {
        return
    }
    
    for next in 0..<n {
        if eggs[current].isBroken {
            hitEgg(current + 1)
        } else if next == current || eggs[next].isBroken {
            continue
        } else {
            eggs[current].duration -= eggs[next].weight
            eggs[next].duration -= eggs[current].weight
            hitEgg(current + 1)
            eggs[current].duration += eggs[next].weight
            eggs[next].duration += eggs[current].weight
        }
    }
}

isOnHand[0] = true
hitEgg(0)

print(answer)