import dao.GenericDaoImpl;
import dao.condition.type.ConditionCompare;
import dao.condition.type.ConditionWHERE;
import domain.Book;

public class Main {

    public static void main(String[] args) {
        GenericDaoImpl<Book> bookDao = new GenericDaoImpl<>(Book.class);

        System.out.println(new ConditionWHERE(new ConditionCompare(1, 2, ">")));

        System.out.println(bookDao.readAll());
        System.out.println(bookDao.read(1));
    }

}
