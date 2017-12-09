import dao.GenericDaoImpl;
import dao.condition.type.ConditionCompare;
import dao.condition.type.ConditionWHERE;
import domain.Author;
import domain.Book;

public class Main {

    public static void main(String[] args) {
        GenericDaoImpl<Book> bookDao = new GenericDaoImpl<>(Book.class);
        System.out.println(bookDao.readAll());
        System.out.println(bookDao.read(1));


        GenericDaoImpl<Author> authorDao = new GenericDaoImpl<>(Author.class);
        System.out.println(authorDao.readAll());
        System.out.println(authorDao.read(1));


        /*
        * QUESTIONS
        * */

        /*
        * Создал еще 2 класса ForeignKey и PrimaryKey
        * PrimaryKey пока не использую, его сделал, чтобы потом можно было составные ключи добавить,
        * просто пока в раздумии на счет них (никогда сам не использовал их, надо посмотреть как и что)
        *
        * А на счет класса ForeignKey
        * там 2 поля 1)Само поле Feild обычное
        *            2)ПОле с названием сущности к которой привязаны
        *
        * на счет него еще один вопрос есть, в плане что загрузки сущности от которой зависим, мы можем
        * добавить сюда какое-то поле, которое будет хранить зависимую сущность, и в зависимости от какого-то
        * флага можно делать ленивую загрузку, либо просто оставить его так, но тогда от него будет пользыы
        * не больше чем от обычного поля Field
        * */

        /*
        * Сделал вроде бы Condition иерархию какую-то)
        * но при создании выглядит так себе, либо нужно использовать какую-то фабрику
        * */
        System.out.println(new ConditionWHERE(new ConditionCompare(1, 2, ">"))); // WHERE 1 > 2
    }

}
