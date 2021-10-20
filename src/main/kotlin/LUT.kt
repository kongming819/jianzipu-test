const val PINYIN_CODE_MAX: Int = 128

const val HANZI_CODE_MIN: Int = 11904

/*
todo: implement pinyin input, more difficult than direct Chinese input because of conflicting pinyins
conflicting pinyin's:
    san - both 三， 散
    shi - 食， 十
    chuo - 绰，龊
    qi - 七，起
    ba - ?
    jiu - 九，就
    da - 大，打
    fu -
    duan
*/

val LHPinyinLUT = mapOf(
    "san" to "散",
    "da" to "大",
    "shi" to "食",
    "zhong" to "中",
    "ming" to "名",
    "gui" to "跪"
)

// 1 thumb -- 4 ring
// 0 no RH
val RHPinyinLUT = mapOf(
    "mo" to "抹",
    "tiao" to "挑",
    "gou" to "勾",
    "ti" to "剔",
    "da" to "打",
    "zhai" to "摘",
    "tuo" to "托",
    "pi" to "擘"
)

val unifyHanziMap = mapOf(
    "踢" to "剔", // ti
    "长" to "長", // chang
    "动" to "動", // dong
    "开" to "開", // kai
    "轮" to "輪", // lun
    "声" to "聲", // sheng
    "带" to "帶", // dai
    "弹" to "彈", // tan
    "来" to "來", // lai
    "园" to "園", // yuan
    "搯" to "掐", // tao
    "劈" to "擘", // pi
    "拨" to "撥", // bo
    "刺" to "剌", // la
    "历" to "歷", // li
    "歴" to "歷",
    "滚" to "滾", // gun
    "叠" to "疊", // die
    "鏁" to "鎖", // suo
    "锁" to "鎖",
    "鎻" to "鎖",
    "绰" to "綽", // chuo
    "缓" to "緩", // huan
    "连" to "連", // lian
    "进" to "進", // jin
    "双" to "雙", // shuang
    "龊" to "齪", // chuo
    "罨" to "掩", // yan
    "少" to "小", // xiao
    "对" to "對", // dui
    "换" to "換" // huan
)

val fuzzyJianzi = mapOf(
    "厂" to "歷",
    /*
    todo: implement a "fuzzy" feature for people who forget the name of a jianzi but remember what it looks like
"巛" to "鎖"
背	北
短	矢
拂	弗
齪	足
弹、彈	吅
拨、撥	癶
散	艹
食	亻人
名	夕
跪	⻊足 sorta
罨、𦌌	𠔿
间、間	日
许、許	午
外	卜
徽	山
就	尢、尤
指	旨
换、換	奐
引	弓
撞	立
逗	豆
猱	犭
注	氵
按	安
起	巳
抓	爪
搯	爫
对、對	业
带	巾
虚	虍
作	乍
退	艮
进、進	隹
小息	省
如	女
双、雙	双
缓、緩	爰
推出	拙
连、連 	車
硬	更
细	田
淌	尚
*/
)