import Foundation

class Node {
    var key: String
    var count: Int = 0 //하위 자식 개수
    var children: [String: Node] = [:]
    
    init(_ key: String) {
        self.key = key
    }
}

class Trie {
    var root: Node
    
    init() {
        self.root = Node("")
    }
    
    func insert(_ word: String) {
        var currentNode = self.root
        
        for char in word {
            let str: String = String(char)
            currentNode.count += 1
            if currentNode.children[str] == nil {
                currentNode.children[str] = Node(str)
            }
            
            currentNode = currentNode.children[str]!
        }
    }
    
    func search(_ query: String) -> Int {
        var currentNode = self.root        
        for char in query {
            let str = String(char)
            if str == "?" {
                return currentNode.count
            }
            
            if currentNode.children[str] == nil {
                return 0 //마지막 글자
            }
            
            currentNode = currentNode.children[str]!
        }
        
        return currentNode.count
    }
}

func solution(_ words:[String], _ queries:[String]) -> [Int] {
    var result: [Int] = []
    
    var tries: [Int: Trie] = [:]
    var reversedTries: [Int: Trie] = [:]
    
    for word in words {
       if tries[word.count] == nil {
           tries[word.count] = Trie()
           reversedTries[word.count] = Trie()
       }
        
       tries[word.count]?.insert(word)
       reversedTries[word.count]?.insert(String(word.reversed()))
    }
    
    for query in queries {
        var count = 0
        if query.hasPrefix("?") { //와일드카드가 접두사로
            if let reversedTrie = reversedTries[query.count] {
                let reversedQuery = String(query.reversed())
                count = reversedTrie.search(reversedQuery)
            }
        } else { //접미사로
            if let trie = tries[query.count] {
                count = trie.search(query)
            }
        }
        result.append(count)
    }
    
    // print("result: \(result)")
    return result
}