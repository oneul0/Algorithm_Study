import Foundation

// MARK: - FileIO

//func stringToAscii(_ str: String) -> Int {
//    str.map { $0.asciiValue! }.map { Int($0) }.reduce(0) {$0 + $1}
//}

struct FileIO {
    private let buffer:[UInt8]
    private var index: Int = 0
    
    init(fileHandle: FileHandle = FileHandle.standardInput) {
        
        buffer = Array(try! fileHandle.readToEnd()!)+[UInt8(0)] // 인덱스 범위 넘어가는 것 방지
    }
    
    @inline(__always) private mutating func read() -> UInt8 {
        defer { index += 1 }
        
        return buffer[index]
    }
    
    @inline(__always) mutating func readInt() -> Int {
        var sum = 0
        var now = read()
        var isPositive = true
        
        while now == 10
                || now == 32 { now = read() } // 공백과 줄바꿈 무시
        if now == 45 { isPositive.toggle(); now = read() } // 음수 처리
        while now >= 48, now <= 57 {
            sum = sum * 10 + Int(now-48)
            now = read()
        }
        
        return sum * (isPositive ? 1:-1)
    }
    
    mutating func readIntArray(_ K: Int) -> [Int] {
        var array = [Int]()
        
        for _ in 0..<K {
            array.append(readInt())
        }
        
        return array
    }
    
    @inline(__always) mutating func readString() -> String {
        var now = read()
        
        while now == 10 || now == 32 { now = read() } // 공백과 줄바꿈 무시
        let beginIndex = index-1
        
        while now != 10,
              now != 32,
              now != 0 { now = read() }
        
        return String(bytes: Array(buffer[beginIndex..<(index-1)]), encoding: .ascii)!
    }
    
    @inline(__always) mutating func readLine() -> String {
        var now = read()
        
        while now == 10 { now = read() } // 공백과 줄바꿈 무시
        let beginIndex = index-1
        
        while now != 10,
              now != 0 { now = read() }
        
        return String(bytes: Array(buffer[beginIndex..<(index-1)]), encoding: .ascii)!
    }
    
    @inline(__always) mutating func readString() -> Int {
        var str = 0
        var now = read()
        while now == 10 || now == 32 { now = read() } // 공백과 줄바꿈 무시
        while now != 10 && now != 32 && now != 0 {
            str += Int(now)
            now = read()
        }
        return str
    }
    
    mutating func readStringArray(_ K: Int) -> [String] {
        var array = [String]()
        
        for _ in 0..<K {
            array.append(readString())
        }
        
        return array
    }
    
    @inline(__always) mutating func readByteSequenceWithoutSpaceAndLineFeed() -> [UInt8] {
        var now = read()
        
        while now == 10 || now == 32 { now = read() } // 공백과 줄바꿈 무시
        let beginIndex = index-1
        
        while now != 10,
              now != 32,
              now != 0 { now = read() }
        
        return Array(buffer[beginIndex..<(index-1)])
    }
}

//    while !((0..<n)~=idx) { //인덱스가 0..<n에 속하지 않을 때 까지, 즉 0..<n의 범위에 들어올 때 까지
//        idx = (idx+n) % n
//    } //양수인 n에 대하여, 인덱스가 음수이면 인덱스 % n 은 음수인 인덱스를 그대로 반환한다.
//deque 쓰는것 보다 insert 쓰는게 훨 빠른듯,,ㅠ
// let (N, P) = (io.readInt(), io.readInt())
// 동시에 두 변수 입력받을 수 있음.
// [].allsatisfy { 만족해야 할 조건문 }
// 열고 닫는 괄호가 있고, 이를 제외하고는 stack에서 숫자만 사용해도 좋은 경우, 괄호를 유니크한 수(ex:0)으로 취급하여 괄호와 숫자를 구분할 수 있음
// 반복문 앞에 outer: 등으로 라벨링을 하면 continue outer등으로 다중 반복문일 때 해당 라벨의 다음 반복으로 넘어감

var file = FileIO()

var conferences: [[Int]] = []
var conferenceCount = 0
for _ in 0..<file.readInt(){
    let (start,end) = (file.readInt(),file.readInt())
    conferences.append([start, end])
}
func compare(a: [Int], b: [Int])->Bool{
    if a[1] < b[1] {return true}
    if a[1] == b[1]{
        if a[0] < b[0] { return true }
    }
    return false
}
conferences.sort(by: compare)
var lastEnd = 0
for conf in conferences{
    if lastEnd <= conf[0] {
        conferenceCount += 1
        lastEnd = conf[1]
    }
}
print(conferenceCount)
