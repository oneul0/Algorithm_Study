
score = {'R':0, 'T':0, 'C':0, 'F': 0 , 'J': 0, 'M': 0, 'A':0, 'N':0}

def cal_score(category, cur_score):
    first_category = category[0]
    second_category = category[1]

    if cur_score < 4:
        # 앞에 것 고른다
        score[first_category] += 4 - cur_score
    elif cur_score > 4:
        # 뒤에 것 고른다.
        score[second_category] += cur_score - 4



def solution(survey, choices):
    answer = ''

    # for 루프에서 survey와 choices를 동시에 순회하는 방식
    for s,c in zip(survey, choices):
        cal_score(s,c)

    keys = list(score.keys())

    for i in range(1, len(keys), 2):  # 두 칸씩 건너뛰며 탐색
        key1 = keys[i-1]
        key2 = keys[i]
        if key1 > key2:
            key1, key2 = key2, key1

        value1 = score[key1]
        value2 = score[key2]

        if value1 < value2:
            answer += key2
        else:
            answer += key1


    return answer
