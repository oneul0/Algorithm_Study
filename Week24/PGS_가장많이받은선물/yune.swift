import Foundation

func solution(_ friends:[String], _ gifts:[String]) -> Int {
    var willInMap = friends.reduce(into: [String: Int]()) {
        $0[$1] = 0
    } // 선물 받을 갯수
    var countMap = [String: Int]()
    var outCountMap = willInMap
    var inCountMap = willInMap
    gifts.forEach { ab in
        if countMap[ab] == nil {
            countMap[ab] = 0
        }
        countMap[ab]! += 1
        let ba: [String] = ab.split { $0 == " " }.map { String($0) }.reversed()
       if countMap[ba[0] + " " + ba[1]] == nil {
           countMap[ba[0] + " " + ba[1]] = 0
       }
        outCountMap[ba[1]]! += 1
        inCountMap[ba[0]]! += 1
    }
    
    var checkMap = Set<String>()
    
    for a in friends {
        for b in friends {
            let ab = a + " " + b
            let ba = b + " " + a
            if checkMap.contains(ab) || checkMap.contains(ba) { continue }
            //기록이 없거나 양쪽이 같을 때
            if (countMap[ab] == nil && countMap[ba] == nil) || (countMap[ab]! == countMap[ba]!) {
                let aPoint = outCountMap[a]! - inCountMap[a]!
                let bPoint = outCountMap[b]! - inCountMap[b]!
                if aPoint == bPoint { 
                    checkMap.insert(ab)
                    checkMap.insert(ba)
                    continue
                }
                let nextTaker = aPoint > bPoint ? a : b
                willInMap[nextTaker]! += 1
            } else { //기록이 있고, 양쪽이 다를 때
                let aPoint = countMap[ab]!
                let bPoint = countMap[ba]!
                let nextTaker = aPoint > bPoint ? a : b
                willInMap[nextTaker]! += 1
            }
            checkMap.insert(ab)
            checkMap.insert(ba)
        }
    }
    let ret = willInMap.values.max()!
    
    return ret
}