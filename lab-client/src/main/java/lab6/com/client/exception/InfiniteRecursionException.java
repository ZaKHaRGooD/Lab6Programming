package lab6.com.client.exception;

/**
 * Класс исключения для обработки ошибки бесконечного вызова скрипта из самого себя
 */
public class InfiniteRecursionException extends RuntimeException {
    public InfiniteRecursionException(String message) {
        super("Бесконечная рекурсия.");
    }
}
