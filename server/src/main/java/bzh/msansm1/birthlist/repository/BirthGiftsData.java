package bzh.msansm1.birthlist.repository;

import bzh.msansm1.birthlist.model.BirthGift;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;

public class BirthGiftsData {

    private static int lineIndex = 0;

    private BirthGiftsData() {
    }

    public static List<BirthGift> getGiftList(Path dataFile) throws IOException {
        lineIndex = 0;
        List<BirthGift> gifts = new ArrayList<>();

        try (Stream<String> stream = Files.lines(dataFile)) {
            stream.forEach(line ->  {
                if (!line.isEmpty()) {
                    gifts.add(transformLineToGift(line));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gifts;
    }

    public static void saveGiftList(Path dataFile, List<BirthGift> gifts) throws IOException {
        StringBuilder giftList = new StringBuilder();
        gifts.stream().forEach(gift -> giftList.append(gift.toCSVLine()));
        Files.write(dataFile, giftList.toString().getBytes("utf-8"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static BirthGift transformLineToGift(String line) {
        lineIndex++;
        BirthGift gift = new BirthGift();
        gift.setFromCSVLine(lineIndex, line);
        return gift;
    }

}
