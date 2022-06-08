package CheckingExchangeRate.service;

import CheckingExchangeRate.feignServices.ExchangeRatesModel;
import CheckingExchangeRate.feignServices.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class CurrencyGatewayService implements CurrencyGateway {

    @Autowired
    ConfigProperties configProperties;

    @Autowired
    private ServiceClient serviceClient;

    @Override
    public double getCurrentValue(String currency) throws Exception {

        String app_id = configProperties.getConfigValue("app_id");
        String base = configProperties.getConfigValue("base");

        ExchangeRatesModel exchangeRatesModel = serviceClient.getLatestBySymbols(app_id, currency, base);

        if (exchangeRatesModel.getRates().size() == 0) throw new Exception("No data of currency " + currency);

        BigDecimal values = exchangeRatesModel.getRates().values().stream().findFirst().get();

        return values.doubleValue();
    }

    @Override
    public double getYesterdayValue(String currency) throws Exception {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = simpleDateFormat.format(cal.getTime());

        ExchangeRatesModel exchangeRatesModelHistory = serviceClient.getHistorical(dateString, getAppId(), currency, getBaseCurrency());

        if (exchangeRatesModelHistory.getRates().size() == 0) throw new Exception("No data of currency " + currency);

        BigDecimal values = exchangeRatesModelHistory.getRates().values().stream().findFirst().get();

        return values.doubleValue();
    }

    private String getAppId() {
        return configProperties.getConfigValue("app_id");
    }

    private String getBaseCurrency() {
        return configProperties.getConfigValue("base");
    }

}
