package cn.njcit.ankeread.DB;

import org.litepal.crud.LitePalSupport;

/**
 * Create by ankele
 * <p>
 * 2020/1/19 - 20:47
 */
public class SearchHistory extends LitePalSupport{

    private String bookName;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
