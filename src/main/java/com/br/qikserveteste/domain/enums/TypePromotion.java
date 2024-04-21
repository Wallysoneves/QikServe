package com.br.qikserveteste.domain.enums;

public enum TypePromotion {

    FLAT_PERCENT("FLAT_PERCENT"),
    QTY_BASED_PRICE_OVERRIDE("QTY_BASED_PRICE_OVERRIDE"),
    BUY_X_GET_Y_FREE("BUY_X_GET_Y_FREE");

    private final String typePromotion;

    TypePromotion(String typePromotion) {
        this.typePromotion = typePromotion;
    }

    public String getTypePromotion() {
        return typePromotion;
    }
}
