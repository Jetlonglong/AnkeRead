package cn.njcit.ankeread.Bean;

import java.util.List;

/**
 * Create by ankele
 * <p>
 * 2020/1/28 - 14:30
 */
public class Hotmonth {


    private NodeBean node;
    private List<BooksBean> books;

    public NodeBean getNode() {
        return node;
    }

    public void setNode(NodeBean node) {
        this.node = node;
    }

    public List<BooksBean> getBooks() {
        return books;
    }

    public void setBooks(List<BooksBean> books) {
        this.books = books;
    }

    public static class NodeBean {
        /**
         * pid : 5bc43b56ee491b1844612c58
         * title : 月榜
         * secondTitle : 月榜
         * created : 2018-10-16T05:50:08.035Z
         * status : true
         * order : 99
         * alias : rankinglist-male-hot-month
         * bookCovers : null
         * bookTitles : []
         */

        private String pid;
        private String title;
        private String secondTitle;
        private String created;
        private boolean status;
        private int order;
        private String alias;
        private Object bookCovers;
        private List<?> bookTitles;

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSecondTitle() {
            return secondTitle;
        }

        public void setSecondTitle(String secondTitle) {
            this.secondTitle = secondTitle;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public Object getBookCovers() {
            return bookCovers;
        }

        public void setBookCovers(Object bookCovers) {
            this.bookCovers = bookCovers;
        }

        public List<?> getBookTitles() {
            return bookTitles;
        }

        public void setBookTitles(List<?> bookTitles) {
            this.bookTitles = bookTitles;
        }
    }

    public static class BooksBean {
        /**
         * _id : 508646479dacd30e3a000001
         * title : 将夜
         * author : 猫腻
         * longIntro : 与天斗，其乐无穷。
         * cover : http://statics.zhuishushenqi.com/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F42314%2F42314_e1c68c4267f54a968806264b7538d14a.jpg%2F
         * majorCate : 玄幻
         * minorCate : 东方玄幻
         * latelyFollower : 7462
         * wordCount : 3991515
         * retentionRatio : 46.61
         * updated : 2017-06-03T00:51:27.023Z
         * isSerial : false
         * chaptersCount : 1119
         * lastChapter : 第六卷忽然之间 第一百三十章 结尾
         * cat : 东方玄幻
         */

        private String _id;
        private String title;
        private String author;
        private String longIntro;
        private String cover;
        private String majorCate;
        private String minorCate;
        private int latelyFollower;
        private int wordCount;
        private String retentionRatio;
        private String updated;
        private boolean isSerial;
        private int chaptersCount;
        private String lastChapter;
        private String cat;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getLongIntro() {
            return longIntro;
        }

        public void setLongIntro(String longIntro) {
            this.longIntro = longIntro;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getMajorCate() {
            return majorCate;
        }

        public void setMajorCate(String majorCate) {
            this.majorCate = majorCate;
        }

        public String getMinorCate() {
            return minorCate;
        }

        public void setMinorCate(String minorCate) {
            this.minorCate = minorCate;
        }

        public int getLatelyFollower() {
            return latelyFollower;
        }

        public void setLatelyFollower(int latelyFollower) {
            this.latelyFollower = latelyFollower;
        }

        public int getWordCount() {
            return wordCount;
        }

        public void setWordCount(int wordCount) {
            this.wordCount = wordCount;
        }

        public String getRetentionRatio() {
            return retentionRatio;
        }

        public void setRetentionRatio(String retentionRatio) {
            this.retentionRatio = retentionRatio;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public boolean isIsSerial() {
            return isSerial;
        }

        public void setIsSerial(boolean isSerial) {
            this.isSerial = isSerial;
        }

        public int getChaptersCount() {
            return chaptersCount;
        }

        public void setChaptersCount(int chaptersCount) {
            this.chaptersCount = chaptersCount;
        }

        public String getLastChapter() {
            return lastChapter;
        }

        public void setLastChapter(String lastChapter) {
            this.lastChapter = lastChapter;
        }

        public String getCat() {
            return cat;
        }

        public void setCat(String cat) {
            this.cat = cat;
        }
    }
}
