# https://www.acmicpc.net/problem/14890

# 입력받기
N, L = map(int, input().split())  # 첫 줄에서 N과 L 입력받기

# 지도 입력받기
map = [list(map(int, input().split())) for _ in range(N)]  # N개의 줄에서 높이 정보 입력받기

'''

경사로를 놓지 못하는 경우
1. 낮은 칸과 높은 칸의 높이차가 1이 아닌 경우
2. 경사로를 놓은 곳에 또 경사로 놓는 경우
3. 낮은 지점의 칸의 높이가 모두 같지 않거나, L개가 연속되지 않는경우
4. 경사로를 놓다가 범위를 벗어난 경우 (221 로 끝나면 안됨)

'''

def isRoad(line):
    incline = [False for _ in range(N)]

    for i in range(1, N):
        # 1번 경우
        if abs(line[i-1] - line[i]) > 1:
            return False
        else:
            if (line[i-1] - line[i]) == 1: # 오른 쪽으로 다리 놓기
                top = line[i] # 높이
                # 내리막이면 다음것을 탐색해야함
                for j in  range(i, i+L):
                    if j >= N :
                        return False
                    elif top != line[j]:
                        return False
                for j in  range(i, i+L): # 별 문제 없다면 다리 놓기
                    incline[j] = True

            elif (line[i-1] - line[i]) == -1:  # 왼쪽으로 다리 놓기
                # 오르막이면 이전 것을 탐색해야함
                top = line[i-1]
                for j in range(i-1, i-1-L, -1):
                    #  왼쪽으로 L만큼 탐색
                    if j<0:
                        # 범위를 벗어나면
                        return False
                    elif line[j] != top or incline[j]:
                        return False
                for j in range(i-1, i-1-L, -1): # 별 문제 없다면 다리 놓기
                    incline[j] = True

    return True


ans = 0
for i in range(N):
    if isRoad(map[i]):
        ans+=1

# 열 데이터 추출
for j in range(N):
    col = [map[i][j] for i in range(N)]
    if isRoad(col):
        ans+=1

print(ans)