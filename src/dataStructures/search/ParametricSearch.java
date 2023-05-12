package dataStructures.search;

import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b>이분 탐색 응용 - 파라메트릭 서치란?</b><br/>
 * 어떤 값이 존재하는 위치를 O(log N)의 시간복잡도로 탐색하는 이분 탐색과<br/>
 * 어떤 조건을 만족하는 최소/최대 값의 위치를 O(log N)의 시간복잡도로 탐색하는 파라메트릭 서치<br/>
 *  - 차이점은? 최적화 문제를 결정조건으로 바꾸어 주어진 문제를 해결한다는 점<br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 1. 결정 문제로 바꾸자; 해당 요소들이 결정 조건을 만족하는지 이분 탐색 과정 중에 판별하자<br/>
 * 2. 최적화 조건에 맞춰 이분 탐색을 진행하자; <br/>
 *  2-1. 이 때 적절하게 범위를 조절하며 진행하게 됨<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 어떤 조건를 만족하는 가장 큰 수 개념 학습 및 구현<br/>
 * 어떤 조건를 만족하는 가장 작은 수 개념 학습 및 구현<br/>
 * --------------------------------------------------------------<br/>
 */
public class ParametricSearch {
    // 특정 조건보다 가장 큰 수를 찾는 방법 구현
    public void searchBiggestOver(int[] arr, int key) {
        Arrays.sort(arr);
        System.out.println("정렬 후 : " + Arrays.toString(arr));

        int start = 0;
        int end = arr.length;
        int resultIdx = -1;

        while(start < end - 1) { // [start, end)로 해야 무한루프를 돌지 않음
            int mid = (start + end) / 2;
            int value = arr[mid];

            if(key > value) { // key보다 작은 value (조건을 만족하지 않음)
                start = mid + 1;
            } else if(key < value) { // key보다 큰 value (조건 만족, k 이상인 값 True)
                // mid를 포함하여 좁힌다
                // 결정 조건을 만족하더라도 더 큰 값이 있을 수 있으므로;
                start = mid;
            } else {
                resultIdx = mid;
                break;
            }
        }

        if(resultIdx < 0) {
            System.out.println(resultIdx);
            resultIdx = start; // 결정됨
        }

        System.out.printf("조건을 만족하는 최대값을 갖는 인덱스 : %d, 값 : %d\n", resultIdx, arr[resultIdx]);
    }

    // 정수 key 이상인 수 중에 가장 작은 값을 구하는 메소드
    public void searchLowestOver(int[] arr, int key) {
        Arrays.sort(arr);
        System.out.println("정렬 후 : " + Arrays.toString(arr));

        int start = 0;
        int end = arr.length - 1;

        while(start < end) {
            int mid = (start + end) / 2;
            int value = arr[mid];

            if(value < key) { // key가 현재 중앙값보다 클 때 (조건 False)
                start = mid + 1;
            } else { // key보다 현재 중앙값이 클 때 (조건 true) value >= key;
                // 그러한 true 조건들 중에 작은 값을 찾아야 하므로 범위를 좁혀간다.
                // 이 때 mid를 포함. 결정 조건을 만족하더라도 더 작은 위치에 있는 값이 있을 수 있으므로;
                // 새로운 범위 [start, end]
                end = mid;
            }
        }

        int resultIdx = start;
        System.out.printf("조건을 만족하는 최솟값을 갖는 인덱스 : %d, 값 : %d\n", resultIdx, arr[resultIdx]);
    }

    public static void main(String[] args) {
        // test01();
        test02();
    }

    static void test01() {
        ParametricSearch test01 = new ParametricSearch();
        int[] arr = {1, 4, 5129, 12, 1320, 44, 55, 2, 6, 7, 8};
        int key = 10;
        System.out.printf("%d 보다 큰 수 중 최대/최소값을 구하라\n", key);
        test01.searchBiggestOver(arr, key); // 5129
        test01.searchLowestOver(arr, key);
    }

    static void test02() {
        ParametricSearch test01 = new ParametricSearch();
        int[] arr = {50, 80, 150, 150, 210, 260};
        int key = 150;
        System.out.printf("%d 보다 큰 수 중 최대/최소값을 구하라\n", key);
        test01.searchBiggestOver(arr, key);
        test01.searchLowestOver(arr, key);
    }
}
