var s = -1
var t = -1
var wordsDupleCheck: Set<String> = []
var wordList = [String]()
var commonPrefixMap: [String.SubSequence: [Int]] = [:]

(0..<Int(readLine()!)!).forEach { index in
    let word = readLine()!
    if wordsDupleCheck.contains(word) { return }
    wordsDupleCheck.insert(word)
    for i in 0...word.count {
        if commonPrefixMap[word.prefix(i)] == nil { commonPrefixMap[word.prefix(i)] = [] }
        commonPrefixMap[word.prefix(i)]!.append(index)
    }
    wordList.append(word)
}

var answerList = [Int]()
var logestPrefixCount = Int.min
var answerCommonPrefix: String.SubSequence = ""

for commonPrefix in commonPrefixMap.keys {
    guard commonPrefixMap[commonPrefix]!.count > 1 else { continue }
    if commonPrefix.count == logestPrefixCount {
        answerList = commonPrefixMap[commonPrefix]!.first! < answerList.first! ? commonPrefixMap[commonPrefix]! : answerList
        answerCommonPrefix = commonPrefix
        continue
    }
    if commonPrefix.count > logestPrefixCount {
        answerList = commonPrefixMap[commonPrefix]!
        logestPrefixCount = commonPrefix.count
        answerCommonPrefix = commonPrefix
        continue
    }
}

print(wordList[answerList[0]])
print(wordList[answerList[1]])