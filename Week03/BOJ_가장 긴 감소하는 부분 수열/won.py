'''
가장 긴 감소하는 부분 수열
dp 배열을 생성한다.
numbers에서 1 ~ 끝번 까지의 인덱스 탐색
idx = 5이면 numbers[5]와 numbers[0] ~ numbers[4]을 비교한다.
numbers[5] 보다 큰 값이 존재하고 그 인덱스가 3이면
dp[5] = max(dp[5], dp[3]+1) 이런식으로 dp 배열을 완성해나감.
최종 답: dp 배열에서 가장 큰 값
'''

# 첫 번째 입력: 수열의 크기
N = int(input())

# 두 번째 입력: 수열을 이루고 있는 것
numbers = list(map(int, input().split()))

dp = [1] * N

for i in range(1, N):
    for k in range(0,i):
        if(numbers[k] > numbers[i]):
            dp[i] = max(dp[i], dp[k]+1)



# 결과 출력
print(max(dp))
