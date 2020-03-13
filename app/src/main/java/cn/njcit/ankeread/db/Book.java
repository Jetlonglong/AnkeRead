package cn.njcit.ankeread.db;

import org.litepal.crud.LitePalSupport;

/**
 * Create by ankele
 * <p>
 * 2020/1/27 - 17:56
 */
public class Book extends LitePalSupport {

    /**
     * name : 斗罗大陆IV终极斗罗
     * url : qbxshttps://www.x23qb.com/book/18063/
     * cover : https://www.x23qb.com/files/article/image/18/18063/18063s.jpg
     * introduce : 一万年后，冰化了。斗罗联邦科考队在极北之地科考时发现了一个有着金银双色花纹的蛋，用仪器探察之后，发现里面居然有生命体征，赶忙将其带回研究所进行孵化。蛋孵化出来了，可孵出来…
     * time : 2020-01-27
     * num : 第八百二十九章 私生子？
     * tag : 玄幻奇幻
     * status : 连载中
     */

    private String name;
    private String url;
    private String cover;
    private String introduce;
    private String time;
    private String num;
    private String tag;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
