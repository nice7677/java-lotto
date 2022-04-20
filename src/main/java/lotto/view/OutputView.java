package lotto.view;

import lotto.model.LottoTicket;
import lotto.model.Money;
import lotto.model.RankResult;
import lotto.model.RankResults;

import java.util.List;

public class OutputView {

    private static final String RESULT_STRATEGY_MESSAGE_FORMAT = "%d개 일치 (%d원)- %d개\n";

    private static final String PROFIT_RATIO_MESSAGE_FORMAT = "총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

    private static final String TICKET_COUNT_MESSAGE_FORMAT = "%d개를 구매하였습니다.\n";

    private static final String WINNING_STRATEGY_MESSAGE = "당첨 통계";

    private static final String WINNING_SEPARATION_MESSAGE = "---------";

    private static final String NEXT_LINE = "\n";


    public void printTicketCount(int ticketCount) {
        System.out.printf(TICKET_COUNT_MESSAGE_FORMAT, ticketCount);
    }

    public void printLottoTickets(List<LottoTicket> lottoTicketList) {
        StringBuilder builder = new StringBuilder();
        lottoTicketList.forEach(lottoTicket -> builder.append(lottoTicket).append(NEXT_LINE));
        System.out.println(builder);

    }

    public void printLottoResults(RankResults rankResults, Money principal) {
        printWinningNotification();
        printWinningResults(rankResults);
        printProfitRatioMessage(rankResults.calculateProfitRatio(principal));

    }

    private void printWinningResults(RankResults rankResults) {
        StringBuilder builder = new StringBuilder();
        for (RankResult rankResult : rankResults.getRankResults()) {
            builder.append(String.format(RESULT_STRATEGY_MESSAGE_FORMAT,
                    rankResult.getRank().getMatchCount(),
                    rankResult.getRank().getAmount(),
                    rankResult.getCount())
            );
        }
        System.out.println(builder);
    }

    private void printWinningNotification() {
        StringBuilder builder = new StringBuilder();
        builder.append(WINNING_STRATEGY_MESSAGE).append(NEXT_LINE)
                .append(WINNING_SEPARATION_MESSAGE);
        System.out.println(builder);
    }

    private void printProfitRatioMessage(double calculateProfitRatio) {
        System.out.printf(PROFIT_RATIO_MESSAGE_FORMAT, calculateProfitRatio);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

}