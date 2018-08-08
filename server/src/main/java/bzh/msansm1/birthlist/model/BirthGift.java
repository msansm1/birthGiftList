package bzh.msansm1.birthlist.model;

public class BirthGift {
    private static final String SEPARATOR = ",";
    private static final String DBQUOTE = "\"";
    private static final int INDEX_NAME = 0;
    private static final int INDEX_URL = 1;
    private static final int INDEX_PERSON = 2;

    private int id;
    private String name;
    private String person;
    private String url;

    public BirthGift() {
    }

    public BirthGift(final int id, final String name, final String person, final String url) {
        this.id = id;
        this.name = name;
        this.person = person;
        this.url = url;
    }

    public void setFromCSVLine(int index, String line) {
        String[] splits = line.split(SEPARATOR);
        this.setId(index);
        this.setName(extractDataFromField(splits[INDEX_NAME]));
        this.setUrl(extractDataFromField(splits[INDEX_URL]));
        this.setPerson(extractDataFromField(splits[INDEX_PERSON]));
    }

    public String toCSVLine() {
        return DBQUOTE + this.name + DBQUOTE + SEPARATOR + DBQUOTE + this.url +
                DBQUOTE + SEPARATOR + DBQUOTE +  this.person + DBQUOTE + SEPARATOR + "\n";
    }

    private String extractDataFromField(String fieldData) {
        if (fieldData.startsWith("\"")) {
            return fieldData.substring(1, fieldData.length()-1);
        }
        return fieldData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
