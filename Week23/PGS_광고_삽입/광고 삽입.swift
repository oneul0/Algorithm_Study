import Foundation

extension String {
    func toSecond() -> Int {
    // HH:MM:SS -> Int
    let hhmmss = self.split { $0 == ":"}.map { Int(String($0))! }
    let pointAsSecond = hhmmss[0] * 3600 + hhmmss[1]*60 + hhmmss[2]
    return pointAsSecond
    }
}

extension Int {
    func toHHMMSS() -> String {
        // Int -> HH:MM:SS
        var time = self
        let hh = time / 3600
        time %= 3600
        let mm = time / 60
        time %= 60
        return String(format: "%02d:%02d:%02d", hh, mm, time)
    }
}


func solution(_ play_time:String, _ adv_time:String, _ logs:[String]) -> String {
    let advTimeScala = adv_time.toSecond()
    let playTimeScala = play_time.toSecond()

    var timeline = [Int](repeating: 0, count: playTimeScala+1)
    
    for log in logs {
        let startEnd = log.split { $0 == "-" }.map { String($0) }
        let start = startEnd[0].toSecond()
        let end = startEnd[1].toSecond()
        timeline[start] += 1
        timeline[end] -= 1
    }
    // 누적 진입 횟수를 구한다.
    for i in 1..<timeline.count {
        timeline[i] += timeline[i-1]
    }
    //누적 재생 횟수를 구한다.
    for i in 1..<timeline.count {
        timeline[i] += timeline[i-1]
    }
    
    var minTime = 0
    var maxSum = timeline[advTimeScala - 1]
    
    for i in 1..<timeline.count - advTimeScala {
        let sum = timeline[i+advTimeScala-1] - timeline[i-1]
        if sum > maxSum {
            maxSum = sum
            minTime = i
        }
    }
    
    return minTime.toHHMMSS()
}