package com.thgykj.ruoter.admin.core.trigger;



/**
 * trigger type enum
 *
 * @author xuxueli 2018-09-16 04:56:41
 */
public enum TriggerTypeEnum {

    MANUAL("jobconf_trigger_type_manual"),
    CRON("jobconf_trigger_type_cron"),
    RETRY("jobconf_trigger_type_retry"),
    PARENT("jobconf_trigger_type_parent"),
    API("jobconf_trigger_type_api"),
    MISFIRE("jobconf_trigger_type_misfire");

    private TriggerTypeEnum(String title){
        this.title = title;
    }
    private String title;
    public String getTitle() {
        return title;
    }

}
