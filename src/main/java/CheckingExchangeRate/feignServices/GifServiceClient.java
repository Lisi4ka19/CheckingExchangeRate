package CheckingExchangeRate.feignServices;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Сервис Feign-клиент для работы с сервисом giphy
 */
@FeignClient(name = "gifService", url = "${gif.service.url}")
public interface GifServiceClient {

    /**
     * Метод для получения рандомного gif-изображения
     *
     * @param api_key id для подключения к сервису
     * @param tag     строка для поиска gif-изображения
     * @return
     */
    @GetMapping("/random")
    public GifModel getRandomGif(@RequestParam("api_key") String api_key, @RequestParam("tag") String tag);


}
