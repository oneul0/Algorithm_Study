let nld = readLine()!.split { $0 == " " }.map { Int(String($0))! }
var isPlaying = true
var time = -1
var currentMusicTime = 0
var currentBellTime = 0
var nextBellTime = 0
var currentPauseTime = 0

while true {
    time += 1
    if time >= (nld[0]*nld[1] + (nld[0]-1)*5) {
        isPlaying = false
    }
    if isPlaying == false && time == nextBellTime {
        break
    }
    if time >= (nld[0]*nld[1] + (nld[0]-1)*5) {
        continue
    }
    if isPlaying {
        currentMusicTime += 1
    }
    if time == nextBellTime {
        nextBellTime += nld[2]
    }
    if !isPlaying {
        currentPauseTime += 1
    }else if currentMusicTime % nld[1] == 0 {
        currentMusicTime = 0
        isPlaying = false
    }
    if currentPauseTime == 5 {
        currentPauseTime = 0
        isPlaying = true
    }
}
print(time)