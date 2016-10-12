package org.stepic.java.week5.lesson4.task8;
import java.io.*;
import java.util.Objects;


/*

Реализуйте метод, который из переданного массива байт восстановит массив объектов Animal. Массив байт устроен
следующим образом. Сначала идет число типа int, записанное при помощи ObjectOutputStream.writeInt(size).
Далее подряд записано указанное количество объектов типа Animal, сериализованных при помощи
ObjectOutputStream.writeObject(animal).

Если вдруг массив байт не является корректным представлением массива экземпляров Animal, то метод должен бросить
исключение java.lang.IllegalArgumentException.

Причины некорректности могут быть разные. Попробуйте подать на вход методу разные некорректные данные и посмотрите,
какие исключения будут возникать. Вот их-то и нужно превратить в IllegalArgumentException и выбросить. Если что-то
забудете, то проверяющая система подскажет. Главное не глотать никаких исключений, т.е. не оставлять нигде пустой catch.

 */
class OddClass implements Serializable {
    public OddClass(int i){}

}
class Animal implements Serializable {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Animal) {
            return Objects.equals(name, ((Animal) obj).name);
        }
        return false;
    }

    public String getName(){ return name; }
}

public class Solution {



//throws IOException, ClassNotFoundException

    public static Animal[] deserializeAnimalArray(byte[] data) throws IllegalArgumentException {
        int count;
        Animal[] animals = new Animal[]{};
        try( ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data)) ) {

            count = ois.readInt();

            animals = new Animal[count];

            for (int i=0; i<count; i++){
                animals[i] = (Animal) ois.readObject();
            }

        } catch (RuntimeException e) {
            //System.out.println(e.getClass());
            //System.out.println(e.getMessage());
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }

        return animals;
    }



    public static void main(String[] args) throws IOException {

;
        Animal cat   = new Animal("cat");
        Animal dog   = new Animal("dog");
        Animal donkey = new Animal("donkey");
        Animal cock = new Animal("cock");

        Animal[] band = new Animal[] {cat, dog, donkey, cock };

        byte[] sBand = new byte[]{};

        try ( ByteArrayOutputStream bos = new ByteArrayOutputStream();
              ObjectOutputStream oos = new ObjectOutputStream(bos); ) {

            oos.writeInt(band.length);

            for (Animal animal : band) {
                oos.writeObject(animal);
            }

            oos.writeObject(new OddClass(1));
            oos.flush();

            sBand = bos.toByteArray();
        } catch (Exception e){
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }

        Animal[] result = deserializeAnimalArray(sBand);

        for (Animal animal: result){
            System.out.println(animal.getName());
        }

    }
}

