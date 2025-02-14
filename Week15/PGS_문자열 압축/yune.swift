import Foundation

func subString(_ from: Int, _ to: Int, _ s: String, _ count: Int) -> Substring.SubSequence {
    return s.suffix(count-from).prefix(to-from+1)
}

func solution(_ s:String) -> Int {
    let wordSize = s.count
    if wordSize == 1 { return 1 }
    var answer = wordSize
    for windowSize in 1...(s.count/2) {
        var subCount = [Int]()
        var partialCount = 0
        var window = s.prefix(windowSize)
        for i in stride(from: 0, to: wordSize, by: windowSize) {
            let partialString = subString(i, i+windowSize-1, s, wordSize)
            if window == partialString {
                partialCount += 1
            }
            if (window != partialString) || ((i + windowSize) >= wordSize) {
                if partialCount > 1 {
                    subCount.append(partialCount - 1)
                    window = partialString
                    partialCount = 1
                } else {
                    window = partialString
                    partialCount = 1
                }
            }
        }
        // 10 이상이면 줄이고, 2 더해야 함
        // 100 이상이면, 줄이고 3 더해야 함
        // go on,,,
        print(subCount)
        let subtract: Int = subCount.map { 
            var digit = 1
            if $0+1 >= 10 { digit += 1 }
            if $0+1 >= 100 { digit += 1 }
            if $0+1 >= 1000 { digit += 1 }
            return $0*windowSize - digit
        }.reduce(0, +)
        answer = min(answer, wordSize - subtract)
        subCount = []
        partialCount = 0
    }
    
    return answer
}