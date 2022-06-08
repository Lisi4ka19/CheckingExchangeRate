package CheckingExchangeRate.feignServices;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Сервис Feign-клиент для работы с сервисом openexchangerates
 */
@FeignClient(name = "exchangeRates", url = "${feign.client.url}")
public interface ServiceClient {

    /**
     * Метод для получения курса валюты на сегодня
     *
     * @param app_id  id аккаунта для подключения к сервису
     * @param symbols код валюты - 3 символа
     * @param base    базовая валюта для получения курса
     * @return ExchangeRatesModel - модель, формируется из JSON
     */
    @GetMapping("/latest.json")
    public ExchangeRatesModel getLatestBySymbols(@RequestParam("app_id") String app_id, @RequestParam("symbols") String symbols, @RequestParam("base") String base);

    /**
     * Метод для получения курса валюты на дату
     *
     * @param dateString дата в строковом представлении - yyyy-mm-dd
     * @param app_id     id аккаунта для подключения к сервису
     * @param symbols    код валюты - 3 символа
     * @param base       базовая валюта для получения курса
     * @return ExchangeRatesModel - модель, формируется из JSON
     */
    @GetMapping("/historical/{date}.json")
    public ExchangeRatesModel getHistorical(@PathVariable("date") String dateString, @RequestParam("app_id") String app_id, @RequestParam("symbols") String symbols, @RequestParam("base") String base);


}
