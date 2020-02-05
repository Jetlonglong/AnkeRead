package cn.njcit.ankeread.Bean;

import java.util.List;

/**
 * Create by ankele
 * <p>
 * 2020/1/26 - 19:42
 */
public class HotWord {

    /**
     * node : {"title":"","alias":"","status":true}
     * books : [{"title":"仙王的日常生活"},{"title":"上门龙婿"},{"title":"庆余年"},{"title":"兽血沸腾"},{"title":"元尊"},{"title":"剑来"},{"title":"诡秘之主"},{"title":"逆天邪神"},{"title":"圣墟"},{"title":"系统"},{"title":"斗破苍穹"},{"title":"史上最强炼气期"},{"title":"我师兄实在太稳健了"},{"title":"伏天氏"},{"title":"龙王殿"},{"title":"修真聊天群"},{"title":"九星毒奶"},{"title":"唐家三少"},{"title":"遮天"},{"title":"全职法师"},{"title":"第一序列"},{"title":"仙武帝尊"},{"title":"完美世界"},{"title":"上门女婿"},{"title":"雪中悍刀行"},{"title":"锦衣之下"},{"title":"亏成首富从游戏开始"},{"title":"轮回乐园"},{"title":"万古神帝"},{"title":"护花高手在都市"}]
     */

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
         * title :
         * alias :
         * status : true
         */

        private String title;
        private String alias;
        private boolean status;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
    }

    public static class BooksBean {
        /**
         * title : 仙王的日常生活
         */

        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
