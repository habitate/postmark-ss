package io.habitate.libs.postmark.client.data.model.stats;

/**
 * Bounce stat object.
 */
public class BounceStat extends BaseStat {

    private Integer hardBounce;
    private Integer softBounce;
    private Integer smtpApiError;
    private Integer spamComplaint;
    private Integer ispBlock;
    private Integer Transient;

    private Integer DMARCPolicy;

    // SETTERS AND GETTERS

    public Integer getTransient() {
        return Transient;
    }

    public void setTransient(Integer aTransient) {
        Transient = aTransient;
    }

    public Integer getHardBounce() {
        return hardBounce;
    }

    public void setHardBounce(Integer hardBounce) {
        this.hardBounce = hardBounce;
    }

    public Integer getSoftBounce() {
        return softBounce;
    }

    public void setSoftBounce(Integer softBounce) {
        this.softBounce = softBounce;
    }

    public Integer getSmtpApiError() {
        return smtpApiError;
    }

    public void setSmtpApiError(Integer smtpApiError) {
        this.smtpApiError = smtpApiError;
    }

    public Integer getSpamComplaint() {
        return spamComplaint;
    }

    public void setSpamComplaint(Integer spamComplaint) {
        this.spamComplaint = spamComplaint;
    }

    public Integer getIspBlock() {
        return ispBlock;
    }

    public void setIspBlock(Integer ispBlock) {
        this.ispBlock = ispBlock;
    }

    public Integer getDMARCPolicy() {
        return DMARCPolicy;
    }

    public void setDMARCPolicy(Integer DMARCPolicy) {
        this.DMARCPolicy = DMARCPolicy;
    }
}
