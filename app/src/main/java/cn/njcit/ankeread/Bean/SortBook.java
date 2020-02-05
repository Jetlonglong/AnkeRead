package cn.njcit.ankeread.Bean;

import java.util.List;

/**
 * Create by ankele
 * <p>
 * 2020/1/30 - 20:08
 */
public class SortBook {

    /**
     * ok : true
     * books : [{"_id":"5be995cc2bef630100774b91","title":"边地南行记","cat":"传记名著","author":"青竹镖","cover":"http://statics.zhuishushenqi.com/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F3340984%2F3340984_b845410fa94040d581fd029060aa28e6.jpg%2F","longIntro":"蜀地少年梁子修前往边地投亲，不料意外横生，不断卷入纷争，辗转流连间结识一群小伙伴，在发现边地恶势力的阴谋后联合众多朋友一起挫败了恶势力的计划，在成长过程中收获了友情爱情和人们的敬重，最后寻得亲人了解了自己的真实身世，联合一众盟友完成了自己的使命并名震边地。","lastChapter":"第57章","retentionRatio":39,"latelyFollower":1365,"wordCount":271746,"majorCate":"传记名著","minorCate":""},{"_id":"57502b8c1cd40b580e46e41f","title":"水利泰斗\u2014\u2014张光斗传","cat":"传记名著","author":"郭梅/周樟钰","cover":"http://statics.zhuishushenqi.com/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F741625%2F_741625_269055.jpg%2F","longIntro":"出身贫寒苦读书，交大十年磨一剑。美国求学遇恩师，品学兼优归桑梓。栉风沐雨修水利，传道授业为人师。他，就是当代李冰、水利泰斗张光斗。本书从张光斗的童年开始说起，以通俗的语言，娓娓讲述了他的水利人生。","lastChapter":"附录3 张光斗生平年表","retentionRatio":34.15,"latelyFollower":1000,"wordCount":78229,"majorCate":"传记名著","minorCate":""}]
     * total : 6892
     */

    private boolean ok;
    private int total;
    private List<BooksBean> books;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<BooksBean> getBooks() {
        return books;
    }

    public void setBooks(List<BooksBean> books) {
        this.books = books;
    }

    public static class BooksBean {
        /**
         * _id : 5be995cc2bef630100774b91
         * title : 边地南行记
         * cat : 传记名著
         * author : 青竹镖
         * cover : http://statics.zhuishushenqi.com/agent/http%3A%2F%2Fimg.1391.com%2Fapi%2Fv1%2Fbookcenter%2Fcover%2F1%2F3340984%2F3340984_b845410fa94040d581fd029060aa28e6.jpg%2F
         * longIntro : 蜀地少年梁子修前往边地投亲，不料意外横生，不断卷入纷争，辗转流连间结识一群小伙伴，在发现边地恶势力的阴谋后联合众多朋友一起挫败了恶势力的计划，在成长过程中收获了友情爱情和人们的敬重，最后寻得亲人了解了自己的真实身世，联合一众盟友完成了自己的使命并名震边地。
         * lastChapter : 第57章
         * retentionRatio : 39.0
         * latelyFollower : 1365
         * wordCount : 271746
         * majorCate : 传记名著
         * minorCate :
         */

        private String _id;
        private String title;
        private String cat;
        private String author;
        private String cover;
        private String longIntro;
        private String lastChapter;
        private double retentionRatio;
        private int latelyFollower;
        private int wordCount;
        private String majorCate;
        private String minorCate;

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

        public String getCat() {
            return cat;
        }

        public void setCat(String cat) {
            this.cat = cat;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getLongIntro() {
            return longIntro;
        }

        public void setLongIntro(String longIntro) {
            this.longIntro = longIntro;
        }

        public String getLastChapter() {
            return lastChapter;
        }

        public void setLastChapter(String lastChapter) {
            this.lastChapter = lastChapter;
        }

        public double getRetentionRatio() {
            return retentionRatio;
        }

        public void setRetentionRatio(double retentionRatio) {
            this.retentionRatio = retentionRatio;
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
    }
}
