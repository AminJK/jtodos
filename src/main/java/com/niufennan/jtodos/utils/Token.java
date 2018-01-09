package com.niufennan.jtodos.utils;

public class Token {
    private String signature;
    private long timestamp;
    private long id;//userId
    public Token(String signature, long timestamp,long id) {
        if (signature == null)
            throw new IllegalArgumentException("signature can not be null");
        this.timestamp = timestamp;
        this.signature = signature;
        this.id=id;
    }
    public long getId(){
        return id;
    }
    public String getSignature() {
        return signature;
    }
    public long getTimestamp() {
        return timestamp;
    }
    //  重写哈希code timestamp 不予考虑, 因为就算 timestamp 不同也认为是相同的 token.
    public int hashCode() {
        return signature.hashCode();
    }

    public boolean equals(Object object) {
        if (object instanceof Token)
            return ((Token) object).signature.equals(this.signature);
        return false;
    }
    //调试用
    @Override
    public String toString() {
        return "Token [signature=" + signature + ", timestamp=" + timestamp + "]";
    }
}
