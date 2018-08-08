package bzh.msansm1.birthlist.repository;

import bzh.msansm1.birthlist.model.BirthGift;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class BirthGiftsDataTest {

    @Test
    public void getGiftListTest() throws URISyntaxException, IOException {
        java.net.URL url = BirthGiftsDataTest.class.getResource("/test.csv");
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        List<BirthGift> res = BirthGiftsData.getGiftList(resPath);
        assertThat(res).hasSize(3)
                        .extracting(gift -> gift.getName())
                        .contains("Gift01", "Gift02", "Gift03");
        assertThat(res)
                .extracting(gift -> gift.getPerson())
                .contains("Taty", "", "Cousin");
    }

    @Test
    public void saveGiftListTest() throws URISyntaxException, IOException {
        java.net.URL url = BirthGiftsDataTest.class.getResource("/test_write.csv");
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        List<BirthGift> gifts = new ArrayList<>();
        gifts.add(new BirthGift(0, "01Gift", "", "http://test/01"));
        gifts.add(new BirthGift(1, "02Gift", "Mamy", "http://test/02"));
        BirthGiftsData.saveGiftList(resPath, gifts);
    }
}
