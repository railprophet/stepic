package org.stepic.java.week4.lesson3.task9;

import java.util.Objects;
import java.util.logging.*;
import java.util.regex.Pattern;

public class Solution {
    public static interface Sendable {
        String getFrom();
        String getTo();
    }

    public static abstract class AbstractSendable implements Sendable {
        protected final String from;
        protected final String to;
        public AbstractSendable(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String getFrom() { return from; }

        @Override
        public String getTo()   { return to; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AbstractSendable that = (AbstractSendable) o;

            if (!from.equals(that.from)) return false;
            if (!to.equals(that.to)) return false;

            return true;
        }
    }

    public static class MailMessage extends AbstractSendable {

        private final String message;

        public MailMessage(String from, String to, String message) {
            super(from, to);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailMessage that = (MailMessage) o;

            if (message != null ? !message.equals(that.message) : that.message != null) return false;

            return true;
        }

    }

    public static class MailPackage extends AbstractSendable {
        private final Package content;

        public MailPackage(String from, String to, Package content) {
            super(from, to);
            this.content = content;
        }

        public Package getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MailPackage that = (MailPackage) o;

            if (!content.equals(that.content)) return false;

            return true;
        }

    }

    public static class Package {
        private final String content;
        private final int price;

        public Package(String content, int price) {
            this.content = content;
            this.price = price;
        }

        public String getContent() {
            return content;
        }

        public int getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Package aPackage = (Package) o;

            if (price != aPackage.price) return false;
            if (!content.equals(aPackage.content)) return false;

            return true;
        }
    }

    public static interface MailService {
        Sendable processMail(Sendable mail);
    }
    public static class RealMailService implements MailService {

        @Override
        public Sendable processMail(Sendable mail) {
            // Здесь описан код настоящей системы отправки почты.
            System.out.println(mail);
            return mail;
        }
    }

    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";

    //-----------------
/*
1) UntrustworthyMailWorker – класс, моделирующий ненадежного работника почты, который вместо того,
чтобы передать почтовый объект непосредственно в сервис почты, последовательно передает этот объект
набору третьих лиц, а затем, в конце концов, передает получившийся объект непосредственно экземпляру
RealMailService. У UntrustworthyMailWorker должен быть конструктор от массива MailService ( результат
вызова processMail первого элемента массива передается на вход processMail второго элемента, и т. д.)
и метод getRealMailService, который возвращает ссылку на внутренний экземпляр RealMailService.
 */
    public static class UntrustworthyMailWorker implements MailService {
        private RealMailService realMailService;
        private MailService[] mailServices;

        public UntrustworthyMailWorker(MailService[] mailServices){
            this.mailServices = mailServices;
            realMailService = new RealMailService();
        }
        @Override
        public Sendable processMail(Sendable sendable){
            for (MailService mailService : mailServices){
                sendable = mailService.processMail(sendable);
            }
            return getRealMailService().processMail(sendable);
        }

        public RealMailService getRealMailService() {return this.realMailService;}

    }
/*
2) Spy – шпион, который логгирует о всей почтовой переписке, которая проходит через его руки. Объект
конструируется от экземпляра Logger, с помощью которого шпион будет сообщать о всех действиях. Он следит
только за объектами класса MailMessage и пишет в логгер следующие сообщения (в выражениях нужно заменить
части в фигурных скобках на значения полей почты):
2.1) Если в качестве отправителя или получателя указан "Austin Powers", то нужно написать в лог сообщение
с уровнем WARN: Detected target mail correspondence: from {from} to {to} "{message}"
2.2) Иначе, необходимо написать в лог сообщение с уровнем INFO: Usual correspondence: from {from} to {to}
*/
    public static class Spy implements MailService {
        private Logger logger;
        public Spy (Logger logger){ this.logger = logger; }
        @Override
        public Sendable processMail(Sendable sendable){
            if (sendable instanceof MailMessage) {

                if ( AUSTIN_POWERS.equals(sendable.getFrom()) ||
                     AUSTIN_POWERS.equals(sendable.getTo()) ) {
                    logger.warning("Detected target mail correspondence: from " + sendable.getFrom() +
                                    " to " + sendable.getTo() +
                                    " \"" + ((MailMessage) sendable).getMessage() +"\"");
                } else {
                    logger.info("Usual correspondence: from "+sendable.getFrom()
                                +" to " + sendable.getTo() );
                }
            }

            return sendable; // pass packages
        }
    }
/*
3) Thief – вор, который ворует самые ценные посылки и игнорирует все остальное. Вор принимает в конструкторе
переменную int – минимальную стоимость посылки, которую он будет воровать. Также, в данном классе должен
присутствовать метод getStolenValue, который возвращает суммарную стоимость всех посылок, которые он своровал.
Воровство происходит следующим образом: вместо посылки, которая пришла вору, он отдает новую, такую же, только
с нулевой ценностью и содержимым посылки "stones instead of {content}".
*/
    static public class Thief implements MailService {
        private int minValue;
        private int StolenValue;
        public Thief (int minValue) { this.minValue = minValue; }
        @Override
        public Sendable processMail (Sendable sendable) {
            if (sendable instanceof MailPackage){
                if ( ( ((MailPackage) sendable).getContent().getPrice() ) >= this.minValue ) {
                    Package pkg = ((MailPackage) sendable).getContent();
                    this.StolenValue += pkg.getPrice();
                    pkg = new Package("stones instead of " + pkg.getContent(), 0);
                    sendable = new MailPackage(sendable.getFrom(), sendable.getTo(), pkg);
                }
            }
            return sendable;
        }
        public int getStolenValue(){ return this.StolenValue; }
    }

/*
4) Inspector – Инспектор, который следит за запрещенными и украденными посылками и бьет тревогу в виде
исключения, если была обнаружена подобная посылка. Если он заметил запрещенную посылку с одним из запрещенных
содержимым ("weapons" и "banned substance"), то он бросает IllegalPackageException. Если он находит посылку,
состаящую из камней (содержит слово "stones"), то тревога прозвучит в виде StolenPackageException. Оба
исключения вы должны объявить самостоятельно в виде непроверяемых исключений.
*/


    public static class StolenPackageException  extends RuntimeException {
        public StolenPackageException() { super(); }
    }
    public static class IllegalPackageException extends RuntimeException {
        public IllegalPackageException (){ super(); }
    }

    static  public class Inspector implements MailService {
        private String[] bunnedContent = {"weapons","banned substance"};
        private String stolen = "stones";
        @Override
        public Sendable processMail(Sendable sendable) throws IllegalPackageException, StolenPackageException {
            if (sendable instanceof MailPackage){
                Package pkg = ((MailPackage) sendable).getContent();
                for (String bunnedKeyword : bunnedContent) {
                    if (pkg.getContent().contains(bunnedKeyword)) throw new IllegalPackageException();
                }

                if (pkg.getContent().contains(stolen)) throw new StolenPackageException();

            }
            return sendable;
        }

    }
    //-----------------
    public static void main(String[] args) {
        MailMessage mail = new MailMessage(AUSTIN_POWERS,"to: ???", WEAPONS  + " " + BANNED_SUBSTANCE);
        RealMailService realMailService = new RealMailService();

        realMailService.processMail(mail);//realMailService

        UntrustworthyMailWorker untrustWorker = new UntrustworthyMailWorker(new MailService[]{realMailService});
        untrustWorker.processMail(mail);   //Untrusted

        Spy spy = new Spy(Logger.getLogger(""));
        spy.processMail(mail); //spy

        MailPackage pkg = new MailPackage(AUSTIN_POWERS,AUSTIN_POWERS,new Package(WEAPONS, 1000));
        Thief thief = new Thief(100);

        thief.processMail(pkg);

        Logger.getLogger("").log("asdasd {0},{1}", new Object[]{1,2});

    }
}
