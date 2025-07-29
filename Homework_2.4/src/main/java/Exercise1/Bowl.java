package Exercise1;

public class Bowl {
    int foodAmount;
    int foodAmountMax;

    public Bowl(int foodAmount){
        if (foodAmount <= 0){
            System.out.println("В миске должна быть еда. Вы ввели неположительное число");
            try {
                throw new IllegalArgumentException("В миске должна быть еда. Вы ввели неположительное число");
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }

        this.foodAmount = foodAmount;
        this.foodAmountMax = foodAmount;
    }

    public boolean takeFood(int portion, boolean satiety){
        System.out.print("В миске " + foodAmount + " единиц(а,ы) еды. ");

        if ( (foodAmount >= portion) && (satiety == false) ) {
            foodAmount -= portion;
            return true;
        } else if (foodAmount == 0){
            System.out.print("В миске не осталось еды. ");
            return false;
        } else if(satiety == true) {
            return true;
        } else {
            System.out.print("Требуемая порция больше, чем еды в миске. ");
            return false;
        }
    }

    public void fillBowl(int food){
        if(foodAmountMax >= (foodAmount + food)){
            foodAmount += food;
            System.out.println("В миску добавлено " + food + " единиц(а,ы) еды. В миске " + foodAmount + " единиц(а,ы) еды. "
                      + "Вместимость миски - " + foodAmountMax + " единиц(а,ы) еды.");
        } else {
            System.out.println("Миска не может вместить столько еды. Уменьшите количество еды");
        }
    }
}
