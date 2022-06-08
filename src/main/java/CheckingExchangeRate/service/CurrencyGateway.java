package CheckingExchangeRate.service;

/**
 * Сервис для получения курсов валют
 */

public interface CurrencyGateway {

    /**
     * Метод для получения курса валюты на сегодня
     *
     * @param currency код валюты - 3 символа
     * @return число с плавающей точкой - курс валюты
     * @throws Exception
     */
    public double getCurrentValue(String currency) throws Exception;

    /**
     * Метод для получения курса валюты на вчерашнюю дату
     *
     * @param currency код валюты - 3 символа
     * @return число с плавающей точкой - курс валюты
     * @throws Exception
     */
    public double getYesterdayValue(String currency) throws Exception;

}
