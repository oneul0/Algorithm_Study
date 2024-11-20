# https://www.acmicpc.net/problem/12015
# 50 min

import bisect
N = int(input())

# 두 번째 입력: 수열을 이루고 있는 것
numbers = list(map(int, input().split()))

# 정답 배열
ans = [numbers[0]]  # 첫 번째 값만 포함


# 값 추가
for i in range(1, len(numbers)):
    x = numbers[i]

    # 들어갈 위치 찾기 (왼쪽 기준)
    pos = bisect.bisect_left(ans, x)

    # 리스트에 값 삽입
    if pos >= len(ans):
        ans.append(x)  # 리스트에 추가
    else:
        ans[pos] = x  # 기존 값을 갱신

print(len(ans))