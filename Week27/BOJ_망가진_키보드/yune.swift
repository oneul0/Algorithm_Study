var totalAnswer = ""
while let m = readLine() {
    if m == "0" { break }
    let m = Int(m)!
    let str = readLine()!.map { String($0) }
    
    var start = 0
    var end = 0
    var answer = 0
    var keyCountMap = [String:Int]()
    
    while end < str.count {
        let key = str[end]
        if keyCountMap.count >= m {
            //마지막 문자열을 키맵에 넣어야하는데 키맵슬롯이 꽉 찬 경우
            // 마지막 문자열을 넣지 않고 계속 첫 문자열, start를 제거한다.
            if keyCountMap[key] == nil {
                if keyCountMap[str[start]]! == 1 {
                    keyCountMap.removeValue(forKey: str[start])
                } else {
                    keyCountMap[str[start]]! -= 1
                }
                start += 1
            } else {
                // 마지막 문자열을 키맵에 추가하지 않아도 될 경우(이미 키맵에 있는 경우)
                // 키카운트만 늘린다.
                keyCountMap[key]! += 1
                end += 1
            }
        } else {
            if keyCountMap[key] == nil {
                keyCountMap[key] = 1
            } else {
                keyCountMap[key]! += 1
            }
            end += 1
        }
        if keyCountMap.count <= m { answer = max(answer, end - start) }
    }
    totalAnswer.write("\(answer)\n")
}
print(totalAnswer)