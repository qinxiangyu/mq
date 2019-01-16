
import ch.qos.logback.classic.encoder.PatternLayoutEncoder

import static ch.qos.logback.classic.Level.INFO

StringBuilder p = new StringBuilder();
//%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}
p.append("rabbit_mq");
p.append(">%d{yyyy-MM-dd/HH:mm:ss:SSS}");
p.append(" %-5p");
if(System.getProperty("PID")){
    p.append(" pid-" + System.getProperty("PID"))
} else {
    p.append(" pid-")
}
p.append("[%X]")
p.append("[%10.10t]")
p.append("%-40.40class{39}[%-5.5line]");
p.append("%m%n");
def USER_DIR = System.getProperty("user.dir");
println USER_DIR
def product = System.getProperty("product","false");
Boolean isLog = Boolean.parseBoolean(product);
appender("CONSOLE", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = p.toString();
    }
}

if(isLog) {
    appender("FILE", RollingFileAppender) {
        file = "${USER_DIR}/log/rabbit_mq.log"
        rollingPolicy(TimeBasedRollingPolicy){
            fileNamePattern = "${USER_DIR}/log/rabbit_mq_%d{yyyy_MM_dd_HH}.zip"
            maxHistory = 240
        }
        //append = true
        //prudent = true
        encoder(PatternLayoutEncoder) {
            pattern = p.toString();
        }
    }
}
if(isLog) {
    logger("com.vmovier.user.center.mapper", DEBUG, ["CONSOLE","FILE"])
    root(INFO, ["CONSOLE","FILE"])
} else {
    logger("com.vmovier.user.center.mapper", DEBUG, ["CONSOLE"])
    root(INFO, ["CONSOLE"])
}