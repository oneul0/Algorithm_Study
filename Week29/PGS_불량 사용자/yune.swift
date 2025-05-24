import Foundation

func solution(_ user_id:[String], _ banned_id:[String]) -> Int {

    var ids: [[String]] = Array(repeating: [], count: banned_id.count)
    
    for (bIndex, banned) in banned_id.enumerated() {
        //dot 위치 구하기
        var dotIndex: [Bool] = Array(repeating: false, count: banned.count)
        for (i, ch) in banned.enumerated() {
            dotIndex[i] = (ch == "*")
        }
        
        //제재 아이디 구하기
        for user in user_id {
            if banned.count != user.count {
                continue
            }
            
            let userString = user.enumerated().map { (index, ch)->String in
                return dotIndex[index] ? "*" : String(ch)
            }.joined()
            
            if userString == banned {
                ids[bIndex].append(user)
            }
        }
    }
    
    var results: Set<[String]> = []
    
    //조합 구하기
    func banResult(banIds: Set<String>, index: Int) {
        for bannedId in ids[index] {
            var newBanIds = banIds
            newBanIds.insert(bannedId)
            
            if newBanIds.count == ids.count {
                results.insert(newBanIds.sorted())
            } else if index+1 < ids.count {
                banResult(banIds: newBanIds, index: index+1)
            }
        }
    }
    
    banResult(banIds: [], index: 0)
    
    return results.count
}