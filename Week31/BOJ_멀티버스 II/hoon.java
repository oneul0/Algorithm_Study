import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 각 우주의 행성 크기 정보를 저장할 리스트의 리스트
	static List<List<Integer>> universes = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());

		int m = Integer.parseInt(st.nextToken()); // 우주의 개수
		int n = Integer.parseInt(st.nextToken()); // 각 우주에 있는 행성의 개수

		// m개의 우주 각각에 대해 행성 크기 정보를 입력받고 좌표 압축 수행
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			List<Integer> currentUniverse = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				int size = Integer.parseInt(st.nextToken());
				currentUniverse.add(size);
			}
			// 현재 우주의 행성 크기 리스트에 대해 좌표 압축 수행
			compressCoordinates(currentUniverse);
			universes.add(currentUniverse); // 압축된 결과를 저장
		}

		int ans = 0;
		// 두 우주가 균등한지 확인 (조합 nC2)
		for (int i = 0; i < m - 1; i++) {
			for (int j = i + 1; j < m; j++) {
				// 두 우주의 행성 순위 리스트가 같은지 비교
				if (universes.get(i).equals(universes.get(j))) {
					ans++;
				}
			}
		}

		System.out.println(ans);
	}

	public static void compressCoordinates(List<Integer> v) {
		// 1. 원본 리스트를 복사하여 정렬 및 중복 제거
		List<Integer> tmp = new ArrayList<>(v);
		Collections.sort(tmp);

		// 중복 제거 (LinkedHashSet을 사용하여 순서 유지하며 중복 제거)
		Set<Integer> distinctElements = new LinkedHashSet<>(tmp);
		tmp.clear();
		tmp.addAll(distinctElements);

		// 2. 원본 리스트의 각 요소를 압축된 값으로 대체
		for (int i = 0; i < v.size(); i++) {
			// tmp 리스트에서 v.get(i) 값의 첫 번째 등장 위치(인덱스)를 찾음
			int idx = Collections.binarySearch(tmp, v.get(i));
			v.set(i, idx); // 해당 인덱스를 압축된 값으로 사용
		}
	}
}