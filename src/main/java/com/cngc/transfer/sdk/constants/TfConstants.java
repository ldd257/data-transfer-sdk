package com.cngc.transfer.sdk.constants;

public class TfConstants {

    public static final String SECRET_PRIVATE_PUBLIC_KEY = "SECRET_PRIVATE_PUBLIC_KEY";
    public static final String MY_PLATFORM_INFO = "MY_PLATFORM_INFO";
    public static final String ENCODING = "UTF-8";
    private TfConstants(){
        throw new IllegalStateException("TfConstants class");
    }
    public static class ProcessConstants{
        private ProcessConstants(){
            throw new IllegalStateException("ProcessConstants class");
        }
        /**
         * 接收模式
         */
        public static final String IS_BROADCAST = "isBroadcast";
        /**
         * 广播模式接收者
         */
        public static final String BROADCAST_RECEIVER = "broadcast_receiver";
        /**
         * 接收者
         */
        public static final String RECEIVERS = "receivers";
        /**
         * 流程id
         */
        public static final String PROCESS_CODE = "process_code";
               /**
         * 应用code
         */
        public static final String APPLICATION_CODE = "application_code";
        /**
         * 包裹code
         */
        public static final String PACKAGE_CODE = "packageCode";
        /**
         * 发送方code
         */
        public static final String PLATFORM_CODE = "platform_code";
        public static final String PACKAGE_NAME = "platform_name";
        public static final String IGNORE = "ignore";
        public static final String CONDITION = "condition";
        public static final String PRODUCT_ID = "product_id";

    }


    public static class Symbol {
        private Symbol(){
            throw new IllegalStateException("Symbol class");
        }
        public static final String SPOT = ".";
        public static final String COMMA = ",";
        public static final String SLASH = "/";
    }
}
