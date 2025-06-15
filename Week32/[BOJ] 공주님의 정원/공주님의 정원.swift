import Foundation

struct Flower: Comparable {
    let start: Int
    let end: Int
    
    static func < (lhs:Flower, rhs: Flower) -> Bool {
        if lhs.start == rhs.start {
            return lhs.end < rhs.end
        }
        return lhs.start < rhs.start
    }
    
    static func == (lhs:Flower, rhs: Flower) -> Bool {
        if lhs.start == rhs.start && lhs.end == rhs.end { return true }
        return false
    }
}

func solution2457() -> Int {
    let startMaxDay = 0301
    let endMinDay = 1201
    
    let flowerCount = Int(String(readLine()!))!
    var flowers = [Flower]()
    
    (0..<flowerCount).forEach{ _ in
        let beginEndMD = readLine()!.split{ $0 == " " }.map{ Int(String($0))! }
        let start = (beginEndMD[0]*100) + beginEndMD[1]
        let end = (beginEndMD[2]*100) + beginEndMD[3]
        if (beginEndMD[2]*100) + beginEndMD[3] < startMaxDay { return }
        flowers.append(Flower(start: start, end: end))
    }
    
    guard !flowers.isEmpty else { return 0 }
    
    flowers.sort { $0 < $1 }
    
    guard flowers[0].start <= startMaxDay else { return 0 }
    
    var lastIdx = 0
    var curStart = 0301
    var curEnd = 0
    var matchFlowerCount = 0
    
    while curEnd < endMinDay {
        var isFound = false

        for idx in lastIdx..<flowers.count {
            if flowers[idx].start > curStart { break }
            if flowers[idx].end > curEnd {
                isFound = true
                lastIdx = idx + 1
                curEnd = flowers[idx].end
            }
        }
        guard isFound else { break }
        curStart = curEnd
        matchFlowerCount += 1
    }
    
    guard curEnd >= endMinDay else { return 0 }

    return matchFlowerCount
}

let answer = solution2457()
print(answer)
