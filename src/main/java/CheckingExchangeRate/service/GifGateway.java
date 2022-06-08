package CheckingExchangeRate.service;

/**
 * Сервис для работы со сторонним хранилищем gif изображений
 */
public interface GifGateway {

    /**
     * Метод для получения ссылки на позитивную gif
     *
     * @return строка- ссылка на gif
     */
    public String getPositiveGif();

    /**
     * Метод для получения ссылки на негативную gif
     *
     * @return строка- ссылка на gif
     */
    public String getNegativeGif();

}
