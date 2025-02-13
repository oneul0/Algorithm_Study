import Foundation

func solution(_ coin:Int, _ cards:[Int]) -> Int {
    let n = cards.count + 1

    var remainCoin = coin
    var roundCount = 1
    var deck: [Int] = cards.reversed()
    var hands: Set<Int> = (0..<n/3).reduce(into: Set<Int>()) { result, _ in
        let card = deck.popLast()!
        result.insert(card)
     }
    var holdCards: Set<Int> = []

    game: while !deck.isEmpty {
        let newCards = (0..<2).map { _ in deck.popLast()! }
        newCards.forEach { card in holdCards.insert(card) }
        
        for card in hands {
            guard hands.contains(n - card) else { continue }
            hands.remove(n - card)
            hands.remove(card)
            roundCount += 1
            continue game
        }
        
        for card in holdCards {
            guard remainCoin > 0 else { break }
            guard hands.contains(n - card) else { continue }
            hands.remove(n - card)
            holdCards.remove(card)
            remainCoin -= 1
            roundCount += 1
            continue game
        }
        
        for card in holdCards {
            guard remainCoin > 1 else { break }
            guard holdCards.contains(n - card) else { continue }
            holdCards.remove(n - card)
            holdCards.remove(card)
            remainCoin -= 2
            roundCount += 1
            continue game
        }
        break
    }
    return roundCount
}