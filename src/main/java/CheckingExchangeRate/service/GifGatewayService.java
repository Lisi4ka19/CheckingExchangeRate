package CheckingExchangeRate.service;

import CheckingExchangeRate.feignServices.GifModel;
import CheckingExchangeRate.feignServices.GifServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GifGatewayService implements GifGateway {

    @Autowired
    private GifServiceClient gifServiceClient;

    @Autowired
    ConfigProperties configProperties;

    @Override
    public String getPositiveGif() {

        String tag = "rich";
        return getGifByTag(tag);

    }

    @Override
    public String getNegativeGif() {
        String tag = "broke";
        return getGifByTag(tag);
    }

    private String getGifByTag(String tag) {

        String key = configProperties.getConfigValue("gif.api_key");

        GifModel gifModel = gifServiceClient.getRandomGif(key, tag);

        String url = "";

        if (gifModel != null) {
            url = gifModel.getData().getUrl();
        }

        return url;
    }

}
