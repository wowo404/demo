package org.liu.interest;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created   by:  wenbi.chen
 * Created Time:  2017-12-22 11:10 PM
 * Description:
 **/
public class TestDebtMatch {

    private final static ArrayList<Integer> investList = new ArrayList<Integer>();
    private final static LinkedBlockingDeque<Integer> debtList = new LinkedBlockingDeque<Integer>();
    static Integer debtId = 0;

    public static void main(String[] args) {

        investList.add(7);
        investList.add(10);
        investList.add(2);
        investList.add(7);


        System.out.println(investList);

        debtList.add(5);
        debtList.add(10);
        debtList.add(3);



        Integer debt = debtList.pollFirst();
        debtId = debt;
        Integer restInvest = investList.get(0);
        Integer restDebt = debt;

        for (Integer invest : investList) {

            restInvest = invest;

            while ((restDebt != null) && (restInvest > restDebt)) {
                restInvest = restInvest - restDebt;
                System.out.println("1  投资 " + invest + "   已投资:" + restDebt + "   资产：" + debtId + "  剩余资产：" + 0);
                restDebt = debtList.pollFirst();
                debtId = restDebt;
                if (restDebt == null) {
                    break;
                }
            }

            if (debtId != null) {
                if (restInvest == restDebt) {
                    System.out.println("2 投资 " + invest + "   已投资:" + restInvest + "   资产：" + debtId + "  剩余资产：" + 0);
                    restDebt = debtList.pollFirst();
                    debtId = restDebt;
                    continue;
                }

                if (restInvest < restDebt) {
                    restDebt = restDebt - restInvest;
                    System.out.println("3 投资 " + invest + "   已投资:" + restInvest + "  资产：" + debtId + "  剩余资产：" + restDebt);
                    continue;

                }

            } else {

                System.out.println("4  投资 " + invest + "   已投资:" + 0 + "  资产：" + 0);
            }
        }

        while ((restDebt = debtList.pollFirst()) != null) {
            System.out.println("5  投资 " + 0 + "   已投资:" + 0 + "  资产：" + restDebt + "  剩余资产：" + restDebt);

        }

    }
}
