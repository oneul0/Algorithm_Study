# 녹색 옷은 입은 애가 젤다지? => 다익스트라
import heapq

# 상하 좌우 방향 정의
direction = [(-1, 0), (1, 0), (0, -1), (0, 1)]

test_cases = []
total_luffy = 0

# 값을 입력 받기(N == 0 이면 종료)
while True:
    N = int(input())
    if N == 0:
        break
    # 동굴의 각 칸에 있는 도둑루피 크기 입력 받기
    cave=[]
    for _ in range(N):
        row = list(map(int, input().split()))
        cave.append(row)

    # 각 테스트 케이스를 리스트에 저장
    test_cases.append(cave)


def dijkstra(array):
    n = len(array)

    # 소모 되는 루피
    rupee = [ [float('inf')] * n for _ in range(n) ]
    rupee[0][0] = array[0][0]

    # 우선 순위 큐 사용
    queue = []
    heapq.heappush(queue, (array[0][0],0 ,0)) #(소요시간, row, col)

    while queue:
        current_time, curr_x, curr_y = heapq.heappop(queue)

        # 이미 최소 시간이 기록된 경우를 초과
        if current_time > rupee[curr_x][curr_y]:
            return

        # 목표 지점에 도달 시 최소 시간을 반환
        if (curr_x, curr_y) == (n-1,n-1) :
            return current_time

        # 상하 좌우로 이동
        for dx, dy in direction:
            nx, ny = curr_x+dx, curr_y+dy
            if 0 <= nx <n and 0 <= ny <n :
                new_time = current_time + array[nx][ny]

                # 최소 시간을 갱신
                if new_time < rupee[nx][ny]:
                    rupee[nx][ny] = new_time
                    heapq.heappush(queue, ( new_time , nx, ny))

    return None;


### enumerate()는 Python 내장 함수로, 반복 가능한 객체(리스트, 튜플 등)를 순회하면서 인덱스와 값을 동시에 가져올 수 있도록 도와주는 함수
for idx, row in enumerate(test_cases, 1):
    ans = dijkstra(row)
    print(f"Problem {idx}: {ans}")
