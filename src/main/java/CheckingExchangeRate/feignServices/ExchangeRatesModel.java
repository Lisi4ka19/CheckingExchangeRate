package CheckingExchangeRate.feignServices;

import java.math.BigDecimal;
import java.util.Map;


public class ExchangeRatesModel {

    private String disclaimer;
    private String license;
    private Long timestamp;
    private String base;
    private Map<String, BigDecimal> rates;


    public ExchangeRatesModel() {
    }


    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public ExchangeRatesModel(String disclaimer, String license, Long timestamp, String base) {
        this.disclaimer = disclaimer;
        this.license = license;
        this.timestamp = timestamp;
        this.base = base;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

}
