package Exercise3;

// 3. Создать класс Park с внутренним классом, с помощью объектов которого
// можно хранить информацию об аттракционах, времени их работы и стоимости.

public class Park {
    String park_name;

    public Park(String park_name){
        this.park_name = park_name;
    }

    public void addAttraction(String attractionName, String workingHours, int ticketPrice){
        Attraction attraction = new Attraction(attractionName, workingHours, ticketPrice);
        attraction.infoAttraction();
    }

    protected class Attraction {
        private String attractionName;
        private String workingHours;
        private int ticketPrice;


        private Attraction(String attractionName, String workingHours, int ticketPrice) {
            this.attractionName = attractionName;
            this.workingHours = workingHours;
            this.ticketPrice = ticketPrice;
                  }

        private void infoAttraction(){
            System.out.println(
                    "Парк: " + park_name + "\n" +
                    "Название аттракциона: " + this.attractionName + "\n" +
                    "Время работы: " + this.workingHours + "\n" +
                    "Цена билета: " + this.ticketPrice + "\n"
            );
        }
    }

}
