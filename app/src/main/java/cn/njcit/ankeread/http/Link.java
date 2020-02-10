package cn.njcit.ankeread.http;

/**
 * Create by ankele
 * <p>
 * 2020/1/26 - 19:45
 */
public class Link {

    /*
    搜索小说
    联想搜索：
    http://api.gdugm.cn//search/suggest?key=武神
    全部搜索：
    http://api.gdugm.cn//book/search?key=武神&start=0&limit=100
                要记住ID，下面获取小说信息和章节需要   start为起始位置 limit为获取个数


                获取小说信息
                http://api.gdugm.cn//book/info?bookId=539344b9bbdb8ce13bf66cd9
                小说封面在小说信息里面有


                获取章节内容
                http://api.gdugm.cn//toc/mix?bookId=539344b9bbdb8ce13bf66cd9


                获取每章内容
                http://chapter.xmxingheju.com/chapter/http%3a%2f%2fbook.kdqb.cc%2fgetBooks.aspx%3fmethod%3dcontent%26bookId%3d189923%26chapterFile%3dU_189923_201904010854348179_9386_1.txt
                标红为章节地址，必须要经过URL编码处理才能获取到内容

                大家都在搜
                http://api.gdugm.cn//node/info?nodeAlias=hot-word

                分类
                http://api.gdugm.cn//book/list?gender=male&type=1&major=玄幻&start=0&limit=50
                gender 性别:
                    男-male  女-female    出版-press
                type 类别：
                    1为热门 2为留存3为连载 4为完结
                major 主题:
                    男生：玄幻、奇幻、武侠、仙侠、都市、职场、历史、军事、游戏、竞技、科幻、灵异、同人、轻小说
                    女生：古代言情、现代言情、青春校园、耽美、玄幻奇幻、武侠仙侠、科幻、游戏竞技、悬疑灵异、同人、女尊、百合
                    出版：传记名著、出版小说、人文社科、生活时尚、经管理财、青春言情、外文原版、政治军事、成功励志、育儿健康
                start 起始位置
                limit 获取个数

                最热榜
                周榜    http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-hot-week
                月榜    http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-hot-month
                总榜    http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-hot-total


                热搜榜
                周榜    http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-hotsearch-week
                月榜    http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-hotsearch-month
                总榜    http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-hotsearch-total


                潜力榜
                周榜    http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-potenial-week
                月榜    http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-potenial-month
                总榜    http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-potenial-total


                留存榜
                周榜    http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-remain-week
                月榜    http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-remain-month
                总榜    http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-remain-total


                完结榜
                周榜    http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-finish-week
                月榜    http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-finish-month
                总榜    http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-finish-total
     */

    //总API
    public static String APIURL = "http://api.pingcc.cn/";
    //小说搜索
    public static String Search = "http://api.pingcc.cn/?xsname=";
    //热搜关键词
    public static String HotWord = "http://api.gdugm.cn//node/info?nodeAlias=hot-word";
    //详情
    public static String Info = "http://api.pingcc.cn/?xsurl1=";
    //内容
    public static String Context = "http://api.pingcc.cn/?xsurl2=";
    //轮播图
    public static String Banner = "http://json.apiopen.top/AnkeBanner";
    //更新轮播图
    public static String UpdateJson = "http://json.apiopen.top/updateJson";
    //图标
    public static String Icon = "http://json.apiopen.top/getAnkeicon";

    //男生最热周榜
    public static String MaleHotWeek = "http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-hot-week";
    //女生最热周榜
    public static String FeMaleHotWeek = "http://api.gdugm.cn//node/info?nodeAlias=rankinglist-female-hot-week";
    //男生最热月榜
    public static String MaleHotMonth = "http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-hot-month";
    //女生最热月榜
    public static String FeMaleHotMonth = "http://api.gdugm.cn//node/info?nodeAlias=rankinglist-female-hot-month";
    //男生最热总榜
    public static String MaleHotTotal = "http://api.gdugm.cn//node/info?nodeAlias=rankinglist-male-hot-total";
    //女生最热总榜
    public static String FeMaleHotTotal = "http://api.gdugm.cn//node/info?nodeAlias=rankinglist-female-hot-total";


    //分类下小说
    public static String SortList = "http://api.gdugm.cn/book/list";
}
