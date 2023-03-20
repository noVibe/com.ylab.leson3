package demonstrations;

import transliterator.Transliterator;
import transliterator.TransliteratorImpl;

public class TransliteratorTest {
    public static void main(String[] args) {
        Transliterator transliterator = new TransliteratorImpl();
        String str = "УПОР НА TEST, ЩАС СПОЮ!";
        System.out.println(transliterator.transliterate(str));
    }
}
