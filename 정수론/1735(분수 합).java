/**
  *  문제 접근법: 분수의 합을 구하는 알고리즘을 만들면 된다. 주의할점은 기약 분수 형태로 출력해야 한다.
  *               우선 최대공약수를 구하기 위해 gcd method를 만들었다. gcd 메서드는 재귀 호출을 통해 최대 공약수를 구한다.
                  최소공배수를 구하기 위해  (첫 번째 분모* 두 번쨰 분모)/gcd(첫 번쨰 분모, 두 번째 분모)

                  분자들도 최소공배수를 이용해 통분한 분자 계산을 한다. (분자*(최소공배수 한 값/분모))

                  분수끼리 더해 새로운 분수가 나오면 새로운 분수를 기약 분수 형태로 만들기 위해 gcd를 적용시킨다.
                  gcd 반환 값으로 분자 분모를 나눈다.

      문제 정보: 1735번 분 수 합 실버3  
      알고리즘 분류: 수학, 정수론, 유클리드 호제법
    
 */ 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N<30000
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int firstNumerator;
        int firstDenominator;
        int secondNumerator;
        int secondDenominator;

        StringTokenizer st = new StringTokenizer(br.readLine());
        firstNumerator = Integer.parseInt(st.nextToken());
        firstDenominator = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        secondNumerator = Integer.parseInt(st.nextToken());
        secondDenominator = Integer.parseInt(st.nextToken());

        int N = gcd(firstDenominator, secondDenominator);

        int denominator = (firstDenominator * secondDenominator) / N;
        firstNumerator *= (denominator / firstDenominator);
        secondNumerator *= (denominator / secondDenominator);
        int numerator = (firstNumerator + secondNumerator);

        int lastNum = gcd(denominator, numerator);
        System.out.println(numerator / lastNum + " " + denominator / lastNum);
    }

    /**
     * 재귀 최대공약수
     * @param firstDenominator 첫 번째 분자
     * @param secondDenominator 두 번째 분자
     * @return 최대공약수
     */
    static int gcd(int firstDenominator, int secondDenominator) {
        if (firstDenominator < secondDenominator) {
            int temp = firstDenominator;
            firstDenominator = secondDenominator;
            secondDenominator = temp;
        }
        if (secondDenominator == 0) {
            return firstDenominator;
        } else {
            return gcd(secondDenominator, firstDenominator % secondDenominator);
        }
    }
}
