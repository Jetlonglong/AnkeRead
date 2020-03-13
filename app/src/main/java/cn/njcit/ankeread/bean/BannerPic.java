package cn.njcit.ankeread.bean;

import java.util.List;

/**
 * Create by ankele
 * <p>
 * 2020/1/27 - 18:15
 */
public class BannerPic {

    /**
     * code : 200
     * message : 成功!
     * result : {"list":[{"cover":"http://img.linshigou.com/1.png","id":1},{"cover":"http://img.linshigou.com/2.jpg","id":2},{"cover":"http://img.linshigou.com/3.jpg","id":3},{"cover":"http://img.linshigou.com/4.jpg","id":4}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * cover : http://img.linshigou.com/1.png
             * id : 1
             */

            private String cover;
            private int id;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
