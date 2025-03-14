import java.io.*;
import java.util.*;

public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
  static LinkedList<Belt> conveyor;
  static int N, K, round;

  public static void main(String[] args) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    conveyor = new LinkedList<>();

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < 2*N; i++) {
      conveyor.add(new Belt(Integer.parseInt(st.nextToken())));
    }

    while(K > 0){
      moveBelt();
      moveRobot();
    }
    bw.write(round + "");
    bw.flush();
    bw.close();
    br.close();

  }

  static void moveBelt(){
    round++;
    conveyor.add(0, conveyor.removeLast());
    if(conveyor.get(N-1).robot) conveyor.get(N-1).robot=false;
  }

  static void moveRobot() {
    for(int i =N-1; i>0; i--){
      if(!conveyor.get(i).robot) continue;
      if(conveyor.get(i+1).robot || conveyor.get(i+1).durability <= 0) continue;
      conveyor.get(i).robot=false;
      conveyor.get(i+1).placeRobot();
      if(conveyor.get(i+1).durability<=0) K--;

      if(i+1 == N-1) conveyor.get(i+1).robot=false;

    }
    if(conveyor.get(0).durability > 0){
      conveyor.get(0).placeRobot();
      if(conveyor.get(0).durability<=0) K--;
    }
  }

  static class Belt{
    int durability;
    boolean robot;
    public Belt(int durability){
      this.durability = durability;
      robot = false;
    }

    public void placeRobot(){
      robot = true;
      durability--;
    }
  }
}

