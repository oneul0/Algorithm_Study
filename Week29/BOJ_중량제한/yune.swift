import Foundation

class FileIO {
    @inline(__always) private var buffer: [UInt8] = Array(FileHandle.standardInput.readDataToEndOfFile()) + [0], byteIdx = 0
    
    @inline(__always) private func readByte() -> UInt8 {
        defer { byteIdx += 1 }
        return buffer.withUnsafeBufferPointer { $0[byteIdx] }
    }
    
    @inline(__always) func readInt() -> Int {
        var number = 0, byte = readByte(), isNegative = false
        while byte == 10 || byte == 32 { byte = readByte() }
        if byte == 45 { byte = readByte(); isNegative = true }
        while 48...57 ~= byte { number = number * 10 + Int(byte - 48); byte = readByte() }
        return number * (isNegative ? -1 : 1)
    }
}
struct Bridge {
    let from: Int
    let to: Int
    let weight: Int
}
let io = FileIO()

let n = io.readInt(),
    m = io.readInt()

var parent = Array(0...n)

let bridgeList: [Bridge] = (0..<m).map { _ in
    let from = io.readInt()
    let to = io.readInt()
    let weight = io.readInt()
    return Bridge(from: from, to: to, weight: weight)
}.sorted { $0.weight > $1.weight }

let start = io.readInt()
let end = io.readInt()

func find(_ v: Int) -> Int {
    if v == parent[v] { return v }

    parent[v] = find(parent[v]);
    return parent[v];
}

func union(_ u: Int, _ v: Int){
    var u = u
    var v = v
    
    if v > u { swap(&u, &v) }
    
    u = find(u)
    v = find(v)
    
    if(u == v) { return }
    
    if(u > v) {
        parent[u] = v
    }
    else{
        parent[v] = u
    }
}

for bridge in bridgeList {
    union(bridge.from, bridge.to);
    
    if(find(start) == find(end)){
        print(bridge.weight)
        break
    }
}