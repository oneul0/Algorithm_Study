class Solution {
  public String solution(String play_time, String adv_time, String[] logs) {
    int playSecTime = calcToSecTime(play_time);
    long[] timeStamp = new long[playSecTime + 1];

    for (String log : logs) {
      String[] times = log.split("-");
      int startTime = calcToSecTime(times[0]);
      int endTime = calcToSecTime(times[1]);
      timeStamp[startTime]++;
      timeStamp[endTime]--;
    }

    for (int i = 1; i <= playSecTime; i++) {
      timeStamp[i] += timeStamp[i - 1];
    }

    for (int i = 1; i <= playSecTime; i++) {
      timeStamp[i] += timeStamp[i - 1];
    }

    int adSecTime = calcToSecTime(adv_time);
    long result = timeStamp[adSecTime - 1];
    int idx = 0;

    for (int i = adSecTime; i <= playSecTime; i++) {
      long totalView = timeStamp[i] - timeStamp[i - adSecTime];
      if (totalView > result) {
        result = totalView;
        idx = i - adSecTime+1;
      }
    }

    return secTimeToFormattedTime(idx);
  }

  public int calcToSecTime(String time) {
    String[] hhMmSs = time.split(":");
    int hh = Integer.parseInt(hhMmSs[0]) * 3600;
    int mm = Integer.parseInt(hhMmSs[1]) * 60;
    int ss = Integer.parseInt(hhMmSs[2]);
    return hh + mm + ss;
  }

  public String secTimeToFormattedTime(long time) {
    long hh = time / 3600;
    time %= 3600;
    long mm = time / 60;
    long ss = time % 60;
    return String.format("%02d:%02d:%02d", hh, mm, ss);
  }
}
