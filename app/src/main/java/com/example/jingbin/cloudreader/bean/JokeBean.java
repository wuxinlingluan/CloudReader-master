package com.example.jingbin.cloudreader.bean;

import java.util.List;

/**
 * Created by ${sheldon} on 2017/1/18.
 */
public class JokeBean {

    /**
     * error_code : 0
     * reason : Success
     * result : {"data":[{"content":"一个腿脚受伤的病人一瘸一拐走进病房，值班大夫拿起病历卡要填写病人基本情况，就问：\u201c贵姓？\u201d　　病人：姓魏！　　大夫：魏什么？（为什么？）　　病人：因为我爸爸姓魏，所以我姓魏。　　大夫：姓名？　　病人：魏伸莫！　　大夫：我问你姓名，你问我为什么？神经病！　　病人：我没有问你？我是叫魏\u2014\u2014伸\u2014\u2014莫。　　病人包扎好伤口，担心明天星期天没有人换药，就问大夫：明天有谁值班？　　大夫没好气地说：刘医生。　　病人很生气：什么态度？我问你谁值班？你说留医生，不留医生？难倒留一个护士？","hashId":"d7174a9ff083b55a6ce1053652f6f489","unixtime":1484707430,"updatetime":"2017-01-18 10:43:50"}]}
     */

    private int error_code;
    private String reason;
    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * content : 一个腿脚受伤的病人一瘸一拐走进病房，值班大夫拿起病历卡要填写病人基本情况，就问：“贵姓？”　　病人：姓魏！　　大夫：魏什么？（为什么？）　　病人：因为我爸爸姓魏，所以我姓魏。　　大夫：姓名？　　病人：魏伸莫！　　大夫：我问你姓名，你问我为什么？神经病！　　病人：我没有问你？我是叫魏——伸——莫。　　病人包扎好伤口，担心明天星期天没有人换药，就问大夫：明天有谁值班？　　大夫没好气地说：刘医生。　　病人很生气：什么态度？我问你谁值班？你说留医生，不留医生？难倒留一个护士？
             * hashId : d7174a9ff083b55a6ce1053652f6f489
             * unixtime : 1484707430
             * updatetime : 2017-01-18 10:43:50
             */

            private String content;
            private String hashId;
            private int unixtime;
            private String updatetime;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHashId() {
                return hashId;
            }

            public void setHashId(String hashId) {
                this.hashId = hashId;
            }

            public int getUnixtime() {
                return unixtime;
            }

            public void setUnixtime(int unixtime) {
                this.unixtime = unixtime;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }
        }
    }
}
