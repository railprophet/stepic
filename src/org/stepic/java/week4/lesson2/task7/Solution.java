package org.stepic.java.week4.lesson2.task7;

/*
Ваша задача — реализовать метод который устанавливает соединение с роботом, отдает ему команду на перемещение
в заданную точку и затем закрывает соединение, выполняя при необходимости повтор этой последовательности до трех раз.

При этом:

Попытка отдать команду роботу считается успешной, если удалось установить соединение и выполнить метод
RobotConnection.moveRobotTo() без исключений. Ошибка закрытия соединения не важна и должна игнорироваться.
Если первая попытка подключиться и отдать команду оказалась неуспешной, то закрываем соединение и выполняем
вторую попытку. Если вторая тоже не удалась, то выполняется третья. После третьей неудачи метод должен бросить
исключение RobotConnectionException.
Метод отвечает за открытие и закрытие соединения. Если соединение удалось установить, то оно обязательно должно
быть закрыто несмотря на возможные исключения, случившиеся в дальнейшем во время работы метода.
Если во время работы метода случилось исключение любого типа, отличного от RobotConnectionException, метод должен
завершиться и выбросить это исключение, не выполняя повторных попыток пообщаться с роботом. Единственное, что
метод должен сделать перед выбросом этого исключения — закрыть открытое соединение RobotConnection.
 */
interface RobotConnection extends AutoCloseable {
    void moveRobotTo(int x, int y);
    @Override
    void close();
}
interface RobotConnectionManager {
    RobotConnection getConnection();
}
class RobotConnectionException extends RuntimeException {
    public RobotConnectionException(String message)                     { super(message); }
    public RobotConnectionException(String message, Throwable cause)    { super(message, cause); }
}
//=================
public class Solution {
    public static void main(String[] args) {

    }

    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) throws RuntimeException {
        int attemptCount =1;
        RobotConnection connection = null;
        while (attemptCount <=3) {
            try {
                connection = robotConnectionManager.getConnection();
                connection.moveRobotTo(toX, toY);
                break; //success
            } catch (RobotConnectionException e) {
                if (attemptCount == 3)
                    throw e; // 3rd fail
                attemptCount++;
            } finally {
                try {connection.close();}
                catch (Exception suppressed) {}
            }
        }
    }

}
