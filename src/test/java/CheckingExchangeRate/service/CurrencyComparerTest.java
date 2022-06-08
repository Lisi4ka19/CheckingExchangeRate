package CheckingExchangeRate.service;

import CheckingExchangeRate.entity.Message;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;


@RunWith(SpringRunner.class)
@SpringBootTest
class CurrencyComparerTest {


    @MockBean
    CurrencyGateway currencyGatewayService;

    @MockBean
    GifGateway gifGatewayService;

    @Autowired
    CurrencyComparer currencyComparer;

    private double currentRates;
    private double historyRates;

    private String negativeGif;
    private String positiveGif;


    @Test
    public void testNegativeMessage() throws Exception {

        currentRates = 0.065;
        historyRates = 0.077;

        String current = "EUR";

        negativeGif = "\\hhtp:negative.gif";
        positiveGif = "\\hhtp:positive.gif";

        doReturn(currentRates).when(currencyGatewayService).getCurrentValue(current);

        doReturn(historyRates).when(currencyGatewayService).getYesterdayValue(current);

        currencyComparer.setCurrencyGatewayService(currencyGatewayService);


        doReturn(this.positiveGif).when(gifGatewayService).getPositiveGif();
        doReturn(this.negativeGif).when(gifGatewayService).getNegativeGif();


        currencyComparer.setGifGatewayService(gifGatewayService);

        Message resultMessage = currencyComparer.compareCurrency(current);

        Assertions.assertEquals(negativeGif, resultMessage.getData());

    }

    @Test
    public void testPositiveMessage() throws Exception {

        currentRates = 0.065;
        historyRates = 0.055;

        String current = "EUR";
        negativeGif = "\\hhtp:negative.gif";
        positiveGif = "\\hhtp:positive.gif";

        doReturn(currentRates).when(currencyGatewayService).getCurrentValue(current);

        doReturn(historyRates).when(currencyGatewayService).getYesterdayValue(current);

        currencyComparer.setCurrencyGatewayService(currencyGatewayService);


        doReturn(this.positiveGif).when(gifGatewayService).getPositiveGif();
        doReturn(this.negativeGif).when(gifGatewayService).getNegativeGif();


        currencyComparer.setGifGatewayService(gifGatewayService);

        Message resultMessage = currencyComparer.compareCurrency(current);

        Assertions.assertEquals(positiveGif, resultMessage.getData());

    }

}