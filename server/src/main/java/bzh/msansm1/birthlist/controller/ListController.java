package bzh.msansm1.birthlist.controller;

import bzh.msansm1.birthlist.model.BirthGift;
import bzh.msansm1.birthlist.repository.BirthGiftsData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ListController {

    @Value("${config.giftsFile}")
    private String dataFilePath;

    @RequestMapping(value="/", method=GET)
    public List<BirthGift> getList() throws IOException {
        return BirthGiftsData.getGiftList(Paths.get(dataFilePath));
    }

    @RequestMapping(value="/", method=POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateList(@RequestBody List<BirthGift> gifts) throws IOException {
        BirthGiftsData.saveGiftList(Paths.get(dataFilePath), gifts);
        // backup on each save
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        BirthGiftsData.saveGiftList(Paths.get(dataFilePath + "." + now.format(formatter)), gifts);
    }

}
