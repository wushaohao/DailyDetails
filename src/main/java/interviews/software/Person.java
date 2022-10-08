package interviews.software;

import java.util.LinkedHashMap;
import java.util.Map;

class Person {
    public String name;

    @Override
    public boolean equals(Object o) {
        Person person = (Person) o;
        return name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return name.length();
    }

    public static void main(String[] args) {
        LinkedHashMap<Person, String> personMap = new LinkedHashMap<>();

        Person person = new Person();

        person.name = "Jack";
        personMap.put(person, "Teacher");

        person.name = "Tom";
        personMap.put(person, "Lawyer");

        person.name = "Lily";
        personMap.put(person, "Student");

        for (Map.Entry<Person, String> entry : personMap.entrySet()) {
            Person k = entry.getKey();
            String v = entry.getValue();
            System.out.printf("%s is a %s\n", k.name, v);
        }
    }
}


