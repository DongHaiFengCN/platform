package com.yh.ydd.common.untils;

public class LoginResponseBody {

    public final static String SUCCESS ="ok";
    /**
     * status : ok
     * msg : 登录成功！
     * data : {"channelId":"7288c3ef","auth":{"expiresIn":1537177157715,"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaGFubmVsSWQiOiI3Mjg4YzNlZiIsInVzZXJuYW1lIjoiMTUwNzAyMDcxOTQiLCJpYXQiOjE1MzY1NzIzNTcsImV4cCI6MjE0MTM3MjM1N30.SCrP2JEUxTYKSEZgsUvRfbo0KDKzGaCxP2SGK73SxGw"}}
     */

    private String status;
    private String msg;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * channelId : 7288c3ef
         * auth : {"expiresIn":1537177157715,"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaGFubmVsSWQiOiI3Mjg4YzNlZiIsInVzZXJuYW1lIjoiMTUwNzAyMDcxOTQiLCJpYXQiOjE1MzY1NzIzNTcsImV4cCI6MjE0MTM3MjM1N30.SCrP2JEUxTYKSEZgsUvRfbo0KDKzGaCxP2SGK73SxGw"}
         */

        private String channelId;
        private AuthBean auth;

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public AuthBean getAuth() {
            return auth;
        }

        public void setAuth(AuthBean auth) {
            this.auth = auth;
        }

        public static class AuthBean {
            /**
             * expiresIn : 1537177157715
             * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaGFubmVsSWQiOiI3Mjg4YzNlZiIsInVzZXJuYW1lIjoiMTUwNzAyMDcxOTQiLCJpYXQiOjE1MzY1NzIzNTcsImV4cCI6MjE0MTM3MjM1N30.SCrP2JEUxTYKSEZgsUvRfbo0KDKzGaCxP2SGK73SxGw
             */

            private long expiresIn;
            private String token;

            public long getExpiresIn() {
                return expiresIn;
            }

            public void setExpiresIn(long expiresIn) {
                this.expiresIn = expiresIn;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }
        }
    }
}
