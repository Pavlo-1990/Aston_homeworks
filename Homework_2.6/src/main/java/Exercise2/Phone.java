package Exercise2;

class Phone {
    private String phone;

    Phone(String phone){
        this.phone = phone;
    }

    String getPhone() {
        return phone;
    }

    String getPhoneFormat() {
        String phoneForm = String.format(
                "%s-%s-%s",
                phone.substring(0, 3),
                phone.substring(3, 5),
                phone.substring(5)
        );

        return phoneForm;
    }
}
