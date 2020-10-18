package FirstExam1.FitnessChaos;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Client implements Comparable<Client>{
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");

    public final String surname;
    public final String name;
    public final LocalDate birthday;

    public Client(String surname, String name, LocalDate birthday) {
        String surnameNorm = (surname);
        String nameNorm = (name);
        if (!surnameNorm.isBlank() && surnameNorm.length() > 2 &&
                !nameNorm.isBlank() && nameNorm.length() > 2 &&
                birthday != null && !LocalDate.now().isBefore(birthday)) {
            this.surname = surnameNorm;
            this.name = nameNorm;
            this.birthday = birthday;
        } else if (surnameNorm.isBlank() || surnameNorm.length() <= 2) {
            throw new IllegalArgumentException("surname '" + surname + "' is invalid or too short");
        } else if (nameNorm.isBlank() || nameNorm.length() <= 2) {
            throw new IllegalArgumentException("name '" + name + "' is invalid or too short");
        } else if (birthday == null) {
            throw new NullPointerException("birthday is null");
        } else {
            throw new IllegalArgumentException("birthday '" + birthday.format(FORMAT) + "' has not yet come");
        }
    }

    public Client(String surname, String name, int year, Month month, int day) {
        this(surname, name, LocalDate.of(year, month, day));
    }

    public Client(String surname, String name, int year, int month, int day) {
        this(surname, name, LocalDate.of(year, month, day));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client person = (Client) o;
        return surname.equalsIgnoreCase(person.surname) &&
                name.equalsIgnoreCase(person.name) &&
                birthday.getYear() == person.birthday.getYear();
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, birthday.getYear());
    }

    @Override
    public String toString() {
        return surname + " " + name;
    }

    public String details() {
        return surname + " " + name + ", " + birthday.format(DateTimeFormatter.ofPattern("yyyy г.р."));
    }

    @Override
    public int compareTo(Client o) {
        if (!surname.equalsIgnoreCase(o.surname)) {
            return surname.compareToIgnoreCase(o.surname);
        } else if (!name.equalsIgnoreCase(o.name)) {
            return name.compareToIgnoreCase(o.name);
        } else {
            return birthday.getYear() - o.birthday.getYear();
        }
    }
}

