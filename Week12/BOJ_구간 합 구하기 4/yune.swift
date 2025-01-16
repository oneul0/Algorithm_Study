let nm = readLine()!.split { $0 == " " }.map { Int(String($0))! }
var arr = [0] + readLine()!.split { $0 == " " }.map { Int(String($0))! }
(1..<arr.count).forEach { i in
    arr[i] = arr[i-1] + arr[i]
}
var answer = ""
(0..<nm[1]).forEach { _ in
    let ij = readLine()!.split { $0 == " " }.map { Int(String($0))! }
    answer.write("\(arr[ij[1]] - arr[ij[0]-1])\n")
}
print(answer)