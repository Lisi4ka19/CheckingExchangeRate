package CheckingExchangeRate.service;

import CheckingExchangeRate.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyComparer {

    @Autowired
    CurrencyGateway currencyGatewayService;

    @Autowired
    GifGateway gifGatewayService;

    public void setCurrencyGatewayService(CurrencyGateway currencyGatewayService) {
        this.currencyGatewayService = currencyGatewayService;
    }

    public void setGifGatewayService(GifGateway gifGatewayService) {
        this.gifGatewayService = gifGatewayService;
    }

    public Message compareCurrency(String symbols) {

        Message messageResult;

        if(symbols.equals("")||symbols==null) return new Message(500, "No currency specified");


        double currentValue;
        double historyValue;
        try {
            currentValue = currencyGatewayService.getCurrentValue(symbols);
            historyValue = currencyGatewayService.getYesterdayValue(symbols);
        } catch (Exception e) {
            messageResult = new Message(500, e.getMessage());
            return messageResult;
        }

        String httpGif = "";
        try {
            if (currentValue > historyValue) httpGif = gifGatewayService.getPositiveGif();
            else httpGif = gifGatewayService.getNegativeGif();

            messageResult = new Message(200, "OK", httpGif);

        } catch (Exception e) {
            messageResult = new Message(500, e.getMessage());
        }

        return messageResult;
    }


}
