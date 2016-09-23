package org.stepic.java.week4.lesson3.task8;

import java.util.logging.*;

/*
В этой задаче вам нужно реализовать метод, настраивающий параметры логирования. Конфигурирование в коде
позволяет выполнить более тонкую и хитрую настройку, чем при помощи properties-файла.

Требуется выставить такие настройки, чтобы:

Логгер с именем "org.stepic.java.logging.ClassA" принимал сообщения всех уровней.
Логгер с именем "org.stepic.java.logging.ClassB" принимал только сообщения уровня WARNING и серьезнее.
Все сообщения, пришедшие от нижестоящих логгеров на уровень "org.stepic.java", независимо от серьезности
сообщения печатались в консоль в формате XML (*) и не передавались вышестоящим обработчикам на уровнях
"org.stepic", "org" и "".
Не упомянутые здесь настройки изменяться не должны.

(*) В реальных программах мы бы конечно печатали XML не в консоль, а в файл. Но проверяющая система не
разрешает создавать файлы на диске, поэтому придется так.

Подсказки:

Level есть не только у Logger, но и у Handler.
Передача сообщения на обработку родительскому Handler'у регулируется свойством useParentHandlers.
 */
public class Solution {
    private static void configureLogging() {
        Logger loggerA = Logger.getLogger("org.stepic.java.logging.ClassA");
        loggerA.setLevel(Level.ALL);
        Logger loggerB = Logger.getLogger("org.stepic.java.logging.ClassB");
        loggerB.setLevel(Level.WARNING);

        Logger rootLogger = Logger.getLogger("org.stepic.java");
        rootLogger.setUseParentHandlers(false);
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new XMLFormatter());
        rootLogger.addHandler(consoleHandler);


    }
}
