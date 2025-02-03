package liveCoding;

public class EnamQ {
//    enum Countries {
//        RU, USA, BY, ENG, FR, MG
//    }
//    public static void main(String[] args) {
//        EnamQ.Countries country = Countries.MG;
//
//        switch (country) {
//            case BY -> System.out.println("This is BY");
//            case RU -> System.out.println("This is RU");
//            case USA -> System.out.println("This is USA");
//            case ENG -> System.out.println("This is ENG");
//            default -> System.out.println("No country");
//        }
//    }

    public static void main(String[] args) {
        Country country = Country.RU;

        // 1. Получение сообщения через метод enum
       // System.out.println(country.getDefaultMessage());  // "This is Russia"

        // 2. Пример использования полного названия
      //  System.out.println("Полное название: " + country.getFullName());  // "Полное название: Россия"

        // 3. Switch с обработкой всех вариантов (без default)
//        switch (country) {
//            case BY -> System.out.println(Country.BY.getDefaultMessage());
//            case RU -> System.out.println(Country.RU.getDefaultMessage());
//            case USA -> System.out.println(Country.USA.getDefaultMessage());
//            case ENG -> System.out.println(Country.ENG.getDefaultMessage());
//        }

        Country.USA.printInfo();  // "США (USA): This is USA"

    }

    enum Country {
        RU("Россия", "This is Russia"),
        USA("США", "This is USA"),
        BY("Беларусь", "This is Belarus"),
        ENG("Англия", "This is England");

        private final String fullName;
        private final String defaultMessage;

        // Конструктор enum
        Country(String fullName, String defaultMessage) {
            this.fullName = fullName;
            this.defaultMessage = defaultMessage;
        }
//
//        // Метод для получения дефолтного сообщения
//        public String getDefaultMessage() {
//            return defaultMessage;
//        }
//
//        // Метод для получения полного названия
//        public String getFullName() {
//            return fullName;
//        }

        public void printInfo() {
            System.out.printf("%s (%s): %s%n",
                    this.fullName,
                    this.name(),
                    this.defaultMessage);
        }
    }
}

