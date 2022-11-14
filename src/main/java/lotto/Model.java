package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import lotto.LottoEnum.LottoReward;

public class Model {
    /**
     * 1에서 45까지 서로 다른 임의의 수 6개의 로또 숫자를 생성한다.
     * @return 로또 숫자를 ArrayList로 return 한다.
     */
    private static List<Integer> CreateLottoNumber() {
        // 1에서 45까지 서로 다른 임의의 수 6개를 생성한다.
        List<Integer> randomNumber = Randoms.pickUniqueNumbersInRange(1, 45, 6);

        // 로또 번호는 오름차순으로 정렬한다.
        randomNumber = SortArrayList(randomNumber);

        return randomNumber;
    }

    /**
     * 1에서 45까지 수 중 로또 숫자와 중복되지 않는 보너스 번호 1개를 생성한다.
     * @return 로또 숫자를 Integer로 return 한다.
     */
    private static Integer CreateLottoBonusNumber(ArrayList<Integer> lottoNumber) {
        // 1에서 45까지 로또 숫자와 중복되지 않는 보너스 번호 1개를 생성한다.
        Integer randomNumber = Randoms.pickNumberInRange(1, 45);

        while (lottoNumber.contains(randomNumber)) {
            randomNumber = Randoms.pickNumberInRange(1, 45);
        }

        return randomNumber;
    }

    /**
     * 집합에 사용자가가 입력한 숫자를 넣는다.
     * @param playerNumbers 사용자가가 입력한 숫자
     * @return 입력한 숫자를 HashSet로 return 한다.
     */
    public static HashSet<Integer> MakeListToSet(List<Integer> playerNumbers) {
        HashSet<Integer> playerNumber = new HashSet<>();

        // 집합에 사용자가가 입력한 숫자를 넣는다.
        for (Integer I : playerNumbers) {
            playerNumber.add(I);
        }

        return playerNumber;
    }

    public static Integer MakeStringToInteger(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 입력된 값이 숫자가 아닙니다.");
        }
    }

    public static Integer CountLottoAmount(Integer purchaseAmount) {
        return purchaseAmount / 1000;
    }

    public static List<Integer> SortArrayList(List<Integer> arraylist) {
        Collections.sort(arraylist);
        return arraylist;
    }

    public static String[] SplitInput(String string) {
        return string.split(",");
    }

    public static List<Integer> MakeStringToListInteger(String[] strings) {
        try {
            List<Integer> result = new ArrayList<>();

            for (String str : strings) {
                result.add(Integer.parseInt(str));
            }

            return result;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 입력된 값이 숫자가 아닙니다.");
        }
    }

    public static List<List<Integer>> PublishLotto(Integer lottoAmount) {
        List<List<Integer>> lottoList = new ArrayList<>();

        for (int i = 1; i < lottoAmount+1; i++) {
            lottoList.add(CreateLottoNumber());
        }

        return lottoList;
    }

    public static Double CalculateEarningRate(Integer spending, Integer income) {
        Double incomeDouble= (double)income;
        Double spendingDouble= (double)spending;
        Double result = Math.round(incomeDouble / spendingDouble * 100 * 10) / 10.0;
        return result;
    }

    public static Integer CalculateEarningSum(LinkedHashMap<Integer, Integer> analyzedResult) {
        List<Integer> analyzedResultKeys = new ArrayList<>(analyzedResult.keySet());

        Integer sum = 0;

        for (int i = 1; i < 6; i++) {
            sum = sum + LottoReward.getRewardByRank(i) * analyzedResult.get(i);
        }

        return sum;
    }
}
