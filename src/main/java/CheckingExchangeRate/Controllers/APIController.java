package CheckingExchangeRate.Controllers;

import CheckingExchangeRate.entity.Message;
import CheckingExchangeRate.service.CurrencyComparer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/currency/")
public class APIController {

    @Autowired
    CurrencyComparer currencyComparer;


    @GetMapping("/comparison")
    public Message comparison(@RequestParam("symbols") String symbols) {

        return currencyComparer.compareCurrency(symbols);
    }

}
