package cn.njcit.ankeread.Bean;

import java.util.List;

/**
 * Create by ankele
 * <p>
 * 2020/1/30 - 13:34
 */
public class SortIcon {


    /**
     * code : 200
     * message : 成功!
     * result : {"man":[{"cover":"http://img.linshigou.com/icon/xh.png","title":"玄幻"},{"cover":"http://img.linshigou.com/icon/qh.png","title":"奇幻"},{"cover":"http://img.linshigou.com/icon/wx.png","title":"武侠"},{"cover":"http://img.linshigou.com/icon/xx.png","title":"仙侠"},{"cover":"http://img.linshigou.com/icon/ds.png","title":"都市"},{"cover":"http://img.linshigou.com/icon/zc.png","title":"职场"},{"cover":"http://img.linshigou.com/icon/ls.png","title":"历史"},{"cover":"http://img.linshigou.com/icon/js.png","title":"军事"},{"cover":"http://img.linshigou.com/icon/yx.png","title":"游戏"},{"cover":"http://img.linshigou.com/icon/jj.png","title":"竞技"},{"cover":"http://img.linshigou.com/icon/kh.png","title":"科幻"},{"cover":"http://img.linshigou.com/icon/ly.png","title":"灵异"},{"cover":"http://img.linshigou.com/icon/tr.png","title":"同人"},{"cover":"http://img.linshigou.com/icon/qxs.png","title":"轻小说"}],"publish":[{"cover":"http://img.linshigou.com/icon/xh.png","title":"传记名著"},{"cover":"http://img.linshigou.com/icon/qh.png","title":"出版小说"},{"cover":"http://img.linshigou.com/icon/wx.png","title":"人文社科"},{"cover":"http://img.linshigou.com/icon/xx.png","title":"生活时尚"},{"cover":"http://img.linshigou.com/icon/ds.png","title":"经管理财"},{"cover":"http://img.linshigou.com/icon/zc.png","title":"青春言情"},{"cover":"http://img.linshigou.com/icon/ls.png","title":"外文原版"},{"cover":"http://img.linshigou.com/icon/js.png","title":"政治军事"},{"cover":"http://img.linshigou.com/icon/yx.png","title":"成功励志"},{"cover":"http://img.linshigou.com/icon/jj.png","title":"育儿健康"}],"woman":[{"cover":"http://img.linshigou.com/icon/xh.png","title":"古代言情"},{"cover":"http://img.linshigou.com/icon/qh.png","title":"现代言情"},{"cover":"http://img.linshigou.com/icon/wx.png","title":"青春校园"},{"cover":"http://img.linshigou.com/icon/xx.png","title":"耽美"},{"cover":"http://img.linshigou.com/icon/ds.png","title":"玄幻奇幻"},{"cover":"http://img.linshigou.com/icon/zc.png","title":"武侠仙侠"},{"cover":"http://img.linshigou.com/icon/ls.png","title":"科幻"},{"cover":"http://img.linshigou.com/icon/js.png","title":"游戏竞技"},{"cover":"http://img.linshigou.com/icon/yx.png","title":"悬疑灵异"},{"cover":"http://img.linshigou.com/icon/jj.png","title":"同人"},{"cover":"http://img.linshigou.com/icon/kh.png","title":"女尊"},{"cover":"http://img.linshigou.com/icon/ly.png","title":"百合"}]}
     */

    private int code;
    private String message;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<ManBean> man;
        private List<PublishBean> publish;
        private List<WomanBean> woman;

        public List<ManBean> getMan() {
            return man;
        }

        public void setMan(List<ManBean> man) {
            this.man = man;
        }

        public List<PublishBean> getPublish() {
            return publish;
        }

        public void setPublish(List<PublishBean> publish) {
            this.publish = publish;
        }

        public List<WomanBean> getWoman() {
            return woman;
        }

        public void setWoman(List<WomanBean> woman) {
            this.woman = woman;
        }

        public static class ManBean {
            /**
             * cover : http://img.linshigou.com/icon/xh.png
             * title : 玄幻
             */

            private String cover;
            private String title;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class PublishBean {
            /**
             * cover : http://img.linshigou.com/icon/xh.png
             * title : 传记名著
             */

            private String cover;
            private String title;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class WomanBean {
            /**
             * cover : http://img.linshigou.com/icon/xh.png
             * title : 古代言情
             */

            private String cover;
            private String title;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
